package com.cn.swm.service.sale.iml;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.swm.dao.goods.GoodsMapper;
import com.cn.swm.dao.sale.CustomerMapper;
import com.cn.swm.dao.sale.InputGoodHanderMapper;
import com.cn.swm.dao.sale.InputGoodItemMapper;
import com.cn.swm.dao.sale.InputHanderMapper;
import com.cn.swm.dao.sale.InputItemMapper;
import com.cn.swm.dao.sale.InputSaleGoodItemMapper;
import com.cn.swm.dao.sale.InputSaleGoodMapper;
import com.cn.swm.dao.sale.TbankMapper;
import com.cn.swm.model.goods.Goods;
import com.cn.swm.model.sale.Customer;
import com.cn.swm.model.sale.InputGoodHander;
import com.cn.swm.model.sale.InputGoodItem;
import com.cn.swm.model.sale.InputHander;
import com.cn.swm.model.sale.InputItem;
import com.cn.swm.model.sale.InputSaleGood;
import com.cn.swm.model.sale.InputSaleGoodItem;
import com.cn.swm.model.sale.Tbank;
import com.cn.swm.service.sale.SaleServiceI;
@Service("SaleService") 
public class SaleServiceIml implements SaleServiceI {
	@Resource  
	private CustomerMapper customerMapper;
	@Resource  
	private InputGoodHanderMapper inputGoodHanderMapper;
	@Resource  
	private InputGoodItemMapper inputGoodItemMapper;
	@Resource  
	private GoodsMapper goodsMapper;
	@Resource  
	private InputSaleGoodMapper inputSaleGoodMapper;
	@Resource  
	private InputSaleGoodItemMapper inputSaleGoodItemMapper;
	@Resource
	private TbankMapper tbankMapper;
	@Resource
	private InputHanderMapper inputHanderMapper;
	@Resource
	private InputItemMapper inputItemMapper;
	public List<Customer> getCustomerList(Customer Customer){
		return customerMapper.getCustomerList(Customer);	
	}
	public int update(Customer Customer){
		return customerMapper.updateCustomer(Customer);
	}

	public int add(Customer Customer){
		return customerMapper.insert(Customer);
	}
	public boolean del(String  spcode){
		return customerMapper.del(spcode);	
	}
	public List<InputGoodHander> getInputGoodList(InputGoodHander hander){
		return inputGoodHanderMapper.getInputGoodList(hander);	
	}
	public int addInputGood(InputGoodHander inputGoodHander){
		return inputGoodHanderMapper.insert(inputGoodHander);
	}
	public int addInputGoodItem(InputGoodItem inputGoodItem){
		
		return inputGoodItemMapper.insert(inputGoodItem);
	}
	public  int updateShop(Goods good){
		return goodsMapper.updateShop(good);
				
	}
	public List<InputSaleGood> getSaleGoodList(InputSaleGood InputSaleGood){
		return inputSaleGoodMapper.getInputGoodList(InputSaleGood);	
	}
	public int InputSaleGood(InputSaleGood inputSaleGood){
		return inputSaleGoodMapper.insert(inputSaleGood);	
	}
	public int InputSaleGoodItem( InputSaleGoodItem inputSaleGoodItem){
		
		return inputSaleGoodItemMapper.insert(inputSaleGoodItem);
	}
	public List<Tbank> getAccountList(Tbank tbank){
		
		return tbankMapper.getAccountList(tbank);
	}
	public int updateAccount(Tbank tbank){
		return tbankMapper.updateAccount(tbank);
	}
	public int insertAccount(Tbank tbank){
		return tbankMapper.insert(tbank);	
	}
	public int addInput(InputHander inputHander){
		return inputHanderMapper.insert(inputHander);
	}
	public int addInputItem(InputItem inputItem){
		return inputItemMapper.insert(inputItem);
	}
	public List<InputHander> getInputList(InputHander hander){
		return inputHanderMapper.getInputList(hander);
	}
	public int updateStatusInput(InputHander hander){
		return inputHanderMapper.updateByPrimaryKey(hander);
	}
	public int updateStatusSale(InputSaleGood InputSaleGood){
		return inputSaleGoodMapper.updateByPrimaryKey(InputSaleGood);
	}
	public int updateStatusInputSale(InputGoodHander hander){
		return inputGoodHanderMapper.updateByPrimaryKey(hander);
	}
}
