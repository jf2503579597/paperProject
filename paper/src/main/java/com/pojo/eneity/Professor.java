package com.pojo.eneity;

import com.pojo.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 职称信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Professor  extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 4025752170740567623L;
	private Long id;                        //主键
	private String professorName;           //职称信息
}
