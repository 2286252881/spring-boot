package com.zh.gytlv.mapper.front;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.zh.gytlv.entity.Menu;

public interface FrontMenuMapper {
	/**
	 * 获取主页菜单
	 * 
	 * @return
	 */
	@Select("select * from t_menu where type='nav'")
	public List<Menu> getFrontMenu();
}
