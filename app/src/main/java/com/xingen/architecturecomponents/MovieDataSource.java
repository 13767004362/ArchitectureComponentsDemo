package com.xingen.architecturecomponents;

import com.xingen.architecturecomponents.room.bean.MovieBean;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by ${新根} on 2017/11/25.
 * blog:http://blog.csdn.net/hexingen
 *
 * 一个数据的管理类,即数据源的入口
 *
 * 1. 可以是网络数据
 * 2. 可以是本地数据库
 */

public interface MovieDataSource {
    Flowable<List<MovieBean>> getMovieList();
    void insertMovieList(List<MovieBean> movieBeans);
    void deleteAllMovies();
}
