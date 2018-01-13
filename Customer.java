package com.cn.swm.model.sale;

import java.util.Date;

public class Customer {
    private String cuscode;

    private String custype;

    private String cusleavel;

    private String cusname;

    private String cusphone;

    private String cusaddress;

    private String cuspost;

    private String cusmail;

    private String cuslimit;

    private String cusinput;

    private String cusout;

    private String cusmanname;

    private String addby;

    private Date addtime;

    private String updby;

    private Date updtime;

    public String getCuscode() {
        return cuscode;
    }

    public void setCuscode(String cuscode) {
        this.cuscode = cuscode == null ? null : cuscode.trim();
    }

    public String getCustype() {
        return custype;
    }

    public void setCustype(String custype) {
        this.custype = custype == null ? null : custype.trim();
    }

    public String getCusleavel() {
        return cusleavel;
    }

    public void setCusleavel(String cusleavel) {
        this.cusleavel = cusleavel == null ? null : cusleavel.trim();
    }

    public String getCusname() {
        return cusname;
    }

    public void setCusname(String cusname) {
        this.cusname = cusname == null ? null : cusname.trim();
    }

    public String getCusphone() {
        return cusphone;
    }

    public void setCusphone(String cusphone) {
        this.cusphone = cusphone == null ? null : cusphone.trim();
    }

    public String getCusaddress() {
        return cusaddress;
    }

    public void setCusaddress(String cusaddress) {
        this.cusaddress = cusaddress == null ? null : cusaddress.trim();
    }

    public String getCuspost() {
        return cuspost;
    }

    public void setCuspost(String cuspost) {
        this.cuspost = cuspost == null ? null : cuspost.trim();
    }

    public String getCusmail() {
        return cusmail;
    }

    public void setCusmail(String cusmail) {
        this.cusmail = cusmail == null ? null : cusmail.trim();
    }

    public String getCuslimit() {
        return cuslimit;
    }

    public void setCuslimit(String cuslimit) {
        this.cuslimit = cuslimit == null ? null : cuslimit.trim();
    }

    public String getCusinput() {
        return cusinput;
    }

    public void setCusinput(String cusinput) {
        this.cusinput = cusinput == null ? null : cusinput.trim();
    }

    public String getCusout() {
        return cusout;
    }

    public void setCusout(String cusout) {
        this.cusout = cusout == null ? null : cusout.trim();
    }

    public String getCusmanname() {
        return cusmanname;
    }

    public void setCusmanname(String cusmanname) {
        this.cusmanname = cusmanname == null ? null : cusmanname.trim();
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
}