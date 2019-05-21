package com.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.model.Goods;

@Repository
public interface GoodsMapper { //以接口形式定义数据库操作方法
	//查询全部
	List<Goods> goodsList();
	//查询（id）
	Goods findGoodsBygId(int gId);
	//查询(name),模糊查询返回list
	List<Goods> findGoodsByName(@Param("gName")String gName);
	//查询全部(类别id)
	List<Goods> findGoodsBycId(int categoryId);
	//增加（id）
	int addGoods(Goods goods);
	//删除（id）
	int deleteGoods(int gId);
	//修改（id）
	int updateGoods(Goods goods);
	
}
