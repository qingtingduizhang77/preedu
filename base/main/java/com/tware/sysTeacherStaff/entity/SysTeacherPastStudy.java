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
 * 学习经历
 * @author Gyz
 */
@Entity
@ApiModel(value="学习经历")
@EntityListeners(AuditingEntityListener.class)
public class SysTeacherPastStudy extends BaseEntity {

    @ApiModelProperty(value="证件号码",name="certNum",example="")
    private String certNum;

    @ApiModelProperty(value="入学时间",name="startDate",example="")
	private Date startDate;

    @ApiModelProperty(value="毕业时间",name="endDate",example="")
	private Date endDate;

    @ApiModelProperty(value="毕业院校",name="school",example="")
	private String school;

    @ApiModelProperty(value="专业",name="major",example="")
	private String major;

    @ApiModelProperty(value="学制",name="length",example="")
	private String length;

    @ApiModelProperty(value="学习形式",name="studyMode",example="")
	private String studyMode;

    @ApiModelProperty(value="学历",name="education",example="")
	private String education;

    @ApiModelProperty(value="学历证书编号",name="educationNumber",example="")
	private String educationNumber;

    @ApiModelProperty(value="学位",name="degree",example="")
	private String degree;

    @ApiModelProperty(value="学位证书编号",name="degreeNumber",example="")
	private String degreeNumber;

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

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getStudyMode() {
        return studyMode;
    }

    public void setStudyMode(String studyMode) {
        this.studyMode = studyMode;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getEducationNumber() {
        return educationNumber;
    }

    public void setEducationNumber(String educationNumber) {
        this.educationNumber = educationNumber;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getDegreeNumber() {
        return degreeNumber;
    }

    public void setDegreeNumber(String degreeNumber) {
        this.degreeNumber = degreeNumber;
    }
}