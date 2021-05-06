package com.dao;

import com.pojo.eneity.College;
import com.pojo.eneity.User;

import java.util.List;

/**
 * 用户数据持久层
 */
public interface UserDao {
	/**
	 * 根据用户信息查询用户
	 * @param user
	 * @return
	 * @throws Exception
	 */
	List<User> findUser(User user) throws Exception;

	/**
	 * 根据用户主键、工号修改用户信息
	 * @param user
	 * @return
	 * @throws Exception
	 */
	int updateUser(User user) throws Exception;

	/**
	 * 添加用户
	 * @param user
	 * @return
	 * @throws Exception
	 */
	int insertUser(User user) throws Exception;

	/**
	 * 删除用户
	 * @param user
	 * @return
	 */
	int deleteUser(User user);
}
