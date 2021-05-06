package com.pojo.eneity;
import com.pojo.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
/**
 * 学院信息表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class College extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 3364571367368355449L;
	private Long id;                        //主键
	private String collName;               //学院名称
}
