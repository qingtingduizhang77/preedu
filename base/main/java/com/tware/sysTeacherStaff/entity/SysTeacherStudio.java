package com.tware.sysTeacherStaff.entity;

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
 * 参加工作室情况
 * @author Gyz
 */
@Entity
@ApiModel(value="参加工作室情况")
@EntityListeners(AuditingEntityListener.class)
public class SysTeacherStudio extends BaseEntity {

    @ApiModelProperty(value="证件号码",name="certNum",example="")
    private String certNum;

    @ApiModelProperty(value="工作室名称",name="studioName",example="")
	private String studioName;

    @ApiModelProperty(value="级别",name="level",example="")
	private String level;

    @ApiModelProperty(value="主要职务",name="job",example="")
	private String job;

    @ApiModelProperty(value="备注",name="remark",example="")
	private String remark;

    public String getCertNum() {
        return certNum;
    }

    public void setCertNum(String certNum) {
        this.certNum = certNum;
    }

    public String getStudioName() {
        return studioName;
    }

    public void setStudioName(String studioName) {
        this.studioName = studioName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}