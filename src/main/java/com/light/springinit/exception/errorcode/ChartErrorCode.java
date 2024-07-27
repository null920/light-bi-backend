package com.light.springinit.exception.errorcode;

/**
 * 图表错误码
 *
 * @author null&&
 * @Date 2024/7/16 16:46
 */
public enum ChartErrorCode implements ErrorCode {
    /**
     * 图表不存在
     */
    CHART_NOT_EXIST("CHART_NOT_EXIST", "图表不存在"),

    /**
     * 图表查询参数错误
     */
    CHART_QUERY_PARAM_ERROR("CHART_QUERY_PARAM_ERROR", "图表查询参数错误"),

    /**
     * 图表查询参数为空
     */
    CHART_QUERY_PARAM_IS_NULL("CHART_QUERY_PARAM_IS_NULL", "图表查询参数为空"),

    /**
     * 没有权限进行此操作
     */
    CHART_OPERATE_NO_AUTH("CHART_OPERATE_NO_AUTH", "没有权限进行此操作"),

    /**
     * 图表操作失败
     */
    CHART_OPERATE_FAILED("CHART_OPERATE_FAILED", "图表操作失败"),

    /**
     * 图表查询失败
     */
    CHART_QUERY_FAIL("CHART_QUERY_FAIL", "图表查询失败");


    private String code;

    private String message;

    ChartErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
