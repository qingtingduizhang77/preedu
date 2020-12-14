package com.tware.sysTeacherStaff.entity;

import com.tware.common.entity.BaseEntity;
import com.tware.org.entity.SysOrg;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swallow.framework.jpaquery.repository.annotations.FieldPath;
import swallow.framework.jpaquery.repository.annotations.JoinEntity;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Transient;
import java.util.Date;

/**
 * 转入记录
 * @author Guoyz
 * createTime   2020/9/16 16:11
 */
@Entity
@ApiModel(value="转入记录")
@EntityListeners(AuditingEntityListener.class)
public class SysTeacherZhuanRu extends BaseEntity {
    private static final String T_Before ="T_Before";
    private static final String T_Now ="T_Now";

    @JoinEntity(name="orgId",joinEntityClass = SysOrg.class,joinEntityAlias = T_Before)
    @ApiModelProperty(value="原就职机构ID",name="orgIdBefore",example="")
    private String orgIdBefore;

    @Transient
    @FieldPath(name="name",joinEntityClass = SysOrg.class, joinEntityAlias = T_Before)
    @ApiModelProperty(value="原就职幼儿园",name="kindergartenBefore",example="")
    private String kindergartenBefore;

    @JoinEntity(name="orgId",joinEntityClass = SysOrg.class, joinEntityAlias = T_Now)
    @ApiModelProperty(value="现就职机构ID",name="orgIdNow",example="")
    private String orgIdNow;

    @Transient
    @FieldPath(name="name",joinEntityClass = SysOrg.class, joinEntityAlias = T_Now)
    @ApiModelProperty(value="现就职幼儿园",name="kindergartenNow",example="")
    private String kindergartenNow;

    @ApiModelProperty(value="姓名",name="name",example="")
    private String name;

    @ApiModelProperty(value="性别",name="sex",example="")
    private String sex;

    @ApiModelProperty(value="证件类型",name="certType",example="")
    private String certType;

    @ApiModelProperty(value="证件号码",name="certNum",example="")
    private String certNum;

    @ApiModelProperty(value="部门",name="departId",example="")
    private String departId;

    @ApiModelProperty(value="工作岗位",name="job",example="")
    private String job;

    @ApiModelProperty(value="转入时间",name="ZhuanRuDate",example="")
    private Date zhuanRuDate;

    @Transient
    private long[] ids;

    public long[] getIds() {
        return ids;
    }

    public void setIds(long[] ids) {
        this.ids = ids;
    }

    public String getOrgIdBefore() {
        return orgIdBefore;
    }

    public void setOrgIdBefore(String orgIdBefore) {
        this.orgIdBefore = orgIdBefore;
    }

    public String getKindergartenBefore() {
        return kindergartenBefore;
    }

    public void setKindergartenBefore(String kindergartenBefore) {
        this.kindergartenBefore = kindergartenBefore;
    }

    public String getOrgIdNow() {
        return orgIdNow;
    }

    public void setOrgIdNow(String orgIdNow) {
        this.orgIdNow = orgIdNow;
    }

    public String getKindergartenNow() {
        return kindergartenNow;
    }

    public void setKindergartenNow(String kindergartenNow) {
        this.kindergartenNow = kindergartenNow;
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

    public String getDepartId() {
        return departId;
    }

    public void setDepartId(String departId) {
        this.departId = departId;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Date getZhuanRuDate() {
        return zhuanRuDate;
    }

    public void setZhuanRuDate(Date zhuanRuDate) {
        this.zhuanRuDate = zhuanRuDate;
    }
}
