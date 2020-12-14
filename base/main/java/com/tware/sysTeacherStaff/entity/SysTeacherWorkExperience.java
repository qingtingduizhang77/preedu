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
 * 教职工工作经历
 * @author Gyz
 */
@Entity
@ApiModel(value="教职工工作经历")
@EntityListeners(AuditingEntityListener.class)
public class SysTeacherWorkExperience extends BaseEntity {

    @ApiModelProperty(value="证件号码",name="certNum",example="")
    private String certNum;

    @ApiModelProperty(value="开始日期",name="startDate",example="")
	private Date startDate;

    @ApiModelProperty(value="结束日期",name="endDate",example="")
	private Date endDate;

    @ApiModelProperty(value="所在单位",name="unit",example="")
	private String unit;

    @ApiModelProperty(value="从事工作或担任职务",name="job",example="")
	private String job;

    @ApiModelProperty(value="从教年限",name="teachLimit",example="")
	private Integer teachLimit;

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

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Integer getTeachLimit() {
        return teachLimit;
    }

    public void setTeachLimit(Integer teachLimit) {
        this.teachLimit = teachLimit;
    }
}