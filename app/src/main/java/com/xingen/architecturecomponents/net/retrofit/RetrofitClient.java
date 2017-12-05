package com.xingen.architecturecomponents.net.retrofit;

import com.xingen.architecturecomponents.net.okhttp.OkHttpProvider;
import com.xingen.architecturecomponents.net.retrofit.bean.MovieBeanList;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ${新根} on 2017/12/4.
 * blog:http://blog.csdn.net/hexingen
 */

public class RetrofitClient {
    private final String BASE_URL = "https://api.douban.com/v2/movie/";
    private final Retrofit retrofit;
    private static RetrofitClient instance;
    private DouBanService service;
    private RetrofitClient(){
       OkHttpClient okHttpClient = OkHttpProvider.createOkHttpClient();
        this.retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        this.service=retrofit.create(DouBanService.class);
    }
    public synchronized static RetrofitClient getInstance(){
        if (instance==null){
            instance=new RetrofitClient();
        }
        return instance;
    }

    public Flowable<MovieBeanList> getMovieList(){
        String url = "search";
        Map<String,String> map=new HashMap<>();
        map.put("q","张艺谋");
        return this.service.movieList(url,map);
    }



}
