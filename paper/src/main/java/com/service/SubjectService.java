package com.service;

import com.pojo.eneity.Subject;
import com.pojo.vo.PageVO;

import java.util.List;

/**
 * 学科信息业务层接口
 */
public interface SubjectService {
	/**
	 * 根据分页信息返回分页对象
	 * @param pageVO
	 * @return
	 * @throws Exception
	 */
	PageVO<Subject> getPageVOByPageVO(PageVO<Subject> pageVO) throws Exception;

	/**
	 * 根据学科信息查询是否存在
	 * @param subject
	 * @return
	 * @throws Exception
	 */
	Subject getSubjectBySubject(Subject subject) throws Exception;

	/**
	 * 添加学科信息
	 * @param subject
	 * @return
	 * @throws Exception
	 */
	boolean saveSubject(Subject subject) throws Exception;

	/**
	 * 删除学科信息
	 * @param subject
	 * @return
	 * @throws Exception
	 */
	boolean deleteSubject(Subject subject) throws Exception;

	/**
	 * 查询所有学科信息
	 * @return
	 * @throws Exception
	 */
	List<Subject> getSubject() throws Exception;
}
