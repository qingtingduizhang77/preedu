package com.tware.log.entity;

import com.tware.common.entity.OnlyIdEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swallow.framework.jpaquery.repository.annotations.CnName;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import java.util.Date;

@ApiModel(value="外部调用操作日志")
@CnName("外部调用操作日志")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class SysExternalLog extends OnlyIdEntity {

    @ApiModelProperty(value="请求描述",name="reqDesc",example="")
    @CnName("请求描述")
    private String reqDesc;

    @ApiModelProperty(value="响应描述",name="resDesc",example="")
    @CnName("响应描述")
    private String resDesc;

    @ApiModelProperty(value="操作类型（0：外部调用；1：请求外部）",name="type",example="")
    @CnName("操作类型")
    private int type;

    @ApiModelProperty(value="信息",name="message",example="")
    @CnName("信息")
    private String message;

    @ApiModelProperty(value="配置用户",name="appId",example="")
    @CnName("配置用户")
    private String appId;

    @ApiModelProperty(value="状态（0：正常；1：系统异常）",name="status",example="")
    @CnName("状态")
    private int status;

    @ApiModelProperty(value="创建时间",name="created",example="")
    @CnName("创建时间")
    private Date created;

    @ApiModelProperty(value="接口类型",name="apiType",example="")
    @CnName("接口类型")
    private String apiType;//1001学生合法性校验、1002教师合法性校验

    public String getReqDesc() {
        return reqDesc;
    }

    public void setReqDesc(String reqDesc) {
        this.reqDesc = reqDesc;
    }

    public String getResDesc() {
        return resDesc;
    }

    public void setResDesc(String resDesc) {
        this.resDesc = resDesc;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getApiType() {
        return apiType;
    }

    public void setApiType(String apiType) {
        this.apiType = apiType;
    }
}
