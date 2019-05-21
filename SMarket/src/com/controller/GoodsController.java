package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.Goods;
import com.service.GoodsService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/goods")
public class GoodsController {
	@Autowired
	private GoodsService goodsService;
	
	//前台相关 1.前台商品List
	@RequestMapping("/fGoodsList")
    @ResponseBody
    public void fGoodsList(HttpServletResponse response,HttpServletRequest request) throws IOException {
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		List<Goods> goodsList = goodsService.goodsList();
		/*System.out.println(goodsList);*/
		JSONArray v = JSONArray.fromObject(goodsList);
		/*System.out.println("向前台传输:" + v);*/
		response.getWriter().print(v);
	}
	
	//2.通过id获取商品详细信息
	@RequestMapping("/fFindGoodsById")
    @ResponseBody
	public void fFindGoodsById(int gId,HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		Goods goods = goodsService.findGoodsBygId(gId);
//		JSONObject v = JSONObject.fromObject(goods);
		ObjectMapper mapper = new ObjectMapper();
		//java对象转json对象
		String jsongoods = mapper.writeValueAsString(goods);
		System.out.println("按id查询到的goods为：" + jsongoods);
		response.getWriter().print(jsongoods);
	}
	
	//3.通过categoryId获取指定类别商品信息list
	@RequestMapping("/fFindGoodsBycId")
    @ResponseBody
    public void fFindGoodsBycId(int categoryId,HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		List<Goods> cGoodsList = goodsService.findGoodsBycId(categoryId);
		JSONArray v = JSONArray.fromObject(cGoodsList);
//		System.out.println("向前台传输指定类别list:" + v);
		response.getWriter().print(v);
	}
	
	//4.通过用户输入内容搜索相关商品信息list
	@RequestMapping("/fFindGoodsBygName")
	@ResponseBody
	public void fFindGoodsBygName(String searchContent,HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		List<Goods> nGoodsList = goodsService.findGoodsByName(searchContent);
		if(nGoodsList != null && !nGoodsList.isEmpty()) { //查询到结果，返回查询得到的list 
			JSONArray v = JSONArray.fromObject(nGoodsList);
			System.out.println("查询结果为：" + v);
			response.getWriter().print(v);
		} else { //查询不到结果返回提示信息
			Map<String,String> map = new HashMap<>();
			map.put("result","false");
			JSONObject v = JSONObject.fromObject(map);
			response.getWriter().print(v);
		}
	}
	
	//后台相关 1.商品List
	@RequestMapping("/findAllGoods")
	public String findAllGoods(HttpServletRequest request) {
		List<Goods> goodsList = goodsService.goodsList();
		request.setAttribute("goodsList", goodsList);
		return "goodsList";
	}
	//2.按id更新指定商品信息
	@RequestMapping("/updateGoods")
	public void updateGoods(Goods goods,HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=null;
		if(goodsService.updateGoods(goods)!=0) {
			try {
				out = response.getWriter();
				out.print("<script>alert('修改信息成功！');window.location.href='http://localhost:8080/SMarket/goods/findAllGoods';</script>");
			}catch(IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				out.flush();
				out.close();
			}
		} else {
			try {
				out = response.getWriter();
				out.print("<script>alert('修改信息失败！');window.history.back(-1);</script>");
			}catch(IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				out.flush();
				out.close();
			}
		}
	}
	//3.按id删除指定商品信息
	@RequestMapping("/deleteGoods")
	public void deleteGoods(int gId,HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=null;
		if(goodsService.deleteGoods(gId)!=0) { /*删除成功*/
			try {
				out = response.getWriter();
				out.print("<script>alert('删除成功！');window.location.href='http://localhost:8080/SMarket/goods/findAllGoods';</script>");
			}catch(IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				out.flush();
				out.close();
			}
		} else { /*数据库执行删除操作失败*/
			try {
				out = response.getWriter();
				out.print("<script>alert('删除失败！数据库操作失败！');window.history.back(-1);</script>");
			}catch(IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				out.flush();
				out.close();
			}
		}
	}
	//4.添加商品addGoods
	@RequestMapping("/addGoods")
	public void addGoods(Goods goods,HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=null;
		if(goodsService.addGoods(goods)!=0) { /*添加成功*/
			try {
				out = response.getWriter();
				out.print("<script>alert('添加成功！');window.location.href='http://localhost:8080/SMarket/goods/findAllGoods';</script>");
			}catch(IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				out.flush();
				out.close();
			}
		} else { /*添加失败*/
			try {
				out = response.getWriter();
				out.print("<script>alert('添加商品失败！数据库操作失败！');window.history.back(-1);</script>");
			}catch(IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				out.flush();
				out.close();
			}
		}
		
	}
	
	
}
