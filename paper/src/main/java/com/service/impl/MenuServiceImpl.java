package com.service.impl;

import com.dao.MenuDao;
import com.pojo.eneity.Menu;
import com.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单业务层实现类
 */
@Service
@Transactional
public class MenuServiceImpl implements MenuService {
	@Autowired
	private MenuDao menuDao;
	/**
	 * 根据权限 id 获取对应的菜单集合
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Menu> getMenuByAuthId(Long id) throws Exception {
		// 首先获取该权限对应的所有菜单功能，其中包括一级菜单和二级菜单
		List<Menu> menuListByAuthId = menuDao.findMenuListByAuthId(id);
		List<Menu> menuList = new ArrayList<>();
		// 遍历获取的集合，首先给新集合添加一级菜单，然后给一级菜单的 childMenus 属性添加其对应的二级菜单
		for (Menu menu : menuListByAuthId) {
			if (menu.getParentMenu().getMenuName() == null) {
				// 获取到一级菜单，再次遍历该一级菜单对应的二级菜单
				List<Menu> childMenuList = new ArrayList<>();
				for (Menu menu1 : menuListByAuthId) {
					// 判断二级菜单的上级菜单的菜单名称是否与当前一级菜单的菜单名称一致
					if (menu.getMenuName().equals(menu1.getParentMenu().getMenuName())) {
						childMenuList.add(menu1);
					}
				}
				// 遍历完成后，将当前一级菜单对应的二级菜单添加进去
				menu.setChildMenus(childMenuList);
				// 再将一级菜单添加到返回的结果中
				menuList.add(menu);
			}
		}
		return menuList;
	}
}
