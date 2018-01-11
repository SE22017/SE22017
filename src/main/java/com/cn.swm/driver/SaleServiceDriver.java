package com.cn.swm.driver;

import com.cn.swm.model.goods.Goods;
import com.cn.swm.model.sale.Customer;
import com.cn.swm.model.sale.Input;
import com.cn.swm.model.sale.InputGoodHander;
import com.cn.swm.model.sale.InputHander;
import com.cn.swm.service.sale.SaleServiceI;
import com.cn.swm.service.sale.iml.SaleServiceIml;


public class SaleServiceDriver {
    SaleServiceI saleServiceI = new SaleServiceIml();

    public void testGetCustomerList(){
        Customer c = new Customer();
        c.setAddby("abc");
        c.setCusleavel("level1");
        System.out.println(saleServiceI.getCustomerList(c));
    }

    public void testUpdate(){
        Customer c = new Customer();
        c.setAddby("abc");
        c.setCusleavel("level1");
        System.out.println(saleServiceI.update(c));
    }

    public void testAdd(){
        Customer c = new Customer();
        c.setAddby("abc");
        c.setCusleavel("level1");
        System.out.println(saleServiceI.add(c));
    }
    public void testDel(){
        Customer c = new Customer();
        String id = c.getCusname();
        System.out.println(saleServiceI.del(id));
    }

    public void testAddInputGood(){
        InputGoodHander in = new InputGoodHander();
        in.setAmount("50");
        in.setFlag("enough");
        System.out.println(saleServiceI.addInputGood(in));
    }

    public void testUpdateShop(){
        Goods good = new Goods();
        good.setSpname("ADB");
        good.setSpprcezj("sp");
        System.out.println(saleServiceI.updateShop(good));
    }

    public void testGetInputList(){
        InputHander hand = new InputHander();
        hand.getAmount();
        hand.getCode();
        hand.setAmount("amount");
        hand.setFlag("Flag");
        hand.setId("newID");
        System.out.println(saleServiceI.getInputList(hand));
    }

    public void testUpdateStatusInputSale(){
        InputGoodHander goodHander = new InputGoodHander();
        goodHander.setFlag("flag");
        goodHander.setAmount("1000");
        goodHander.setNote("nothing");
        goodHander.getAmount();
        goodHander.getId();
        goodHander.getAmount();
        goodHander.getShop();
        System.out.println(saleServiceI.updateStatusInputSale(goodHander));
    }
}
