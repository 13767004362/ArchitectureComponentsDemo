package com.xingen.architecturecomponents.room.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.xingen.architecturecomponents.room.dao.MovieDao;
import com.xingen.architecturecomponents.room.bean.MovieBean;

/**
 * Created by ${新根} on 2017/11/25.
 * blog:http://blog.csdn.net/hexingen
 * <p>
 * 创建一个RoomDatabase抽象子类:
 * <p>
 * 1. 在这里可以指定指定数据库表对应实体
 * 2. 数据库版本
 * 3. 创建数据库的操作
 */
@Database(entities = {MovieBean.class},version = 1)
public abstract class MovieDatabase extends RoomDatabase {
    /**
     * 获取Dao对象
     *
     * @return
     */
    public abstract MovieDao movieDao();

    /**
     * 单例类MovieDatabase，同步获取对象
     */
    private static volatile MovieDatabase instance;

    public static MovieDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (MovieDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(), MovieDatabase.class, "movies.db").build();
                }
            }
        }
        return instance;
    }
}
