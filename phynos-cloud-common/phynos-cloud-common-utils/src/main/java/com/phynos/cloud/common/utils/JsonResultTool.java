package com.phynos.cloud.common.utils;

/**
 * @author by Lupc
 * @date 2020/3/20.
 */
public class JsonResultTool<T> {

    private ResultCodeEnum resultCodeEnum;

    private T data;

    public JsonResultTool(ResultCodeEnum resultCodeEnum, T data) {
        this.resultCodeEnum = resultCodeEnum;
        this.data = data;
    }

    public ResultCodeEnum getResultCodeEnum() {
        return resultCodeEnum;
    }

    public void setResultCodeEnum(ResultCodeEnum resultCodeEnum) {
        this.resultCodeEnum = resultCodeEnum;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
