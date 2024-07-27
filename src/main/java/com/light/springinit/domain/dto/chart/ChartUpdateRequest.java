package com.light.springinit.domain.dto.chart;

import com.light.springinit.common.BaseRequest;
import lombok.*;

import java.util.List;

/**
 * @author null&&
 * @Date 2024/7/20 20:05
 */

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ChartUpdateRequest extends BaseRequest {

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
}
