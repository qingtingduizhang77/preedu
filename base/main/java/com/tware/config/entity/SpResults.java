package com.tware.config.entity;

import com.tware.common.entity.BaseSimpleEntity;
import io.swagger.annotations.ApiModel;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swallow.framework.jpaquery.repository.annotations.CnName;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Transient;

/**
 * 审批结果
 * @author zhengjc
 */
@ApiModel(value="审批结果")
@CnName("审批结果")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class SpResults extends BaseSimpleEntity {

    @CnName("年份")
    private String year;

    @CnName("证件号")
    private String certNo;

    @CnName("业务类型")
    private String bizType;

    @CnName("审批状态")
    private String spStatus;

    @CnName("实际状态")
    private String status;

    @CnName("备注")
    private String remark;

    @Transient
    private String[] certNos;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCertNo() {
        return certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getSpStatus() {
        return spStatus;
    }

    public void setSpStatus(String spStatus) {
        this.spStatus = spStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String[] getCertNos() {
        return certNos;
    }

    public void setCertNos(String[] certNos) {
        this.certNos = certNos;
    }
}
