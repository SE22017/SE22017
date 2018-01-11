package com.cn.swm.service.stub;

import com.cn.swm.model.goods.Goods;
import com.cn.swm.service.goods.GoodServiceI;

import java.util.List;
import java.util.Map;


public class GoodServiceStub implements GoodServiceI {
    @Override
    public List<Goods> getGoodList(Goods goods) {
        List<Goods> goodslist = null;
        Goods good1 = new Goods();
        Goods good2 = new Goods();
        goodslist.add(good1);
        goodslist.add(good2);
        return goodslist ;
    }

    @Override
    public int update(Goods goods) {
        return 1;
    }

    @Override
    public int addMenu(Goods goods) {
        return 1;
    }

    @Override
    public boolean del(String goodsId) {
        return false;
    }

    @Override
    public List<Goods> goodList(Map<String, Object> param) {
        return null;
    }

    @Override
    public Goods getGood(Goods good) {
        Goods goods = new Goods();
        return goods;
    }
}
