package com.pojo.eneity;

import com.pojo.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 期刊类别表
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Periodical extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 8691650582283144362L;
	private Long id;                //主键
	private String periodName;      //类别名称
}
