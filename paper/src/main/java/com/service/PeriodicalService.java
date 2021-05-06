package com.service;

import com.pojo.eneity.Periodical;
import com.pojo.vo.PageVO;

import java.util.List;

/**
 * 期刊信息业务层
 */
public interface PeriodicalService {
	/**
	 * 根据分页信息查询分页列表
	 * @param pageVO
	 * @return
	 */
	PageVO<Periodical> getPageVOByPageVO(PageVO<Periodical> pageVO) throws Exception ;

	/**
	 * 根据期刊信息查询期刊信息
	 * @param periodical
	 * @return
	 */
	Periodical getPeriodicalByName(Periodical periodical) throws Exception ;

	/**
	 * 保存期刊类别信息
	 * @param periodical
	 * @return
	 */
	boolean savePeriodical(Periodical periodical) throws Exception ;

	/**
	 * 删除期刊信息
	 * @param periodical
	 * @return
	 */
	boolean deletePeriodical(Periodical periodical) throws Exception;
	/**
	 * 查询所有期刊信息
	 * @return
	 */
	List<Periodical> getPeriodical() throws Exception;
}
