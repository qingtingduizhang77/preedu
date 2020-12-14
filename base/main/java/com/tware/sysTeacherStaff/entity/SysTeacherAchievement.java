package com.tware.sysTeacherStaff.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tware.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import java.util.Date;

/**
 * 教学科研成果及获奖（奖励）信息
 * @author Gyz
 */
@Entity
@ApiModel(value="教学科研成果及获奖（奖励）信息")
@EntityListeners(AuditingEntityListener.class)
public class SysTeacherAchievement extends BaseEntity {

    @ApiModelProperty(value="证件号码",name="certNum",example="")
    private String certNum;

    @ApiModelProperty(value="奖励名称",name="achievementName",example="")
	private String achievementName;

    @ApiModelProperty(value="奖励类别",name="achievementType",example="")
	private String achievementType;

    @ApiModelProperty(value="奖励级别",name="achievementLevel",example="")
	private String achievementLevel;

    @ApiModelProperty(value="授奖单位",name="achievementUnit",example="")
	private String achievementUnit;

    @ApiModelProperty(value="获奖年月",name="achievementDate",example="")
	private Date achievementDate;

    public String getCertNum() {
        return certNum;
    }

    public void setCertNum(String certNum) {
        this.certNum = certNum;
    }

    public String getAchievementName() {
        return achievementName;
    }

    public void setAchievementName(String achievementName) {
        this.achievementName = achievementName;
    }

    public String getAchievementType() {
        return achievementType;
    }

    public void setAchievementType(String achievementType) {
        this.achievementType = achievementType;
    }

    public String getAchievementLevel() {
        return achievementLevel;
    }

    public void setAchievementLevel(String achievementLevel) {
        this.achievementLevel = achievementLevel;
    }

    public String getAchievementUnit() {
        return achievementUnit;
    }

    public void setAchievementUnit(String achievementUnit) {
        this.achievementUnit = achievementUnit;
    }

    public Date getAchievementDate() {
        return achievementDate;
    }

    public void setAchievementDate(Date achievementDate) {
        this.achievementDate = achievementDate;
    }
}