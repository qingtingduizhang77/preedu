package com.tware.student.control;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.tware.common.utils.DateUtil;
import com.tware.common.utils.ExcelUtils;
import com.tware.common.utils.ImportExcelUtil;
import com.tware.common.utils.TypeNameUtil;
import com.tware.config.entity.QSpResults;
import com.tware.config.entity.SpResults;
import com.tware.config.service.SpResultsService;
import com.tware.kindergarten.entity.QSysKindergarten;
import com.tware.kindergarten.entity.SysKindergarten;
import com.tware.kindergarten.service.SysKindergartenService;
import com.tware.log.annotation.ViLog;
import com.tware.student.entity.QSysStudent;
import com.tware.student.entity.SysStudent;
import com.tware.student.entity.SysStudentAdjustRecord;
import com.tware.student.entity.vo.SysStudentVo;
import com.tware.student.service.SysStudentService;
import com.tware.user.entity.User;
import io.swagger.annotations.*;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.OLE2NotOfficeXmlFileException;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import swallow.framework.jpaquery.repository.annotations.Like;
import swallow.framework.web.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 儿童（学生）
 * @author zhengjc
 *
 */
@RestController
@Api(tags = "儿童（学生）")
@RequestMapping("/api/student")
public class SysStudentControl {

    private static final Logger log = LoggerFactory.getLogger(SysStudentControl.class);

    @Autowired
    private SysStudentService service;

    @Autowired
    private SysKindergartenService sysKindergartenService;

    @Autowired
    private SpResultsService spResultsService;

    /**
     * 查询对象
     *
     */
    @ApiModel(value="儿童（学生）查询对象")
    static class QueryBean extends BasePageQueryBean {

        @ApiModelProperty(value="所属幼儿园",name="orgId",example="")
        private String orgId;

        @ApiModelProperty(value="原就读幼儿园",name="oldOrgId",example="")
        private String oldOrgId;

        @ApiModelProperty(value="儿童姓名",name="name",example="")
        @Like
        private String name;

        @ApiModelProperty(value="证件类型",name="certType",example="")
        private String certType;

        @ApiModelProperty(value="证件号码",name="certNo",example="")
        @Like
        private String certNo;

        @ApiModelProperty(value="所在年级",name="grade",example="")
        private String grade;

        @ApiModelProperty(value="所在班级",name="clazz",example="")
        private String clazz;

        private Date lastmodi;

        @ApiModelProperty(value="转出原因",name="adjustCause",example="")
        private String adjustCause;

        @ApiModelProperty(value="转出日期",name="adjustTime",example="")
        private Date adjustTime;

        @ApiModelProperty(value="申请年度",name="year",example="")
        private String year;

        public String getOrgId() {
            return orgId;
        }

        public void setOrgId(String orgId) {
            this.orgId = orgId;
        }

        public String getOldOrgId() {
            return oldOrgId;
        }

