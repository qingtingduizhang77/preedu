package com.tware.config.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class HeTongVo {

    @ApiModelProperty(value="合同签订单位",name="heTongUnit",example="")
    private String heTongUnit;

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    @ApiModelProperty(value="合同有效期开始",name="heTongStart",example="")
    private Date heTongStart;

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    @ApiModelProperty(value="合同有效期结束",name="heTongEnd",example="")
    private Date heTongEnd;

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    @ApiModelProperty(value="社保开始缴纳时间",name="shebaoStartDate",example="")
    private Date shebaoStartDate;

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    @ApiModelProperty(value="社保结束缴纳时间",name="shebaoEndDate",example="")
    private Date shebaoEndDate;

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
