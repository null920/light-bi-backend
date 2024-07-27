package com.light.springinit.service.impl;


import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.light.springinit.common.PageRequest;
import com.light.springinit.common.PageResponse;
import com.light.springinit.common.response.ChartOperateResponse;
import com.light.springinit.common.response.ChartQueryResponse;
import com.light.springinit.common.response.ChartQueryResponse;
import com.light.springinit.constant.PageConstant;
import com.light.springinit.constant.UserRole;
import com.light.springinit.domain.dto.chart.ChartCreateRequest;
import com.light.springinit.domain.dto.chart.ChartQueryRequest;
import com.light.springinit.domain.dto.chart.ChartUpdateRequest;
import com.light.springinit.domain.entity.*;
import com.light.springinit.domain.entity.Chart;
import com.light.springinit.domain.entity.Chart;
import com.light.springinit.domain.entity.convertor.ChartConvertor;
import com.light.springinit.domain.entity.convertor.ChartConvertor;
import com.light.springinit.domain.entity.convertor.ChartConvertor;
import com.light.springinit.domain.entity.convertor.UserConvertor;
import com.light.springinit.domain.vo.ChartVO;
import com.light.springinit.domain.vo.ChartVO;
import com.light.springinit.domain.vo.ChartVO;
import com.light.springinit.domain.vo.ChartVO;
import com.light.springinit.exception.*;
import com.light.springinit.exception.ChartException;
import com.light.springinit.exception.ChartException;
import com.light.springinit.exception.errorcode.*;
import com.light.springinit.exception.errorcode.ChartErrorCode;
import com.light.springinit.exception.errorcode.ChartErrorCode;
import com.light.springinit.mapper.ChartMapper;
import com.light.springinit.service.ChartService;
import com.light.springinit.service.UserService;
import com.light.springinit.utils.SqlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ycri
 * @description 针对表【chart(图表信息表)】的数据库操作Service实现
 * @createDate 2024-07-26 19:38:54
 */
