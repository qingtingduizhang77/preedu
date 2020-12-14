package com.tware.sysTeacherStaff.entity;

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
 * 教师资格信息
 * @author Gyz
 */
@Entity
@ApiModel(value="教师资格信息")
@EntityListeners(AuditingEntityListener.class)
public class SysTeacherCertification extends BaseEntity {

    @ApiModelProperty(value="证件号码",name="certNum",example="")
    private String certNum;

    @ApiModelProperty(value="教师资格证号码",name="teacherCertificationNumber",example="")
	private String teacherCertificationNumber;

    @ApiModelProperty(value="认定机构",name="certificationCertification",example="")
	private String certificationCertification;

    @ApiModelProperty(value="资格种类",name="certificationType",example="")
	private String certificationType;

    @ApiModelProperty(value="任教学科",name="subject",example="")
	private String subject;

    public String getCertNum() {
        return certNum;
    }

    public void setCertNum(String certNum) {
        this.certNum = certNum;
    }

    public String getTeacherCertificationNumber() {
        return teacherCertificationNumber;
    }

    public void setTeacherCertificationNumber(String teacherCertificationNumber) {
        this.teacherCertificationNumber = teacherCertificationNumber;
    }

    public String getCertificationCertification() {
        return certificationCertification;
    }

    public void setCertificationCertification(String certificationCertification) {
        this.certificationCertification = certificationCertification;
    }

    public String getCertificationType() {
        return certificationType;
    }

    public void setCertificationType(String certificationType) {
        this.certificationType = certificationType;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}