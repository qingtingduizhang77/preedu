package com.tware.sysTeacherStaff.service;

import com.alibaba.fastjson.JSON;
import com.querydsl.core.QueryResults;
import com.tware.common.service.JDBCDaoImp;
import com.tware.common.utils.ExcelUtils;
import com.tware.common.utils.PasswordHelper;
import com.tware.common.utils.TypeNameUtil;
import com.tware.student.entity.SysStudent;
import com.tware.sysTeacherStaff.control.SysTeacherStaffControl;
import com.tware.sysTeacherStaff.entity.*;
import com.tware.sysTeacherStaff.repository.*;
import com.tware.user.entity.User;
import com.tware.user.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import swallow.framework.exception.SwallowException;
import swallow.framework.service.BaseService;
import swallow.framework.web.BasePageQueryBean;
import swallow.framework.web.PageListData;

import javax.transaction.Transactional;
import java.util.*;

/**
 * @author Guoyz
 * createTime   2020/9/7 16:11
 */
@Service
public class SysTeacherStaffService extends BaseService<SysTeacherStaffRepository, SysTeacherStaff> {

    @Autowired
    private SysTeacherAchievementRepository sysTeacherAchievementRepository;
    @Autowired
    private SysTeacherCertificationRegisterRepository sysTeacherCertificationRegisterRepository;
    @Autowired
    private SysTeacherCertificationRepository sysTeacherCertificationRepository;
    @Autowired
    private SysTeacherExamineRepository sysTeacherExamineRepository;
    @Autowired
    private SysTeacherFamilyTiesRepository sysTeacherFamilyTiesRepository;
    @Autowired
    private SysTeacherHeTongRepository sysTeacherHeTongRepository;
    @Autowired
    private SysTeacherJobRepository sysTeacherJobRepository;
    @Autowired
    private SysTeacherPastStudyRepository sysTeacherPastStudyRepository;
    @Autowired
    private SysTeacherStaffRepository sysTeacherStaffRepository;//这是主表，其他是副表
    @Autowired
    private SysTeacherStudioRepository sysTeacherStudioRepository;
    @Autowired
    private SysTeacherTitleRepository sysTeacherTitleRepository;
    @Autowired
    private SysTeacherTrainingInfoRepository sysTeacherTrainingInfoRepository;
    @Autowired
    private SysTeacherWorkExperienceRepository sysTeacherWorkExperienceRepository;
    @Autowired
    private UserService userService;//账号
    @Autowired
    private JDBCDaoImp jdbcDaoImp;

    //增
    @Transactional
    public Map<String, Object> insertItem(Map<String, Object> entity) throws SwallowException {
        SysTeacherStaff sysTeacherStaffNew = JSON.parseObject(JSON.toJSONString(entity.get("sysTeacherStaff")), SysTeacherStaff.class);
        if(sysTeacherStaffNew.getCertNum()==null || "".equals(sysTeacherStaffNew.getCertNum())){
            throw new SwallowException("身份证号码为空！");
        }
        String certNumNew = sysTeacherStaffNew.getCertNum();//输入的身份证号码

        //判断表里是否存在该教师
        SysTeacherStaff sysTeacherStaffOld = super.getItem(qu -> qu.where(QSysTeacherStaff.sysTeacherStaff.certNum.eq(certNumNew)));
        if(sysTeacherStaffOld!=null){//存在该教师
            String name = sysTeacherStaffOld.getName();
            String orgId = sysTeacherStaffOld.getOrgId();
            if(name.equals(sysTeacherStaffNew.getName()) && orgId.equals(sysTeacherStaffNew.getOrgId())){//如果姓名和幼儿园一样，更新当前教职工信息。
                sysTeacherStaffNew.setId(sysTeacherStaffOld.getId());//ID
                sysTeacherStaffNew.setVersion(sysTeacherStaffOld.getVersion());
                sysTeacherStaffNew.setStatus(sysTeacherStaffOld.getStatus());//教职工状态
                sysTeacherStaffNew.setUserId(sysTeacherStaffOld.getUserId());//教职工账号

                super.updateItem(sysTeacherStaffNew);//更新主表
                deletefubiao(sysTeacherStaffNew.getCertNum());//删副表
                insertfubiao(sysTeacherStaffNew.getCertNum(),entity);//添加副表
                return queryItemById(sysTeacherStaffNew.getId());
            }else {
                throw new SwallowException("教师已存在,不可重复新增！");
            }

        }else {//不存在该教师
            sysTeacherStaffNew = insertItemPro(sysTeacherStaffNew);
            //添加副表
            insertfubiao(sysTeacherStaffNew.getCertNum(), entity);
            return queryItemById(sysTeacherStaffNew.getId());
        }
    }

