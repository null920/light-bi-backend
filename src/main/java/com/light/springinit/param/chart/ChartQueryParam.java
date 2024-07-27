package com.light.springinit.param.chart;

import com.light.springinit.common.PageRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * @author null&&
 * @Date 2024/7/20 20:10
 */
@Setter
@Getter
public class ChartQueryParam extends PageRequest {

    /**
     * chart_id
     */
    private String chartId;

    /**
     * 分析目标
     */
    private String goal;

    /**
     * 图表类型
     */
    private String chartType;

    /**
     * 提交用户 id
     */
    private String userId;
}
