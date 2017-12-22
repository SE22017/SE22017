package com.cn.swm.service.goods.imp;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.swm.dao.goods.GoodsMapper;
import com.cn.swm.model.goods.Goods;
import com.cn.swm.model.leave.Tleave;
import com.cn.swm.service.goods.GoodServiceI;
@Service("goodService") 
public class GoodsServiceImp implements GoodServiceI {
	@Resource  
	private GoodsMapper goodsMapper;
	public List<Goods> getGoodList(Goods goods){
		return goodsMapper.getGoodsList(goods);	
	}
	public int update(Goods goods){
		return goodsMapper.updateGoods(goods);
	}

	public int addMenu(Goods goods){
		return goodsMapper.insert(goods);
	}
	public boolean del(String  spcode){
		return goodsMapper.del(spcode);	
	}
	public List<Goods> goodList(Map<String, Object> param){
		
		return goodsMapper.goodList(param);
	}
	public Goods getGood(Goods good){
		return goodsMapper.getGood(good);
	}
}
