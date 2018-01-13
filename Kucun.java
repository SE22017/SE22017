package com.cn.swm.model.goods;

import java.math.BigDecimal;
import java.util.Date;

public class Kucun {
	private String id;
	private String goodName;
	private Integer number;
	private Date date;
	
	 public String getId() {
	        return id;
	    }

	    public void setId(String id) {
	        this.id = id == null ? null : id.trim();
	    }
	    
	    public String getgoodName() {
	        return goodName;
	    }

	    public void setgoodName(String goodName) {
	        this.goodName = goodName == null ? null : goodName.trim();
	    }
	    
	    public Integer getNumber(){
	    	return number;
	    }
	    
	    public void setNumber(Integer number){
	    	this.number = number;
	    }
	    
	    public java.sql.Date getDate() {
	        return (java.sql.Date) date;
	    }

	    public void setDate(Date date) {
	        this.date = date;
	    }
	
}