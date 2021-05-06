package com.pojo.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 分页信息
 */
public class PageVO<E> implements Serializable {
	private static final long serialVersionUID = 2879252253761603618L;
	private Integer pageNum;                // 开始页数
	private Integer pageSize;               // 每页数量
	private List<E> list;                   // 分页查询结果
	private Long totalCount;                // 总条数
	private Integer totalPage;              // 总页数

	public PageVO() {
	}

	public PageVO(Integer pageNum, Integer pageSize) {
		this.pageNum = pageNum;
		this.pageSize = pageSize;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public List<E> getList() {
		return list;
	}

	public void setList(List<E> list) {
		this.list = list;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
}
