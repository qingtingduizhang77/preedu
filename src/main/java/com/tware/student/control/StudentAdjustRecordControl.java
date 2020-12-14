package com.tware.student.control;

import com.tware.student.entity.QSysStudentAdjustRecord;
import com.tware.student.entity.SysStudentAdjustRecord;
import com.tware.student.service.SysStudentAdjustRecordService;
import com.tware.user.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import swallow.framework.jpaquery.repository.annotations.CnName;
import swallow.framework.jpaquery.repository.annotations.Like;
import swallow.framework.web.ApiResult;
import swallow.framework.web.BasePageQueryBean;
import swallow.framework.web.PageListData;

import java.util.Arrays;
import java.util.Date;

/**
 * 儿童（学生）调整记录
 * @author zhengjc
 *
 */
@RestController
@Api(tags = "儿童（学生）调整记录")
@RequestMapping("/api/adjust/record")
public class StudentAdjustRecordControl {

    private static final Logger log = LoggerFactory.getLogger(StudentAdjustRecordControl.class);

    @Autowired
    private SysStudentAdjustRecordService service;

    /**
     * 查询对象
     *
     */
    @ApiModel(value="儿童（学生）调整记录查询对象")
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

        @ApiModelProperty(value="新所在年级",name="newGrade",example="")
        private String newGrade;

        @ApiModelProperty(value="新所在班级",name="newClazz",example="")
        private String newClazz;

        //开始时间
        @ApiModelProperty(value="开始时间",name="startTime",example="")
        private Date startTime;
        //截至时间
        @ApiModelProperty(value="截至时间",name="endTime",example="")
        private Date endTime;

        @CnName("修改时间")
        @ApiModelProperty(value="修改时间",name="lastmodi",example="")
        private Date lastmodi;

        @ApiModelProperty(value="转出原因",name="adjustCause",example="")
        @Like
        private String adjustCause;

        @CnName("调整时间")
        @ApiModelProperty(value="调整时间",name="adjustTime",example="")
        private Date adjustTime;

        @ApiModelProperty(value="调整类型：1：转入；2：转出；3：调班；4：升班；5：毕业",name="adjustType",example="")
        private String adjustType;

        @CnName("操作时间")
        @ApiModelProperty(value="操作时间",name="created",example="")
        private Date created;

        private String[] adjustTypes;

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

        public Date getStartTime() {
            return startTime;
        }

        public void setStartTime(Date startTime) {
            this.startTime = startTime;
        }

        public Date getEndTime() {
            return endTime;
        }

        public void setEndTime(Date endTime) {
            this.endTime = endTime;
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

        public String getAdjustType() {
            return adjustType;
        }

        public void setAdjustType(String adjustType) {
            this.adjustType = adjustType;
        }

        public String getNewGrade() {
            return newGrade;
        }

        public void setNewGrade(String newGrade) {
            this.newGrade = newGrade;
        }

        public String getNewClazz() {
            return newClazz;
        }

        public void setNewClazz(String newClazz) {
            this.newClazz = newClazz;
        }

        public Date getAdjustTime() {
            return adjustTime;
        }

        public void setAdjustTime(Date adjustTime) {
            this.adjustTime = adjustTime;
        }

        public Date getCreated() {
            return created;
        }

        public void setCreated(Date created) {
            this.created = created;
        }

        public String[] getAdjustTypes() {
            return adjustTypes;
        }

        public void setAdjustTypes(String[] adjustTypes) {
            this.adjustTypes = adjustTypes;
        }
    }

    /**
     * 通过查询bean进行分页查询数据
     * @param queryBean
     * @return
     */
    @PostMapping("listpage")
    @SuppressWarnings("unchecked")
    public ApiResult<PageListData<SysStudentAdjustRecord>> query(@RequestBody QueryBean queryBean){
        try {
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            QSysStudentAdjustRecord qSysStudentAdjustRecord = QSysStudentAdjustRecord.sysStudentAdjustRecord;
            Date startTime = queryBean.getStartTime();
            Date endTime = queryBean.getEndTime();
            String[] adjustTypes = queryBean.getAdjustTypes();
            queryBean.setStartTime(null);
            queryBean.setEndTime(null);
            queryBean.setAdjustTypes(null);
            return ApiResult.success(service.getAllItemPageByQuerybean(queryBean,
                    query-> {
                        query.where(qSysStudentAdjustRecord.adjustType.in(adjustTypes));
                        if (user.getType() != 1) {
                            if (Arrays.asList(adjustTypes).contains("2") || Arrays.asList(adjustTypes).contains("5")) {
                                query.where(qSysStudentAdjustRecord.oldOrgId.eq(user.getOrgId()));
                            }else {
                                query.where(qSysStudentAdjustRecord.orgId.eq(user.getOrgId()));
                            }
                        }
                        //开始和结束字段
                        if(startTime != null && endTime != null ){
                            query.where(qSysStudentAdjustRecord.adjustTime.between(startTime, endTime));
                        }else if(startTime!=null){
                            query.where(qSysStudentAdjustRecord.adjustTime.after(startTime));
                        }else if(endTime!=null){
                            query.where(qSysStudentAdjustRecord.adjustTime.before(endTime));
                        }
                        return query;
                    }));
        } catch (Exception e) {
            log.error("查询儿童调整记录对象出错:"+e.getMessage(),e);
            return ApiResult.fail("查询儿童调整记录对象出错:"+e.getMessage());
        }
    }
}
