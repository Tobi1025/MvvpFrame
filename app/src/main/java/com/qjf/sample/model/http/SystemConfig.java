package com.qjf.sample.model.http;

/**
 * Created by qiaojingfei on 2017/11/1.
 */

public class SystemConfig {
    /**
     * 测试环境
     * http://api.svipmovie.com/front/
     * http://120.77.209.164:8090/gbidding-mobile/
     */
    public static final String Test_HOST = "http://120.77.209.164:8090/gbidding-mobile/";

    /**
     * 接口测试环境地址
     */
    public static  final String News_Host = "http://v.juhe.cn/toutiao/";
    /**
     * 当前服务器环境，只切换这里即可
     */
    public static final String HOST = Test_HOST;
    /**
     * 是否显示Log
     */
    public static final boolean LOGFLAG = true;
}
