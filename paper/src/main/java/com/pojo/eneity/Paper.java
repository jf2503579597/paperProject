package com.pojo.eneity;

import com.pojo.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 论文信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Paper extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 2105100238940878387L;
	private Long id;                                    // 主键
	private String title;                               // 论文题目
	private Periodical periodical;                      // 期刊类别
	private String periodNo;                            // 期号（自动递增）
	private Subject subject;                            // 学科信息
	private PeriodicalLevel periodicalLevel;            // 期刊级别
	private ProjectSource projectSource;                // 项目来源
	private User user;                                  // 作者
	private String exam;                                // 审核状态
	private String examFile;                            // 审核失败理由
	private Date createTime;                            // 创建时间
	private Date updateTime;                            // 修改时间
	private String downloadAddress;                     // 下载路径

	private String createYear;                              // 创建时间年份
}
