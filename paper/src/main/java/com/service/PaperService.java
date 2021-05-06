package com.service;


import com.pojo.eneity.Paper;
import com.pojo.eneity.User;
import com.pojo.vo.PageVO;

import java.util.List;

public interface PaperService {

	/**
	 * 根据论文信息查询论文
	 * @param paper
	 * @return
	 */
	List<Paper> getPaperByPaper(Paper paper) throws Exception;

	/**
	 * 添加论文信息
	 * @param paper
	 * @return
	 * @throws Exception
	 */
	boolean savePaper(Paper paper) throws Exception;

	/**
	 * 根据分页信息查询论文
	 * @param pageVO
	 * @return
	 * @throws Exception
	 */
	PageVO<Paper> getPaperByPage(PageVO<Paper> pageVO, boolean isCheck, User user) throws Exception;

	/**
	 * 根据主键审核论文
	 * @param paper
	 * @return
	 * @throws Exception
	 */
	boolean checkPaper(Paper paper) throws Exception;

	/**
	 * 根据主键删除论文
	 * @param paper
	 * @return
	 */
	boolean deletePaper(Paper paper) throws Exception;
}
