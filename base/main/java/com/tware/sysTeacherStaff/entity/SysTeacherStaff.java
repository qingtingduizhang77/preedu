package com.tware.sysTeacherStaff.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tware.common.entity.BaseEntity;
import com.tware.config.entity.SpResults;
import com.tware.org.entity.SysOrg;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;
import swallow.framework.jpaquery.repository.annotations.CnName;
import swallow.framework.jpaquery.repository.annotations.FieldPath;
import swallow.framework.jpaquery.repository.annotations.JoinEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Transient;
import java.util.Date;

/**
 * 教职工基本信息
 * @author Guoyz
 * createTime   2020/9/7 14:52
 */
@Entity
@ApiModel(value="教职工基本信息")
@EntityListeners(AuditingEntityListener.class)
public class SysTeacherStaff extends BaseEntity {

    @JoinEntity(name="orgId",joinEntityClass = SysOrg.class)
    @ApiModelProperty(value="所属机构ID",name="orgId",example="")
    private String orgId;

    @Transient
    @FieldPath(name="name",joinEntityClass = SysOrg.class)
    @ApiModelProperty(value="现工作单位",name="kindergarten",example="")
    private String kindergarten;

    @ApiModelProperty(value="状态",name="status",example="")
    private Integer status;//正常时为0,教师转出时改为-1。教师删除时改为-2。

    @ApiModelProperty(value="账号ID",name="userId",example="")
    private Long userId;

    @ApiModelProperty(value="年级",name="nianji",example="")
    private String nianji;

    @ApiModelProperty(value="班号",name="banHao",example="")
    private String banHao;

    @ApiModelProperty(value="是否达到退休年龄",name="sfTuiXiu",example="")
    private String sfTuiXiu;

    @ApiModelProperty(value="在深社保卡电脑号",name="sheBaoNum",example="")
    private String sheBaoNum;

    @ApiModelProperty(value="现居住地址",name="address",example="")
    private String address;

    @ApiModelProperty(value="户籍信息",name="placeInfo",example="")
    private String placeInfo;

    @ApiModelProperty(value="是否办理居住证",name="juZhuZheng",example="")
    private String juZhuZheng;

    @ApiModelProperty(value=" 已领取保教人员从教津贴年限（年）",name="jinTieYear",example="")
    private Integer jinTieYear;

    @ApiModelProperty(value="资格证名称",name="teacherCertName",example="")
    private String teacherCertName;

    @ApiModelProperty(value="资格证编号",name="teacherCertNum",example="")
    private String teacherCertNum;

    @ApiModelProperty(value="发证单位",name="teacherCertUnit",example="")
    private String teacherCertUnit;

    @ApiModelProperty(value="发证时间",name="teacherCertDate",example="")
    private Date teacherCertDate;

    @ApiModelProperty(value="园长资格证书编号",name="yuanZhangCertNum",example="")
    private String yuanZhangCertNum;

    @ApiModelProperty(value="银行卡开户行",name="bankName",example="")
    private String bankName;

    @ApiModelProperty(value="银行卡开户人",name="bankHolder",example="")
    private String bankHolder;

    @ApiModelProperty(value="银行卡卡号",name="bankNum",example="")
    private String bankNum;

    @ApiModelProperty(value="银行卡支行",name="bankSite",example="")
    private String bankSite;

    @ApiModelProperty(value="部门",name="departId",example="")
    private String departId;

    @ApiModelProperty(value="姓名",name="name",example="")
    private String name;

    @ApiModelProperty(value="性别",name="sex",example="")
    private String sex;

    @ApiModelProperty(value="证件类型",name="certType",example="")
    private String certType;

    @Column(unique=true)//证件号码唯一，同时作为账号使用
    //@JoinEntity(name="certNo",joinEntityClass = SpResults.class)
    @ApiModelProperty(value="证件号码",name="certNum",example="")
    private String certNum;//删除后修改号码为：certNum+"#DEL#"+id

    @ApiModelProperty(value="手机号码",name="contactWay",example="")
    private String contactWay;