        public void setOldOrgId(String oldOrgId) {
            this.oldOrgId = oldOrgId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCertType() {
            return certType;
        }

        public void setCertType(String certType) {
            this.certType = certType;
        }

        public String getCertNo() {
            return certNo;
        }

        public void setCertNo(String certNo) {
            this.certNo = certNo;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public String getClazz() {
            return clazz;
        }

        public void setClazz(String clazz) {
            this.clazz = clazz;
        }

        public Date getLastmodi() {
            return lastmodi;
        }

        public void setLastmodi(Date lastmodi) {
            this.lastmodi = lastmodi;
        }

        public String getAdjustCause() {
            return adjustCause;
        }

        public void setAdjustCause(String adjustCause) {
            this.adjustCause = adjustCause;
        }

        public Date getAdjustTime() {
            return adjustTime;
        }

        public void setAdjustTime(Date adjustTime) {
            this.adjustTime = adjustTime;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }
    }


    /**
     * 查询对象
     *
     */
    @ApiModel(value="儿童（学生）查询对象")
    static class QueryBeanNoPage extends BaseQueryBean {

        @ApiModelProperty(value="所属幼儿园",name="orgId",example="")
        private String orgId;

        @ApiModelProperty(value="原就读幼儿园",name="oldOrgId",example="")
        private String oldOrgId;

        @ApiModelProperty(value="儿童姓名",name="name",example="")
        @Like
        private String name;

        @ApiModelProperty(value="证件类型",name="certType",example="")
        private String certType;

        @ApiModelProperty(value="证件号码",name="certNo",example="")
        @Like
        private String certNo;

        @ApiModelProperty(value="所在年级",name="grade",example="")
        private String grade;

        @ApiModelProperty(value="所在班级",name="clazz",example="")
        private String clazz;

        private Date lastmodi;

        @ApiModelProperty(value="转出原因",name="adjustCause",example="")
        private String adjustCause;

        @ApiModelProperty(value="转出日期",name="adjustTime",example="")
        private Date adjustTime;

        @ApiModelProperty(value="申请年度",name="year",example="")
        private String year;

        public String getOrgId() {
            return orgId;
        }

        public void setOrgId(String orgId) {
            this.orgId = orgId;
        }

        public String getOldOrgId() {
            return oldOrgId;
        }

        public void setOldOrgId(String oldOrgId) {
            this.oldOrgId = oldOrgId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCertType() {
            return certType;
        }

        public void setCertType(String certType) {
            this.certType = certType;
        }

        public String getCertNo() {
            return certNo;
        }

        public void setCertNo(String certNo) {
            this.certNo = certNo;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public String getClazz() {
            return clazz;
        }

        public void setClazz(String clazz) {
            this.clazz = clazz;
        }

        public Date getLastmodi() {
            return lastmodi;
        }

        public void setLastmodi(Date lastmodi) {
            this.lastmodi = lastmodi;
        }

        public String getAdjustCause() {
            return adjustCause;
        }

        public void setAdjustCause(String adjustCause) {
            this.adjustCause = adjustCause;
        }

        public Date getAdjustTime() {
            return adjustTime;
        }

        public void setAdjustTime(Date adjustTime) {
            this.adjustTime = adjustTime;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }
    }

    /**
     * 通过查询bean进行分页查询数据
     * @param queryBean
     * @return
     */
    @PostMapping("listpage")
    @SuppressWarnings("unchecked")
    public ApiResult<PageListData<SysStudent>> query(@RequestBody QueryBean queryBean){
        try {
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            QSysStudent qSysStudent = QSysStudent.sysStudent;
            queryBean.setYear(null);
            return ApiResult.success(service.getAllItemPageByQuerybean(queryBean,
                    query-> {
                        query.where(qSysStudent.status.eq(0));
                        if (user.getType() != 1) {
                            query.where(qSysStudent.orgId.eq(user.getOrgId()));
                        }
                        return query;
                    }));
        } catch (Exception e) {
            log.error("查询儿童对象出错:"+e.getMessage(),e);
            return ApiResult.fail("查询儿童对象出错:"+e.getMessage());
        }
    }

    @ApiOperation(value = "根据id取儿童信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
            @ApiImplicitParam(name = "id", value = "儿童id", required = true, dataType = "long")
    })
    @RequestMapping("{id}")
    @SuppressWarnings("unchecked")
    public ApiResult<SysStudent> getItemById(@PathVariable long id){
        try {
            return ApiResult.success(service.getItemById(id));
        } catch (Exception e) {
            log.error("查询儿童信息出错:"+e.getMessage(),e);
            return ApiResult.fail("查询儿童信息出错:"+e.getMessage());
        }
    }

    /**
     * 删除儿童
     * @param ids
     * @return
     */
    @ViLog(operEvent = "删除儿童（学生）对象", operType =3)//日志记录
    @ApiOperation(value = "删除儿童")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
            @ApiImplicitParam(name = "ids", value = "儿童IDs数组", required = true,allowMultiple=true, dataType = "Long")
    })
    @DeleteMapping
    public BaseApiResult delete(@RequestBody Long []ids) {
        try {
                service.updateStatus(ids,-1);// 软删除
            return BaseApiResult.successResult();
        } catch (Exception e) {
            log.error("删除儿童出错:"+e.getMessage(),e);
            return BaseApiResult.failResult(500,"删除儿童出错:"+e.getMessage());
        }
    }

