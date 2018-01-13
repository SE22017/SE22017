package com.cn.swm.model.sys;

import java.util.Date;

public class Sequence {
    private String sequencecode;

    private String code;
    
    private String sequenceyear;

    private String sequencemonth;

    private String sequenceday;

    private String sequencevalue;

    private String resetflag;

    private String resettype;

    private String activeflag;

    private String addby;

    private Date addtime;

    private String updby;

    private Date updtime;

    public String getSequencecode() {
        return sequencecode;
    }

    public void setSequencecode(String sequencecode) {
        this.sequencecode = sequencecode == null ? null : sequencecode.trim();
    }

    public String getSequenceyear() {
        return sequenceyear;
    }

    public void setSequenceyear(String sequenceyear) {
        this.sequenceyear = sequenceyear == null ? null : sequenceyear.trim();
    }

    public String getSequencemonth() {
        return sequencemonth;
    }

    public void setSequencemonth(String sequencemonth) {
        this.sequencemonth = sequencemonth == null ? null : sequencemonth.trim();
    }

    public String getSequenceday() {
        return sequenceday;
    }

    public void setSequenceday(String sequenceday) {
        this.sequenceday = sequenceday == null ? null : sequenceday.trim();
    }

    public String getSequencevalue() {
        return sequencevalue;
    }

    public void setSequencevalue(String sequencevalue) {
        this.sequencevalue = sequencevalue == null ? null : sequencevalue.trim();
    }

    public String getResetflag() {
        return resetflag;
    }

    public void setResetflag(String resetflag) {
        this.resetflag = resetflag == null ? null : resetflag.trim();
    }

    public String getResettype() {
        return resettype;
    }

    public void setResettype(String resettype) {
        this.resettype = resettype == null ? null : resettype.trim();
    }

    public String getActiveflag() {
        return activeflag;
    }

    public void setActiveflag(String activeflag) {
        this.activeflag = activeflag == null ? null : activeflag.trim();
    }

    public String getAddby() {
        return addby;
    }

    public void setAddby(String addby) {
        this.addby = addby == null ? null : addby.trim();
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public String getUpdby() {
        return updby;
    }

    public void setUpdby(String updby) {
        this.updby = updby == null ? null : updby.trim();
    }

    public Date getUpdtime() {
        return updtime;
    }

    public void setUpdtime(Date updtime) {
        this.updtime = updtime;
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
    
}