    //内部使用,新增教师用insertItem方法
    private SysTeacherStaff insertItemPro(SysTeacherStaff sysTeacherStaff) throws SwallowException {

        //添加账号
        User user = this.insertUser(sysTeacherStaff);
        //添加主表
        sysTeacherStaff.setUserId(user.getId());
        sysTeacherStaff.setStatus(0);
        sysTeacherStaff = super.insertItem(sysTeacherStaff);
        return sysTeacherStaff;
    }

    //创建账号
    private User insertUser(SysTeacherStaff sysTeacherStaff) {
        //添加账号
        User user = new User();
        String s = sysTeacherStaff.getCertNum();
        user.setUsername(s);
        user.setPassword(PasswordHelper.encryptPassword(s.substring(s.length()-6)));
        user.setOrgId(sysTeacherStaff.getOrgId());
        user.setName(sysTeacherStaff.getName());
        user.setType(3);//3是教职工
        return userService.insertItem(user);
    }
    //查询详细信息
    public Map<String, Object> queryItemById(long id) {
        SysTeacherStaff sysTeacherStaff = super.getItemById(id);
        if(sysTeacherStaff==null)return null;
        String certNum = sysTeacherStaff.getCertNum();
        int i = certNum.indexOf("#DEL#");//不存在传-1
        if(i!=-1){
            certNum = certNum.substring(0,certNum.indexOf("#"));//原始的证件号码
             }
        HashMap<String, Object> item = new HashMap<>();
        List<SysTeacherAchievement> sysTeacherAchievementList = sysTeacherAchievementRepository.findByCertNum(certNum);
        List<SysTeacherCertificationRegister> sysTeacherCertificationRegisterList = sysTeacherCertificationRegisterRepository.findByCertNum(certNum);
        List<SysTeacherCertification> sysTeacherCertificationList = sysTeacherCertificationRepository.findByCertNum(certNum);
        List<SysTeacherExamine> sysTeacherExamineList = sysTeacherExamineRepository.findByCertNum(certNum);
        List<SysTeacherFamilyTies> sysTeacherFamilyTiesList = sysTeacherFamilyTiesRepository.findByCertNum(certNum);
        List<SysTeacherHeTong> sysTeacherHeTongList = sysTeacherHeTongRepository.findByCertNum(certNum);
        List<SysTeacherJob> sysTeacherJobList = sysTeacherJobRepository.findByCertNum(certNum);
        List<SysTeacherPastStudy> sysTeacherPastStudyList = sysTeacherPastStudyRepository.findByCertNum(certNum);
        List<SysTeacherStudio> sysTeacherStudioList = sysTeacherStudioRepository.findByCertNum(certNum);
        List<SysTeacherTitle> sysTeacherTitleList = sysTeacherTitleRepository.findByCertNum(certNum);
        List<SysTeacherTrainingInfo> sysTeacherTrainingInfoList = sysTeacherTrainingInfoRepository.findByCertNum(certNum);
        List<SysTeacherWorkExperience> sysTeacherWorkExperienceList = sysTeacherWorkExperienceRepository.findByCertNum(certNum);

        item.put("sysTeacherAchievement",sysTeacherAchievementList.size()>0?sysTeacherAchievementList:"");
        item.put("sysTeacherCertificationRegister",sysTeacherCertificationRegisterList.size()>0?sysTeacherCertificationRegisterList:"");
        item.put("sysTeacherCertification",sysTeacherCertificationList.size()>0?sysTeacherCertificationList:"");
        item.put("sysTeacherExamine",sysTeacherExamineList.size()>0?sysTeacherExamineList:"");
        item.put("sysTeacherFamilyTies",sysTeacherFamilyTiesList.size()>0?sysTeacherFamilyTiesList:"");
        item.put("sysTeacherHeTong",sysTeacherHeTongList.size()>0?sysTeacherHeTongList:"");
        item.put("sysTeacherJob",sysTeacherJobList.size()>0?sysTeacherJobList:"");
        item.put("sysTeacherPastStudy",sysTeacherPastStudyList.size()>0?sysTeacherPastStudyList:"");
        item.put("sysTeacherStudio",sysTeacherStudioList.size()>0?sysTeacherStudioList:"");
        item.put("sysTeacherTitle",sysTeacherTitleList.size()>0?sysTeacherTitleList:"");
        item.put("sysTeacherTrainingInfo",sysTeacherTrainingInfoList.size()>0?sysTeacherTrainingInfoList:"");
        item.put("sysTeacherWorkExperience",sysTeacherWorkExperienceList.size()>0?sysTeacherWorkExperienceList:"");

        item.put("sysTeacherStaff",sysTeacherStaff);
        return item;
    }

