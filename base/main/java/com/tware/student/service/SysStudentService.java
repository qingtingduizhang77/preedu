package com.tware.student.service;

import com.querydsl.core.QueryResults;
import com.tware.common.service.JDBCDaoImp;
import com.tware.common.utils.TypeNameUtil;
import com.tware.config.service.SpResultsService;
import com.tware.kindergarten.entity.QSysKindergarten;
import com.tware.kindergarten.entity.SysKindergarten;
import com.tware.kindergarten.service.SysKindergartenService;
import com.tware.student.entity.QSysStudent;
import com.tware.student.entity.SysStudent;
import com.tware.student.entity.SysStudentAdjustRecord;
import com.tware.student.entity.vo.SysStudentVo;
import com.tware.student.repository.SysStudentRepository;
import com.tware.user.entity.User;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swallow.framework.exception.SwallowException;
import swallow.framework.service.BaseService;

import java.util.*;

@Service
public class SysStudentService extends BaseService<SysStudentRepository, SysStudent> {

    @Autowired
    private JDBCDaoImp jdbcDaoImp;

    @Autowired
    private SysStudentAdjustRecordService studentAdjustRecordService;

    @Autowired
    private SysKindergartenService kindergartenService;

    @Autowired
    private SpResultsService spResultsService;

    /**
     * 更改儿童信息状态
     * @param ids
     * @param status
     * @return
     */
    public boolean updateStatus(Long[] ids, int status){
        Map<String, Object> map = new HashMap<>();
        if (ids.length <= 0) {
            return true;
        }
        String sql = "update sys_student set status = :status where id in (:ids)";
        map.put("status", status);
        map.put("ids", Arrays.asList(ids));
        return jdbcDaoImp.executeSql(sql, map);
    }

    public SysStudent getStudentInfo(String name, String certNo){// 系统外部调用接口，是否是系统内部学生
        QSysStudent qSysStudent = QSysStudent.sysStudent;
        return getRepsitory()
                .findOneEntityByColumns(query -> {
                    return query.where(qSysStudent.name.eq(name).and(qSysStudent.certNo.eq(certNo)
                            .and(qSysStudent.status.ne(-1))));
                });
    }

    public SysStudent getItemByCertNo(Long id, String certNo){
        QSysStudent qSysStudent = QSysStudent.sysStudent;
        return getRepsitory()
                .findOneEntityByColumns(query -> {
                    if (id != null) {
                        query.where(qSysStudent.id.ne(id));
                    }
                    query.where(qSysStudent.status.ne(-1));
                    return query.where(qSysStudent.certNo.eq(certNo));
                });
    }

    public List<SysStudent> getItems(Long id, String certNo, String orgId, Integer status){
        QSysStudent qSysStudent = QSysStudent.sysStudent;
        return  super.getAllItems(query -> {
                    if (id != null) {
                        query.where(qSysStudent.id.eq(id));
                    }
                    if (StringUtils.isNotEmpty(orgId)) {
                        query.where(qSysStudent.orgId.eq(orgId));
                    }
                    if (status != null) {
                        query.where(qSysStudent.status.eq(status));
                    }else{
                        query.where(qSysStudent.status.ne(-1));
                    }
                    return query.where(qSysStudent.certNo.eq(certNo));
                });
    }


    public SysStudent getStudentById(long id){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        QSysStudent qSysStudent = QSysStudent.sysStudent;
        return getRepsitory()
                .findOneEntityByColumns(query -> {
                    query.where(qSysStudent.id.eq(id).and(qSysStudent.status.in(0)));
                    if (user.getType() != 1) {
                        query.where(qSysStudent.orgId.eq(user.getOrgId()));
                    }
                    return query;
                });
    }

    public SysStudent getStudentById(long id, Long[] status){
        QSysStudent qSysStudent = QSysStudent.sysStudent;
        return getRepsitory()
                .findOneEntityByColumns(query -> {
                    query.where(qSysStudent.id.eq(id).and(qSysStudent.status.in(status)));
                    return query;
                });
    }

