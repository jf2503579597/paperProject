package com.controller;

import com.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 根据教师信息分析统计图
 */
@Controller
@RequestMapping("/teacher_chart")
public class ChartByTeacherController {
	@Autowired
	private PaperService paperService;

	/**
	 * 转发到论文分析图界面
	 * @return
	 */
	@GetMapping("/chart_analy")
	public String forwardChartTeacher() {
		return "chart/teacherChart";
	}
}
