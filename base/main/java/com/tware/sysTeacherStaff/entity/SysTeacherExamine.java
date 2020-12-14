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
 * 教职工考核信息
 * @author Gyz
 */
@Entity
@ApiModel(value="教职工考核信息")
@EntityListeners(AuditingEntityListener.class)
public class SysTeacherExamine extends BaseEntity {

    @ApiModelProperty(value="证件号码",name="certNum",example="")
    private String certNum;

    @ApiModelProperty(value="考核年度",name="examineYear",example="")
	private String examineYear;

    @ApiModelProperty(value="考核结果",name="examineResult",example="")
	private String examineResult;

    @ApiModelProperty(value="考核单位",name="examineUnit",example="")
	private String examineUnit;

    public String getCertNum() {
        return certNum;
    }

    public void setCertNum(String certNum) {
        this.certNum = certNum;
    }

    public String getExamineYear() {
        return examineYear;
    }

    public void setExamineYear(String examineYear) {
        this.examineYear = examineYear;
    }

    public String getExamineResult() {
        return examineResult;
    }

    public void setExamineResult(String examineResult) {
        this.examineResult = examineResult;
    }

    public String getExamineUnit() {
        return examineUnit;
    }

    public void setExamineUnit(String examineUnit) {
        this.examineUnit = examineUnit;
    }
}