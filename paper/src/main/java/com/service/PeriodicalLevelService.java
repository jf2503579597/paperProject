package com.service;

import com.pojo.eneity.PeriodicalLevel;

import java.util.List;

/**
 * 期刊级别业务层接口
 */
public interface PeriodicalLevelService {
	/**
	 * 查询所有期刊级别信息
	 * @return
	 * @throws Exception
	 */
	List<PeriodicalLevel> getPeriodicalLevel() throws Exception;
}
