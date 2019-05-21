package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapper.GoodsMapper;
import com.model.Goods;
import com.service.GoodsService;

@Service("GoodsService")
@Transactional
public class GoodsServiceImpl implements GoodsService {
	@Autowired
	public GoodsMapper goodsMapper;
	
	//查询全部
	public List<Goods> goodsList() {
		return goodsMapper.goodsList();
	}
	
	//查询（id）
	public Goods findGoodsBygId(int gId) {
		return goodsMapper.findGoodsBygId(gId);
	}
	
	//查询(name)
	public List<Goods> findGoodsByName(String gName) {
		return goodsMapper.findGoodsByName(gName);
	}
	
	//查询list(类别id)
	public List<Goods> findGoodsBycId(int categoryId) {
		return goodsMapper.findGoodsBycId(categoryId);
	}
	
	//增加（id）
	public int addGoods(Goods goods) {
		return goodsMapper.addGoods(goods);
	}
	
	//删除（id）
	public int deleteGoods(int gId) {
		return goodsMapper.deleteGoods(gId);
	}
	
	//修改（id）
	public int updateGoods(Goods goods) {
		return goodsMapper.updateGoods(goods);
	}
}
