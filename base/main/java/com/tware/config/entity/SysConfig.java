package com.tware.config.entity;

import com.tware.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swallow.framework.jpaquery.repository.annotations.CnName;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;

/**
 * 系统配置
 * @author zhengjc
 */
@ApiModel(value="系统配置对象")
@CnName("系统配置")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class SysConfig extends BaseEntity {

    private String configKey;

    private String configValue;

    private String description;

    public String getConfigKey() {
        return configKey;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
