package com.light.springinit.domain.dto.chart;

import com.light.springinit.common.BaseRequest;
import lombok.*;

/**
 * 帖子创建请求
 *
 * @author null&&
 * @Date 2024/7/20 19:36
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ChartCreateRequest extends BaseRequest {

    private static final long serialVersionUID = 1L;

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
