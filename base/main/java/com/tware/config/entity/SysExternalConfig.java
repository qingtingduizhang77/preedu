package com.tware.config.entity;

import com.tware.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swallow.framework.jpaquery.repository.annotations.CnName;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;

/**
 * 外部调用系统配置
 * @author zhengjc
 */
@ApiModel(value="外部调用系统配置对象")
@CnName("外部调用系统配置")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class SysExternalConfig extends BaseEntity {

    @ApiModelProperty(value="appId",name="appId",example="")
    @CnName("appId")
    private String appId;

    @ApiModelProperty(value="appSecret",name="appSecret",example="")
    @CnName("appSecret")
    private String appSecret;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }
}
