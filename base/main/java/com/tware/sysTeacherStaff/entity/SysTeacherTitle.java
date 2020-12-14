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
 * 专业技术资格信息
 * @author Gyz
 */
@Entity
@ApiModel(value="专业技术资格信息")
@EntityListeners(AuditingEntityListener.class)
public class SysTeacherTitle extends BaseEntity {

    @ApiModelProperty(value="证件号码",name="certNum",example="")
    private String certNum;

    @ApiModelProperty(value="职称名称",name="titleName",example="")
	private String titleName;

    @ApiModelProperty(value="职称等级",name="level",example="")
	private String level;

    @ApiModelProperty(value="取得日期",name="date",example="")
	private Date date;

    @ApiModelProperty(value="发证机构",name="certifyingAuthority",example="")
	private String certifyingAuthority;

    public String getCertNum() {
        return certNum;
    }

    public void setCertNum(String certNum) {
        this.certNum = certNum;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCertifyingAuthority() {
        return certifyingAuthority;
    }

    public void setCertifyingAuthority(String certifyingAuthority) {
        this.certifyingAuthority = certifyingAuthority;
    }
}