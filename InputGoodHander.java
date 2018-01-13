package com.cn.swm.model.sale;

import java.util.List;

public class InputGoodHander {
    private String id;

    private String code;

    private String supply;

    private String shop;

    private String person;

    private String note;

    private String amount;

    private String flag;
    
    private String ioflag;
    
    private String status;
    
    private List<InputGoodItem> items; 
    
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

    public String getSupply() {
        return supply;
    }

    public void setSupply(String supply) {
        this.supply = supply == null ? null : supply.trim();
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop == null ? null : shop.trim();
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person == null ? null : person.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount == null ? null : amount.trim();
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }

	public List<InputGoodItem> getItems() {
		return items;
	}

	public void setItems(List<InputGoodItem> items) {
		this.items = items;
	}

	public String getIoflag() {
		return ioflag;
	}

	public void setIoflag(String ioflag) {
		this.ioflag = ioflag;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
    
}