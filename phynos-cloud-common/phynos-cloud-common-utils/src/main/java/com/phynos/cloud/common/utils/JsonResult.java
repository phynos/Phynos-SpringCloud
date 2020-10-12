package com.phynos.cloud.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


public class JsonResult {

    private int status;

    private boolean ok;

    private String msg;

    //这个字段用来开发阶段传递一些错误信息给前端，方便调试（不必定义过多的错误码）
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String tip;

    @JsonProperty("data")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;

    public JsonResult() {
        status = 0;
        ok = true;
        msg = "ok";
    }

    // 错误时的构造器
    private JsonResult(int status, String msg) {
        this.status = status;
        this.msg = msg;
        if (this.status >= 10000 && this.status < 20000) {
            this.ok = false;
        } else {
            this.ok = true;
        }
    }

    private JsonResult(int status, String msg, String tip) {
        this(status, msg);
        this.tip = tip;
    }

    public static JsonResult ok() {
        return code(ResultCodeEnum.OK);
    }

    /**
     * 自定义msg，不再使用枚举默认的msg
     * @param error
     * @param message
     * @return
     */
    public static JsonResult codeMsg(ResultCodeEnum error, String message) {
        return code(error.getCode(), message);
    }

    public static JsonResult code(int code, String message) {
        JsonResult jr = new JsonResult(code, message);
        return jr;
    }

    public static JsonResult code(ResultCodeEnum error) {
        int code = error.getCode();
        String message = error.getMsg();
        JsonResult jr = new JsonResult(code, message);
        return jr;
    }

    public static JsonResult code(ResultCodeEnum error, String tip) {
        int code = error.getCode();
        String message = error.getMsg();
        JsonResult jr = new JsonResult(code, message, tip);
        return jr;
    }

    public static JsonResult data(Object data) {
        JsonResult jr = new JsonResult(
                ResultCodeEnum.OK.getCode(),
                ResultCodeEnum.OK.getMsg());
        jr.setData(data);
        return jr;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getTip() {
        return tip;
    }

    public boolean isOk() {
        if (this.status >= 10000 && this.status < 20000) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String s = JsonUtil.objectToString(this);
        return s == null ? "{}" : s;
    }

}