package com.pojo.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity implements Serializable {
	private static final long serialVersionUID = 7551641622955197533L;
	private Integer pageNum;                            // 从多少个开始查
	private Integer pageSize;                           // 查询多少个
}
