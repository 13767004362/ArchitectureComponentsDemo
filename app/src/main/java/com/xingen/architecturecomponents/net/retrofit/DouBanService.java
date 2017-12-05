package com.xingen.architecturecomponents.net.retrofit;

import com.xingen.architecturecomponents.net.retrofit.bean.MovieBeanList;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by ${新根} on 2017/12/4.
 * blog:http://blog.csdn.net/hexingen
 */

public interface DouBanService {

    @GET("{path}")
    Flowable<MovieBeanList> movieList(@Path("path") String path, @QueryMap Map<String, String> options);
}
