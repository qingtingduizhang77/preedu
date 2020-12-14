package com.tware.kindergarten.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tware.common.entity.BaseEntity;
import com.tware.common.utils.TypeNameUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swallow.framework.jpaquery.repository.annotations.CnName;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Transient;
import java.util.Date;

/**
 * 幼儿园
 * @author zhengjc
 */
@ApiModel(value="幼儿园对象")
@CnName("幼儿园")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class SysKindergarten extends BaseEntity {

    @ApiModelProperty(value="机构Id",name="orgId",example="")
    @CnName("机构Id")
    private String orgId;

    @ApiModelProperty(value="幼儿园名称",name="name",example="")
    @CnName("幼儿园名称")
    private String name;

    @ApiModelProperty(value="编码",name="code",example="")
    @CnName("编码")
    private String code;

    @ApiModelProperty(value="所属街道编码",name="streetCode",example="")
    @CnName("所属街道编码")
    private String streetCode;

    @ApiModelProperty(value="所属片区编码",name="areaCode",example="")
    @CnName("所属片区编码")
    private String areaCode;

    @ApiModelProperty(value="办学性质",name="schoolNature",example="")
    @CnName("办学性质")
    private String schoolNature;

    @ApiModelProperty(value="状态（0：开启；1：关闭）",name="status",example="")
    @CnName("状态")
    private int status;

    @ApiModelProperty(value="所属机构ID",name="parentOrgId",example="")
    @CnName("所属机构ID")
    private String parentOrgId;

    @ApiModelProperty(value="地址",name="address",example="")
    @CnName("地址")
    private String address;

    @ApiModelProperty(value="办园类型",name="byType",example="")
    @CnName("办园类型")
    private String byType;

    @ApiModelProperty(value="证书编号",name="certCode",example="")
    @CnName("证书编号")
    private String certCode;

    @ApiModelProperty(value="批准设立时间",name="setupTime",example="")
    @CnName("批准设立时间")
    private Date setupTime;

    @ApiModelProperty(value="注册地址",name="registerAddress",example="")
    @CnName("注册地址")
    private String registerAddress;

    @ApiModelProperty(value="举办者",name="organizer",example="")
    @CnName("举办者")
    private String organizer;

    @ApiModelProperty(value="法定代表人",name="legalPerson",example="")
    @CnName("法定代表人")
    private String legalPerson;

    @ApiModelProperty(value="园长",name="principal",example="")
    @CnName("园长")
    private String principal;

    @ApiModelProperty(value="园长证件号码",name="principalCertNo",example="")
    @CnName("园长证件号码")
    private String principalCertNo;

    @ApiModelProperty(value="办园等级",name="byLevel",example="")
    @CnName("办园等级")
    private String byLevel;

    @ApiModelProperty(value="联系电话",name="telephone",example="")
    @CnName("联系电话")
    private String telephone;

    @ApiModelProperty(value="区级以上政府类奖惩",name="rewards",example="")
    @CnName("区级以上政府类奖惩")
    private String rewards;

    @ApiModelProperty(value="年检结论",name="asConclusion",example="")
    @CnName("年检结论")
    private String asConclusion;

    @ApiModelProperty(value="总占地面积",name="area",example="")
    @CnName("总占地面积")
    private Double area;

    @ApiModelProperty(value="总建筑面积",name="conArea",example="")
    @CnName("总建筑面积")
    private Double conArea;

    @ApiModelProperty(value="户外活动面积",name="outdoorArea",example="")
    @CnName("户外活动面积")
    private Double outdoorArea;

    @ApiModelProperty(value="园舍性质",name="schoolProperty",example="")
    @CnName("园舍性质")
    private String schoolProperty;

    @ApiModelProperty(value="租金",name="rent",example="")
    @CnName("租金")
    private Double rent;

    @ApiModelProperty(value="合同到期时间",name="contractEndTime",example="")
    @CnName("合同到期时间")
    private Date contractEndTime;

    @ApiModelProperty(value="收费标准",name="charges",example="")
    @CnName("收费标准")
    private Double charges;

    @ApiModelProperty(value="总收入",name="totalIncome",example="")
    @CnName("总收入")
    private Double totalIncome;

    @ApiModelProperty(value="补助收入",name="subsidyIncome",example="")
    @CnName("补助收入")
    private Double subsidyIncome;

    @ApiModelProperty(value="班级数",name="classNum",example="")
    @CnName("班级数")
    private Integer classNum;

    @ApiModelProperty(value="人数",name="personNum",example="")
    @CnName("人数")
    private Integer personNum;


    @Transient
    private String streetCodeName;

    @Transient
    private String areaCodeName;

    @Transient
    private String schoolNatureName;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStreetCode() {
        return streetCode;
    }

    public void setStreetCode(String streetCode) {
        this.streetCode = streetCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getSchoolNature() {
        return schoolNature;
    }

    public void setSchoolNature(String schoolNature) {
        this.schoolNature = schoolNature;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getParentOrgId() {
        return parentOrgId;
    }

    public void setParentOrgId(String parentOrgId) {
        this.parentOrgId = parentOrgId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getByType() {
        return byType;
    }

    public void setByType(String byType) {
        this.byType = byType;
    }

    public String getCertCode() {
        return certCode;
    }

    public void setCertCode(String certCode) {
        this.certCode = certCode;
    }

    public Date getSetupTime() {
        return setupTime;
    }

    public void setSetupTime(Date setupTime) {
        this.setupTime = setupTime;
    }

    public String getRegisterAddress() {
        return registerAddress;
    }

    public void setRegisterAddress(String registerAddress) {
        this.registerAddress = registerAddress;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getPrincipalCertNo() {
        return principalCertNo;
    }

    public void setPrincipalCertNo(String principalCertNo) {
        this.principalCertNo = principalCertNo;
    }

    public String getByLevel() {
        return byLevel;
    }

    public void setByLevel(String byLevel) {
        this.byLevel = byLevel;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getRewards() {
        return rewards;
    }

    public void setRewards(String rewards) {
        this.rewards = rewards;
    }

    public String getAsConclusion() {
        return asConclusion;
    }

    public void setAsConclusion(String asConclusion) {
        this.asConclusion = asConclusion;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Double getConArea() {
        return conArea;
    }

    public void setConArea(Double conArea) {
        this.conArea = conArea;
    }

    public Double getOutdoorArea() {
        return outdoorArea;
    }

    public void setOutdoorArea(Double outdoorArea) {
        this.outdoorArea = outdoorArea;
    }

    public String getSchoolProperty() {
        return schoolProperty;
    }

    public void setSchoolProperty(String schoolProperty) {
        this.schoolProperty = schoolProperty;
    }

    public Double getRent() {
        return rent;
    }

    public void setRent(Double rent) {
        this.rent = rent;
    }

    public Date getContractEndTime() {
        return contractEndTime;
    }

    public void setContractEndTime(Date contractEndTime) {
        this.contractEndTime = contractEndTime;
    }

    public Double getCharges() {
        return charges;
    }

    public void setCharges(Double charges) {
        this.charges = charges;
    }

    public Double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(Double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public Double getSubsidyIncome() {
        return subsidyIncome;
    }

    public void setSubsidyIncome(Double subsidyIncome) {
        this.subsidyIncome = subsidyIncome;
    }

    public Integer getClassNum() {
        return classNum;
    }

    public void setClassNum(Integer classNum) {
        this.classNum = classNum;
    }

    public Integer getPersonNum() {
        return personNum;
    }

    public void setPersonNum(Integer personNum) {
        this.personNum = personNum;
    }

    public String getStreetCodeName() {
        streetCodeName = TypeNameUtil.getTypeName("yryssjd", streetCode);
        return streetCodeName;
    }

    public void setStreetCodeName(String streetCodeName) {
        this.streetCodeName = streetCodeName;
    }

    public String getAreaCodeName() {
        areaCodeName = TypeNameUtil.getTypeName("yeysspq", areaCode);
        return areaCodeName;
    }

    public void setAreaCodeName(String areaCodeName) {
        this.areaCodeName = areaCodeName;
    }

    public String getSchoolNatureName() {
        schoolNatureName = TypeNameUtil.getTypeName("bxxz", schoolNature);
        return schoolNatureName;
    }

    public void setSchoolNatureName(String schoolNatureName) {
        this.schoolNatureName = schoolNatureName;
    }
}
