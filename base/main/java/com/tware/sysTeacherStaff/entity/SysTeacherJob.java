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
 * 行政职务任职信息
 * @author Gyz
 */
@Entity
@ApiModel(value="行政职务任职信息")
@EntityListeners(AuditingEntityListener.class)
public class SysTeacherJob extends BaseEntity {

    @ApiModelProperty(value="证件号码",name="certNum",example="")
    private String certNum;

    @ApiModelProperty(value="任聘起始时间",name="startDate",example="")
	private Date startDate;

    @ApiModelProperty(value="任聘终止时间",name="endDate",example="")
	private Date endDate;

    @ApiModelProperty(value="任聘单位",name="unit",example="")
	private String unit;

    @ApiModelProperty(value="部门",name="department",example="")
	private String department;

    @ApiModelProperty(value="正副职",name="mainOrDputy",example="")
	private String mainOrDputy;

    @ApiModelProperty(value="任职文号",name="jobNumber",example="")
	private String jobNumber;

    public String getCertNum() {
        return certNum;
    }

    public void setCertNum(String certNum) {
        this.certNum = certNum;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getMainOrDputy() {
        return mainOrDputy;
    }

    public void setMainOrDputy(String mainOrDputy) {
        this.mainOrDputy = mainOrDputy;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }
}