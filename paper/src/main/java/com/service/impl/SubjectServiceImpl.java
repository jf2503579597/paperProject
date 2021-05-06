package com.service.impl;

import com.dao.PaperDao;
import com.dao.SubjectDao;
import com.pojo.eneity.Paper;
import com.pojo.eneity.Subject;
import com.pojo.eneity.User;
import com.pojo.vo.PageVO;
import com.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.ObjectView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 学科信息业务层实现类
 */
@Service
@Transactional
public class SubjectServiceImpl implements SubjectService {
	@Autowired
	private SubjectDao subjectDao;
	@Autowired
	private PaperDao paperDao;
	/**
	 * 根据分页信息返回分页对象
	 * @param pageVO
	 * @return
	 * @throws Exception
	 */
	@Override
	public PageVO<Subject> getPageVOByPageVO(PageVO<Subject> pageVO) throws Exception {
		Map<String, Object> map = new HashMap<>();
		Integer pageNum = pageVO.getPageNum();
		Integer pageSize = pageVO.getPageSize();
		pageNum = (pageNum - 1) * pageSize;
		map.put("pageNum", pageNum);
		map.put("pageSize", pageSize);
		// 查询分页信息
		List<Subject> subjectList = subjectDao.findSubject(map);
		pageVO.setList(subjectList);
		// 查询全部信息
		map = new HashMap<>();
		subjectList = subjectDao.findSubject(map);
		Integer totalCount = subjectList.size();
		pageVO.setTotalCount(totalCount.longValue());
		Integer totalPage = totalCount.intValue() / pageVO.getPageSize() == 0 ? totalCount.intValue() / pageVO.getPageSize() : totalCount.intValue() / pageVO.getPageSize() + 1;
		pageVO.setTotalPage(totalPage);
		return pageVO;
	}

	/**
	 * 根据学科信息查询是否存在
	 * @param subject
	 * @return
	 * @throws Exception
	 */
	@Override
	public Subject getSubjectBySubject(Subject subject) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("subject", subject);
		List<Subject> subject1 = subjectDao.findSubject(map);
		if (subject != null && !subject1.isEmpty()) {
			return subject1.get(0);
		}
		return null;
	}

	/**
	 * 添加学科信息
	 * @param subject
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean saveSubject(Subject subject) throws Exception {
		int count = subjectDao.insertSubject(subject);
		if (count > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 删除学科信息
	 * @param subject
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean deleteSubject(Subject subject) throws Exception {
		// 首先删除这个学科对应的所有论文
		Paper paper = new Paper();
		paper.setSubject(subject);
		List<Paper> paperByQuery = paperDao.findPaperByQuery(paper);
		int paperCount = 1;
		if (paperByQuery != null && !paperByQuery.isEmpty()) {
			paperCount = paperDao.deletePaper(paper);
		}
		int subCount = subjectDao.deleteSubject(subject);
		if (paperCount > 0 && subCount > 0) {
			return true;
		}
		return false;
	}
	/**
	 * 查询所有学科信息
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Subject> getSubject() throws Exception {
		Map<String, Object> map = new HashMap<>();
		List<Subject> subjectList = subjectDao.findSubject(map);
		return subjectList;
	}
}