    /**
     * 新增儿童（学生）对象
     * @param item
     * @return
     */
    @ViLog(operEvent = "新增儿童（学生）对象", operType =1)//日志记录
    @SuppressWarnings("unchecked")
    @PutMapping()
    public ApiResult<SysStudent> saveStudent(@RequestBody SysStudent item) {
        try {
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            if (service.getItemByCertNo(null, item.getCertNo()) != null) {
                return ApiResult.fail("系统已存在此学生！");
            }
            if (item.getFlag() == 0) {
                String orgId = "";
                if (user.getType()==2){
                    orgId = user.getOrgId();
                }
                List<SysStudent> list = service.getItems(null, item.getCertNo(), orgId, -1);
                if (list != null && list.size() > 0) {
                    return ApiResult.fail(1001,"回收站已存在此学生！确定保存？");
                }
            }
            return ApiResult.success(service.insertItem(item));
        } catch (Exception e) {
            log.error("新增儿童对象出错:"+e.getMessage(),e);
            return ApiResult.fail("新增儿童出错:"+e.getMessage());
        }
    }

    /**
     * 修改儿童（学生）对象
     * @param item
     * @return
     */
    @ViLog(operEvent = "修改儿童（学生）对象", operType =2)//日志记录
    @PostMapping
    @SuppressWarnings("unchecked")
    public ApiResult<SysStudent> saveSysStudent(@RequestBody SysStudent item){
        try {
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            SysStudent sysStudent = service.getItemByCertNo(null, item.getCertNo());
            if (sysStudent != null && !item.getId().equals(sysStudent.getId())) {
                return ApiResult.fail("系统已存在此学生！");
            }
            if (item.getFlag() == 0 && sysStudent == null) {
                String orgId = "";
                if (user.getType()==2){
                    orgId = user.getOrgId();
                }
                List<SysStudent> list = service.getItems(null, item.getCertNo(), orgId, -1);
                if (list != null && list.size() > 0) {
                    return ApiResult.fail(1001,"回收站已存在此学生！确定保存？");
                }
            }
            return ApiResult.success(service.updateItem(item));
        } catch (Exception e) {
            log.error("修改儿童对象出错:"+e.getMessage(),e);
            return ApiResult.fail("修改儿童对象出错:"+e.getMessage());
        }
    }


