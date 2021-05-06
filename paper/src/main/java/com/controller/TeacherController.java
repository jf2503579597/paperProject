package com.controller;

import com.controller.base.BaseController;
import com.pojo.eneity.*;
import com.pojo.vo.PageVO;
import com.service.CollegeService;
import com.service.MajorService;
import com.service.ProfessorService;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 教师功能控制层
 */
@Controller
@RequestMapping("/teacher")
public class TeacherController extends BaseController {
	@Autowired
	private UserService userService;

	@Autowired
	private ProfessorService professorService;

	@Autowired
	private CollegeService collegeService;

	@Autowired
	private MajorService majorService;
	/**
	 * 跳转到添加教师页面
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/add_teacher")
	public String forwardAddTeacher() throws Exception {
		// 获取最后一个工号加1
		List<User> userList = userService.getUserList();
		User user = userList.get(userList.size() - 1);
		String card = String.valueOf((Integer.parseInt(user.getCard()) + 1));
		request.setAttribute("card", card);
		System.out.println(card);
		return "teacher/addTeacher";
	}

	/**
	 * 获取职称信息
	 * @return
	 */
	@PostMapping("/getProfessor")
	@ResponseBody
	public List<Professor> getProfessor() throws Exception {
		List<Professor> list = professorService.getProfessor();
		return list;
	}
	/**
	 * 获取学院信息
	 * @return
	 */
	@PostMapping("/getCollege")
	@ResponseBody
	public List<College> getCollege() throws Exception  {
		List<College> list = collegeService.getCollege(new College());
		return list;
	}
	/**
	 * 获取学院对应的专业信息，需要传递一个 学院id
	 * @return
	 */
	@PostMapping("/getMajor")
	@ResponseBody
	public List<Major> getMajor(Long collId) throws Exception  {
		List<Major> list = majorService.getMajor(collId);
		return list;
	}

	@PostMapping("/addTeacher")
	@ResponseBody
	public boolean addTeacher() throws Exception {
		String card = request.getParameter("card");             // 工号
		String password = request.getParameter("password");     // 密码
		String name = request.getParameter("name");             // 姓名
		String gender = request.getParameter("gender");         // 性别
		String birthday = request.getParameter("birthday");     // 生日
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = format.parse(birthday);
		Long professor = Long.parseLong(request.getParameter("professor"));   // 职称
		Long college = Long.parseLong(request.getParameter("college"));       // 学院
		Long major = Long.parseLong(request.getParameter("major"));           // 专业
		Long authority = Long.parseLong(request.getParameter("authority"));   // 权限
		User user = new User();
		user.setCard(card);
		user.setPassword(password);
		user.setName(name);
		user.setGender(gender);
		user.setBirthday(date);
		// 由于存储只需要存储主键，所以不需要赋值
		user.setProfessor(new Professor(professor, null));
		user.setCollege(new College(college, null));
		user.setMajor(new Major(major, null, college));
		user.setAuthority(new Authority(authority, null));
		boolean b = userService.saveUser(user);
		return b;
	}

	/**
	 * 转发到查询教师页面
	 * @return
	 */
	@GetMapping("/find_teacher")
	public String forwardFindTeacher() {
		return "teacher/findTeacher";
	}

	/**
	 * 异步获取教师分页信息
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@PostMapping("/teacherPage")
	@ResponseBody
	public PageVO<User> getUserByPageVO(Integer pageNum, Integer pageSize) throws Exception  {
		PageVO<User> pageVO = new PageVO<>();
		pageVO.setPageNum(pageNum);
		pageVO.setPageSize(pageSize);
		pageVO = userService.getUserByPage(pageVO);
		return pageVO;
	}

	/**
	 * 删除用户
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/deleteTeacher")
	@ResponseBody
	public boolean deleteTeacher() throws Exception {
		// 删除用户
		Long id = Long.parseLong(request.getParameter("id"));
		User user = new User();
		user.setId(id);
		boolean flag = userService.deleteUser(user);
		return flag;
	}

	/**
	 * 转发到用户详情页面
	 * @return
	 */
	@GetMapping("/details")
	public String forwardDetails(Long id) throws Exception {
		User user = new User();
		user.setId(id);
		User userByQuery = userService.getUserByQuery(user);
		request.setAttribute("userById", userByQuery);
		return "teacher/details";
	}
}
