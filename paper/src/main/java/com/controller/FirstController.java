package com.controller;

import com.controller.base.BaseController;
import com.pojo.eneity.Paper;
import com.pojo.eneity.User;
import com.service.PaperService;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 首页控制层
 */
@Controller
@RequestMapping("/first")
public class FirstController extends BaseController {
	@Autowired
	private UserService userService;
	@Autowired
	private PaperService paperService;
	/**
	 * 转发到修改密码页面
	 * @return
	 */
	@GetMapping("/update_password")
	public String forwardUpdatePassword() {
		return "/first/updatePassword";
	}

	/**
	 * 进行修改密码
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/updatePassword")
	@ResponseBody
	public boolean updatePassword(String oldPassword, String newPassword) throws Exception {
		User user = (User) session.getAttribute("user");
		user = userService.getUserByQuery(user);
		System.out.println(user);
		if (oldPassword.equals(user.getPassword())) {
			// 旧密码相同，进行修改密码
			user.setPassword(newPassword);
			boolean b = userService.updateUser(user);
			return b;
		}
		return false;
	}

	/**
	 * 转发到审核通知页面
	 * @return
	 */
	@GetMapping("/exam")
	public String exam() {
		return "first/exam";
	}

	/**
	 * 查询教师有多少论文未被审核
	 * @return
	 */
	@PostMapping("/teachExam")
	@ResponseBody
	public Integer teachExam() throws Exception {
		User user = (User) session.getAttribute("user");
		Paper paper = new Paper();
		paper.setUser(user);
		paper.setExam("未审核");
		List<Paper> paperList = paperService.getPaperByPaper(paper);
		return paperList.size();
	}

	/**
	 * 查询管理员还需要审核多少篇论文
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/adminExam")
	@ResponseBody
	public Integer adminExam() throws Exception {
		Paper paper = new Paper();
		paper.setExam("未审核");
		List<Paper> paperList = paperService.getPaperByPaper(paper);
		return paperList.size();
	}
}
