package com.service.impl;

import com.dao.PaperDao;
import com.dao.ProjectSourceDao;
import com.pojo.eneity.Paper;
import com.pojo.eneity.ProjectSource;
import com.pojo.eneity.Subject;
import com.pojo.vo.PageVO;
import com.service.ProjectSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目来源业务层实现类
 */
@Service
@Transactional
public class ProjectSourceServiceImpl implements ProjectSourceService {
	@Autowired
	private ProjectSourceDao projectSourceDao;
	@Autowired
	private PaperDao paperDao;
	/**
	 * 根据分页查询分页列表
	 * @param pageVO
	 * @return
	 */
	@Override
	public PageVO<ProjectSource> getPageVOByPageVO(PageVO<ProjectSource> pageVO) throws Exception {
		Map<String, Object> map = new HashMap<>();
		Integer pageNum = pageVO.getPageNum();
		Integer pageSize = pageVO.getPageSize();
		pageNum = (pageNum - 1) * pageSize;
		map.put("pageNum", pageNum);
		map.put("pageSize", pageSize);
		// 查询分页信息
		List<ProjectSource> projectSourceList = projectSourceDao.findProjectSource(map);
		pageVO.setList(projectSourceList);
		// 查询全部信息
		map = new HashMap<>();
		projectSourceList = projectSourceDao.findProjectSource(map);
		Integer totalCount = projectSourceList.size();
		pageVO.setTotalCount(totalCount.longValue());
		Integer totalPage = totalCount.intValue() / pageVO.getPageSize() == 0 ? totalCount.intValue() / pageVO.getPageSize() : totalCount.intValue() / pageVO.getPageSize() + 1;
		pageVO.setTotalPage(totalPage);
		return pageVO;
	}
	/**
	 * 根据项目来源信息查询项目来源
	 * @param projectSource
	 * @return
	 */
	@Override
	public ProjectSource getProjectSourceBySource(ProjectSource projectSource) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("projectSource", projectSource);
		List<ProjectSource> projectSourceList = projectSourceDao.findProjectSource(map);
		if (projectSourceList != null && !projectSourceList.isEmpty()) {
			return projectSourceList.get(0);
		}
		return null;
	}
	/**
	 * 保存项目来源信息
	 * @param projectSource
	 * @return
	 */
	@Override
	public boolean saveProjectSource(ProjectSource projectSource) throws Exception {
		int count = projectSourceDao.insertProjectSource(projectSource);
		if (count > 0) {
			return true;
		}
		return false;
	}
	/**
	 * 删除项目来源信息和使用该来源信息的所有项目
	 * @param projectSource
	 * @return
	 */
	@Override
	public boolean deleteProjectSource(ProjectSource projectSource) throws Exception {
		// 首先删除这个学科对应的所有论文
		Paper paper = new Paper();
		paper.setProjectSource(projectSource);
		List<Paper> paperByQuery = paperDao.findPaperByQuery(paper);
		int paperCount = 1;
		if (paperByQuery != null && !paperByQuery.isEmpty()) {
			paperCount = paperDao.deletePaper(paper);
		}
		int projectSourceCount = projectSourceDao.deleteProjectSource(projectSource);
		if (paperCount > 0 && projectSourceCount > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 查询所有项目来源
	 * @return
	 */
	@Override
	public List<ProjectSource> getProjectSource() throws Exception {
		Map<String, Object> map = new HashMap<>();
		List<ProjectSource> projectSourceList = projectSourceDao.findProjectSource(map);
		return projectSourceList;
	}
}
