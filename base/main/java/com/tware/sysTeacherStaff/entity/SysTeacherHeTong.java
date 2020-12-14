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
 * 合同信息
 * @author Guoyz
 * createTime   2020/9/11 11:21
 */
@Entity
@ApiModel(value="合同信息")
@EntityListeners(AuditingEntityListener.class)
public class SysTeacherHeTong extends BaseEntity {

    @ApiModelProperty(value="证件号码",name="certNum",example="")
    private String certNum;

    @ApiModelProperty(value="合同签订单位",name="Unit",example="")
    private String heTongUnit;

    @ApiModelProperty(value="合同有效期开始",name="name",example="")
    private Date heTongStart;

    @ApiModelProperty(value="合同有效期结束",name="name",example="")
    private Date heTongEnd;

    @ApiModelProperty(value="社保开始缴纳时间",name="shebaoDate",example="")
    private Date shebaoStartDate;

    @ApiModelProperty(value="社保结束缴纳时间",name="shebaoDate",example="")
    private Date shebaoEndDate;

    public String getCertNum() {
        return certNum;
    }

    public void setCertNum(String certNum) {
        this.certNum = certNum;
    }

    public String getHeTongUnit() {
        return heTongUnit;
    }

    public void setHeTongUnit(String heTongUnit) {
        this.heTongUnit = heTongUnit;
    }

    public Date getHeTongStart() {
        return heTongStart;
    }

    public void setHeTongStart(Date heTongStart) {
        this.heTongStart = heTongStart;
    }

    public Date getHeTongEnd() {
        return heTongEnd;
    }

    public void setHeTongEnd(Date heTongEnd) {
        this.heTongEnd = heTongEnd;
    }

    public Date getShebaoStartDate() {
        return shebaoStartDate;
    }

    public void setShebaoStartDate(Date shebaoStartDate) {
        this.shebaoStartDate = shebaoStartDate;
    }

    public Date getShebaoEndDate() {
        return shebaoEndDate;
    }

    public void setShebaoEndDate(Date shebaoEndDate) {
        this.shebaoEndDate = shebaoEndDate;
    }
}
