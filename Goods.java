package com.cn.swm.model.goods;

import java.math.BigDecimal;
import java.util.Date;

public class Goods {
    private String spcode;

    private String spname;

    private String spver;

    private Integer spnumber;

    private BigDecimal spprice;

    private String spprcezj;

    private String sppricels;

    private String sppricelszj;

    private String spcategory;

    private Date createtime;

    private String crateperson;

    public String getSpcode() {
        return spcode;
    }

    public void setSpcode(String spcode) {
        this.spcode = spcode == null ? null : spcode.trim();
    }

    public String getSpname() {
        return spname;
    }

    public void setSpname(String spname) {
        this.spname = spname == null ? null : spname.trim();
    }

    public String getSpver() {
        return spver;
    }

    public void setSpver(String spver) {
        this.spver = spver == null ? null : spver.trim();
    }

    public Integer getSpnumber() {
        return spnumber;
    }

    public void setSpnumber(Integer spnumber) {
        this.spnumber = spnumber;
    }

    public BigDecimal getSpprice() {
        return spprice;
    }

    public void setSpprice(BigDecimal spprice) {
        this.spprice = spprice;
    }

    public String getSpprcezj() {
        return spprcezj;
    }

    public void setSpprcezj(String spprcezj) {
        this.spprcezj = spprcezj == null ? null : spprcezj.trim();
    }

    public String getSppricels() {
        return sppricels;
    }

    public void setSppricels(String sppricels) {
        this.sppricels = sppricels == null ? null : sppricels.trim();
    }

    public String getSppricelszj() {
        return sppricelszj;
    }

    public void setSppricelszj(String sppricelszj) {
        this.sppricelszj = sppricelszj == null ? null : sppricelszj.trim();
    }

    public String getSpcategory() {
        return spcategory;
    }

    public void setSpcategory(String spcategory) {
        this.spcategory = spcategory == null ? null : spcategory.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getCrateperson() {
        return crateperson;
    }

    public void setCrateperson(String crateperson) {
        this.crateperson = crateperson == null ? null : crateperson.trim();
    }
}