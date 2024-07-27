package com.light.springinit.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.light.springinit.common.PageRequest;
import com.light.springinit.common.PageResponse;
import com.light.springinit.common.response.ChartOperateResponse;
import com.light.springinit.common.response.ChartQueryResponse;
import com.light.springinit.common.result.MultiResult;
import com.light.springinit.common.result.Result;
import com.light.springinit.domain.dto.chart.ChartCreateRequest;
import com.light.springinit.domain.dto.chart.ChartQueryRequest;
import com.light.springinit.domain.dto.chart.ChartUpdateRequest;
import com.light.springinit.domain.entity.convertor.MultiResultConvertor;
import com.light.springinit.domain.vo.ChartVO;
import com.light.springinit.exception.errorcode.ChartErrorCode;
import com.light.springinit.param.chart.ChartCreateParam;
import com.light.springinit.param.chart.ChartQueryParam;
import com.light.springinit.param.chart.ChartUpdateParam;
import com.light.springinit.service.ChartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.stream.Stream;

/**
 * @author null&&
 * @Date 2024/7/20 22:26
 */
@RestController
@RequestMapping("chart")
@RequiredArgsConstructor
@Slf4j
public class ChartController {

    @Autowired
    private ChartService chartService;

    @PostMapping("/create")
    @SaCheckLogin
    public Result<Boolean> createChart(@Valid @RequestBody ChartCreateParam chartCreateParam) {
        ChartCreateRequest chartCreateRequest = new ChartCreateRequest();
        chartCreateRequest.setGoal(chartCreateParam.getGoal());
        chartCreateRequest.setChartType(chartCreateParam.getChartType());
        chartCreateRequest.setChartData(chartCreateParam.getChartData());
        ChartOperateResponse createChartResult = chartService.createChart(chartCreateRequest);
        if (createChartResult.getSuccess() != null) {
            return Result.success(true);
        }
        return Result.error(createChartResult.getResponseCode(), createChartResult.getResponseMessage());
    }

    /**
     * 获取当前用户图表列表（分页）
     *
     * @param pageRequest 分页参数
     * @return 图表列表
     */
    @PostMapping("/list/my")
    @SaCheckLogin
    public MultiResult<ChartVO> getMyPageChart(@RequestBody PageRequest pageRequest) {
        if (pageRequest.getPageSize() == 0) {
            pageRequest.setPageSize(10);
            if (pageRequest.getCurrentPage() == 0) {
                pageRequest.setCurrentPage(1);
            }
        }
        PageResponse<ChartVO> myPageChartResult = chartService.getMyPageChart(pageRequest);
        if (myPageChartResult.getSuccess() != null) {
            return MultiResultConvertor.convert(myPageChartResult);
        }
        return MultiResult.errorMulti(myPageChartResult.getResponseCode(), myPageChartResult.getResponseMessage());
    }

    /**
     * 获取图表列表（分页，默认获取第 1 页，1 页10 条）
     *
     * @param chartQueryParam 图表查询参数
     * @return 图表列表
     */
    @PostMapping("/list")
    @SaCheckLogin
    public MultiResult<ChartVO> getPageChart(@Valid @RequestBody ChartQueryParam chartQueryParam) {
        String chartId = chartQueryParam.getChartId();
        String goal = chartQueryParam.getGoal();
        String chartType = chartQueryParam.getChartType();
        String userId = chartQueryParam.getUserId();
        int currentPage = chartQueryParam.getCurrentPage();
        int pageSize = chartQueryParam.getPageSize();
        String sortField = chartQueryParam.getSortField();
        String sortOrder = chartQueryParam.getSortOrder();

//        boolean anyNonBlank = Stream.of(chartId, goal, chartType, userId).anyMatch(StringUtils::isNotBlank);
//        if (!anyNonBlank) {
//            return MultiResult.errorMulti(ChartErrorCode.CHART_QUERY_PARAM_IS_NULL.getCode(), ChartErrorCode.CHART_QUERY_PARAM_IS_NULL.getMessage());
//        }
        ChartQueryRequest chartQueryRequest = new ChartQueryRequest();
        if (pageSize <= 0) {
            chartQueryRequest.setPageSize(10);
            if (currentPage <= 0) {
                chartQueryRequest.setCurrentPage(1);
            }
        }
        if (chartId.isEmpty()) {
            chartQueryRequest.setChartId(null);
        } else {
            chartQueryRequest.setChartId(Long.valueOf(chartId));
        }
        if (userId.isEmpty()) {
            chartQueryRequest.setUserId(null);
        } else {
            chartQueryRequest.setUserId(Long.valueOf(userId));
        }
        chartQueryRequest.setGoal(goal);
        chartQueryRequest.setChartType(chartType);
        chartQueryRequest.setSortField(sortField);
        chartQueryRequest.setSortOrder(sortOrder);
        PageResponse<ChartVO> pageChartByUserIdResult = chartService.getPageChart(chartQueryRequest);
        if (pageChartByUserIdResult.getSuccess() != null) {
            return MultiResultConvertor.convert(pageChartByUserIdResult);
        }
        return MultiResult.errorMulti(pageChartByUserIdResult.getResponseCode(), pageChartByUserIdResult.getResponseMessage());
    }

    @GetMapping("/{chartId}")
    public Result<ChartVO> getChartById(@PathVariable("chartId") @NotBlank(message = "图表id不能为空") String chartId) {
        if (chartId == null) {
            return Result.error(ChartErrorCode.CHART_QUERY_PARAM_IS_NULL.getCode(), ChartErrorCode.CHART_QUERY_PARAM_IS_NULL.getMessage());
        }
        ChartQueryRequest chartQueryRequest = new ChartQueryRequest();
        chartQueryRequest.setChartId(Long.valueOf(chartId));
        ChartQueryResponse<ChartVO> chartByIdResult = chartService.getChartById(chartQueryRequest);
        if (chartByIdResult.getSuccess() != null) {
            return Result.success(chartByIdResult.getData());
        }
        return Result.error(chartByIdResult.getResponseCode(), chartByIdResult.getResponseMessage());
    }

    @PostMapping("/update")
    @SaCheckLogin
    public Result<Boolean> updateChart(@Valid @RequestBody ChartUpdateParam chartUpdateParam) {
        ChartUpdateRequest chartUpdateRequest = new ChartUpdateRequest();
        chartUpdateRequest.setChartId(Long.valueOf(chartUpdateParam.getChartId()));
        chartUpdateRequest.setGoal(chartUpdateParam.getGoal());
        chartUpdateRequest.setChartType(chartUpdateParam.getChartType());
        chartUpdateRequest.setChartData(chartUpdateParam.getChartData());
        Boolean updateResult = chartService.updateChart(chartUpdateRequest);
        if (Boolean.TRUE.equals(updateResult)) {
            return Result.success(true);
        }
        return Result.error(ChartErrorCode.CHART_OPERATE_FAILED.getCode(), ChartErrorCode.CHART_OPERATE_FAILED.getMessage());
    }

    @DeleteMapping("/delete")
    @SaCheckLogin
    public Result<Boolean> deleteChart(@Valid @RequestParam(value = "chartId") @NotBlank(message = "图表id不能为空") String chartId) {
        Boolean deleteResult = chartService.deleteChart(Long.valueOf(chartId));
        if (Boolean.TRUE.equals(deleteResult)) {
            return Result.success(true);
        }
        return Result.error(ChartErrorCode.CHART_OPERATE_FAILED.getCode(), ChartErrorCode.CHART_OPERATE_FAILED.getMessage());
    }
}
