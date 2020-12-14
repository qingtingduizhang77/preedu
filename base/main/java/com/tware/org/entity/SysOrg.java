package com.tware.org.entity;

import com.tware.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swallow.framework.jpaquery.repository.annotations.CnName;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;

/**
 * 用户
 * @author zhengjc
 */
@ApiModel(value="系统机构对象")
@CnName("机构")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class SysOrg extends BaseEntity {

    @ApiModelProperty(value="机构Id",name="orgId",example="")
    @CnName("机构Id")
    private String orgId;

    @ApiModelProperty(value="机构名称",name="name",example="")
    @CnName("机构名称")
    private String name;

    @ApiModelProperty(value="机构类型",name="type",example="")
    @CnName("机构类型")
    private Integer type;

    @ApiModelProperty(value="机构编码",name="code",example="")
    @CnName("机构编码")
    private String code;


    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
