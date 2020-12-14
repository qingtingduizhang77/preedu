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
 * 教师资格证定期注册
 * @author Gyz
 */
@Entity
@ApiModel(value="教师资格证定期注册")
@EntityListeners(AuditingEntityListener.class)
public class SysTeacherCertificationRegister extends BaseEntity {

    @ApiModelProperty(value="证件号码",name="certNum",example="")
    private String certNum;

    @ApiModelProperty(value="注册时间",name="registerDate",example="")
	private Date registerDate;

    @ApiModelProperty(value="注册单位",name="registerUnit",example="")
	private String registerUnit;

    @ApiModelProperty(value="注册教师资格证编号",name="registerNumber",example="")
	private String registerNumber;

    public String getCertNum() {
        return certNum;
    }

    public void setCertNum(String certNum) {
        this.certNum = certNum;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getRegisterUnit() {
        return registerUnit;
    }

    public void setRegisterUnit(String registerUnit) {
        this.registerUnit = registerUnit;
    }

    public String getRegisterNumber() {
        return registerNumber;
    }

    public void setRegisterNumber(String registerNumber) {
        this.registerNumber = registerNumber;
    }
}