package com.controller;

import com.controller.base.BaseController;
import com.pojo.eneity.Menu;
import com.pojo.eneity.User;
import com.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 菜单控制层
 */
@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController {
	@Autowired
	private MenuService menuService;

	/**
	 * 根据权限获得菜单集合并转发出去
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/index")
	public String index() throws Exception {
		User user = (User) session.getAttribute("user");
		Long id = user.getAuthority().getId();
		List<Menu> menuByAuthId = menuService.getMenuByAuthId(id);
		request.setAttribute("menuList", menuByAuthId);
		return "menu/index";
	}
}
