package com.service.impl;

import com.dao.PaperDao;
import com.dao.PeriodicalDao;
import com.pojo.eneity.Paper;
import com.pojo.eneity.Periodical;
import com.pojo.eneity.Subject;
import com.pojo.vo.PageVO;
import com.service.PeriodicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 期刊类别业务层实现类
 */
@Service
@Transactional
public class PeriodicalServiceImpl implements PeriodicalService {
	@Autowired
	private PeriodicalDao periodicalDao;
	@Autowired
	private PaperDao paperDao;
	/**
	 * 根据分页信息查询分页列表
	 * @param pageVO
	 * @return
	 */
	@Override
	public PageVO<Periodical> getPageVOByPageVO(PageVO<Periodical> pageVO) throws Exception  {
		Map<String, Object> map = new HashMap<>();
		Integer pageNum = pageVO.getPageNum();
		Integer pageSize = pageVO.getPageSize();
		pageNum = (pageNum - 1) * pageSize;
		map.put("pageNum", pageNum);
		map.put("pageSize", pageSize);
		// 查询分页信息
		List<Periodical> periodicalList = periodicalDao.findPeriodical(map);
		pageVO.setList(periodicalList);
		// 查询全部信息
		map = new HashMap<>();
		periodicalList = periodicalDao.findPeriodical(map);
		Integer totalCount = periodicalList.size();
		pageVO.setTotalCount(totalCount.longValue());
		Integer totalPage = totalCount.intValue() / pageVO.getPageSize() == 0 ? totalCount.intValue() / pageVO.getPageSize() : totalCount.intValue() / pageVO.getPageSize() + 1;
		pageVO.setTotalPage(totalPage);
		return pageVO;
	}

	/**
	 * 根据期刊信息查询期刊信息
	 * @param periodical
	 * @return
	 */
	@Override
	public Periodical getPeriodicalByName(Periodical periodical) throws Exception  {
		Map<String, Object> map = new HashMap<>();
		map.put("periodical", periodical);
		List<Periodical> periodicalList = periodicalDao.findPeriodical(map);
		if (periodicalList != null && !periodicalList.isEmpty()) {
			return periodicalList.get(0);
		}
		return null;
	}

	/**
	 * 保存期刊类别信息
	 * @param periodical
	 * @return
	 */
	@Override
	public boolean savePeriodical(Periodical periodical) throws Exception  {
		int count = periodicalDao.insertPeriodical(periodical);
		if (count > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 删除期刊信息
	 * @param periodical
	 * @return
	 */
	@Override
	public boolean deletePeriodical(Periodical periodical) throws Exception {
		// 首先删除这个学科对应的所有论文
		Paper paper = new Paper();
		paper.setPeriodical(periodical);
		List<Paper> paperByQuery = paperDao.findPaperByQuery(paper);
		int paperCount = 1;
		if (paperByQuery != null && !paperByQuery.isEmpty()) {
			paperCount = paperDao.deletePaper(paper);
		}
		int subCount = periodicalDao.deletePeriodical(periodical);
		if (paperCount > 0 && subCount > 0) {
			return true;
		}
		return false;
	}
	/**
	 * 查询所有期刊信息
	 * @return
	 */
	@Override
	public List<Periodical> getPeriodical() throws Exception  {
		Map<String, Object> map = new HashMap<>();
		map.put("periodical", null);
		List<Periodical> periodicalList = periodicalDao.findPeriodical(map);
		return periodicalList;
	}

}
