package com.dao;

import com.pojo.eneity.PeriodicalLevel;

import java.util.List;

public interface PeriodicalLevelDao {
	/**
	 * 查询所有期刊级别信息
	 * @return
	 */
	List<PeriodicalLevel> findPeriodicalLevel();

}
