package com.pojo.eneity;

import com.pojo.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 菜单信息表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu extends BaseEntity implements Serializable {
	private static final long serialVersionUID = -2867444876726793463L;
	private Long id;                    // 主键
	private Menu parentMenu;            // 上级菜单
	private String menuName;            // 菜单名称
	private List<Menu> childMenus;      // 每个菜单所对应的下级菜单
	private String url;                 // 跳转路由（一级菜单无）
	private String icon;                // 菜单图标
	private Date createTime;            // 创建时间
	private Date updateTime;            // 修改时间
}
