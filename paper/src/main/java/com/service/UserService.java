package com.service;

import com.pojo.eneity.College;
import com.pojo.eneity.User;
import com.pojo.vo.PageVO;

import java.util.List;

/**
 * 用户业务层
 */
public interface UserService {
	/**
	 * 根据用户信息查询用户
	 * @param user
	 * @return
	 * @throws Exception
	 */
	User getUserByQuery(User user) throws Exception;
	/**
	 * 根据用户工号、主键修改用户信息
	 * @param user
	 * @return
	 * @throws Exception
	 */
	boolean updateUser(User user) throws Exception;

	/**
	 * 获取所有用户
	 * @return
	 */
	List<User> getUserList() throws Exception;

	/**
	 * 添加用户
	 * @param user
	 * @return
	 * @throws Exception
	 */
	boolean saveUser(User user) throws Exception;

	/**
	 * 获取教师分页信息
	 * @param pageVO
	 * @return
	 */
	PageVO<User> getUserByPage(PageVO<User> pageVO) throws Exception;

	/**
	 * 根据 id 删除用户
	 * @param user
	 * @return
	 */
	boolean deleteUser(User user) throws Exception;

}
