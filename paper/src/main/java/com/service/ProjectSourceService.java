package com.service;

import com.pojo.eneity.ProjectSource;
import com.pojo.vo.PageVO;

import java.util.List;

/**
 * 项目来源业务层接口
 */
public interface ProjectSourceService {
	/**
	 * 根据分页查询分页列表
	 * @param pageVO
	 * @return
	 */
	PageVO<ProjectSource> getPageVOByPageVO(PageVO<ProjectSource> pageVO) throws Exception;

	/**
	 * 根据项目来源信息查询项目来源
	 * @param projectSource
	 * @return
	 */
	ProjectSource getProjectSourceBySource(ProjectSource projectSource) throws Exception;

	/**
	 * 保存项目来源信息
	 * @param projectSource
	 * @return
	 */
	boolean saveProjectSource(ProjectSource projectSource) throws Exception;

	/**
	 * 删除项目来源信息和使用该来源信息的所有项目
	 * @param projectSource
	 * @return
	 */
	boolean deleteProjectSource(ProjectSource projectSource) throws Exception;
	/**
	 * 查询所有项目来源
	 * @return
	 */
	List<ProjectSource> getProjectSource() throws Exception;
}
