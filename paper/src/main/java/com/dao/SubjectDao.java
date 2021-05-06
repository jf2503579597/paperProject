package com.dao;

import com.pojo.eneity.Subject;

import java.util.List;
import java.util.Map;

/**
 * 学科信息数据持久层接口
 */
public interface SubjectDao {
	/**
	 * 根据 map 集合查询学科信息结果
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List<Subject> findSubject(Map<String, Object> map) throws Exception;

	/**
	 * 添加学科信息
	 * @param subject
	 * @return
	 */
	int insertSubject(Subject subject);

	/**
	 * 删除学科信息
	 * @param subject
	 * @return
	 */
	int deleteSubject(Subject subject);
}
