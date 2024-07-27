package com.light.springinit.param.chart;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author null&&
 * @Date 2024/7/20 20:01
 */
@Setter
@Getter
public class ChartUpdateParam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * chart_id
     */
    @NotBlank(message = "图表id不能为空")
    private String chartId;

    /**
     * 分析目标
     */
    @NotBlank(message = "分析目标不能为空")
    private String goal;

    /**
     * 图表数据
     */
    @NotBlank(message = "图表数据不能为空")
    private String chartData;

    /**
     * 图表类型
     */
    @NotBlank(message = "图表类型不能为空")
    private String chartType;
}
