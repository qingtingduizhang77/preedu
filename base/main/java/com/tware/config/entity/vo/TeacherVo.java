package com.tware.config.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import swallow.framework.jpaquery.repository.annotations.CnName;

import java.util.Date;
import java.util.List;

public class TeacherVo {

    @CnName("现工作单位")
    private String kindergartenName;

    @CnName("幼儿园编码")
    private String kindergartenCode;

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

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
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

    @ApiModelProperty(value="姓名",name="name",example="")
    private String name;

    @ApiModelProperty(value="性别",name="sex",example="")
    private String sex;

    @ApiModelProperty(value="证件类型",name="certType",example="")
    private String certType;

    @ApiModelProperty(value="证件号码",name="certNum",example="")
    private String certNum;

    @ApiModelProperty(value="手机号码",name="contactWay",example="")
    private String contactWay;

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    @ApiModelProperty(value="出生日期",name="birthay",example="")
    private Date birthay;

    @ApiModelProperty(value="工作岗位",name="job",example="")
    private String job;

    @ApiModelProperty(value="全日制最高学历",name="fullTimeEducation",example="")
    private String fullTimeEducation;

    @ApiModelProperty(value="全日制最高学位",name="fullTimeDegree",example="")
    private String fullTimeDegree;

    @ApiModelProperty(value="非全日制最高学历",name="partTimeEducation",example="")
    private String partTimeEducation;

    @ApiModelProperty(value="非全日制最高学位",name="partTimeDegree",example="")
    private String partTimeDegree;

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    @ApiModelProperty(value="参加工作时间",name="totalWorkTime",example="")
    private Date totalWorkTime;

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    @ApiModelProperty(value="进入本园时间",name="nowWorkTime",example="")
    private Date nowWorkTime;

    @CnName("合同列表")
    private List<HeTongVo> heTongList;

    public String getKindergartenName() {
        return kindergartenName;
    }

    public void setKindergartenName(String kindergartenName) {
        this.kindergartenName = kindergartenName;
    }

    public String getKindergartenCode() {
        return kindergartenCode;
    }

    public void setKindergartenCode(String kindergartenCode) {
        this.kindergartenCode = kindergartenCode;
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

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
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

    public Date getTotalWorkTime() {
        return totalWorkTime;
    }

    public void setTotalWorkTime(Date totalWorkTime) {
        this.totalWorkTime = totalWorkTime;
    }

    public Date getNowWorkTime() {
        return nowWorkTime;
    }

    public void setNowWorkTime(Date nowWorkTime) {
        this.nowWorkTime = nowWorkTime;
    }

    public List<HeTongVo> getHeTongList() {
        return heTongList;
    }

    public void setHeTongList(List<HeTongVo> heTongList) {
        this.heTongList = heTongList;
    }
}
