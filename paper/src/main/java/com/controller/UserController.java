package com.controller;

import com.controller.base.BaseController;
import com.pojo.eneity.Authority;
import com.pojo.eneity.College;
import com.pojo.eneity.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 用户控制层
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	@Autowired
	private UserService userService;

	/**
	 * 转发到登录页面
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/login")
	public String forwardLogin() throws Exception{
		return "user/user_login";
	}

	/**
	 * 登录验证
	 * @param username
	 * @param password
	 * @param auth
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/login")
	@ResponseBody
	public boolean login(String username, String password, Long auth) throws Exception{
		Authority authority = new Authority();
		authority.setId(auth);
		User user = new User();
		user.setCard(username);
		user.setAuthority(authority);
		User userByQuery = userService.getUserByQuery(user);
		if (userByQuery != null && userByQuery.getPassword() != null && userByQuery.getPassword().equals(password)) {
			session.setAttribute("user", userByQuery);
			return true;
		}
		return false;
	}

	/**
	 * 转发到登录页面
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/logout")
	public String logout() throws Exception{
		session.setAttribute("user", null);
		return "user/user_login";
	}
	@GetMapping("/myInfo")
	public String myInfo() {
		return "user/myInfo";
	}
	@PostMapping("/update")
	@ResponseBody
	public boolean update() throws Exception {
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String birthday = request.getParameter("birthday");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = format.parse(birthday);
		User user = (User) session.getAttribute("user");
		user.setName(name);
		user.setGender(gender);
		user.setBirthday(date);
		boolean b = userService.updateUser(user);
		if (b) {
			User userByQuery = userService.getUserByQuery(user);
			session.setAttribute("user", userByQuery);
		}
		return b;
	}

}
