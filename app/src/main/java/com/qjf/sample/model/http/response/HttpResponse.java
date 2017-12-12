package com.qjf.sample.model.http.response;

import java.util.Map;

/**
 * Created by qiaojingfei on 2017/11/1.
 */

public class HttpResponse {
    /**
     * 状态码
     */
    int code;
    /**
     * 错误信息
     */
    String message;

    /**
     * 返回结果数据
     */
    Map<String, Object> result; // 返回json对象


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getResult() {
        return result;
    }

    public void setResult(Map<String, Object> result) {
        this.result = result;
    }

}
