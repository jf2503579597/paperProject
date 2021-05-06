package com.dao;

import com.pojo.eneity.Paper;

import java.util.List;

/**
 * 论文信息数据持久层
 */
public interface PaperDao {
	/**
	 * 根据论文信息查询论文
	 * @param paper
	 * @return
	 * @throws Exception
	 */
	List<Paper> findPaperByQuery(Paper paper) throws Exception;

	/**
	 * 根据论文信息删除论文
	 * @param paper
	 * @return
	 */
	int deletePaper(Paper paper) throws Exception;

	/**
	 * 添加论文信息
	 * @param paper
	 * @return
	 * @throws Exception
	 */
	int insertPaper(Paper paper) throws Exception;

	/**
	 * 修改论文信息
	 * @param paper
	 * @return
	 * @throws Exception
	 */
	int updatePaper(Paper paper) throws Exception;
}