    //删
    @Transactional
    public void deleteItemById01(long id) {
        SysTeacherStaff sysTeacherStaff = super.getItemById(id);
        Long userId = sysTeacherStaff.getUserId();
        User user = userService.getItemById(userId);
        if(user!=null){//软删账号
            String username = user.getUsername();
            user.setUsername(username+"#DEL#"+user.getId());
            user.setDisable(-1);
            userService.updateItem(user);
        }
        //软删教师表
        String certNum = sysTeacherStaff.getCertNum();
        sysTeacherStaff.setCertNum(certNum+"#DEL#"+sysTeacherStaff.getId());
        sysTeacherStaff.setStatus(-2);
        super.updateItem(sysTeacherStaff);

    }
    //删副表
    private void deletefubiao(String certNum){
        sysTeacherAchievementRepository.deleteAllByCertNum(certNum);
        sysTeacherCertificationRepository.deleteAllByCertNum(certNum);
        sysTeacherCertificationRegisterRepository.deleteAllByCertNum(certNum);
        sysTeacherExamineRepository.deleteAllByCertNum(certNum);
        sysTeacherFamilyTiesRepository.deleteAllByCertNum(certNum);
        sysTeacherHeTongRepository.deleteAllByCertNum(certNum);
        sysTeacherJobRepository.deleteAllByCertNum(certNum);
        sysTeacherPastStudyRepository.deleteAllByCertNum(certNum);
        sysTeacherStudioRepository.deleteAllByCertNum(certNum);
        sysTeacherTitleRepository.deleteAllByCertNum(certNum);
        sysTeacherTrainingInfoRepository.deleteAllByCertNum(certNum);
        sysTeacherWorkExperienceRepository.deleteAllByCertNum(certNum);
    }
    //增副表
    private void insertfubiao(String certNum, Map<String, Object> entity){
        List<SysTeacherAchievement> sysTeacherAchievementList = JSON.parseArray(JSON.toJSONString(entity.get("sysTeacherAchievement")), SysTeacherAchievement.class);
        List<SysTeacherCertification> sysTeacherCertificationList = JSON.parseArray(JSON.toJSONString(entity.get("sysTeacherCertification")), SysTeacherCertification.class);
        List<SysTeacherCertificationRegister> sysTeacherCertificationRegisterList = JSON.parseArray(JSON.toJSONString(entity.get("sysTeacherCertificationRegister")), SysTeacherCertificationRegister.class);
        List<SysTeacherExamine> sysTeacherExamineList = JSON.parseArray(JSON.toJSONString(entity.get("sysTeacherExamine")), SysTeacherExamine.class);
        List<SysTeacherFamilyTies> sysTeacherFamilyTiesList = JSON.parseArray(JSON.toJSONString(entity.get("sysTeacherFamilyTies")), SysTeacherFamilyTies.class);
        List<SysTeacherHeTong> sysTeacherHeTongList = JSON.parseArray(JSON.toJSONString(entity.get("sysTeacherHeTong")), SysTeacherHeTong.class);
        List<SysTeacherJob> sysTeacherJobList = JSON.parseArray(JSON.toJSONString(entity.get("sysTeacherJob")), SysTeacherJob.class);
        List<SysTeacherPastStudy> sysTeacherPastStudyList = JSON.parseArray(JSON.toJSONString(entity.get("sysTeacherPastStudy")), SysTeacherPastStudy.class);
        List<SysTeacherStudio> sysTeacherStudioList = JSON.parseArray(JSON.toJSONString(entity.get("sysTeacherStudio")), SysTeacherStudio.class);
        List<SysTeacherTitle> sysTeacherTitleList = JSON.parseArray(JSON.toJSONString(entity.get("sysTeacherTitle")), SysTeacherTitle.class);
        List<SysTeacherTrainingInfo> sysTeacherTrainingInfoList = JSON.parseArray(JSON.toJSONString(entity.get("sysTeacherTrainingInfo")), SysTeacherTrainingInfo.class);
        List<SysTeacherWorkExperience> sysTeacherWorkExperienceList = JSON.parseArray(JSON.toJSONString(entity.get("sysTeacherWorkExperience")), SysTeacherWorkExperience.class);

        if (sysTeacherAchievementList != null && sysTeacherAchievementList.size() > 0) {
            for (SysTeacherAchievement sysTeacherAchievement : sysTeacherAchievementList) {
                sysTeacherAchievement.setCertNum(certNum);
            }
            sysTeacherAchievementRepository.saveAll(sysTeacherAchievementList);
        }
        if (sysTeacherCertificationList != null && sysTeacherCertificationList.size() > 0) {
            for (SysTeacherCertification sysTeacherCertification : sysTeacherCertificationList) {
                sysTeacherCertification.setCertNum(certNum);
            }
            sysTeacherCertificationRepository.saveAll(sysTeacherCertificationList);
        }
        if (sysTeacherCertificationRegisterList != null && sysTeacherCertificationRegisterList.size() > 0) {
            for (SysTeacherCertificationRegister sysTeacherCertificationRegister : sysTeacherCertificationRegisterList) {
                sysTeacherCertificationRegister.setCertNum(certNum);
            }
            sysTeacherCertificationRegisterRepository.saveAll(sysTeacherCertificationRegisterList);
        }
        if (sysTeacherExamineList != null && sysTeacherExamineList.size() > 0) {
            for (SysTeacherExamine sysTeacherExamine : sysTeacherExamineList) {
                sysTeacherExamine.setCertNum(certNum);
            }
            sysTeacherExamineRepository.saveAll(sysTeacherExamineList);
        }
        if (sysTeacherFamilyTiesList != null && sysTeacherFamilyTiesList.size() > 0) {
            for (SysTeacherFamilyTies sysTeacherFamilyTies : sysTeacherFamilyTiesList) {
                sysTeacherFamilyTies.setCertNum(certNum);
            }
            sysTeacherFamilyTiesRepository.saveAll(sysTeacherFamilyTiesList);
        }
        if (sysTeacherHeTongList != null && sysTeacherHeTongList.size() > 0) {
            for (SysTeacherHeTong sysTeacherHeTong : sysTeacherHeTongList) {
                sysTeacherHeTong.setCertNum(certNum);
            }
            sysTeacherHeTongRepository.saveAll(sysTeacherHeTongList);
        }
        if (sysTeacherJobList != null && sysTeacherJobList.size() > 0) {
            for (SysTeacherJob sysTeacherJob : sysTeacherJobList) {
                sysTeacherJob.setCertNum(certNum);
            }
            sysTeacherJobRepository.saveAll(sysTeacherJobList);
        }
        if (sysTeacherPastStudyList != null && sysTeacherPastStudyList.size() > 0) {
            for (SysTeacherPastStudy sysTeacherPastStudy : sysTeacherPastStudyList) {
                sysTeacherPastStudy.setCertNum(certNum);
            }
            sysTeacherPastStudyRepository.saveAll(sysTeacherPastStudyList);
        }
        if (sysTeacherStudioList != null && sysTeacherStudioList.size() > 0) {
            for (SysTeacherStudio sysTeacherStudio : sysTeacherStudioList) {
                sysTeacherStudio.setCertNum(certNum);
            }
            sysTeacherStudioRepository.saveAll(sysTeacherStudioList);
        }
        if (sysTeacherTitleList != null && sysTeacherTitleList.size() > 0) {
            for (SysTeacherTitle sysTeacherTitle : sysTeacherTitleList) {
                sysTeacherTitle.setCertNum(certNum);
            }
            sysTeacherTitleRepository.saveAll(sysTeacherTitleList);
        }
        if (sysTeacherTrainingInfoList != null && sysTeacherTrainingInfoList.size() > 0) {
            for (SysTeacherTrainingInfo sysTeacherTrainingInfo : sysTeacherTrainingInfoList) {
                sysTeacherTrainingInfo.setCertNum(certNum);
            }
            sysTeacherTrainingInfoRepository.saveAll(sysTeacherTrainingInfoList);
        }
        if (sysTeacherWorkExperienceList != null && sysTeacherWorkExperienceList.size() > 0) {
            for (SysTeacherWorkExperience sysTeacherWorkExperience : sysTeacherWorkExperienceList) {
                sysTeacherWorkExperience.setCertNum(certNum);
            }
            sysTeacherWorkExperienceRepository.saveAll(sysTeacherWorkExperienceList);
        }

    }

