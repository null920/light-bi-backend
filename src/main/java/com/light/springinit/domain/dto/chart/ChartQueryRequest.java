package com.light.springinit.domain.dto.chart;

import com.light.springinit.common.PageRequest;
import lombok.*;

/**
 * 帖子查询请求
 *
 * @author null&&
 * @Date 2024/7/20 19:47
 */

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ChartQueryRequest extends PageRequest {

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
     * 图表类型
     */
    private String chartType;

    /**
     * 提交用户 id
     */
    private Long userId;
}
