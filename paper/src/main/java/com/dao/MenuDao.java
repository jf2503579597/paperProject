package com.dao;

import com.pojo.eneity.Menu;

import java.util.List;

/**
 * 菜单信息数据持久层
 */
public interface MenuDao {
	/**
	 * 根据权限查询其对应的菜单功能
	 * @return
	 * @throws Exception
	 */
	List<Menu> findMenuListByAuthId(Long id) throws Exception;
}
