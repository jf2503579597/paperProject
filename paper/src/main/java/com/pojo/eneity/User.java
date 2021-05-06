package com.pojo.eneity;

import com.pojo.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息表
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class User extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 4236983487872324594L;
	private Long id;                    // 主键
	private String card;                // 工号（登录账号）
	private String name;                // 姓名
	private String gender;              // 性别
	private String password;            // 密码
	private Date birthday;              // 出生日期
	private Professor professor;        // 职称
	private College college;            // 学院
	private Major major;                // 专业
	private Authority authority;        // 权限
	private Date createTime;            // 创建时间
	private Date updateTime;            // 修改时间
}
