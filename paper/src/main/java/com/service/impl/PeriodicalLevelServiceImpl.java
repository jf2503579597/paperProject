package com.service.impl;

import com.dao.PeriodicalLevelDao;
import com.pojo.eneity.PeriodicalLevel;
import com.pojo.eneity.Subject;
import com.service.PeriodicalLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class PeriodicalLevelServiceImpl implements PeriodicalLevelService {
	@Autowired
	private PeriodicalLevelDao periodicalLevelDao;
	/**
	 * 查询所有论文等级信息
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<PeriodicalLevel> getPeriodicalLevel() throws Exception {
		List<PeriodicalLevel> periodicalLevelList= periodicalLevelDao.findPeriodicalLevel();
		return periodicalLevelList;
	}
}
