package com.pojo.eneity;

import com.pojo.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 学科信息表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subject extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 842472137596375862L;
	private Long id;                //主键
	private String subName;         //学科名称

}
