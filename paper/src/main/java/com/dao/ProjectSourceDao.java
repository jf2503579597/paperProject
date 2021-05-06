package com.dao;

import com.pojo.eneity.ProjectSource;

import java.util.List;
import java.util.Map;

/**
 * 项目来源数据持久层
 */
public interface ProjectSourceDao {
	/**
	 * 根据分页信息/来源信息查询
	 * @param map
	 * @return
	 */
	List<ProjectSource> findProjectSource(Map<String, Object> map);

	/**
	 * 添加项目来源
	 * @param projectSource
	 * @return
	 */
	int insertProjectSource(ProjectSource projectSource);

	/**
	 * 删除项目来源
	 * @param projectSource
	 * @return
	 */
	int deleteProjectSource(ProjectSource projectSource);
}
