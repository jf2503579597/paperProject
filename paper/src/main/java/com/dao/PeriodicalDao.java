package com.dao;

import com.pojo.eneity.Periodical;

import java.util.List;
import java.util.Map;

/**
 * 期刊信息数据持久层
 */
public interface PeriodicalDao {
	/**
	 * 根据分页信息查询列表
	 * @param map
	 * @return
	 */
	List<Periodical> findPeriodical(Map<String, Object> map);

	/**
	 * 保存期刊类别信息
	 * @param periodical
	 * @return
	 */
	int insertPeriodical(Periodical periodical);

	/**
	 * 删除期刊类别信息
	 * @param periodical
	 * @return
	 */
	int deletePeriodical(Periodical periodical);
}
