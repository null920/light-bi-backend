package com.light.springinit.domain.vo;

import com.light.springinit.domain.info.ChartInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 帖子视图VO
 *
 * @author null&&
 * @Date 2024/7/17 11:03
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ChartVO implements Serializable {

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
    private UserVO userVO;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    public ChartVO(ChartInfo chartInfo) {
        this.chartId = chartInfo.getChartId();
        this.goal = chartInfo.getGoal();
        this.chartData = chartInfo.getChartData();
        this.chartType = chartInfo.getChartType();
        this.genChartData = chartInfo.getGenChartData();
        this.genChartResult = chartInfo.getGenChartResult();
        this.userVO = new UserVO(chartInfo.getUserInfo());
        this.createTime = chartInfo.getCreateTime();
        this.updateTime = chartInfo.getUpdateTime();
    }
}
