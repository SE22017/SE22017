package com.cn.swm.model.sale;

import java.util.List;

public class InputGood {
private InputGoodHander hander;
private List<InputGoodItem> items;
public InputGoodHander getHander() {
	return hander;
}
public void setHander(InputGoodHander hander) {
	this.hander = hander;
}
public List<InputGoodItem> getItems() {
	return items;
}
public void setItems(List<InputGoodItem> items) {
	this.items = items;
}

}
