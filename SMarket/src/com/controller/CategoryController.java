package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.Category;
import com.model.Goods;
import com.service.CategoryService;
import com.service.GoodsService;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private GoodsService goodsService;
	
	//前台相关：1.商品分类列表
	@RequestMapping("/fKindList")
    @ResponseBody
    public void fkindList(HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		List<Category> categoryList = categoryService.selectAllCategory();
		JSONArray v = JSONArray.fromObject(categoryList);
		System.out.println("类别列表json为：" + v);
		response.getWriter().print(v);
	}
	
	//后台相关----------------------------------------------------------------------
	//1.类别信息list
	@RequestMapping("/findAllCategory")
	public String findAllCategory(HttpServletRequest request) {
		List<Category> categoryList = categoryService.selectAllCategory();
		request.setAttribute("categoryList", categoryList);
		return "category";
	}
	
	//2.按id修改指定类别信息
	@RequestMapping("/updateCategory")
	public void updateCategory(Category category,HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=null;
		if(categoryService.updateCategory(category)!=0) {
			try {
				out = response.getWriter();
				out.print("<script>alert('修改信息成功！');window.location.href='http://localhost:8080/SMarket/category/findAllCategory';</script>");
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
	
	//3.删除指定类别（id）
	@RequestMapping("/deleteCategory")
	public void deleteCategory(int cId,HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=null;
		List<Category> childCategory = categoryService.selectCategoryByPid(cId);
		List<Goods> cGoods = goodsService.findGoodsBycId(cId);
		if(childCategory!=null && !childCategory.isEmpty()) { /*存在子类别，不可删除*/
			try {
				out = response.getWriter();
				out.print("<script>alert('删除失败！存在子类别，请先删除其子类别！');window.history.back(-1);</script>");
			}catch(IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				out.flush();
				out.close();
			}
		} else { /*无子类别*/
			if(cGoods!=null && !cGoods.isEmpty()) { /*无子类别，但类别内存在商品，不可删除*/
				try {
					out = response.getWriter();
					out.print("<script>alert('删除失败！类别内存在商品，不可删除！');window.history.back(-1);</script>");
				}catch(IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finally{
					out.flush();
					out.close();
				}
			} else { /*无子类别、无所属商品，允许删除*/
				if(categoryService.deleteCategory(cId)!=0) { /*删除成功*/
					try {
						out = response.getWriter();
						out.print("<script>alert('删除成功！');window.location.href='http://localhost:8080/SMarket/category/findAllCategory';</script>");
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
		}
	}
	
	//4.添加类别
	@RequestMapping("/addCategory")
	public void addCategory(Category category,HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=null;
		if(categoryService.addCategory(category)!=0) {
			try {
				out = response.getWriter();
				out.print("<script>alert('添加成功！');window.location.href='http://localhost:8080/SMarket/category/findAllCategory';</script>");
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
				out.print("<script>alert('添加类别失败！数据库操作失败！');window.history.back(-1);</script>");
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
