package com.dao;

import com.pojo.eneity.College;

import java.util.List;

/**
 * 学院信息数据持久层
 */
public interface CollegeDao {
	List<College> findCollege(College college);

	/**
	 * 添加学院信息
	 * @param college
	 * @return
	 */
	int insertCollege(College college);

	/**
	 * 删除学院信息
	 * @param college
	 * @return
	 */
	int deleteCollege(College college);
}
