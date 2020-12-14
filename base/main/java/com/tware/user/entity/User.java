package com.tware.user.entity;

import com.tware.common.entity.BaseEntity;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swallow.framework.jpaquery.repository.annotations.CnName;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Transient;

/**
 * 用户
 * @author zhengjc
 */
@ApiModel(value="系统用户对象")
@CnName("用户")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class User extends BaseEntity  implements Serializable{



    private static final long serialVersionUID = -2737992290520797269L;

    @ApiModelProperty(value="姓名",name="name",example="")
    @CnName("姓名")
    private String name;

	@ApiModelProperty(value="用户名",name="username",example="")
    @CnName("用户名")
    @Column(unique = true)
    private String username;

	@ApiModelProperty(value="密码",name="password",example="")
    @CnName("密码")
    @Column(nullable = false)
    private String password;

	@ApiModelProperty(value="是否禁用(-1:删除；0:正常；1：禁用)",name="disable",example="")
    @CnName("是否禁用")
    private int disable;

    @CnName("token")
    private String token;

    @ApiModelProperty(value="机构Id",name="orgId",example="")
    @CnName("机构Id")
    private String orgId;

    @ApiModelProperty(value="用户类型",name="type",example="")
    @CnName("用户类型")
    private Integer type;

    @Transient
    private Integer loginUserid;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDisable() {
        return disable;
    }

    public void setDisable(int disable) {
        this.disable = disable;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }


    public Integer getLoginUserid() {
        return this.loginUserid;
    }

    public void setLoginUserid(Integer loginUserid) {
        this.loginUserid = loginUserid;
    }

    @Override
    public Long getId()
    {
        if(super.getId()==null && this.loginUserid!=null && this.loginUserid>0)
        {
            super.setId(new Long(this.loginUserid));
        }
        return super.getId();
    }
}
