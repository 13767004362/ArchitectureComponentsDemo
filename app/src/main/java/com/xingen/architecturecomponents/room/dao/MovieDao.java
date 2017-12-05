package com.xingen.architecturecomponents.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.xingen.architecturecomponents.room.bean.MovieBean;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by ${新根} on 2017/11/25.
 * blog:http://blog.csdn.net/hexingen
 *
 * 增删查改SQL的DAO层：
 *
 * 用于操作数据库中的一个表
 */
@Dao
public interface MovieDao {
    /**
     * 查询movies表中全部数据，且返回RxJava2 中Flowable对象
     * @return
     */
    @Query("select * from movies")
    Flowable<List<MovieBean>> getMovieList();

    /**
     * 插入全部数据
     */
    @Insert
    void insertMovieList(List<MovieBean> movieBeans);
    /**
     * 删除movies表中全部数据
     */
    @Query("delete from movies")
    void deleteAllMovies();
}
