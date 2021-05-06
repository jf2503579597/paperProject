package com.service.impl;

import com.dao.CollegeDao;
import com.dao.MajorDao;
import com.dao.UserDao;
import com.pojo.eneity.College;
import com.pojo.eneity.Major;
import com.pojo.eneity.Paper;
import com.pojo.eneity.User;
import com.pojo.vo.PageVO;
import com.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 学院业务层实现类
 */
@Service
@Transactional
public class CollegeServiceImpl implements CollegeService {
	@Autowired
	private CollegeDao collegeDao;
	@Autowired
	private MajorDao majorDao;
	@Autowired
	private UserDao userDao;
	/**
	 * 获取所有学院信息
	 * @return
	 */
	@Override
	public List<College> getCollege(College college) {
		List<College> collegeList = collegeDao.findCollege(college);
		return collegeList;
	}

	/**
	 * 查询学院分页信息
	 * @param pageVO
	 * @return
	 */
	@Override
	public PageVO<College> getPageVOByPageVO(PageVO<College> pageVO) {
		College college = new College();
		Integer pageNum = (pageVO.getPageNum() - 1) * pageVO.getPageSize();
		Integer pageSize = pageVO.getPageSize();
		college.setPageNum(pageNum);
		college.setPageSize(pageSize);
		List<College> collegeList = getCollege(college);
		pageVO.setList(collegeList);
		college = new College();
		Integer size = getCollege(college).size();
		Long totalCount = size.longValue();
		Integer totalPage = totalCount.intValue() / pageVO.getPageSize() == 0 ? totalCount.intValue() / pageVO.getPageSize() : totalCount.intValue() / pageVO.getPageSize() + 1;
		pageVO.setTotalCount(totalCount);
		pageVO.setTotalPage(totalPage);
		return pageVO;
	}

	/**
	 * 查询当前学院信息是否存在
	 * @param college
	 * @return
	 */
	@Override
	public College getCollegeByName(College college) {
		List<College> collegeList = getCollege(college);
		if (collegeList != null && !collegeList.isEmpty()) {
			return collegeList.get(0);
		}
		return null;
	}

	/**
	 * 添加学院信息
	 * @param college
	 * @return
	 */
	@Override
	public boolean saveCollege(College college) {
		int result = collegeDao.insertCollege(college);
		if (result > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 删除学院信息，同时删除该学院下的所有专业，同时删除所有专业下的所有教师
	 * @param college
	 * @return
	 */
	@Override
	public boolean deleteCollege(College college) {
		int result = collegeDao.deleteCollege(college);
		Major major = new Major();
		major.setCollId(college.getId());
		List<Major> majorList = majorDao.findMajor(major);
		for (Major major1 : majorList) {
			// 循环所有专业，删除该专业的所有教师
			User user = new User();
			user.setMajor(major);
			userDao.deleteUser(user);
		}
		// 删除该学院下的所有专业
		majorDao.deleteMajor(major);
		if (result > 0) {
			return true;
		}
		return false;
	}
}
