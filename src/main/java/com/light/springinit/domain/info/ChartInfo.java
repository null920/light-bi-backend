package com.light.springinit.domain.info;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 帖子信息
 *
 * @author null&&
 * @Date 2024/7/17 10:51
 */
@Getter
@Setter
@NoArgsConstructor
public class ChartInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * chart_id
     */
    private Long chartId;

    /**
     * 分析目标
     */
    private String goal;

    /**
     * 图表数据
     */
    private String chartData;

    /**
     * 图表类型
     */
    private String chartType;

    /**
     * 生成的图表数据
     */
    private String genChartData;

    /**
     * 生成的分析结论
     */
    private String genChartResult;

    /**
     * 提交用户信息
     */
    private UserInfo userInfo;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
