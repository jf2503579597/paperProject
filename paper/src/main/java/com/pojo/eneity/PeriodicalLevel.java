package com.pojo.eneity;

import com.pojo.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 期刊级别表
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PeriodicalLevel extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 7370827388766809592L;
	private Long id;                //主键
	private String levelName;       //刊物级别名称
}
