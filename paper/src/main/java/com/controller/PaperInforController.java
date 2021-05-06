package com.controller;

import com.controller.base.BaseController;
import com.pojo.eneity.Periodical;
import com.pojo.eneity.ProjectSource;
import com.pojo.eneity.Subject;
import com.pojo.vo.PageVO;
import com.service.PeriodicalService;
import com.service.ProjectSourceService;
import com.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 论文基本信息维护控制层
 */
@Controller
@RequestMapping("/paper_infor")
public class PaperInforController extends BaseController {
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private ProjectSourceService projectSourceService;
	@Autowired
	private PeriodicalService periodicalService;

	/**
	 * 转发到学科信息页面
	 * @return
	 */
	@GetMapping("/subject")
	public String forwardSubject() throws Exception {
		return "paper_infor/subject";
	}

	/**
	 * 分页查询学科信息
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/subPage")
	@ResponseBody
	public PageVO<Subject> getPageVOByPage(Integer pageNum, Integer pageSize) throws Exception {
		PageVO<Subject> pageVO = new PageVO<>(pageNum, pageSize);
		pageVO = subjectService.getPageVOByPageVO(pageVO);
		return pageVO;
	}

	/**
	 * 转发到添加学科信息页面
	 * @return
	 */
	@GetMapping("/addSubject")
	public String forwardAdd() {
		return "paper_infor/addSubject";
	}

	/**
	 * 异步添加学科信息
	 * @return
	 */
	@PostMapping("addSubject")
	@ResponseBody
	public boolean addSubject(String subName) throws Exception {
		Subject subject = new Subject();
		subject.setSubName(subName);
		Subject subjectBySubject = subjectService.getSubjectBySubject(subject);
		if (subjectBySubject == null) {
			boolean b = subjectService.saveSubject(subject);
			return b;
		}
		return false;
	}

	/**
	 * 异步删除学科信息，包括这个学科下的所有论文
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@PostMapping("deleteSubject")
	@ResponseBody
	public boolean deleteSubject(Long id) throws Exception{
		Subject subject = new Subject();
		subject.setId(id);
		boolean flag = subjectService.deleteSubject(subject);
		return flag;
	}

	/**
	 * 转发到项目来源维护页面
	 * @return
	 */
	@GetMapping("project_source")
	public String forwardSource() {
		return "paper_infor/projectSource";
	}

	/**
	 * 分页查询项目来源信息
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/sourcePage")
	@ResponseBody
	public PageVO<ProjectSource> getPageVOBySourcePage(Integer pageNum, Integer pageSize) throws Exception {
		PageVO<ProjectSource> pageVO = new PageVO<>(pageNum, pageSize);
		pageVO = projectSourceService.getPageVOByPageVO(pageVO);
		return pageVO;
	}

	/**
	 * 转发到添加项目来源页面
	 * @return
	 */
	@GetMapping("/addSource")
	public String forwardAddSource() {
		return "paper_infor/addSource";
	}

	/**
	 * 异步添加项目来源信息
	 * @return
	 */
	@PostMapping("addSource")
	@ResponseBody
	public boolean addSource(String sourceName) throws Exception {
		ProjectSource projectSource = new ProjectSource();
		projectSource.setSourceName(sourceName);
		// 先查询是否存在，不存在再添加
		ProjectSource source = projectSourceService.getProjectSourceBySource(projectSource);
		if (source == null) {
			return projectSourceService.saveProjectSource(projectSource);
		}
		return false;
	}

	/**
	 * 异步删除项目来源信息，包括这个项目来源下的所有论文
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@PostMapping("deleteSource")
	@ResponseBody
	public boolean deleteSource(Long id) throws Exception{
		ProjectSource projectSource = new ProjectSource();
		projectSource.setId(id);
		boolean flag = projectSourceService.deleteProjectSource(projectSource);
		return flag;
	}

	/**
	 * 转发到期刊类别维护页面
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/period")
	public String forwardPeriod() throws Exception {
		return "paper_infor/periodical";
	}

	/**
	 * 分页查询期刊类别信息
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/periodPage")
	@ResponseBody
	public PageVO<Periodical> getPageVOByPeriodicalPage(Integer pageNum, Integer pageSize) throws Exception {
		PageVO<Periodical> pageVO = new PageVO<>(pageNum, pageSize);
		// 查询
		pageVO = periodicalService.getPageVOByPageVO(pageVO);
		return pageVO;
	}

	/**
	 * 转发到添加期刊类别页面
	 * @return
	 */
	@GetMapping("/addPeriodical")
	public String forwardAddPeriodical() {
		return "paper_infor/addPeriodical";
	}

	/**
	 * 异步添加期刊类别信息
	 * @return
	 */
	@PostMapping("addPeriodical")
	@ResponseBody
	public boolean addPeriodical(String periodName) throws Exception {
		// 先查询是否存在，不存在则添加
		Periodical periodical = new Periodical();
		periodical.setPeriodName(periodName);
		// 先查询是否存在，不存在再添加
		Periodical periodical1 = periodicalService.getPeriodicalByName(periodical);
		if (periodical1 == null) {
			return periodicalService.savePeriodical(periodical);
		}
		return false;
	}

	/**
	 * 异步删除期刊类别信息，包括这个期刊类别下的所有论文
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@PostMapping("deletePeriodical")
	@ResponseBody
	public boolean deletePeriodical(Long id) throws Exception{
		// 在该刊物类别下的所有论文一起删除
		Periodical periodical = new Periodical();
		periodical.setId(id);
		boolean flag = periodicalService.deletePeriodical(periodical);
		return flag;
	}
}
