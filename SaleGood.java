package com.cn.swm.model.sale;

import java.util.List;

public class SaleGood {
private InputSaleGood hander;
private List<InputSaleGoodItem> items;
public InputSaleGood getHander() {
	return hander;
}
public void setHander(InputSaleGood hander) {
	this.hander = hander;
}
public List<InputSaleGoodItem> getItems() {
	return items;
}
public void setItems(List<InputSaleGoodItem> items) {
	this.items = items;
}

}
