package com.xingen.architecturecomponents.room;

import com.xingen.architecturecomponents.MovieDataSource;
import com.xingen.architecturecomponents.room.bean.MovieBean;
import com.xingen.architecturecomponents.room.dao.MovieDao;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by ${新根} on 2017/11/25.
 * blog:http://blog.csdn.net/hexingen
 *
 *  一个Dao对象操作数据库的管理类
 */

public class LocalMovieDataSource implements MovieDataSource {
   private MovieDao movieDao;

    public LocalMovieDataSource(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    @Override
    public Flowable<List<MovieBean>> getMovieList() {
        return movieDao.getMovieList();
    }
    @Override
    public void insertMovieList(List<MovieBean> movieBeans) {
        movieDao.insertMovieList(movieBeans);
    }
    @Override
    public void deleteAllMovies() {
        movieDao.deleteAllMovies();
    }
}
