package com.tware.config.entity.vo;

import swallow.framework.jpaquery.repository.annotations.CnName;

import java.util.Date;

public class StudentVo {


    @CnName("所属幼儿园名称")
    private String kindergartenName;

    @CnName("幼儿园编码")
    private String kindergartenCode;

    @CnName("姓名")
    private String name;

    @CnName("性别")
    private String sex;

    @CnName("证件类型")
    private String certType;

    @CnName("证件号码")
    private String certNo;

    @CnName("出生日期")
    private Date birthdayTime;

    @CnName("所在年级")
    private String grade;

    @CnName("所在班级")
    private String clazz;

    @CnName("入学日期")
    private Date enrollmentTime;

    @CnName("户籍")
    private String census;

    @CnName("户口性质")
    private String residents;

    @CnName("籍贯")
    private String hometown;

    @CnName("家长手机号")
    private String telephone;

    @CnName("出生地")
    private String birthplace;

    @CnName("是否烈士子女")
    private String isMartyrsChildren;

    @CnName("是否孤儿")
    private String isOrphan;

    @CnName("是否残疾")
    private String isDisability;

    @CnName("是否单亲家庭")
    private String isOneParentFamily;

    @CnName("现居住地址")
    private String currentAddress;

    @CnName("监护人")
    private String guardian;

    @CnName("监护人姓名")
    private String guardianName;

    @CnName("监护人证件类型")
    private String guardianCertType;

    @CnName("监护人证件号码")
    private String guardianCertNo;

    @CnName("监护人户籍")
    private String guardianCensus;

    @CnName("监护人联系电话")
    private String guardianPhone;

    @CnName("监护人工作单位")
    private String guardianWorkUnit;

    @CnName("监护人2")
    private String guardian2;

    @CnName("监护人姓名2")
    private String guardianName2;

    @CnName("监护人证件类型2")
    private String guardianCertType2;

    @CnName("监护人证件号码2")
    private String guardianCertNo2;

    @CnName("监护人户籍2")
    private String guardianCensus2;

    @CnName("监护人联系电话2")
    private String guardianPhone2;

    @CnName("监护人工作单位2")
    private String guardianWorkUnit2;

    @CnName("开户行")
    private String bankName;

    @CnName("开户人")
    private String accountHolder;

    @CnName("卡号")
    private String cardNo;

    @CnName("支行")
    private String subBranch;

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
}