    /**
     * 批量导入儿童（学生）信息
     * @param file
     * @return
     */
    @ViLog(operEvent = "批量导入儿童（学生）信息", operType =1)//日志记录
    @RequestMapping(value = "/import")
    @Transactional
    public BaseApiResult importExcel(@RequestParam(value = "file") MultipartFile file) {
        log.info("----------start----------importExcel---student");
        if (file == null) {
            return BaseApiResult.failResult(500, "请重新上传文件！");
        }
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        boolean flag = true;
        String msg = "";
        try {
            // 读取内容
            InputStream in = file.getInputStream();
            Map<String, Object> objMap = new ImportExcelUtil().getMapByExcel(in, file.getOriginalFilename(), false);

            @SuppressWarnings("unchecked")
            List<ArrayList<Object>> objList = (List<ArrayList<Object>>) objMap.get("list");
            if (objList != null && objList.size() > 2) {
                for (int i = 2; i < objList.size(); i++) {
                    int length = i - 1;
                    List<Object> list = objList.get(i);
                    boolean ff = false;// 判断整行全部为空，是则不计
                    for(Object obj :list){
                        if (StringUtils.isNotEmpty(obj.toString())) {
                            ff = true;
                            break;
                        }
                    }
                    if (!ff) {
                        continue;
                    }
                    if (list.size() == 0 ||
                            StringUtils.isEmpty(list.get(0).toString()) ||StringUtils.isEmpty(list.get(1).toString()) ||
                            StringUtils.isEmpty(list.get(2).toString()) ||StringUtils.isEmpty(list.get(3).toString()) ||
                            StringUtils.isEmpty(list.get(4).toString()) ||StringUtils.isEmpty(list.get(5).toString()) ||
                            StringUtils.isEmpty(list.get(6).toString()) ||StringUtils.isEmpty(list.get(7).toString()) ||
                            StringUtils.isEmpty(list.get(8).toString()) ||StringUtils.isEmpty(list.get(9).toString()) ||
                            StringUtils.isEmpty(list.get(10).toString()) ||StringUtils.isEmpty(list.get(11).toString()) ||
                            StringUtils.isEmpty(list.get(12).toString()) ||StringUtils.isEmpty(list.get(13).toString()) ||
                            StringUtils.isEmpty(list.get(14).toString()) ) {
                        msg += "第" + length + "条有空值！\n";
                        continue;
                    }
                    SysStudent item = new SysStudent();
                    if (user.getType() == 1) {
                        try {
                            SysKindergarten sysKindergarten = sysKindergartenService.getSysKindergartenByName(list.get(0).toString());
                            if (sysKindergarten == null) {
                                msg += "第" + length + "条幼儿园不存在！\n" ;
                                continue;
                            }
                            item.setOrgId(sysKindergarten.getOrgId());//机构ID
                        }catch (Exception e){
                            log.error(e.getMessage());
                            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                            return BaseApiResult.failResult(500, "系统出现重复的幼儿园名称！");
                        }
                    }else {
                        item.setOrgId(user.getOrgId());
                    }
                    item.setName(list.get(1).toString());//儿童姓名
                    item.setCertType(TypeNameUtil.getTypeKeyByValue("zjlx", list.get(2).toString()));//证件类型
                    item.setCertNo(list.get(3).toString());//证件号码
                    item.setBirthdayTime(DateUtil.parse("yyyy-MM-dd",list.get(4).toString()));//生日
                    item.setSex(TypeNameUtil.getTypeKeyByValue("xb", list.get(5).toString()));//性别
                    item.setEnrollmentTime(DateUtil.parse("yyyy-MM-dd",list.get(6).toString()));//入园时间
                    item.setCensus(TypeNameUtil.getTypeKeyByValue("ethj", list.get(7).toString()));//儿童户籍
                    item.setGrade(TypeNameUtil.getTypeKeyByValue("nj", list.get(8).toString()));//所在年级
                    item.setClazz(TypeNameUtil.getTypeKeyByValue("bh", list.get(9).toString()));//所在班级
                    item.setGuardian(TypeNameUtil.getTypeKeyByValue("yetgx", list.get(10).toString()));//监护人关系
                    item.setGuardianName(list.get(11).toString());//监护人姓名
                    item.setGuardianCertType(TypeNameUtil.getTypeKeyByValue("zjlx", list.get(12).toString()));//监护人证件类型
                    item.setGuardianCertNo(list.get(13).toString());//监护人证件号码
                    item.setGuardianPhone(list.get(14).toString());//监护人联系电话
                    SysStudent sysStudent = service.getItemByCertNo(null,item.getCertNo());
                    if (sysStudent != null) {
                        if (!item.getOrgId().equals(sysStudent.getOrgId())) {
                            msg += "第" + length + "条此证件号码学生已存在系统！\n";
                            continue;
                        }
                        if (sysStudent.getName().equals(item.getName())
                                && sysStudent.getCertNo().equals(item.getCertNo())) {// 更新儿童信息
                            BeanUtils.copyProperties(item, sysStudent,"id","version","created","creator","lastmodi","modifier","status",
                                    "residents","nation","hometown","telephone","birthplace","isMartyrsChildren","isOrphan","isDisability","isOneParentFamily",
                                    "currentAddress","guardianCensus","guardianWorkUnit","guardian2","guardianName2","guardianCertType2","guardianCertNo2","guardianCensus2",
                                    "guardianPhone2","guardianWorkUnit2","bankName","accountHolder","cardNo","subBranch");
                            service.updateItem(sysStudent);
                        }else{
                            msg += "第" + length + "条证件号码及姓名不一致！\n";
                            continue;
                        }
                    }else {
                        service.insertItem(item);
                    }

                }
            } else {
                flag = false;
            }
        }catch (EncryptedDocumentException e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return BaseApiResult.failResult(500, "导入失败！模板格式错误，请把文档重新另存为一份.xsl或.xslx");
        }catch (OLE2NotOfficeXmlFileException e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return BaseApiResult.failResult(500, "导入失败！模板格式错误，请把文档重新另存为一份.xsl或.xslx");
        }catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return BaseApiResult.failResult(500, "导入失败！" + e.getMessage());
        }
        if (!flag) {
            return BaseApiResult.failResult(500, "导入失败，找不到要导入的数据！");
        }

