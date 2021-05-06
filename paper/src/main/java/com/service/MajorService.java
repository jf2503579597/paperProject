package com.service;

import com.pojo.eneity.Major;
import com.pojo.vo.PageVO;

import java.util.List;

/**
 * 专业业务层接口
 */
public interface MajorService {
	/**
	 * 根据学院id获取其所有专业信息
	 * @param collId
	 * @return
	 */
	List<Major> getMajor(Long collId);

	/**
	 * 分页查询专业信息
	 * @param pageVO
	 * @return
	 */
	PageVO<Major> getPageVOByPageVO(PageVO<Major> pageVO);

	/**
	 * 根据专业信息查询对应的专业是否存在
	 * @param major
	 * @return
	 */
	Major getMajorByName(Major major);

	/**
	 * 添加专业信息
	 * @param major
	 * @return
	 */
	boolean saveMajor(Major major);

	/**
	 * 删除专业信息
	 * @param major
	 * @return
	 */
	boolean deleteMajor(Major major);
}
