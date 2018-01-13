package com.cn.swm.model.sale;

import java.util.List;

public class Input {
	private InputHander hander;
	private List<InputItem> items;
	public InputHander getHander() {
		return hander;
	}
	public void setHander(InputHander hander) {
		this.hander = hander;
	}
	public List<InputItem> getItems() {
		return items;
	}
	public void setItems(List<InputItem> items) {
		this.items = items;
	}
}
