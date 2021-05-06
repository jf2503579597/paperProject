package com.service;

import com.pojo.eneity.Menu;

import java.util.List;

/**
 * 菜单业务层接口
 */
public interface MenuService {
	/**
	 * 根据权限 id 获取对应的菜单集合
	 * @param id
	 * @return
	 * @throws Exception
	 */
	List<Menu> getMenuByAuthId(Long id) throws Exception;
}
