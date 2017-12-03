package com.zh.gytlv.controller.back;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zh.gytlv.entity.Ztree;
import com.zh.gytlv.service.back.UserService;

@Controller
public class BackManagerController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/toManager")
	public String toManager(HttpServletRequest request, Map<String, Object> map) {
		List<Ztree> allNodes = userService.getAllNodes();
		map.put("nodes", allNodes);
		return "back/manager";
	}
	
	@RequestMapping("/{tab}/{id}")
	public String showTabs(@PathVariable String tab,HttpServletRequest request){
		return "back/tabs/"+tab;
	}
	
	@RequestMapping("/noAuthc")
	public String noAuthc(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return "/400";
	}
}
