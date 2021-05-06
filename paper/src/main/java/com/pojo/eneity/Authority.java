package com.pojo.eneity;

import com.pojo.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 权限信息表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Authority extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 6523985146990699616L;
	private Long id;            // 主键
	private String name;        // 权限名称
}
