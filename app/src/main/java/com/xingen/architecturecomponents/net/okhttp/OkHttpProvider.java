package com.xingen.architecturecomponents.net.okhttp;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by ${新根} on 2017/12/4.
 * blog:http://blog.csdn.net/hexingen
 */

public class OkHttpProvider {
    /**
     * 自定义配置OkHttpClient
     * @return
     */
    public static OkHttpClient createOkHttpClient(){
        OkHttpClient.Builder builder=new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor();
        //打印一次请求的全部信息
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(loggingInterceptor);
        return builder.build();
    }
}
