package com.service.impl;

import com.dao.ProfessorDao;
import com.dao.UserDao;
import com.pojo.eneity.Major;
import com.pojo.eneity.Professor;
import com.pojo.eneity.User;
import com.pojo.vo.PageVO;
import com.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 职称业务层实现类
 */
@Service
@Transactional
public class ProfessorServiceImpl implements ProfessorService {
	@Autowired
	private ProfessorDao professorDao;
	@Autowired
	private UserDao userDao;
	/**
	 * 获取所有职称信息
	 * @return
	 */
	@Override
	public List<Professor> getProfessor() {
		List<Professor> professor = professorDao.findProfessor(new Professor());
		return professor;
	}

	/**
	 * 根据分页信息查询职称信息
	 * @param pageVO
	 * @return
	 */
	@Override
	public PageVO<Professor> getPageVOByPageVO(PageVO<Professor> pageVO) {
		Professor professor = new Professor();
		Integer pageNum = (pageVO.getPageNum() - 1) * pageVO.getPageSize();
		Integer pageSize = pageVO.getPageSize();
		professor.setPageNum(pageNum);
		professor.setPageSize(pageSize);
		List<Professor> majorList = professorDao.findProfessor(professor);
		pageVO.setList(majorList);
		professor = new Professor();
		Integer size = professorDao.findProfessor(professor).size();
		Long totalCount = size.longValue();
		Integer totalPage = totalCount.intValue() / pageVO.getPageSize() == 0 ? totalCount.intValue() / pageVO.getPageSize() : totalCount.intValue() / pageVO.getPageSize() + 1;
		pageVO.setTotalCount(totalCount);
		pageVO.setTotalPage(totalPage);
		return pageVO;
	}

	/**
	 * 根据职称信息查询是否存在
	 * @param professor
	 * @return
	 */
	@Override
	public Professor getProfessorByName(Professor professor) {
		List<Professor> majorList = professorDao.findProfessor(professor);
		if (majorList != null && !majorList.isEmpty()) {
			return majorList.get(0);
		}
		return null;
	}

	/**
	 * 添加职称信息
	 * @param professor
	 * @return
	 */
	@Override
	public boolean saveProfessor(Professor professor) {
		int result = professorDao.insertProfessor(professor);
		if (result > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 删除职称信息
	 * @param professor
	 * @return
	 */
	@Override
	public boolean deleteProfessor(Professor professor) {
		// 删除该专业下的所有教师
		User user = new User();
		user.setProfessor(professor);
		userDao.deleteUser(user);
		// 删除该专业
		int i = professorDao.deleteProfessor(professor);
		if (i > 0) {
			return true;
		}
		return false;
	}
}
