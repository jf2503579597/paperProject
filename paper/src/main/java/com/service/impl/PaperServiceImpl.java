package com.service.impl;

import com.dao.PaperDao;
import com.dao.UserDao;
import com.pojo.eneity.Paper;
import com.pojo.eneity.User;
import com.pojo.vo.PageVO;
import com.service.PaperService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.util.List;

@Service
@Transactional
public class PaperServiceImpl implements PaperService {
	@Autowired
	private PaperDao paperDao;
	@Autowired
	private UserDao userDao;
	/**
	 * 根据论文信息查询论文
	 * @param paper
	 * @return
	 */
	@Override
	public List<Paper> getPaperByPaper(Paper paper) throws Exception {
		List<Paper> paperByQuery = paperDao.findPaperByQuery(paper);
		// 根据查出的每个论文的作者 id 查询其对应的作者
		User user;
		for (Paper paper1 : paperByQuery) {
			if (paper1.getUser() != null && paper1.getUser().getId() != null) {
				// 查询对应的作者
				user = new User();
				user.setId(paper1.getUser().getId());
				List<User> user1 = userDao.findUser(user);
				if (user1 != null && !user1.isEmpty()) {
					paper1.setUser(user1.get(0));
				}
			}
		}
		return paperByQuery;
	}

	/**
	 * 添加论文信息
	 * @param paper
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean savePaper(Paper paper) throws Exception {
		int i = paperDao.insertPaper(paper);
		if (i > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 根据分页信息查询论文
	 * @param pageVO
	 * @return
	 * @throws Exception
	 */
	@Override
	public PageVO<Paper> getPaperByPage(PageVO<Paper> pageVO, boolean isCheck, User user) throws Exception {
		Paper paper = new Paper();
		Integer pageNum = (pageVO.getPageNum() - 1) * pageVO.getPageSize();
		Integer pageSize = pageVO.getPageSize();
		paper.setPageNum(pageNum);
		paper.setPageSize(pageSize);
		// 如果是审核页面，则显示未审核的论文，如果是查询页面，则显示已审核的论文
		if (isCheck) {
			paper.setExam("未审核");
		} else {
			paper.setExam("已审核");
		}
		// 如果是我的论文页面，则显示我的所有论文
		if (user != null) {
			paper.setUser(user);
			paper.setExam(null);
		}
		List<Paper> paperList = getPaperByPaper(paper);
		pageVO.setList(paperList);
		paper = new Paper();
		Integer size = paperDao.findPaperByQuery(paper).size();
		Long totalCount = size.longValue();
		Integer totalPage = totalCount.intValue() / pageVO.getPageSize() == 0 ? totalCount.intValue() / pageVO.getPageSize() : totalCount.intValue() / pageVO.getPageSize() + 1;
		pageVO.setTotalCount(totalCount);
		pageVO.setTotalPage(totalPage);
		return pageVO;
	}

	/**
	 * 根据主键审核论文
	 * @param paper
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean checkPaper(Paper paper) throws Exception {
		int i = paperDao.updatePaper(paper);
		if (i > 0) {
			return true;
		}
		return false;
	}
	/**
	 * 根据主键删除论文
	 * @param paper
	 * @return
	 */
	@Override
	public boolean deletePaper(Paper paper) throws Exception {
		int i = paperDao.deletePaper(paper);
		if (i > 0) {
			return true;
		}
		return false;
	}

}