    //改
    @Transactional
    public Map<String, Object> updateItem(Map<String, Object> entity) throws SwallowException {
        SysTeacherStaff sysTeacherStaff = JSON.parseObject(JSON.toJSONString(entity.get("sysTeacherStaff")), SysTeacherStaff.class);

        SysTeacherStaff sysTeacherStaffOld = super.getItemById(sysTeacherStaff.getId());

        if (!sysTeacherStaffOld.getCertNum().equals(sysTeacherStaff.getCertNum())){//修改了证件号,不建议修改证件号，这很麻烦
            User user = userService.getUserByUsername(sysTeacherStaff.getCertNum());
            if(user==null) {
                user = userService.getItemById(sysTeacherStaffOld.getUserId());
                String certNum = sysTeacherStaff.getCertNum();
                user.setUsername(certNum);
                user.setPassword(PasswordHelper.encryptPassword(certNum.substring(certNum.length()-6)));
                userService.updateItem(user);
            }else {
                throw new SwallowException("证件号码已存在，无法修改。");
            }
        }
        sysTeacherStaff = super.updateItem(sysTeacherStaff);

        deletefubiao(sysTeacherStaff.getCertNum());//删副表
        insertfubiao(sysTeacherStaff.getCertNum(),entity);//添加副表
        return queryItemById(sysTeacherStaff.getId());
    }



