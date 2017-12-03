package com.zh.gytlv.service.front.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zh.gytlv.entity.Menu;
import com.zh.gytlv.mapper.front.FrontMenuMapper;
import com.zh.gytlv.service.front.FrontMenuService;

@Service("frontMenuService")
public class FrontMenuServiceImpl implements FrontMenuService{

	@Autowired
	private FrontMenuMapper frontMenuMapper;

	@Override
	public List<Menu> getFrontMenu() {
		return frontMenuMapper.getFrontMenu();
	}
	
	
}