    @ApiModelProperty(value="出生日期",name="birthay",example="")
    private Date birthay;

    @ApiModelProperty(value="邮箱地址",name="email",example="")
    private String email;

    @ApiModelProperty(value="工作岗位",name="job",example="")
    private String job;

    @ApiModelProperty(value="籍贯",name="nativePlace",example="")
    private String nativePlace;

    @ApiModelProperty(value="出生地",name="birthplace",example="")
    private String birthplace;

    @ApiModelProperty(value="户籍地",name="placeOfDomicile",example="")
    private String placeOfDomicile;

    @ApiModelProperty(value="民族",name="nation",example="")
    private String nation;

    @ApiModelProperty(value="政治面貌",name="politicsStatus",example="")
    private String politicsStatus;

    @ApiModelProperty(value="入党时间",name="partyTime",example="")
    private Date partyTime;

    @ApiModelProperty(value="婚姻状况",name="maritalStatus",example="")
    private String maritalStatus;

    @ApiModelProperty(value="全日制最高学历",name="fullTimeEducation",example="")
    private String fullTimeEducation;

    @ApiModelProperty(value="全日制最高学位",name="fullTimeDegree",example="")
    private String fullTimeDegree;

    @ApiModelProperty(value="非全日制最高学历",name="partTimeEducation",example="")
    private String partTimeEducation;

    @ApiModelProperty(value="非全日制最高学位",name="partTimeDegree",example="")
    private String partTimeDegree;

    @ApiModelProperty(value="是否师范生",name="normalSchoolStudent",example="")
    private String normalSchoolStudent;

    @ApiModelProperty(value="教学学段",name="teachStage",example="")
    private String teachStage;

    @ApiModelProperty(value="教学学科",name="teachSubject",example="")
    private String teachSubject;

    @ApiModelProperty(value="参加工作时间",name="totalWorkTime",example="")
    private Date totalWorkTime;

    @ApiModelProperty(value="在深工作时间",name="shenzhenWorkTime",example="")
    private Date shenzhenWorkTime;

    @ApiModelProperty(value="在龙华工作时间",name="longhuaWorkTime",example="")
    private Date longhuaWorkTime;

    @ApiModelProperty(value="进入本园时间",name="nowWorkTime",example="")
    private Date nowWorkTime;

    @ApiModelProperty(value="人员类别",name="persionType",example="")
    private String persionType;

    @ApiModelProperty(value="骨干教师级别",name="backboneTeacherLevel",example="")
    private String backboneTeacherLevel;

    @ApiModelProperty(value="评选骨干教师时间",name="backboneTeacherDate",example="")
    private String backboneTeacherDate;

    @ApiModelProperty(value="现聘职务起始时间",name="jobStartTime",example="")
    private String jobStartTime;

    @ApiModelProperty(value="教师特长",name="speciality",example="")
    private String speciality;

    @ApiModelProperty(value="累计从教年限",name="totalEducationTime",example="")
    private Integer totalEducationTime;

    @ApiModelProperty(value="现有职称（职务）名称",name="jobName",example="")
    private String jobName;

    @ApiModelProperty(value="现有职称（职务）等级",name="jobLevel",example="")
    private String jobLevel;

    @ApiModelProperty(value="现有专业技术资格评定时间",name="jobAssessTime",example="")
    private Date jobAssessTime;

    @Transient
    private String sexName;//性别的名字

    @Transient
    private String certTypeName;//证件类型的名字

    @Transient
    private String departIdName;//部门的名字

    @Transient
    private String jobsName;//工作岗位的名字

    @Transient
    private int flag;// 操作标识：   1:跳过判断; 0:进行判断;

    @Transient
    //@FieldPath(name="year",joinEntityClass = SpResults.class)
    @CnName("申请年度")
    private String year;

    @Transient
    //@FieldPath(name="bizType",joinEntityClass = SpResults.class)
    @CnName("申请事项")
    private String bizType;

    @Transient
    //@FieldPath(name="spStatus",joinEntityClass = SpResults.class)
    @CnName("审批状态")
    private String spStatus;

