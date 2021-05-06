package com.dao;

import com.pojo.eneity.Major;

import java.util.List;

/**
 * 专业信息数据持久层
 */
public interface MajorDao {
	List<Major> findMajor(Major major);

	/**
	 * 删除专业信息
	 * @param major1
	 * @return
	 */
	int deleteMajor(Major major1);

	/**
	 * 添加专业信息
	 * @param major
	 * @return
	 */
	int insertMajor(Major major);
}
