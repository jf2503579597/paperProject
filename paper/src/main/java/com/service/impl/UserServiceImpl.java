package com.service.impl;

import com.dao.PaperDao;
import com.dao.UserDao;
import com.pojo.eneity.College;
import com.pojo.eneity.Paper;
import com.pojo.eneity.User;
import com.pojo.vo.PageVO;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户业务层
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private PaperDao paperDao;
	/**
	 * 根据用户信息查询用户
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@Override
	public User getUserByQuery(User user) throws Exception {
		List<User> user1 = userDao.findUser(user);
		if (user1 != null && !user1.isEmpty()) {
			return user1.get(0);
		}
		return null;
	}

	/**
	 * 根据用户工号、主键修改用户信息
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean updateUser(User user) throws Exception {
		int i = userDao.updateUser(user);
		if (i > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 获取所有用户
	 * @return
	 */
	@Override
	public List<User> getUserList() throws Exception{
		List<User> user = userDao.findUser(null);
		return user;
	}

	/**
	 * 添加用户
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean saveUser(User user) throws Exception {
		int i = userDao.insertUser(user);
		if (i > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 获取教师分页信息
	 * @param pageVO
	 * @return
	 */
	@Override
	public PageVO<User> getUserByPage(PageVO<User> pageVO) throws Exception {
		User user = new User();
		if (pageVO.getList() != null && !pageVO.getList().isEmpty()) {
			user = pageVO.getList().get(0);
		}
		Integer pageNum = (pageVO.getPageNum() - 1) * pageVO.getPageSize();
		Integer pageSize = pageVO.getPageSize();
		user.setPageNum(pageNum);
		user.setPageSize(pageSize);
		List<User> userList = userDao.findUser(user);
		pageVO.setList(userList);
		user = new User();
		Integer size = getUserList().size();
		Long totalCount = size.longValue();
		Integer totalPage = totalCount.intValue() / pageVO.getPageSize() == 0 ? totalCount.intValue() / pageVO.getPageSize() : totalCount.intValue() / pageVO.getPageSize() + 1;
		pageVO.setTotalCount(totalCount);
		pageVO.setTotalPage(totalPage);
		return pageVO;
	}

	/**
	 * 根据 id 删除用户
	 * @param user
	 * @return
	 */
	@Override
	public boolean deleteUser(User user) throws Exception {
		Paper paper = new Paper();
		paper.setUser(user);
		paperDao.deletePaper(paper);
		int i = userDao.deleteUser(user);
		if (i > 0) {
			return true;
		}
		return false;
	}
}
