package com.tware.sysTeacherStaff.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tware.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import java.util.Date;

/**
 * 家庭情况及社会关系
 * @author Gyz
 */
@Entity
@ApiModel(value="家庭情况及社会关系")
@EntityListeners(AuditingEntityListener.class)
public class SysTeacherFamilyTies extends BaseEntity {

    @ApiModelProperty(value="证件号码",name="certNum",example="")
    private String certNum;

    @ApiModelProperty(value="姓名",name="name",example="")
	private String name;

    @ApiModelProperty(value="关系",name="relation",example="")
	private String relation;

    @ApiModelProperty(value="出生日期",name="birthdate",example="")
	private Date birthdate;

    @ApiModelProperty(value="政治面貌",name="politicsStatus",example="")
	private String politicsStatus;

    @ApiModelProperty(value="工作单位",name="unit",example="")
	private String unit;


    public String getCertNum() {
        return certNum;
    }

    public void setCertNum(String certNum) {
        this.certNum = certNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getPoliticsStatus() {
        return politicsStatus;
    }

    public void setPoliticsStatus(String politicsStatus) {
        this.politicsStatus = politicsStatus;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}