        log.info("----------end----------importExcel---student");
        return ApiResult.success(StringUtils.isEmpty(msg) ? "导入成功！" : msg);
    }

    /**
     * 儿童（学生）导出
     * @return
     */
    @ViLog(operEvent = "儿童（学生）导出", operType =4)//日志记录
    @ApiOperation(value = "儿童（学生）导出")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @RequestMapping(value = "/export")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response, @RequestBody QueryBeanNoPage queryBean) {
        log.info("----------start----------exportExcel---student");
        try {
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            QSysStudent qSysStudent = QSysStudent.sysStudent;
            List<SysStudent> students = service.getAllItemByQuerybean(queryBean,
                    query-> {
                        query.leftJoin(QSysKindergarten.sysKindergarten).on(QSysKindergarten.sysKindergarten.orgId.eq(qSysStudent.orgId));
                        query.where(qSysStudent.status.eq(0));
                        if (user.getType() != 1) {
                            query.where(qSysStudent.orgId.eq(user.getOrgId()));
                        }
                        return query;
                    });
            List<Map<String, Object>> rowlist = new ArrayList<>();
            for (SysStudent student : students){
                student.setSex(TypeNameUtil.getTypeName("xb", student.getSex()));
                student.setCertType(TypeNameUtil.getTypeName("zjlx", student.getCertType()));
                student.setGrade(TypeNameUtil.getTypeName("nj", student.getGrade()));
                student.setClazz(TypeNameUtil.getTypeName("bh", student.getClazz()));
                rowlist.add(ExcelUtils.getObjectToMap(student));
            }

            String[] titles = { "就读幼儿园", "姓名", "性别", "证件类型", "证件号码", "所在年级", "所在班级"};
            String[] columnKey = { "kindergartenName", "name", "sex", "certType", "certNo", "grade", "clazz"};

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss"); // 日期格式化
            request.setCharacterEncoding("utf-8");
            String fileName = "student_" + sdf.format(new Date()) + ".xls";
            fileName = new String(fileName.getBytes("gbk"), "iso8859-1");
            HSSFWorkbook workbook = ExcelUtils.eqEcel(rowlist, titles, "儿童（学生）", columnKey);
            response.reset();
            response.setContentType("contentType=application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName);
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Connection", "close");
            workbook.write(response.getOutputStream());
            response.flushBuffer();

        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("----------end----------exportExcel---student");
    }

    /**
     * 儿童（学生）转出
     * @param item
     * @return
     */
    @ViLog(operEvent = "儿童（学生）转出", operType =1)//日志记录
    @SuppressWarnings("unchecked")
    @RequestMapping("/transferOut")
    @Transactional
    public BaseApiResult transferOut(@RequestBody SysStudentAdjustRecord item) {
        try {
            if (item.getStudentIds()!= null && item.getStudentIds().length > 0) {
                for (Long studentId : item.getStudentIds()) {
                    SysStudentAdjustRecord sysStudentAdjustRecord = new SysStudentAdjustRecord();
                    BeanUtils.copyProperties(item, sysStudentAdjustRecord, "id","version","created","creator","lastmodi","modifier");
                    sysStudentAdjustRecord.setStudentId(studentId);
                    service.adjustStudentInfo(sysStudentAdjustRecord, 2);
                }
            }
            return BaseApiResult.successResult();
        } catch (Exception e) {
            log.error("儿童转出出错:"+e.getMessage(),e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ApiResult.fail("儿童转出出错:"+e.getMessage());
        }
    }

    /**
     * 儿童（学生）毕业
     * @param item
     * @return
     */
    @ViLog(operEvent = "儿童（学生）毕业", operType = 1)//日志记录
    @SuppressWarnings("unchecked")
    @RequestMapping("/graduate")
    public BaseApiResult graduate(@RequestBody SysStudentAdjustRecord item) {
        try {
            if (item.getStudentIds()!= null && item.getStudentIds().length > 0) {
                for (Long studentId : item.getStudentIds()) {
                    SysStudentAdjustRecord sysStudentAdjustRecord = new SysStudentAdjustRecord();
                    BeanUtils.copyProperties(item, sysStudentAdjustRecord, "id","version","created","creator","lastmodi","modifier");
                    sysStudentAdjustRecord.setStudentId(studentId);
                    service.adjustStudentInfo(sysStudentAdjustRecord, 5);
                }
            }
            return BaseApiResult.successResult();
        } catch (Exception e) {
            log.error("儿童毕业操作出错:"+e.getMessage(),e);
            return ApiResult.fail("儿童毕业操作出错:"+e.getMessage());
        }
    }

    /**
     * 儿童（学生）一键毕业
     * @param item
     * @return
     */
    @ViLog(operEvent = "儿童（学生）一键毕业", operType = 1)//日志记录
    @SuppressWarnings("unchecked")
    @RequestMapping("/batchGraduate")
    @Transactional
    public BaseApiResult batchGraduate(@RequestBody SysStudentAdjustRecord item) {
        try {
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            QSysStudent qSysStudent = QSysStudent.sysStudent;
            List<SysStudent> students = service.getAllItems(
                    query-> {
                        query.where(qSysStudent.status.eq(0));
                        query.where(qSysStudent.grade.in("12","13"));// 大班级，大大班级
                        if (user.getType() != 1) {
                            query.where(qSysStudent.orgId.eq(user.getOrgId()));
                        }
                        return query;
                    });
            if (students!= null && students.size() > 0) {
                for (SysStudent sysStudent : students) {
                    SysStudentAdjustRecord sysStudentAdjustRecord = new SysStudentAdjustRecord();
                    BeanUtils.copyProperties(item, sysStudentAdjustRecord, "id","version","created","creator","lastmodi","modifier");
                    sysStudentAdjustRecord.setStudentId(sysStudent.getId());
                    service.adjustStudentInfo(sysStudentAdjustRecord, 5);
                }
            }
            return BaseApiResult.successResult();
        } catch (Exception e) {
            log.error("儿童一键毕业操作出错:"+e.getMessage(),e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ApiResult.fail("儿童一键毕业操作出错:"+e.getMessage());
        }
    }

    /**
     * 儿童（学生）一键升班
     * @param item
     * @return
     */
    @ViLog(operEvent = "儿童（学生）一键升班", operType = 1)//日志记录
    @SuppressWarnings("unchecked")
    @RequestMapping("/goUpGrade")
    @Transactional
    public BaseApiResult goUpGrade(@RequestBody SysStudentAdjustRecord item) {
        try {
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            QSysStudent qSysStudent = QSysStudent.sysStudent;
            List<SysStudent> students = service.getAllItems(
                    query-> {
                        query.where(qSysStudent.status.eq(0));
                        if (user.getType() != 1) {
                            query.where(qSysStudent.orgId.eq(user.getOrgId()));
                        }
                        return query;
                    });
            if (students!= null && students.size() > 0) {
                for (SysStudent sysStudent : students) {
                    SysStudentAdjustRecord sysStudentAdjustRecord = new SysStudentAdjustRecord();
                    BeanUtils.copyProperties(item, sysStudentAdjustRecord, "id","version","created","creator","lastmodi","modifier");
                    sysStudentAdjustRecord.setStudentId(sysStudent.getId());
                    service.adjustStudentInfo(sysStudentAdjustRecord, 4);
                }
            }
            return BaseApiResult.successResult();
        } catch (Exception e) {
            log.error("儿童一键升班操作出错:"+e.getMessage(),e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ApiResult.fail("儿童一键升班操作出错:"+e.getMessage());
        }
    }

    /**
     * 儿童（学生）调班
     * @param item
     * @return
     */
    @ViLog(operEvent = "儿童（学生）调班", operType = 1)//日志记录
    @SuppressWarnings("unchecked")
    @RequestMapping("/adjustClass")
    @Transactional
    public BaseApiResult adjustClass(@RequestBody SysStudentAdjustRecord item) {
        try {
            if (item.getStudentIds()!= null && item.getStudentIds().length > 0) {
                for (Long studentId : item.getStudentIds()) {
                    SysStudentAdjustRecord sysStudentAdjustRecord = new SysStudentAdjustRecord();
                    BeanUtils.copyProperties(item, sysStudentAdjustRecord, "id","version","created","creator","lastmodi","modifier");
                    sysStudentAdjustRecord.setStudentId(studentId);
                    service.adjustStudentInfo(sysStudentAdjustRecord, 3);
                }
            }
            return BaseApiResult.successResult();
        } catch (Exception e) {
            log.error("儿童毕业操作出错:"+e.getMessage(),e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ApiResult.fail("儿童毕业操作出错:"+e.getMessage());
        }
    }


    /**
     * 儿童（学生）转入
     * @param item
     * @return
     */
    @ViLog(operEvent = "儿童（学生）转入", operType =1)//日志记录
    @SuppressWarnings("unchecked")
    @RequestMapping("/transferIn")
    public BaseApiResult transferIn(@RequestBody SysStudentAdjustRecord item) {
        try {
                service.adjustStudentInfo(item, 1);
            return BaseApiResult.successResult();
        } catch (Exception e) {
            log.error("儿童转入出错:"+e.getMessage(),e);
            return ApiResult.fail("儿童转入出错:"+e.getMessage());
        }
    }


    /**
     * 转入列表
     * @param queryBean
     * @return
     */
    @PostMapping("/transferIn/list")
    @SuppressWarnings("unchecked")
    public ApiResult<PageListData<SysStudent>> queryTransferIn(@RequestBody QueryBean queryBean){
        try {
            QSysStudent qSysStudent = QSysStudent.sysStudent;
            String certNo = queryBean.getCertNo();
            queryBean.setCertNo(null);
            return ApiResult.success(service.getAllItemPageByQuerybean(queryBean,
                    query-> {
                        if (StringUtils.isNotEmpty(certNo)) {// 证件号码不为空才做查询
                            query.where(qSysStudent.certNo.eq(certNo));
                            query.where(qSysStudent.status.in(1,2));
                        }else {// 否则返还空列表
                            query.where(qSysStudent.id.eq(-1L));
                        }
                        return query;
                    }));
        } catch (Exception e) {
            log.error("查询儿童转入列表出错:"+e.getMessage(),e);
            return ApiResult.fail("查询儿童转入列表出错:"+e.getMessage());
        }
    }

    /**
     * 中转站列表
     * @param query
     * @return
     */
    @PostMapping("/transfer/list")
    @SuppressWarnings("unchecked")
    public ApiResult<PageListData<SysStudentVo>> queryTransfer(@RequestBody QueryBean query){
        try {
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            QueryResults<SysStudentVo> list = service.getTransferList(user.getType(),user.getOrgId(), query.getOldOrgId(),query.getName(),query.getCertType(),query.getCertNo(),
                    query.getGrade(),query.getClazz(),query.getAdjustCause(),query.getAdjustTime(),query.getPage(),query.getPageSize());
            return ApiResult.success(new PageListData<>(query.getPageSize(),list.getTotal(),list.getResults()));
        } catch (Exception e) {
            log.error("查询儿童中转站列表出错:"+e.getMessage(),e);
            return ApiResult.fail("查询儿童中转站列表出错:"+e.getMessage());
        }
    }

    /**
     * 回收站列表
     * @param queryBean
     * @return
     */
    @PostMapping("/del/list")
    @SuppressWarnings("unchecked")
    public ApiResult<PageListData<SysStudent>> queryDel(@RequestBody QueryBean queryBean){
        try {
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            QSysStudent qSysStudent = QSysStudent.sysStudent;
            return ApiResult.success(service.getAllItemPageByQuerybean(queryBean,
                    query-> {
                        query.where(qSysStudent.status.eq(-1));
                        if (user.getType() != 1) {
                            query.where(qSysStudent.orgId.eq(user.getOrgId()));
                        }
                        return query;
                    }));
        } catch (Exception e) {
            log.error("查询儿童回收站列表出错:"+e.getMessage(),e);
            return ApiResult.fail("查询儿童回收站列表出错:"+e.getMessage());
        }
    }

    /**
     * 儿童（学生）还原
     * @param item
     * @return
     */
    @ViLog(operEvent = "儿童（学生）还原", operType =1)//日志记录
    @SuppressWarnings("unchecked")
    @RequestMapping("/restore")
    @Transactional
    public BaseApiResult restore(@RequestBody SysStudentVo item) {
        try {
            String msg = "";
            Long[] ids = item.getIds();
            String[] certNos = item.getCertNos();
            if (certNos != null && certNos.length > 0) {
                for (int i = 0;i < certNos.length; i++) {
                    if (service.getItemByCertNo(null, certNos[i]) != null) {
                        msg += certNos[i] + "学生\n";
                    }else{
                        SysStudent sysStudent = service.getItemById(ids[i]);
                        sysStudent.setStatus(0);
                        service.updateItem(sysStudent);
                    }
                }
            }
            return ApiResult.success(StringUtils.isEmpty(msg) ? "还原成功！" : "系统已存在\n"+ msg);
        } catch (Exception e) {
            log.error("儿童还原出错:"+e.getMessage(),e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ApiResult.fail("儿童还原出错:"+e.getMessage());
        }
    }

    /**
     * 申请结果列表
     * @param query
     * @return
     */
    @PostMapping("/apply/results")
    @SuppressWarnings("unchecked")
    public ApiResult<PageListData<SysStudent>> queryApplyResults(@RequestBody QueryBean query){
        try {
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            QueryResults<SysStudent> list = service.getApplyResults(user.getType(),query.getOrgId(),
                    query.getName(),query.getCertNo(),query.getYear(),query.getPage(), query.getPageSize());
            return ApiResult.success(new PageListData<>(query.getPageSize(),list.getTotal(),list.getResults()));
        } catch (Exception e) {
            log.error("查询申请结果出错:"+e.getMessage(),e);
            return ApiResult.fail("查询申请结果出错:"+e.getMessage());
        }
    }

    /**
     * 申请结果导出
     * @return
     */
    @ViLog(operEvent = "申请结果导出", operType =4)//日志记录
    @ApiOperation(value = "申请结果导出")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @RequestMapping(value = "/apply/results/export")
    public void exportExcelApplyResults(HttpServletRequest request, HttpServletResponse response, @RequestBody QueryBeanNoPage queryBean) {
        log.info("----------start----------exportExcelApplyResults");
        try {
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            List<SysStudent> students  = service.getApplyResults(user.getType(),queryBean.getOrgId(),
                    queryBean.getName(),queryBean.getCertNo(),queryBean.getYear(),0, 0).getResults();
            List<Map<String, Object>> rowlist = new ArrayList<>();
            for (SysStudent student : students){
                if (StringUtils.isNotEmpty(student.getSpStatus())) {
                    student.setSpStatus(student.getSpStatus().equals("0")?"不通过":"通过");
                }else {
                    student.setSpStatus("无申请");
                }
                if (StringUtils.isNotEmpty(student.getActStatus())) {
                    student.setActStatus(student.getActStatus().equals("0")?"不通过":"通过");
                }else {
                    student.setActStatus("无申请");
                }
                rowlist.add(ExcelUtils.getObjectToMap(student));
            }

            String[] titles = { "就读幼儿园", "儿童姓名", "证件号码",  "审批结果", "实际结果", "备注"};
            String[] columnKey = { "kindergartenName", "name", "certNo", "spStatus", "actStatus","remark"};

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss"); // 日期格式化
            request.setCharacterEncoding("utf-8");
            String fileName = "applyResults_" + sdf.format(new Date()) + ".xls";
            fileName = new String(fileName.getBytes("gbk"), "iso8859-1");
            HSSFWorkbook workbook = ExcelUtils.eqEcel(rowlist, titles, "申请结果", columnKey);
            response.reset();
            response.setContentType("contentType=application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName);
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Connection", "close");
            workbook.write(response.getOutputStream());
            response.flushBuffer();

        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("----------end----------exportExcelApplyResults");
    }

    /**
     * 修正结果
     * @param item
     * @return
     */
    @ViLog(operEvent = "修正结果", operType =1)//日志记录
    @SuppressWarnings("unchecked")
    @RequestMapping("/update/actStatus")
    @Transactional
    public BaseApiResult actStatus(@RequestBody SpResults item) {
        try {
            return new ApiResult(0,spResultsService.updItems(item));
        } catch (Exception e) {
            log.error("修正结果出错:"+e.getMessage(),e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ApiResult.fail("修正结果出错:"+e.getMessage());
        }
    }
}
