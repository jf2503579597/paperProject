package com.pojo.eneity;

import com.pojo.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 项目来源信息表
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProjectSource extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 4376244166298043689L;
	private Long id;                // 主键
	private String sourceName;      // 来源名称

}
