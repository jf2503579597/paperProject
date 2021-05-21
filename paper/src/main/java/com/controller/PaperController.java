package com.controller;

import com.controller.base.BaseController;
import com.pojo.eneity.*;
import com.pojo.vo.PageVO;
import com.service.*;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

@MultipartConfig
@Controller
@RequestMapping("/paper")
public class PaperController extends BaseController {
	@Autowired
	private PaperService paperService;                      // 论文业务层
	@Autowired
	private PeriodicalService periodicalService;            // 期刊类别业务层
	@Autowired
	private SubjectService subjectService;                  // 学科业务层
	@Autowired
	private PeriodicalLevelService periodicalLevelService;  // 期刊级别表
	@Autowired
	private ProjectSourceService projectSourceService;      // 项目来源业务层
	@Autowired
	private CollegeService collegeService;                  // 学院信息业务层
	@Autowired
	private MajorService majorService;                      // 专业信息业务层
	@Autowired
	private ProfessorService professorService;              // 职称信息业务层

	/**
	 * 转发到上传页面
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/upload")
	public String forwardUploadPaper() throws Exception {
		List<Paper> paperByPaper = paperService.getPaperByPaper(new Paper());
		if (paperByPaper != null && !paperByPaper.isEmpty()) {
			Paper paper = paperByPaper.get(paperByPaper.size() - 1);
			Integer periodNo = Integer.parseInt(paper.getPeriodNo()) + 1;
			session.setAttribute("periodNo", periodNo);
		} else {
			session.setAttribute("periodNo", "17033100");
		}
		return "/paper/upload";
	}

	/**
	 * 获取类别、学科、期刊级别、项目来源、教师所在学院、教师所在专业、教师职称信息
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/getData")
	@ResponseBody
	public Map<String, Object> getData() throws Exception{
		Map<String, Object> map = new HashMap<>();
		List<Periodical> periodicalList = periodicalService.getPeriodical();
		map.put("periodicalList", periodicalList);
		List<Subject> subjectList = subjectService.getSubject();
		map.put("subjectList", subjectList);
		List<PeriodicalLevel> periodicalLevelList = periodicalLevelService.getPeriodicalLevel();
		map.put("periodicalLevelList", periodicalLevelList);
		List<ProjectSource> projectSourceList = projectSourceService.getProjectSource();
		map.put("projectSourceList", projectSourceList);
		List<College> collegeList = collegeService.getCollege(new College());
		map.put("collegeList", collegeList);
		List<Professor> professorList = professorService.getProfessor();
		map.put("professorList", professorList);
		return map;
	}

	/**
	 * 添加论文信息并上传文件
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/uploadFile")
	@ResponseBody
	public boolean uploadFile(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest request) throws Exception {
		// 服务器路径：System.getProperty("catalina.home")
		String fileStr = multipartFile.getOriginalFilename();
		System.out.println("文件旧名称：" + fileStr);
		/**
		 * 2. 使用随机生成的字符串+源图片扩展名组成新的图片名称,防止图片重名。
		 * “fileStr.substring(fileStr.lastIndexOf("."))”就是获取文件的后缀名。
		 */
		// 存储到服务器的 work 文件夹中
		String newFileName = "/work/" + UUID.randomUUID().toString() +
				fileStr.substring(fileStr.lastIndexOf("."));
		System.out.println("文件新名称：" + newFileName);
		// 新名称要存到数据库
		Paper paper = new Paper();
		// 添加下载地址
		paper.setDownloadAddress(newFileName);
		// 添加标题
		paper.setTitle(request.getParameter("title"));
		// 添加期号
		paper.setPeriodNo(request.getParameter("periodNoo"));
		System.out.println(request.getParameter("periodNoo"));
		// 添加期刊类别
		paper.setPeriodical(new Periodical(Long.parseLong(request.getParameter("periodical")), null));
		// 添加学科信息
		paper.setSubject(new Subject(Long.parseLong(request.getParameter("subject")), null));
		// 添加期刊级别
		paper.setPeriodicalLevel(new PeriodicalLevel(Long.parseLong(request.getParameter("periodLevel")), null));
		// 添加来源信息
		paper.setProjectSource(new ProjectSource(Long.parseLong(request.getParameter("source")), null));
		// 添加发布用户
		paper.setUser((User) session.getAttribute("user"));
		// 添加审核状态
		paper.setExam("未审核");
		// 开始添加
		boolean b = paperService.savePaper(paper);
		if (b) {
			// 保存在服务器的 work 中
			multipartFile.transferTo(new File(System.getProperty("catalina.home") + newFileName));
		}
		return b;
	}

	/**
	 * 转发到论文查询页面
	 * @return
	 */
	@GetMapping("/find_paper")
	public String forwardFindPaper() {
		return "paper/findPaper";
	}

	/**
	 * 根据分页信息以及对应的论文信息查询论文
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@PostMapping("/paperPage")
	@ResponseBody
	public PageVO<Paper> getPaperList(Integer pageNum, Integer pageSize,
	                                  boolean isCheck, Long periodical, Long subject,
	                                  Long periodicalLevel, Long projectSource, Long college,
	                                  Long major, Long professor, String name) throws Exception {
		PageVO<Paper> pageVO = new PageVO<>();
		pageVO.setPageNum(pageNum);
		pageVO.setPageSize(pageSize);
		Paper paper = new Paper();
		User user = new User();
		if (periodical != null && periodical != 0) {
			Periodical periodical1 = new Periodical();
			periodical1.setId(periodical);
			paper.setPeriodical(periodical1);
		}
		if (subject != null && subject != 0) {
			Subject subject1 = new Subject();
			subject1.setId(subject);
			paper.setSubject(subject1);
		}
		if (periodicalLevel != null && periodicalLevel != 0) {
			PeriodicalLevel periodicalLevel1 = new PeriodicalLevel();
			periodicalLevel1.setId(periodicalLevel);
			paper.setPeriodicalLevel(periodicalLevel1);
		}
		if (projectSource != null && projectSource != 0) {
			ProjectSource projectSource1 = new ProjectSource();
			projectSource1.setId(projectSource);
			paper.setProjectSource(projectSource1);
		}

		if (college != null && college > 0) {
			College college1 = new College();
			college1.setId(college);
			user.setCollege(college1);
		}
		if (major != null && major > 0) {
			Major major1 = new Major();
			major1.setId(major);
			user.setMajor(major1);
		}
		if (professor != null && professor > 0) {
			Professor professor1 = new Professor();
			professor1.setId(professor);
			user.setProfessor(professor1);
		}
		if (name != null && name.length() > 0) {
			user.setName(name);
		}
		paper.setUser(user);
		List<Paper> paperList = new ArrayList<>();
		paperList.add(paper);
		pageVO.setList(paperList);
		pageVO = paperService.getPaperByPage(pageVO, isCheck, null);
		return pageVO;
	}

	/**
	 * 下载文件
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/download")
	public ResponseEntity<byte[]> download(Long id) throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		// 给要下载的文件起一个名字
		// 首先查询出当前论文的下载地址
		Paper paper = new Paper();
		paper.setId(id);
		List<Paper> paperByPaper = paperService.getPaperByPaper(paper);
		paper = paperByPaper.get(0);
		String suffix = paper.getDownloadAddress().substring(paper.getDownloadAddress().indexOf("."));
		String name = paper.getTitle() + suffix;
		String downName = new String(name.getBytes("utf-8"), "iso8859-1");
		headers.setContentDispositionFormData("attachment", downName);
		//G盘中的0.jpg是待下载的图片。这里讲待下载的图片放在G盘，也可以放在项目目录下
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(new File(System.getProperty("catalina.home") + paper.getDownloadAddress())),
				headers, HttpStatus.CREATED);
	}

	/**
	 * 转发到审核页面
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/check")
	public String forwardCheck() throws Exception {
		return "paper/check";
	}

	/**
	 * 审核论文
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/check")
	@ResponseBody
	public boolean check() throws Exception {
		// 直接进行审核
		Long id = Long.parseLong(request.getParameter("id"));
		Paper paper = new Paper();
		paper.setId(id);
		paper.setExam("已审核");
		boolean b = paperService.checkPaper(paper);
		return b;
	}

	/**
	 * 拒绝论文
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/fail")
	@ResponseBody
	public boolean fail(Long id, String examFile) throws Exception {
		Paper paper = new Paper();
		paper.setId(id);
		paper.setExam("审核失败");
		paper.setExamFile(examFile);
		boolean b = paperService.checkPaper(paper);
		return b;
	}

	/**
	 * 删除论文
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/deletePaper")
	@ResponseBody
	public boolean deletePaper() throws Exception {
		// 删除论文
		Long id = Long.parseLong(request.getParameter("id"));
		Paper paper = new Paper();
		paper.setId(id);
		boolean b = paperService.deletePaper(paper);
		return b;
	}

	/**
	 * 转发到论文详情页面
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/details")
	public String details(Long id) throws Exception {
		Paper paper = new Paper();
		paper.setId(id);
		Paper paper1 = paperService.getPaperByPaper(paper).get(0);
		request.setAttribute("paperById", paper1);
		return "paper/details";
	}
	@GetMapping("/mypaper")
	public String forwardMyPaper() throws Exception {
		return "paper/myPaper";
	}

	/**
	 * 根据分页信息查询我的论文
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@PostMapping("/myPaperPage")
	@ResponseBody
	public PageVO<Paper> getPaperListByMyPaper(Integer pageNum, Integer pageSize, boolean isCheck) throws Exception {
		PageVO<Paper> pageVO = new PageVO<>();
		pageVO.setPageNum(pageNum);
		pageVO.setPageSize(pageSize);
		User user = (User) session.getAttribute("user");
		pageVO = paperService.getPaperByPage(pageVO, isCheck, user);
		return pageVO;
	}
}
