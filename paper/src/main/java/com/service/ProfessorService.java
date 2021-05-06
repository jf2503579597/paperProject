package com.service;

import com.pojo.eneity.Professor;
import com.pojo.vo.PageVO;

import java.util.List;

/**
 * 职称业务层接口
 */
public interface ProfessorService {
	/**
	 * 获取所有职称信息
	 * @return
	 */
	List<Professor> getProfessor();

	/**
	 * 根据分页信息查询职称信息
	 * @param pageVO
	 * @return
	 */
	PageVO<Professor> getPageVOByPageVO(PageVO<Professor> pageVO);

	/**
	 * 根据职称信息查询是否存在
	 * @param professor
	 * @return
	 */
	Professor getProfessorByName(Professor professor);

	/**
	 * 添加职称信息
	 * @param professor
	 * @return
	 */
	boolean saveProfessor(Professor professor);

	/**
	 * 删除职称信息
	 * @param professor
	 * @return
	 */
	boolean deleteProfessor(Professor professor);
}
