package com.cn.swm.service.sale;

import java.util.List;

import com.cn.swm.model.goods.Goods;
import com.cn.swm.model.sale.Customer;
import com.cn.swm.model.sale.InputGoodHander;
import com.cn.swm.model.sale.InputGoodItem;
import com.cn.swm.model.sale.InputHander;
import com.cn.swm.model.sale.InputItem;
import com.cn.swm.model.sale.InputSaleGood;
import com.cn.swm.model.sale.InputSaleGoodItem;
import com.cn.swm.model.sale.Tbank;

public interface SaleServiceI {
	
	List<Customer> getCustomerList(Customer customer);

	int update(Customer customer);

	int add(Customer customer);
	
	boolean del(String  customerId);
	
	int addInputGood(InputGoodHander inputGoodHander);
	
	int addInputGoodItem(InputGoodItem inputGoodItem);
	
	List<InputGoodHander> getInputGoodList(InputGoodHander hander);
	
	List<InputSaleGood> getSaleGoodList(InputSaleGood inputSaleGood);
	
	int updateShop(Goods good);
	
	int InputSaleGood(InputSaleGood inputSaleGood);
	
	int InputSaleGoodItem( InputSaleGoodItem inputSaleGoodItem);
	
	List<Tbank> getAccountList(Tbank tbank);
	
	int updateAccount(Tbank tbank);
	
	int insertAccount(Tbank tbank);
	
	int addInput(InputHander inputHander);
	
	int addInputItem(InputItem inputItem);
	
	List<InputHander> getInputList(InputHander hander);
	
	int updateStatusInput(InputHander hander);
	
	int updateStatusSale(InputSaleGood InputSaleGood);
	
	int updateStatusInputSale(InputGoodHander hander);
}
