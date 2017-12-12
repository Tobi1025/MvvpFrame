package com.qjf.sample.model.http.response;

import java.util.Map;

/**
 * Created by qiaojingfei on 2017/11/17.
 */

public class NewsHttpResponse {

    String reason;

    Map<String, Object> result;

    public Map<String, Object> getResult() {
        return result;
    }

    public String getReason() {
        return reason;
    }
}
