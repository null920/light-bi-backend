package com.light.springinit.domain.entity.convertor;


import com.light.springinit.domain.entity.Chart;
import com.light.springinit.domain.info.ChartInfo;
import com.light.springinit.domain.vo.ChartVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

/**
 * @author null&&
 * @Date 2024/7/17 17:41
 */
@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ChartConvertor {
    ChartConvertor INSTANCE = Mappers.getMapper(ChartConvertor.class);

    /**
     * 转换为vo
     *
     * @param request
     * @return
     */
    @Mapping(target = "chartId", source = "request.chartId")
    public ChartVO mapToVo(Chart request);

    /**
     * 转换为实体
     *
     * @param request
     * @return
     */
    @Mapping(target = "chartId", source = "request.chartId")
    public Chart mapToEntity(ChartInfo request);
}
