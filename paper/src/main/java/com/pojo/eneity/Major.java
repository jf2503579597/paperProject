package com.pojo.eneity;

import com.pojo.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Major extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 2773545702596421714L;
	private Long id;                //主键
	private String majorName;       //专业名称
	private Long collId;            //所属院系
}
