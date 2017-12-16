package com.zh.gytlv.controller.front;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zh.gytlv.entity.Article;
import com.zh.gytlv.entity.Menu;
import com.zh.gytlv.service.front.FrontMenuService;
import com.zh.gytlv.service.front.IndexService;

import utils.IpUtil;

@Controller
public class IndexController {
	
	@Autowired
	private FrontMenuService frontMenuService;
	
	@Autowired
	private IndexService indexService;

	@RequestMapping("/news")
	@ResponseBody
	public List<Article> getNewArticle() {
		return indexService.getNewArticle();
	}

	@RequestMapping("/tops")
	@ResponseBody
	public List<Article> getTopArticle() {
		return indexService.getTopArticle();
	}

	@RequestMapping("/clicks")
	@ResponseBody
	public List<Article> getClickArticle() {
		return indexService.getClickArticle();
	}

	/**
	 * 文章详情
	 * 
	 * @param id
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/articleDetail")
	public ModelAndView getArticleDetail(HttpServletRequest request, String id) throws Exception {
		ModelAndView mv=new ModelAndView("front/details");
		List<Menu> ms = frontMenuService.getFrontMenu();
		mv.addObject("ms", ms);
		String visitorIp=IpUtil.getClientIp(request);
		Article article = indexService.getArticleDetail(id,visitorIp);
		mv.addObject("article", article);
		return mv;
	}
}
