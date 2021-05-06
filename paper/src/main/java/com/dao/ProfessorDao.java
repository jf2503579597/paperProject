package com.dao;

import com.pojo.eneity.Professor;

import java.util.List;

/**
 * 职称信息数据持久层
 */
public interface ProfessorDao {
	List<Professor> findProfessor(Professor professor);

	/**
	 * 添加职称信息
	 * @param professor
	 * @return
	 */
	int insertProfessor(Professor professor);

	/**
	 * 删除职称信息
	 * @param professor
	 * @return
	 */
	int deleteProfessor(Professor professor);
}
