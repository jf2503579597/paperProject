package com.controller;

import com.controller.base.BaseController;
import com.pojo.eneity.*;
import com.pojo.vo.PageVO;
import com.service.CollegeService;
import com.service.MajorService;
import com.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/teacher_infor")
public class TeacherInforController extends BaseController {
	@Autowired
	private MajorService majorService;
	@Autowired
	private CollegeService collegeService;
	@Autowired
	private ProfessorService professorService;
	/**
	 * 转发到专业信息维护页面
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/major")
	public String forwardMajorInfor() throws Exception {
		return "teacher_infor/major_infor";
	}
	/**
	 * 分页查询专业信息
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/majorPage")
	@ResponseBody
	public PageVO<Major> getPageVOByPage(Integer pageNum, Integer pageSize) throws Exception {
		PageVO<Major> pageVO = new PageVO<>(pageNum, pageSize);
		pageVO = majorService.getPageVOByPageVO(pageVO);
		return pageVO;
	}

	/**
	 * 转发到添加专业信息页面
	 * @return
	 */
	@GetMapping("/addMajor")
	public String forwardAdd() {
		return "teacher_infor/addMajor";
	}

	/**
	 * 异步获取所有学院信息
	 * @return
	 */
	@PostMapping("/getCollegeList")
	@ResponseBody
	public List<College> getCollegeList() {
		return collegeService.getCollege(new College());
	}

	/**
	 * 异步添加专业信息
	 * @return
	 */
	@PostMapping("addMajor")
	@ResponseBody
	public boolean addMajor(String majorName, Long id) throws Exception {
		Major major = new Major();
		major.setMajorName(majorName);
		major.setCollId(id);
		Major majorByName = majorService.getMajorByName(major);
		if (majorByName == null) {
			boolean b = majorService.saveMajor(major);
			return b;
		}
		return false;
	}

	/**
	 * 异步删除专业信息，包括这个专业下的所有教师
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@PostMapping("deleteMajor")
	@ResponseBody
	public boolean deleteMajor(Long id) throws Exception{
		Major major = new Major();
		major.setId(id);
		boolean flag = majorService.deleteMajor(major);
		return flag;
	}

	/**
	 * 转发到学院信息维护页面
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/college")
	public String forwardCollegeInfor() throws Exception {
		return "teacher_infor/college_infor";
	}
	/**
	 * 分页查询学院信息
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/collegePage")
	@ResponseBody
	public PageVO<College> getPageVOBySourcePage(Integer pageNum, Integer pageSize) throws Exception {
		PageVO<College> pageVO = new PageVO<>(pageNum, pageSize);
		pageVO = collegeService.getPageVOByPageVO(pageVO);
		return pageVO;
	}

	/**
	 * 转发到添加学院信息页面
	 * @return
	 */
	@GetMapping("/addCollege")
	public String forwardAddCollege() {
		return "teacher_infor/addCollege";
	}

	/**
	 * 异步添加学院信息
	 * @return
	 */
	@PostMapping("addCollege")
	@ResponseBody
	public boolean addCollege(String collegeName) throws Exception {
		College college = new College();
		college.setCollName(collegeName);
		// 先查询是否存在，不存在再添加
		College collegeByName = collegeService.getCollegeByName(college);
		if (collegeByName == null) {
			return collegeService.saveCollege(college);
		}
		return false;
	}

	/**
	 * 异步删除学院信息，包括这个学院下的所有专业与专业下的教师
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@PostMapping("deleteCollege")
	@ResponseBody
	public boolean deleteCollege(Long id) throws Exception{
		College college = new College();
		college.setId(id);
		boolean flag = collegeService.deleteCollege(college);
		return flag;
	}

	/**
	 * 转发到职称信息维护页面
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/professor")
	public String forwardProfessorInfor() throws Exception {
		return "teacher_infor/professor_infor";
	}

	/**
	 * 分页查询职称信息
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/professorPage")
	@ResponseBody
	public PageVO<Professor> getPageVOByProfessorPage(Integer pageNum, Integer pageSize) throws Exception {
		PageVO<Professor> pageVO = new PageVO<>(pageNum, pageSize);
		pageVO = professorService.getPageVOByPageVO(pageVO);
		return pageVO;
	}

	/**
	 * 转发到添加职称信息页面
	 * @return
	 */
	@GetMapping("/addProfessor")
	public String forwardAddSource() {
		return "teacher_infor/addProfessor";
	}

	/**
	 * 异步添加职称信息
	 * @return
	 */
	@PostMapping("addProfessor")
	@ResponseBody
	public boolean addProfessor(String professorName) throws Exception {
		Professor professor = new Professor();
		professor.setProfessorName(professorName);
		// 先查询是否存在，不存在再添加
		Professor professorByName = professorService.getProfessorByName(professor);
		if (professorByName == null) {
			return professorService.saveProfessor(professor);
		}
		return false;
	}

	/**
	 * 异步删除职称信息，包括这个职称下的所有教师
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@PostMapping("deleteProfessor")
	@ResponseBody
	public boolean deleteProfessor(Long id) throws Exception{
		Professor professor = new Professor();
		professor.setId(id);
		boolean flag = professorService.deleteProfessor(professor);
		return flag;
	}

	/**
	 * 异步获取学院信息，根据专业的学院id获取学院信息
	 * @param collegeId
	 * @return
	 * @throws Exception
	 */
	@PostMapping("getCollege")
	@ResponseBody
	public College getCollege(Long collegeId) throws Exception {
		College college = new College();
		college.setId(collegeId);
		College college1 = collegeService.getCollegeByName(college);
		return college1;
	}
}