    //查
    public PageListData<SysTeacherStaff> getAllItemPageByQuerybean(BasePageQueryBean queryBean) {
        return super.getAllItemPageByQuerybean(queryBean);
    }

    //重置密码
    @Transactional
    public void resetPassword(long id) {
        User user = userService.getItemById(id);
        if(user!=null) {//取证件后六位
            user.setPassword(PasswordHelper.encryptPassword(user.getUsername().substring(user.getUsername().length()-6)));
            userService.updateItem(user);
        }
    }

    //导出
    public HSSFWorkbook export(List<SysTeacherStaff> itemList) throws Exception {
        List<Map<String, Object>> rowlist = new ArrayList<>();
        if (!CollectionUtils.isEmpty(itemList)) {
            for (SysTeacherStaff sysTeacherStaff : itemList) {
                sysTeacherStaff.setSexName(TypeNameUtil.getTypeName("xb", sysTeacherStaff.getSex()));
                sysTeacherStaff.setCertTypeName(TypeNameUtil.getTypeName("zjlx", sysTeacherStaff.getCertType()));
                sysTeacherStaff.setDepartIdName(TypeNameUtil.getTypeName("bm",sysTeacherStaff.getDepartId()));
                sysTeacherStaff.setJobsName(TypeNameUtil.getTypeName("zyzw",sysTeacherStaff.getJob()));
                rowlist.add(ExcelUtils.getObjectToMap(sysTeacherStaff));
            }
        }
        String[] titles = {"就职幼儿园", "姓名", "性别", "证件类型", "证件号码", "部门", "工作岗位"};//列名
        String[] columnKey = {"kindergarten", "name", "sexName", "certTypeName", "certNum", "departIdName", "jobsName"};//对应实体的字段
        HSSFWorkbook workbook = ExcelUtils.eqEcel(rowlist, titles, "教职工信息", columnKey);
        return workbook;
    }

    public SysTeacherStaff getTeacherInfo(String name, String certNo){
        QSysTeacherStaff qSysTeacherStaff = QSysTeacherStaff.sysTeacherStaff;
        return getRepsitory()
                .findOneEntityByColumns(query -> {
                    return query.where(qSysTeacherStaff.name.eq(name).and(qSysTeacherStaff.certNum.eq(certNo)));
                });
    }

    public List<SysTeacherHeTong> getHeTongList(String certNum){
        return sysTeacherHeTongRepository.findByCertNum(certNum);
    }

    //根据身份证号码查询
    public SysTeacherStaff getItemByCertNum(String certNum){
        return super.getItem(qu->qu.where(QSysTeacherStaff.sysTeacherStaff.certNum.eq(certNum)));
    }

