package com.tware.student.entity;

import com.tware.common.entity.BaseEntity;
import com.tware.config.entity.SpResults;
import com.tware.kindergarten.entity.SysKindergarten;
import com.tware.org.entity.SysOrg;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swallow.framework.jpaquery.repository.annotations.CnName;
import swallow.framework.jpaquery.repository.annotations.FieldPath;
import swallow.framework.jpaquery.repository.annotations.JoinEntity;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Transient;
import java.util.Date;

/**
 * 儿童（学生）
 * @author zhengjc
 */
@ApiModel(value="儿童（学生）对象")
@CnName("儿童（学生）")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class SysStudent extends BaseEntity {

    @JoinEntity(name="orgId",joinEntityClass = SysOrg.class)
    @ApiModelProperty(value="所属幼儿园",name="orgId",example="")
    @CnName("所属幼儿园")
    private String orgId;

    @Transient
    @FieldPath(name="name",joinEntityClass = SysOrg.class)
    @ApiModelProperty(value="所属幼儿园名称",name="kindergartenName",example="")
    @CnName("所属幼儿园名称")
    private String kindergartenName;

    @ApiModelProperty(value="姓名",name="name",example="")
    @CnName("姓名")
    private String name;

    @ApiModelProperty(value="性别",name="sex",example="")
    @CnName("性别")
    private String sex;

    @ApiModelProperty(value="证件类型",name="certType",example="")
    @CnName("证件类型")
    private String certType;

//    @JoinEntity(name="certNo",joinEntityClass = SpResults.class)
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

    @ApiModelProperty(value="入学日期",name="enrollmentTime",example="")
    @CnName("入学日期")
    private Date enrollmentTime;

    @ApiModelProperty(value="状态（-1：删除；0：正常；1：转出；2：毕业）",name="status",example="")
    @CnName("状态")
    private int status;

    @ApiModelProperty(value="户籍",name="census",example="")
    @CnName("户籍")
    private String census;

    @ApiModelProperty(value="户口性质",name="residents",example="")
    @CnName("户口性质")
    private String residents;

    @ApiModelProperty(value="民族",name="nation",example="")
    @CnName("民族")
    private String nation;

    @ApiModelProperty(value="籍贯",name="hometown",example="")
    @CnName("籍贯")
    private String hometown;

    @ApiModelProperty(value="家长手机号",name="telephone",example="")
    @CnName("家长手机号")
    private String telephone;

    @ApiModelProperty(value="出生地",name="birthplace",example="")
    @CnName("出生地")
    private String birthplace;

    @ApiModelProperty(value="是否烈士子女",name="isMartyrsChildren",example="")
    @CnName("是否烈士子女")
    private String isMartyrsChildren;

    @ApiModelProperty(value="是否孤儿",name="isOrphan",example="")
    @CnName("是否孤儿")
    private String isOrphan;

    @ApiModelProperty(value="是否残疾",name="isDisability",example="")
    @CnName("是否残疾")
    private String isDisability;

    @ApiModelProperty(value="是否单亲家庭",name="isOneParentFamily",example="")
    @CnName("是否单亲家庭")
    private String isOneParentFamily;

    @ApiModelProperty(value="现居住地址",name="currentAddress",example="")
    @CnName("现居住地址")
    private String currentAddress;

    @ApiModelProperty(value="监护人",name="guardian",example="")
    @CnName("监护人")
    private String guardian;

    @ApiModelProperty(value="监护人姓名",name="guardianName",example="")
    @CnName("监护人姓名")
    private String guardianName;

    @ApiModelProperty(value="监护人证件类型",name="guardianCertType",example="")
    @CnName("监护人证件类型")
    private String guardianCertType;

    @ApiModelProperty(value="监护人证件号码",name="guardianCertNo",example="")
    @CnName("监护人证件号码")
    private String guardianCertNo;

    @ApiModelProperty(value="监护人户籍",name="guardianCensus",example="")
    @CnName("监护人户籍")
    private String guardianCensus;

    @ApiModelProperty(value="监护人联系电话",name="guardianPhone",example="")
    @CnName("监护人联系电话")
    private String guardianPhone;

    @ApiModelProperty(value="监护人工作单位",name="guardianWorkUnit",example="")
    @CnName("监护人工作单位")
    private String guardianWorkUnit;

    @ApiModelProperty(value="监护人2",name="guardian2",example="")
    @CnName("监护人2")
    private String guardian2;

    @ApiModelProperty(value="监护人姓名2",name="guardianName2",example="")
    @CnName("监护人姓名2")
    private String guardianName2;

    @ApiModelProperty(value="监护人证件类型2",name="guardianCertType2",example="")
    @CnName("监护人证件类型2")
    private String guardianCertType2;

    @ApiModelProperty(value="监护人证件号码2",name="guardianCertNo2",example="")
    @CnName("监护人证件号码2")
    private String guardianCertNo2;

    @ApiModelProperty(value="监护人户籍2",name="guardianCensus2",example="")
    @CnName("监护人户籍2")
    private String guardianCensus2;

    @ApiModelProperty(value="监护人联系电话2",name="guardianPhone2",example="")
    @CnName("监护人联系电话2")
    private String guardianPhone2;

    @ApiModelProperty(value="监护人工作单位2",name="guardianWorkUnit2",example="")
    @CnName("监护人工作单位2")
    private String guardianWorkUnit2;

    @ApiModelProperty(value="开户行",name="bankName",example="")
    @CnName("开户行")
    private String bankName;

    @ApiModelProperty(value="开户人",name="accountHolder",example="")
    @CnName("开户人")
    private String accountHolder;

    @ApiModelProperty(value="卡号",name="cardNo",example="")
    @CnName("卡号")
    private String cardNo;

    @ApiModelProperty(value="支行",name="subBranch",example="")
    @CnName("支行")
    private String subBranch;

    @JoinEntity(name="orgId",joinEntityClass = SysKindergarten.class)
    @ApiModelProperty(value="原就读幼儿园ID",name="oldOrgId",example="")
    @CnName("原就读幼儿园ID")
    private String oldOrgId;

    @Transient
    @FieldPath(name="name",joinEntityClass = SysKindergarten.class)
    @ApiModelProperty(value="原就读幼儿园名称",name="oldKindergartenName",example="")
    @CnName("原就读幼儿园名称")
    private String oldKindergartenName;

    @Transient
    private int flag;// 操作标识 1 ：跳过判断

    @Transient
//    @FieldPath(name="year",joinEntityClass = SpResults.class)
    @CnName("申请年度")
    private String year;

    @Transient
//    @FieldPath(name="bizType",joinEntityClass = SpResults.class)
    @CnName("申请事项")
    private String bizType;

    @Transient
//    @FieldPath(name="spStatus",joinEntityClass = SpResults.class)
    @CnName("审批状态")
    private String spStatus;

    @Transient
//    @FieldPath(name="status",joinEntityClass = SpResults.class)
    @CnName("实际状态")
    private String actStatus;

    @Transient
//    @FieldPath(name="remark",joinEntityClass = SpResults.class)
    @CnName("备注")
    private String remark;

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

    public Date getEnrollmentTime() {
        return enrollmentTime;
    }

    public void setEnrollmentTime(Date enrollmentTime) {
        this.enrollmentTime = enrollmentTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCensus() {
        return census;
    }

    public void setCensus(String census) {
        this.census = census;
    }

    public String getResidents() {
        return residents;
    }

    public void setResidents(String residents) {
        this.residents = residents;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public String getIsMartyrsChildren() {
        return isMartyrsChildren;
    }

    public void setIsMartyrsChildren(String isMartyrsChildren) {
        this.isMartyrsChildren = isMartyrsChildren;
    }

    public String getIsOrphan() {
        return isOrphan;
    }

    public void setIsOrphan(String isOrphan) {
        this.isOrphan = isOrphan;
    }

    public String getIsDisability() {
        return isDisability;
    }

    public void setIsDisability(String isDisability) {
        this.isDisability = isDisability;
    }

    public String getIsOneParentFamily() {
        return isOneParentFamily;
    }

    public void setIsOneParentFamily(String isOneParentFamily) {
        this.isOneParentFamily = isOneParentFamily;
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(String currentAddress) {
        this.currentAddress = currentAddress;
    }

    public String getGuardian() {
        return guardian;
    }

    public void setGuardian(String guardian) {
        this.guardian = guardian;
    }

    public String getGuardianName() {
        return guardianName;
    }

    public void setGuardianName(String guardianName) {
        this.guardianName = guardianName;
    }

    public String getGuardianCertType() {
        return guardianCertType;
    }

    public void setGuardianCertType(String guardianCertType) {
        this.guardianCertType = guardianCertType;
    }

    public String getGuardianCertNo() {
        return guardianCertNo;
    }

    public void setGuardianCertNo(String guardianCertNo) {
        this.guardianCertNo = guardianCertNo;
    }

    public String getGuardianCensus() {
        return guardianCensus;
    }

    public void setGuardianCensus(String guardianCensus) {
        this.guardianCensus = guardianCensus;
    }

    public String getGuardianPhone() {
        return guardianPhone;
    }

    public void setGuardianPhone(String guardianPhone) {
        this.guardianPhone = guardianPhone;
    }

    public String getGuardianWorkUnit() {
        return guardianWorkUnit;
    }

    public void setGuardianWorkUnit(String guardianWorkUnit) {
        this.guardianWorkUnit = guardianWorkUnit;
    }

    public String getGuardian2() {
        return guardian2;
    }

    public void setGuardian2(String guardian2) {
        this.guardian2 = guardian2;
    }

    public String getGuardianName2() {
        return guardianName2;
    }

    public void setGuardianName2(String guardianName2) {
        this.guardianName2 = guardianName2;
    }

    public String getGuardianCertType2() {
        return guardianCertType2;
    }

    public void setGuardianCertType2(String guardianCertType2) {
        this.guardianCertType2 = guardianCertType2;
    }

    public String getGuardianCertNo2() {
        return guardianCertNo2;
    }

    public void setGuardianCertNo2(String guardianCertNo2) {
        this.guardianCertNo2 = guardianCertNo2;
    }

    public String getGuardianCensus2() {
        return guardianCensus2;
    }

    public void setGuardianCensus2(String guardianCensus2) {
        this.guardianCensus2 = guardianCensus2;
    }

    public String getGuardianPhone2() {
        return guardianPhone2;
    }

    public void setGuardianPhone2(String guardianPhone2) {
        this.guardianPhone2 = guardianPhone2;
    }

    public String getGuardianWorkUnit2() {
        return guardianWorkUnit2;
    }

    public void setGuardianWorkUnit2(String guardianWorkUnit2) {
        this.guardianWorkUnit2 = guardianWorkUnit2;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getSubBranch() {
        return subBranch;
    }

    public void setSubBranch(String subBranch) {
        this.subBranch = subBranch;
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

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getSpStatus() {
        return spStatus;
    }

    public void setSpStatus(String spStatus) {
        this.spStatus = spStatus;
    }

    public String getActStatus() {
        return actStatus;
    }

    public void setActStatus(String actStatus) {
        this.actStatus = actStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
