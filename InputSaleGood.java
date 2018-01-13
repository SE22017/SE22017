package com.cn.swm.model.sale;

public class InputSaleGood {
    private String id;

    private String code;

    private String customer;

    private String saleman;

    private String addby;

    private Double amount;

    private Double dist;

    private Double distmoney;

    private Double distamount;

    private String note;

    private String flag;

    private String ioflag;
    
    private String shop;
    
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer == null ? null : customer.trim();
    }

    public String getSaleman() {
        return saleman;
    }

    public void setSaleman(String saleman) {
        this.saleman = saleman == null ? null : saleman.trim();
    }

    public String getAddby() {
        return addby;
    }

    public void setAddby(String addby) {
        this.addby = addby == null ? null : addby.trim();
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getDist() {
        return dist;
    }

    public void setDist(Double dist) {
        this.dist = dist;
    }

    public Double getDistmoney() {
        return distmoney;
    }

    public void setDistmoney(Double distmoney) {
        this.distmoney = distmoney;
    }

    public Double getDistamount() {
        return distamount;
    }

    public void setDistamount(Double distamount) {
        this.distamount = distamount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }

    public String getIoflag() {
        return ioflag;
    }

    public void setIoflag(String ioflag) {
        this.ioflag = ioflag == null ? null : ioflag.trim();
    }

	public String getShop() {
		return shop;
	}

	public void setShop(String shop) {
		this.shop = shop;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
    
}