@Service
public class ChartServiceImpl extends ServiceImpl<ChartMapper, Chart>
        implements ChartService {
    @Autowired
    private UserService userService;

    @Autowired
    private ChartMapper chartMapper;

    /**
     * 创建图表
     *
     * @param chartCreateRequest 创建图表请求
     * @return 图表操作响应
     */
    @Override
    public ChartOperateResponse createChart(ChartCreateRequest chartCreateRequest) {
        Chart chart = new Chart();
        chart.create(chartCreateRequest);
        chart.setUserId(Long.valueOf((String) StpUtil.getLoginId()));
        ChartOperateResponse createChartResult = new ChartOperateResponse();
        if (save(chart)) {
            Chart findChart = chartMapper.findChartById(chart.getChartId());
            if (findChart == null) {
                throw new ChartException(ChartErrorCode.CHART_OPERATE_FAILED);
            }
            createChartResult.setSuccess(true);
        }
        return createChartResult;
    }

    /**
     * 获取当前用户图表列表（分页）
     *
     * @param pageRequest 分页参数
     * @return 图表列表
     */
    @Override
    public PageResponse<ChartVO> getMyPageChart(PageRequest pageRequest) {
        int currentPage = pageRequest.getCurrentPage();
        int pageSize = pageRequest.getPageSize();
        String sortField = pageRequest.getSortField();
        String sortOrder = pageRequest.getSortOrder();
        Long loginId = Long.valueOf((String) StpUtil.getLoginId());

        userService.checkUserExistAndStatus(loginId);
        // 构造查询条件
        QueryWrapper<Chart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", loginId);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(PageConstant.SORT_ORDER_ASC), sortField);

        Page<Chart> chartPage = chartMapper.selectPage(new Page<>(currentPage, pageSize), queryWrapper);
        // 如果用户没有图表
        if (chartPage.getTotal() == 0) {
            return PageResponse.of(null, 0, pageSize, currentPage);
        }
        // 关联图表创建者的信息
        List<ChartVO> pageChartResult = chartPage.getRecords()
                .stream()
                .map(chart -> {
                    ChartVO chartVO = ChartConvertor.INSTANCE.mapToVo(chart);
                    User chartUser = userService.findUserById(chart.getUserId());
                    chartVO.setUserVO(UserConvertor.INSTANCE.mapToVo(chartUser));
                    return chartVO;
                }).collect(Collectors.toList());
        return PageResponse.of(pageChartResult, (int) chartPage.getTotal(), pageSize, currentPage);
    }

    /**
     * 获取图表列表（分页）
     *
     * @param chartQueryRequest 查询图表请求
     * @return 图表列表
     */
    @Override
    public PageResponse<ChartVO> getPageChart(ChartQueryRequest chartQueryRequest) {
        int currentPage = chartQueryRequest.getCurrentPage();
        int pageSize = chartQueryRequest.getPageSize();
        String sortField = chartQueryRequest.getSortField();
        String sortOrder = chartQueryRequest.getSortOrder();
        Long userId = chartQueryRequest.getUserId();
        Long chartId = chartQueryRequest.getChartId();
        String chartType = chartQueryRequest.getChartType();
        String goal = chartQueryRequest.getGoal();

        // 构造查询条件
        QueryWrapper<Chart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(userId != null && userId > 0, "user_id", userId);
        queryWrapper.eq(chartId != null && chartId > 0, "chart_id", chartId);
        queryWrapper.eq(chartType != null && !chartType.isEmpty(), "chart_type", chartType);
        queryWrapper.eq(goal != null && !goal.isEmpty(), "goal", goal);

        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(PageConstant.SORT_ORDER_ASC), sortField);

        Page<Chart> chartPage = chartMapper.selectPage(new Page<>(currentPage, pageSize), queryWrapper);
        // 如果用户没有图表
        if (chartPage.getTotal() == 0) {
            return PageResponse.of(null, 0, pageSize, currentPage);
        }
        // 关联帖子创建者的信息
        List<ChartVO> pageChartResult = chartPage.getRecords()
                .stream()
                .map(chart -> {
                    ChartVO chartVO = ChartConvertor.INSTANCE.mapToVo(chart);
                    User chartUser = userService.findUserById(chart.getUserId());
                    chartVO.setUserVO(UserConvertor.INSTANCE.mapToVo(chartUser));
                    return chartVO;
                }).collect(Collectors.toList());
        return PageResponse.of(pageChartResult, (int) chartPage.getTotal(), pageSize, currentPage);
    }


    /**
     * 根据id 获取图表详情
     *
     * @param chartQueryRequest 查询图表请求
     * @return 图表详情
     */
    @Override
    public ChartQueryResponse<ChartVO> getChartById(ChartQueryRequest chartQueryRequest) {
        Chart chart;
        Long chartId = chartQueryRequest.getChartId();
        // 校验参数
        if (null == chartId) {
            throw new ChartException(ChartErrorCode.CHART_QUERY_PARAM_ERROR);
        }
        chart = chartMapper.findChartById(chartId);
        ChartQueryResponse<ChartVO> response = new ChartQueryResponse<>();
        // 校验帖子是否存在
        if (chart == null) {
            throw new ChartException(ChartErrorCode.CHART_NOT_EXIST);
        }
        response.setSuccess(true);
        ChartVO chartVO = ChartConvertor.INSTANCE.mapToVo(chart);
        User chartUser = userService.findUserById(chart.getUserId());
        chartVO.setUserVO(UserConvertor.INSTANCE.mapToVo(chartUser));
        response.setData(chartVO);
        return response;
    }

    /**
     * 更新图表
     *
     * @param chartUpdateRequest 更新图表请求
     * @return 更新结果
     */
    @Override
    public Boolean updateChart(ChartUpdateRequest chartUpdateRequest) {
        Long chartId = chartUpdateRequest.getChartId();
        Long loginId = Long.valueOf((String) StpUtil.getLoginId());
        Chart chartById = chartMapper.findChartById(chartId);
        User userById = userService.findUserById(loginId);
        // 校验帖子是否存在
        if (chartById == null) {
            throw new ChartException(ChartErrorCode.CHART_NOT_EXIST);
        }
        // 校验用户状态
        if (userById.getUserRole() == UserRole.BAN) {
            throw new UserException(UserErrorCode.USER_STATUS_IS_BAN);
        }
        // 校验权限
        if (!chartById.getUserId().equals(loginId) && !userById.getUserRole().equals(UserRole.ADMIN)) {
            throw new ChartException(ChartErrorCode.CHART_OPERATE_NO_AUTH);
        }
        Chart chart = new Chart();
        chart.update(chartUpdateRequest);
        if (chartMapper.updateById(chart) == 0) {
            throw new ChartException(ChartErrorCode.CHART_OPERATE_FAILED);
        }
        return true;
    }

    @Override
    public Boolean deleteChart(Long chartId) {
        Long loginId = Long.valueOf((String) StpUtil.getLoginId());
        Chart chartById = chartMapper.findChartById(chartId);
        User userById = userService.findUserById(loginId);
        // 校验帖子是否存在
        if (chartById == null) {
            throw new ChartException(ChartErrorCode.CHART_NOT_EXIST);
        }
        // 校验用户状态
        if (userById.getUserRole() == UserRole.BAN) {
            throw new UserException(UserErrorCode.USER_STATUS_IS_BAN);
        }
        // 校验权限
        if (!chartById.getUserId().equals(loginId) && !userById.getUserRole().equals(UserRole.ADMIN)) {
            throw new ChartException(ChartErrorCode.CHART_OPERATE_NO_AUTH);
        }
        if (chartMapper.deleteById(chartId) == 0) {
            throw new ChartException(ChartErrorCode.CHART_OPERATE_FAILED);
        }
        return true;
    }
}




