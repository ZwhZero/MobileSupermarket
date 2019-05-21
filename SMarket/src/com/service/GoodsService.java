package com.service;

import java.util.List;
import com.model.Goods;

public interface GoodsService {
	//查询全部
	List<Goods> goodsList();
	//查询（id）
	Goods findGoodsBygId(int gId);
	//查询(name)
	List<Goods> findGoodsByName(String gName);
	//查询(类别id)
	List<Goods> findGoodsBycId(int categoryId);
	//增加（id）
	int addGoods(Goods goods);
	//删除（id）
	int deleteGoods(int gId);
	//修改（id）
	int updateGoods(Goods goods);
	
}