    /**
     * 儿童（学生）1：转入；2：转出；3：调班；4：升班；5：毕业
     * @param item
     * @param type
     * @return
     */
    public boolean adjustStudentInfo(SysStudentAdjustRecord item, int type){
        if (type == 1) {// 转入
            return transferInStudent(item);
        }
        SysStudent student = this.getStudentById(item.getStudentId());
        if (student == null)
            throw new SwallowException("此学生不存在！");
        BeanUtils.copyProperties(student, item, "id","version","created","creator","lastmodi","modifier");
        if (type == 2) {// 转出
            student.setStatus(1);// 1：转出；2：毕业
        }else if(type == 3){// 调班
            if (StringUtils.isEmpty(item.getNewGrade()) || StringUtils.isEmpty(item.getNewClazz())) {
                throw new SwallowException("调至年级班级不能为空！");
            }
            item.setGrade(student.getGrade());
            item.setClazz(student.getClazz());
            student.setGrade(item.getNewGrade());
            student.setClazz(item.getNewClazz());
        }else if (type == 4){ // 升班
            // 若是毕业班就令儿童（学生）毕业
            if (student.getGrade().equals(TypeNameUtil.getTypeKeyByValue("nj","大大班级")) ||
                    student.getGrade().equals(TypeNameUtil.getTypeKeyByValue("nj","大班级"))) {
                student.setStatus(2);
                item.setAdjustCause("99");// 升班导致毕业
                type = 5;
            }else {
                student.setGrade((Integer.parseInt(student.getGrade())+1)+"");
                item.setNewGrade(student.getGrade());
                item.setNewClazz(student.getClazz());
            }
        }else if (type == 5) { // 毕业
            if (!student.getGrade().equals(TypeNameUtil.getTypeKeyByValue("nj","大大班级")) &&
                    !student.getGrade().equals(TypeNameUtil.getTypeKeyByValue("nj","大班级"))) {
                throw new SwallowException(student.getName()+ "学生不符合毕业！");
            }
            student.setStatus(2);
        }
        SysKindergarten kindergarten = kindergartenService.getSysKindergartenByOrgId(student.getOrgId());
        if (type == 2 || type == 5) {// 转出 / 毕业
            student.setOldOrgId(student.getOrgId());
            student.setOrgId(null);
            item.setOldOrgId(kindergarten.getOrgId());
            item.setOldKindergartenName(kindergarten.getName());
            item.setOrgId(null);
        }else if (type == 3 || type == 4){ //调班 / 升班
            item.setAdjustTime(null);
            item.setOrgId(kindergarten.getOrgId());
            item.setKindergartenName(kindergarten.getName());
        }
        item.setAdjustType(type + "");//type 1：转入；2：转出；3：调班；4：升班；5：毕业
        super.updateItem(student);
        studentAdjustRecordService.insertItem(item);
        return true;
    }

    public boolean transferInStudent(SysStudentAdjustRecord item){
        SysStudent student = this.getStudentById(item.getStudentId(),new Long[]{1L,2L});
        if (student == null)
            throw new SwallowException("此学生不可进行转入操作！");
        BeanUtils.copyProperties(student, item, "id","version","created","creator","lastmodi","modifier","orgId");
        student.setStatus(0);
        student.setOrgId(item.getOrgId());
        student.setGrade(item.getNewGrade());
        student.setClazz(item.getNewClazz());
        SysKindergarten kindergarten = kindergartenService.getSysKindergartenByOrgId(item.getOrgId());
        item.setOldOrgId(item.getOldOrgId());
        item.setKindergartenName(kindergarten.getName());
        SysKindergarten oldKindergarten = kindergartenService.getSysKindergartenByOrgId(student.getOldOrgId());
        item.setOldKindergartenName(oldKindergarten.getName());
        item.setAdjustType(1 + "");//type 1：转入；2：转出；3：调班；4：升班；5：毕业
        super.updateItem(student);
        studentAdjustRecordService.insertItem(item);
        return true;
    }

    public QueryResults<SysStudentVo> getTransferList(int userType, String orgId,String oldOrgId, String name, String certType, String certNo,
                                                      String grade,String clazz, String adjustCause, Date adjustTime,int page, int pageSize){
        return getRepsitory().getTransferList(userType, orgId,oldOrgId, name, certType, certNo,
                grade, clazz, adjustCause, adjustTime, page, pageSize);
    }

    public QueryResults<SysStudent> getApplyResults(int userType,String orgId, String name, String certNo,String year,
                                                    int page, int pageSize){
        if (StringUtils.isEmpty(year))
            year =  spResultsService.getApplyYear();
        return getRepsitory().getApplyResults(userType, orgId, name, certNo, year, page, pageSize);
    }
}