    @Transient
    //@FieldPath(name="status",joinEntityClass = SpResults.class)
    @CnName("实际状态")
    private String actStatus;

    @Transient
    //@FieldPath(name="remark",joinEntityClass = SpResults.class)
    @CnName("备注")
    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getSexName() {
        return sexName;
    }

    public void setSexName(String sexName) {
        this.sexName = sexName;
    }

    public String getCertTypeName() {
        return certTypeName;
    }

    public void setCertTypeName(String certTypeName) {
        this.certTypeName = certTypeName;
    }

    public String getDepartIdName() {
        return departIdName;
    }

    public void setDepartIdName(String departIdName) {
        this.departIdName = departIdName;
    }

    public String getJobsName() {
        return jobsName;
    }

    public void setJobsName(String jobsName) {
        this.jobsName = jobsName;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getKindergarten() {
        return kindergarten;
    }

    public void setKindergarten(String kindergarten) {
        this.kindergarten = kindergarten;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNianji() {
        return nianji;
    }

    public void setNianji(String nianji) {
        this.nianji = nianji;
    }

    public String getBanHao() {
        return banHao;
    }

    public void setBanHao(String banHao) {
        this.banHao = banHao;
    }

    public String getSfTuiXiu() {
        return sfTuiXiu;
    }

    public void setSfTuiXiu(String sfTuiXiu) {
        this.sfTuiXiu = sfTuiXiu;
    }

    public String getSheBaoNum() {
        return sheBaoNum;
    }

    public void setSheBaoNum(String sheBaoNum) {
        this.sheBaoNum = sheBaoNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPlaceInfo() {
        return placeInfo;
    }

    public void setPlaceInfo(String placeInfo) {
        this.placeInfo = placeInfo;
    }

    public String getJuZhuZheng() {
        return juZhuZheng;
    }

    public void setJuZhuZheng(String juZhuZheng) {
        this.juZhuZheng = juZhuZheng;
    }

    public Integer getJinTieYear() {
        return jinTieYear;
    }

    public void setJinTieYear(Integer jinTieYear) {
        this.jinTieYear = jinTieYear;
    }

    public String getTeacherCertName() {
        return teacherCertName;
    }

    public void setTeacherCertName(String teacherCertName) {
        this.teacherCertName = teacherCertName;
    }

    public String getTeacherCertNum() {
        return teacherCertNum;
    }

    public void setTeacherCertNum(String teacherCertNum) {
        this.teacherCertNum = teacherCertNum;
    }

    public String getTeacherCertUnit() {
        return teacherCertUnit;
    }

    public void setTeacherCertUnit(String teacherCertUnit) {
        this.teacherCertUnit = teacherCertUnit;
    }

    public Date getTeacherCertDate() {
        return teacherCertDate;
    }

    public void setTeacherCertDate(Date teacherCertDate) {
        this.teacherCertDate = teacherCertDate;
    }

    public String getYuanZhangCertNum() {
        return yuanZhangCertNum;
    }

    public void setYuanZhangCertNum(String yuanZhangCertNum) {
        this.yuanZhangCertNum = yuanZhangCertNum;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankHolder() {
        return bankHolder;
    }

    public void setBankHolder(String bankHolder) {
        this.bankHolder = bankHolder;
    }

    public String getBankNum() {
        return bankNum;
    }

    public void setBankNum(String bankNum) {
        this.bankNum = bankNum;
    }

    public String getBankSite() {
        return bankSite;
    }

    public void setBankSite(String bankSite) {
        this.bankSite = bankSite;
    }

    public String getDepartId() {
        return departId;
    }

    public void setDepartId(String departId) {
        this.departId = departId;
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

    public String getCertNum() {
        return certNum;
    }

    public void setCertNum(String certNum) {
        this.certNum = certNum;
    }

    public String getContactWay() {
        return contactWay;
    }

    public void setContactWay(String contactWay) {
        this.contactWay = contactWay;
    }

    public Date getBirthay() {
        return birthay;
    }

    public void setBirthay(Date birthay) {
        this.birthay = birthay;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public String getPlaceOfDomicile() {
        return placeOfDomicile;
    }

    public void setPlaceOfDomicile(String placeOfDomicile) {
        this.placeOfDomicile = placeOfDomicile;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getPoliticsStatus() {
        return politicsStatus;
    }

    public void setPoliticsStatus(String politicsStatus) {
        this.politicsStatus = politicsStatus;
    }

    public Date getPartyTime() {
        return partyTime;
    }

    public void setPartyTime(Date partyTime) {
        this.partyTime = partyTime;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getFullTimeEducation() {
        return fullTimeEducation;
    }

    public void setFullTimeEducation(String fullTimeEducation) {
        this.fullTimeEducation = fullTimeEducation;
    }

    public String getFullTimeDegree() {
        return fullTimeDegree;
    }

    public void setFullTimeDegree(String fullTimeDegree) {
        this.fullTimeDegree = fullTimeDegree;
    }

    public String getPartTimeEducation() {
        return partTimeEducation;
    }

    public void setPartTimeEducation(String partTimeEducation) {
        this.partTimeEducation = partTimeEducation;
    }

    public String getPartTimeDegree() {
        return partTimeDegree;
    }

    public void setPartTimeDegree(String partTimeDegree) {
        this.partTimeDegree = partTimeDegree;
    }

    public String getNormalSchoolStudent() {
        return normalSchoolStudent;
    }

    public void setNormalSchoolStudent(String normalSchoolStudent) {
        this.normalSchoolStudent = normalSchoolStudent;
    }

    public String getTeachStage() {
        return teachStage;
    }

    public void setTeachStage(String teachStage) {
        this.teachStage = teachStage;
    }

    public String getTeachSubject() {
        return teachSubject;
    }

    public void setTeachSubject(String teachSubject) {
        this.teachSubject = teachSubject;
    }

    public Date getTotalWorkTime() {
        return totalWorkTime;
    }

    public void setTotalWorkTime(Date totalWorkTime) {
        this.totalWorkTime = totalWorkTime;
    }

    public Date getShenzhenWorkTime() {
        return shenzhenWorkTime;
    }

    public void setShenzhenWorkTime(Date shenzhenWorkTime) {
        this.shenzhenWorkTime = shenzhenWorkTime;
    }

    public Date getLonghuaWorkTime() {
        return longhuaWorkTime;
    }

    public void setLonghuaWorkTime(Date longhuaWorkTime) {
        this.longhuaWorkTime = longhuaWorkTime;
    }

    public Date getNowWorkTime() {
        return nowWorkTime;
    }

    public void setNowWorkTime(Date nowWorkTime) {
        this.nowWorkTime = nowWorkTime;
    }

    public String getPersionType() {
        return persionType;
    }

    public void setPersionType(String persionType) {
        this.persionType = persionType;
    }

    public String getBackboneTeacherLevel() {
        return backboneTeacherLevel;
    }

    public void setBackboneTeacherLevel(String backboneTeacherLevel) {
        this.backboneTeacherLevel = backboneTeacherLevel;
    }

    public String getBackboneTeacherDate() {
        return backboneTeacherDate;
    }

    public void setBackboneTeacherDate(String backboneTeacherDate) {
        this.backboneTeacherDate = backboneTeacherDate;
    }

    public String getJobStartTime() {
        return jobStartTime;
    }

    public void setJobStartTime(String jobStartTime) {
        this.jobStartTime = jobStartTime;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public Integer getTotalEducationTime() {
        return totalEducationTime;
    }

    public void setTotalEducationTime(Integer totalEducationTime) {
        this.totalEducationTime = totalEducationTime;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobLevel() {
        return jobLevel;
    }

    public void setJobLevel(String jobLevel) {
        this.jobLevel = jobLevel;
    }

    public Date getJobAssessTime() {
        return jobAssessTime;
    }

    public void setJobAssessTime(Date jobAssessTime) {
        this.jobAssessTime = jobAssessTime;
    }
}
