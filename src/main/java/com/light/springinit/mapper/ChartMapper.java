package com.light.springinit.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.light.springinit.domain.entity.Chart;

import java.util.List;

/**
 * @author Ycri
 * @description 针对表【chart(图表信息表)】的数据库操作Mapper
 * @createDate 2024-07-26 19:38:54
 * @Entity generator.domain.Chart
 */
public interface ChartMapper extends BaseMapper<Chart> {

    /**
     * 根据帖子id 查询帖子
     *
     * @param postId 帖子id
     * @return 帖子详情
     */
    Chart findChartById(Long postId);

    /**
     * 根据用户id 查询所有帖子
     *
     * @param userId 用户id
     * @return 帖子列表
     */
    List<Chart> findPageChartByUserId(Long userId);
}




