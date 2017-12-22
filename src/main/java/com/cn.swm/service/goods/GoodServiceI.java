package com.cn.swm.service.goods;

import java.util.List;
import java.util.Map;

import com.cn.swm.model.goods.Goods;
import com.cn.swm.model.leave.Tleave;

public interface GoodServiceI {

	List<Goods> getGoodList(Goods goods);

	int update(Goods goods);

	int addMenu(Goods goods);
	
	boolean del(String  goodsId);
	
	List<Goods> goodList(Map<String, Object> param);

	Goods getGood(Goods good);
}
