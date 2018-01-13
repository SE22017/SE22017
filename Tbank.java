package com.cn.swm.model.sale;

public class Tbank {
    private String id;

    private String account;

    private String name;

    private String flag;

    private String num;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}
    
}