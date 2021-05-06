package com.service;

import com.pojo.eneity.College;
import com.pojo.vo.PageVO;

import java.util.List;

/**
 * 学院业务层接口
 */
public interface CollegeService {
	/**
	 * 获取所有学院信息
	 * @return
	 */
	List<College> getCollege(College college);

	/**
	 * 查询学院分页信息
	 * @param pageVO
	 * @return
	 */
	PageVO<College> getPageVOByPageVO(PageVO<College> pageVO);

	/**
	 * 查询当前学院信息是否存在
	 * @param college
	 * @return
	 */
	College getCollegeByName(College college);

	/**
	 * 添加学院信息
	 * @param college
	 * @return
	 */
	boolean saveCollege(College college);

	/**
	 * 删除学院信息
	 * @param college
	 * @return
	 */
	boolean deleteCollege(College college);
}
