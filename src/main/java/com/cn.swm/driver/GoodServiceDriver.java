package com.cn.swm.driver;

import com.cn.swm.model.goods.Goods;
import com.cn.swm.service.goods.GoodServiceI;
import com.cn.swm.service.goods.imp.GoodsServiceImp;
import sun.net.idn.Punycode;

import java.util.List;
import java.util.Map;


public class GoodServiceDriver {
    GoodServiceI goodService = new GoodsServiceImp();

    public void testGoodsUpdate() {
        Goods goods = new Goods();
        goods.setSpname("hhh");
        goods.setSpnumber(12);
        System.out.println(goodService.update(goods));
    }

    public void testGetGoodsList(){
        Goods g1 = new Goods();
        g1.setSpname("abc");
        Goods g2 = new Goods();
        g2.setSpname("wahaha");
        List<Goods> list = null;
        list.add(g1);
        list.add(g2);
        System.out.println(goodService.getGoodList(g1));
        System.out.println(goodService.getGoodList(g2));
    }

    public void testaddMenu(){
        Goods goods = new Goods();
        goods.setSpname("hhh");
        goods.setSpnumber(2);
        System.out.println(goodService.addMenu(goods));
    }

    public void testDel(){
        Goods goods = new Goods();
        goods.setSpname("HHH");
        String goodsid = goods.getSpname();
        System.out.println(goodService.del(goodsid));
    }

    public void testGoodsList(){
        Goods goods = new Goods();
        goods.setSpname("dengpao");
        goods.setSpnumber(13);
        String name = goods.getSpname();
        Map<String,Object> map = null;
        System.out.println(goodService.goodList(map));

    }

    public void testGetGood(){
        Goods g1 = new Goods();
        g1.setSpname("abc");
        Goods g2 = new Goods();
        g2.setSpname("wahaha");
        List<Goods> list = null;
        list.add(g1);
        list.add(g2);
        System.out.println(goodService.getGood(g1));
        System.out.println(goodService.getGood(g2));
    }
}
