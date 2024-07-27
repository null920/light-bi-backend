package com.light.springinit.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.light.springinit.common.PageRequest;
import com.light.springinit.common.PageResponse;
import com.light.springinit.common.response.ChartOperateResponse;

import com.light.springinit.common.response.ChartQueryResponse;
import com.light.springinit.domain.dto.chart.ChartCreateRequest;
import com.light.springinit.domain.dto.chart.ChartQueryRequest;
import com.light.springinit.domain.dto.chart.ChartUpdateRequest;
import com.light.springinit.domain.entity.Chart;
import com.light.springinit.domain.vo.ChartVO;

/**
 * @author Ycri
 * @description 针对表【chart(图表信息表)】的数据库操作Service
 * @createDate 2024-07-26 19:38:54
 */
public interface ChartService extends IService<Chart> {

    /**
     * 创建图表
     *
     * @param chartCreateRequest 创建图表请求
     * @return 图表操作响应
     */
    ChartOperateResponse createChart(ChartCreateRequest chartCreateRequest);


    /**
     * 获取当前用户图表列表（分页）
     *
     * @param pageRequest 分页参数
     * @return 图表列表
     */
    PageResponse<ChartVO> getMyPageChart(PageRequest pageRequest);


    /**
     * 获取所有图表列表（分页）
     *
     * @param chartQueryRequest 查询图表请求
     * @return 图表列表
     */
    PageResponse<ChartVO> getPageChart(ChartQueryRequest chartQueryRequest);

    /**
     * 根据id 获取图表详情
     *
     * @param chartQueryRequest 查询图表请求
     * @return 图表详情
     */
    ChartQueryResponse<ChartVO> getChartById(ChartQueryRequest chartQueryRequest);

    /**
     * 更新图表
     *
     * @param chartUpdateRequest 更新图表请求
     * @return 更新结果
     */
    Boolean updateChart(ChartUpdateRequest chartUpdateRequest);

    /**
     * 删除图表
     *
     * @param chartId 删除图表参数
     * @return 删除结果
     */
    Boolean deleteChart(Long chartId);
}
