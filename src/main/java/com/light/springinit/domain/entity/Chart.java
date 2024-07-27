package com.light.springinit.domain.entity;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.annotation.*;
import com.light.springinit.domain.dto.PostCreateRequest;
import com.light.springinit.domain.dto.PostUpdateRequest;
import com.light.springinit.domain.dto.chart.ChartCreateRequest;
import com.light.springinit.domain.dto.chart.ChartUpdateRequest;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 图表信息表
 *
 * @TableName chart
 */
@TableName(value = "chart")
@Data
public class Chart implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * chart_id
     */
    @TableId(type = IdType.ASSIGN_ID)
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
     * 提交用户 id
     */
    private Long userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除
     */
    @TableLogic
    private Integer deleted;


    public Chart create(ChartCreateRequest chartCreateRequest) {
        this.setGoal(chartCreateRequest.getGoal());
        this.setChartData(chartCreateRequest.getChartData());
        this.setChartType(chartCreateRequest.getChartType());
        return this;
    }

    public Chart update(ChartUpdateRequest chartUpdateRequest) {
        this.setChartId(chartUpdateRequest.getChartId());
        this.setGoal(chartUpdateRequest.getGoal());
        this.setChartData(chartUpdateRequest.getChartData());
        this.setChartType(chartUpdateRequest.getChartType());
        return this;
    }
}