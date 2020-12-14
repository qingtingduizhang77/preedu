package com.tware.student.entity.vo;

import com.tware.student.entity.SysStudent;

import java.util.Date;

public class SysStudentVo extends SysStudent {

    private String adjustCause;

    private Date adjustTime;

    private Date opTime;

    private Long[] ids;

    private String[] certNos;

    private int flag;// 操作标识 1 ：跳过判断

    public String getAdjustCause() {
        return adjustCause;
    }

    public void setAdjustCause(String adjustCause) {
        this.adjustCause = adjustCause;
    }

    public Date getAdjustTime() {
        return adjustTime;
    }

    public void setAdjustTime(Date adjustTime) {
        this.adjustTime = adjustTime;
    }

    public Date getOpTime() {
        return opTime;
    }

    public void setOpTime(Date opTime) {
        this.opTime = opTime;
    }

    public Long[] getIds() {
        return ids;
    }

    public void setIds(Long[] ids) {
        this.ids = ids;
    }

    public String[] getCertNos() {
        return certNos;
    }

    public void setCertNos(String[] certNos) {
        this.certNos = certNos;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
