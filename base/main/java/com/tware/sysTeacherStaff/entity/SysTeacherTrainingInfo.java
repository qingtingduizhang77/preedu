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
 * 培训信息
 * @author Gyz
 */
@Entity
@ApiModel(value="培训信息")
@EntityListeners(AuditingEntityListener.class)
public class SysTeacherTrainingInfo extends BaseEntity {

    @ApiModelProperty(value="证件号码",name="certNum",example="")
    private String certNum;

    @ApiModelProperty(value="开始时间",name="startDate",example="")
	private Date startDate;

    @ApiModelProperty(value="结束时间",name="endDate",example="")
	private Date endDate;

    @ApiModelProperty(value="培训级别",name="trainingLevel",example="")
	private String trainingLevel;

    @ApiModelProperty(value="培训内容",name="trainingContent",example="")
	private String trainingContent;

    @ApiModelProperty(value="培训机构",name="trainingOrganization",example="")
	private String trainingOrganization;

    @ApiModelProperty(value="培训地点",name="trainingSite",example="")
	private String trainingSite;

    @ApiModelProperty(value="学时",name="trainingHours",example="")
	private Integer trainingHours;

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

    public String getTrainingLevel() {
        return trainingLevel;
    }

    public void setTrainingLevel(String trainingLevel) {
        this.trainingLevel = trainingLevel;
    }

    public String getTrainingContent() {
        return trainingContent;
    }

    public void setTrainingContent(String trainingContent) {
        this.trainingContent = trainingContent;
    }

    public String getTrainingOrganization() {
        return trainingOrganization;
    }

    public void setTrainingOrganization(String trainingOrganization) {
        this.trainingOrganization = trainingOrganization;
    }

    public String getTrainingSite() {
        return trainingSite;
    }

    public void setTrainingSite(String trainingSite) {
        this.trainingSite = trainingSite;
    }

    public Integer getTrainingHours() {
        return trainingHours;
    }

    public void setTrainingHours(Integer trainingHours) {
        this.trainingHours = trainingHours;
    }
}