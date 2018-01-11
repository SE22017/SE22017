package com.cn.swm.service.stub;

import com.cn.swm.model.goods.Goods;
import com.cn.swm.model.sale.*;
import com.cn.swm.service.sale.SaleServiceI;

import java.util.List;

public class SaleServiceStub implements SaleServiceI{

    @Override
    public List<Customer> getCustomerList(Customer customer) {
        Customer c1 = new Customer();
        Customer c2 = new Customer();
        List<Customer> cl = null;
        cl.add(c1);
        cl.add(c2);
        return cl;
    }

    @Override
    public int update(Customer customer) {
        return 1;
    }

    @Override
    public int add(Customer customer) {
        return 10086;
    }

    @Override
    public boolean del(String customerId) {
        return false;
    }

    @Override
    public int addInputGood(InputGoodHander inputGoodHander) {
        return 2364;
    }

    @Override
    public int addInputGoodItem(InputGoodItem inputGoodItem) {
        return 333;
    }

    @Override
    public List<InputGoodHander> getInputGoodList(InputGoodHander hander) {
        InputGoodHander h1 = new InputGoodHander();
        InputGoodHander h2 = new InputGoodHander();
        List<InputGoodHander> handlist = null;
        handlist.add(h1);
        handlist.add(h2);
        return handlist;
    }

    @Override
    public List<InputSaleGood> getSaleGoodList(InputSaleGood inputSaleGood) {
       List<InputSaleGood> salelist = null;
       salelist.add(inputSaleGood);
       return salelist;
    }

    @Override
    public int updateShop(Goods good) {
        return 111;
    }

    @Override
    public int InputSaleGood(InputSaleGood inputSaleGood) {
        return 222;
    }

    @Override
    public int InputSaleGoodItem(InputSaleGoodItem inputSaleGoodItem) {
        return 333;
    }

    @Override
    public List<Tbank> getAccountList(Tbank tbank) {
        Tbank b1 = tbank;
        Tbank b2 = new Tbank();
        List<Tbank> banklist = null;
        banklist.add(b1);
        banklist.add(b2);
        return banklist;
    }

    @Override
    public int updateAccount(Tbank tbank) {
        return 233;
    }

    @Override
    public int insertAccount(Tbank tbank) {
        return 111;
    }

    @Override
    public int addInput(InputHander inputHander) {
        return 222;
    }

    @Override
    public int addInputItem(InputItem inputItem) {
        return 333;
    }

    @Override
    public List<InputHander> getInputList(InputHander hander) {
        InputHander h1 = new InputHander();
        InputHander h2 = hander;
        InputHander h3 = new InputHander();
        List<InputHander> handlist = null;
        handlist.add(h1);
        handlist.add(h2);
        handlist.add(h3);
        return handlist;
    }

    @Override
    public int updateStatusInput(InputHander hander) {
        return 123;
    }

    @Override
    public int updateStatusSale(InputSaleGood InputSaleGood) {
        return 111;
    }

    @Override
    public int updateStatusInputSale(InputGoodHander hander) {
        return 222;
    }
}
