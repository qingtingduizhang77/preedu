package com.tware.student.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tware.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swallow.framework.jpaquery.repository.annotations.CnName;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Transient;
import java.util.Date;

/**
 * 儿童（学生）调整记录
 * @author zhengjc
 */
@ApiModel(value="儿童（学生）调整记录对象")
@CnName("儿童（学生）调整记录")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class SysStudentAdjustRecord extends BaseEntity {

    @ApiModelProperty(value="所属幼儿园",name="orgId",example="")
    @CnName("所属幼儿园")
    private String orgId;

    @ApiModelProperty(value="所属幼儿园名称",name="kindergartenName",example="")
    @CnName("所属幼儿园名称")
    private String kindergartenName;

    @ApiModelProperty(value="原就读幼儿园ID",name="oldOrgId",example="")
    @CnName("原就读幼儿园ID")
    private String oldOrgId;

    @ApiModelProperty(value="原就读幼儿园",name="oldOrgName",example="")
    @CnName("原就读幼儿园")
    private String oldKindergartenName;

    @ApiModelProperty(value="儿童id",name="studentId",example="")
    @CnName("儿童id")
    private Long studentId;

    @ApiModelProperty(value="姓名",name="name",example="")
    @CnName("姓名")
    private String name;

    @ApiModelProperty(value="性别",name="sex",example="")
    @CnName("性别")
    private String sex;

    @ApiModelProperty(value="证件类型",name="certType",example="")
    @CnName("证件类型")
    private String certType;

    @ApiModelProperty(value="证件号码",name="certNo",example="")
    @CnName("证件号码")
    private String certNo;

    @ApiModelProperty(value="出生日期",name="birthdayTime",example="")
    @CnName("出生日期")
    private Date birthdayTime;

    @ApiModelProperty(value="所在年级",name="grade",example="")
    @CnName("所在年级")
    private String grade;

    @ApiModelProperty(value="所在班级",name="clazz",example="")
    @CnName("所在班级")
    private String clazz;

    @ApiModelProperty(value="调至年级",name="newGrade",example="")
    @CnName("调至年级")
    private String newGrade;

    @ApiModelProperty(value="调至班级",name="newClazz",example="")
    @CnName("调至班级")
    private String newClazz;

    @ApiModelProperty(value="调整日期",name="adjustTime",example="")
    @CnName("调整日期")
    private Date adjustTime;

    @ApiModelProperty(value="调整原因",name="adjustCause",example="")
    @CnName("调整原因")
    private String adjustCause;

    @ApiModelProperty(value="调整类型",name="adjustType",example="")
    @CnName("调整类型（1：转入；2：转出；3：调班；4：升班；5：毕业）")
    private String adjustType;

    @Transient
    @CnName("用于批量操作")
    private Long[] studentIds;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getKindergartenName() {
        return kindergartenName;
    }

    public void setKindergartenName(String kindergartenName) {
        this.kindergartenName = kindergartenName;
    }

    public String getOldOrgId() {
        return oldOrgId;
    }

    public void setOldOrgId(String oldOrgId) {
        this.oldOrgId = oldOrgId;
    }

    public String getOldKindergartenName() {
        return oldKindergartenName;
    }

    public void setOldKindergartenName(String oldKindergartenName) {
        this.oldKindergartenName = oldKindergartenName;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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

    public Date getBirthdayTime() {
        return birthdayTime;
    }

    public void setBirthdayTime(Date birthdayTime) {
        this.birthdayTime = birthdayTime;
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

    public Long[] getStudentIds() {
        return studentIds;
    }

    public void setStudentIds(Long[] studentIds) {
        this.studentIds = studentIds;
    }
}
