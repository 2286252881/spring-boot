package com.zh.gytlv.controller.front;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zh.gytlv.entity.Menu;
import com.zh.gytlv.service.front.FrontMenuService;
@Controller
public class PageController {

	@Autowired
	private FrontMenuService frontMenuService;
	
	@RequestMapping("/")
	public ModelAndView showIndex(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("front/index");
		List<Menu> ms = frontMenuService.getFrontMenu();
		mv.addObject("ms", ms);
		mv.addObject("selected", "/");
		return mv;
	}
	
	@RequestMapping("/{page}")
	public ModelAndView showPage(HttpServletRequest request,@PathVariable String page) {
		ModelAndView mv = new ModelAndView("front/"+page);
		List<Menu> ms = frontMenuService.getFrontMenu();
		mv.addObject("ms", ms);
		mv.addObject("selected", page);
		return mv;
	}
	
	@RequestMapping("/404")
	public String showPage() {
		return "404";
	}
}