    //导入方法
    @Transactional
    public void importExcel(SysTeacherStaff item) throws SwallowException{
        SysTeacherStaff oldItem = getItemByCertNum(item.getCertNum());
        if(oldItem==null){//教师不存在
            insertItemPro(item);//插入
        }else {//教师存在
            String name = oldItem.getName();
            String orgId = oldItem.getOrgId();
            if(name.equals(item.getName()) && orgId.equals(item.getOrgId())){//如果姓名和幼儿园一样，更新当前教职工信息。
                oldItem.setCertType(item.getCertType());//证件类型
                oldItem.setBirthay(item.getBirthay());//出生日期
                oldItem.setSex(item.getSex());//性别
                oldItem.setSheBaoNum(item.getSheBaoNum());//在深社保卡电脑号
                oldItem.setContactWay(item.getContactWay());//手机号码
                oldItem.setPlaceInfo(item.getPlaceInfo());//户籍信息
                oldItem.setJuZhuZheng(item.getJuZhuZheng());//是否办理居住证
                oldItem.setTotalWorkTime(item.getTotalWorkTime());//参加工作时间
                oldItem.setNowWorkTime(item.getNowWorkTime());//进入本园时间
                oldItem.setJob(item.getJob());//主要职务
                oldItem.setNianji(item.getNianji());//年级
                oldItem.setBanHao(item.getBanHao());//班号
                oldItem.setTeacherCertName(item.getTeacherCertName());//资格证名称
                oldItem.setTeacherCertNum(item.getTeacherCertNum());//资格证编号
                oldItem.setJinTieYear(item.getJinTieYear());//已领取保教人员从教津贴年限（年）
                oldItem.setSfTuiXiu(item.getSfTuiXiu());//是否达到退休年龄
                super.updateItem(oldItem);
            }else {
                throw new SwallowException("教师已存在,不可重复新增！");
            }
        }
    }


    //恢复已删除的教职工
    @Transactional
    public void recoverItem(long id) {
        SysTeacherStaff item = super.getItemById(id);
        if(item.getStatus()!=-2){
            throw new SwallowException(item.getCertNum().substring(0,item.getCertNum().indexOf("#")));
        }
        String certNum = item.getCertNum();
        certNum = certNum.substring(0,certNum.indexOf("#"));//原始的证件号码
        SysTeacherStaff itemByCertNum = getItemByCertNum(certNum);
        if(itemByCertNum!=null){
            throw new SwallowException(certNum);
        }
        //恢复教职工列表
        item.setCertNum(certNum);
        item.setStatus(0);
        super.updateItem(item);

        //恢复用户账号
        User user = userService.getItemById(item.getUserId());
        String username = user.getUsername();
        username = username.substring(0,username.indexOf("#"));//原始的账号
        user.setUsername(username);
        user.setDisable(0);
        userService.updateItem(user);

    }

    //回收站列表
    public PageListData<SysTeacherStaff> huishouList(SysTeacherStaffControl.QueryBean queryBean){
        QSysTeacherStaff sysTeacherStaff = QSysTeacherStaff.sysTeacherStaff;
        String delCertNum = null;
        if(queryBean.getCertNum()!=null && queryBean.getCertNum().length()>=6){
            delCertNum = queryBean.getCertNum();//有待修改 #DEL#%
            queryBean.setCertNum(null);
        }
        final String finalDelCertNum = delCertNum;
        PageListData<SysTeacherStaff> pageListData = super.getAllItemPageByQuerybean(
                queryBean, qu-> {
                    qu.where(sysTeacherStaff.status.eq(-2));
                    if(finalDelCertNum !=null){
                        qu.where(sysTeacherStaff.certNum.like("%"+finalDelCertNum+"%"));
                    }
                    return qu;
                });

        List<SysTeacherStaff> itemList = pageListData.getItems();
        for (SysTeacherStaff item : itemList) {
            String oldNum = item.getCertNum();
            String newNum = oldNum.substring(0,oldNum.indexOf("#"));//从0索引开始截取到第一个“#”（不包括）
            item.setCertNum(newNum);
        }
        return pageListData;

    }

    public QueryResults<SysTeacherStaff> getApplyResults(String orgId, String name, String certNo, String year,
                                                    int page, int pageSize){
        return getRepsitory().getApplyResults(orgId, name, certNo, year, page, pageSize);
    }

}
