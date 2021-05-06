package com.service.impl;

import com.dao.MajorDao;
import com.dao.UserDao;
import com.pojo.eneity.College;
import com.pojo.eneity.Major;
import com.pojo.eneity.User;
import com.pojo.vo.PageVO;
import com.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 专业业务层实现类
 */
@Service
@Transactional
public class MajorServiceImpl implements MajorService {
	@Autowired
	private MajorDao majorDao;
	@Autowired
	private UserDao userDao;
	/**
	 * 根据学院id获取其所有专业信息
	 * @param collId
	 * @return
	 */
	@Override
	public List<Major> getMajor(Long collId) {
		Major major = new Major();
		major.setCollId(collId);
		List<Major> majorByCollId = majorDao.findMajor(major);
		return majorByCollId;
	}

	/**
	 * 分页查询专业信息
	 * @param pageVO
	 * @return
	 */
	@Override
	public PageVO<Major> getPageVOByPageVO(PageVO<Major> pageVO) {
		Major major = new Major();
		Integer pageNum = (pageVO.getPageNum() - 1) * pageVO.getPageSize();
		Integer pageSize = pageVO.getPageSize();
		major.setPageNum(pageNum);
		major.setPageSize(pageSize);
		List<Major> majorList = majorDao.findMajor(major);
		pageVO.setList(majorList);
		major = new Major();
		Integer size = majorDao.findMajor(major).size();
		Long totalCount = size.longValue();
		Integer totalPage = totalCount.intValue() / pageVO.getPageSize() == 0 ? totalCount.intValue() / pageVO.getPageSize() : totalCount.intValue() / pageVO.getPageSize() + 1;
		pageVO.setTotalCount(totalCount);
		pageVO.setTotalPage(totalPage);
		return pageVO;
	}

	/**
	 * 根据专业信息查询对应的专业是否存在
	 * @param major
	 * @return
	 */
	@Override
	public Major getMajorByName(Major major) {
		List<Major> majorList = majorDao.findMajor(major);
		if (majorList != null && !majorList.isEmpty()) {
			return majorList.get(0);
		}
		return null;
	}

	/**
	 * 添加专业信息
	 * @param major
	 * @return
	 */
	@Override
	public boolean saveMajor(Major major) {
		int result = majorDao.insertMajor(major);
		if (result > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 删除专业信息
	 * @param major
	 * @return
	 */
	@Override
	public boolean deleteMajor(Major major) {
		// 删除该专业下的所有教师
		User user = new User();
		user.setMajor(major);
		userDao.deleteUser(user);
		// 删除该专业
		int i = majorDao.deleteMajor(major);
		if (i > 0) {
			return true;
		}
		return false;
	}
}
