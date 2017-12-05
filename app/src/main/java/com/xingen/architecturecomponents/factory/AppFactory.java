package com.xingen.architecturecomponents.factory;

import android.content.Context;

import com.xingen.architecturecomponents.MovieDataSource;
import com.xingen.architecturecomponents.net.retrofit.RetrofitClient;
import com.xingen.architecturecomponents.room.LocalMovieDataSource;
import com.xingen.architecturecomponents.room.db.MovieDatabase;
import com.xingen.architecturecomponents.viewModel.ViewModelFactory;

/**
 * Created by ${新根} on 2017/11/25.
 * blog:http://blog.csdn.net/hexingen
 * 静态工厂提供对象
 */

public class AppFactory {
    /**
     *创建MovieDataSource 对象
     * @param context
     * @return
     */
    public static MovieDataSource provideMovieDataSource(Context context){
        MovieDatabase database=MovieDatabase.getInstance(context);
        return  new LocalMovieDataSource(database.movieDao());
    }

    /**
     * 创建ViewModelFactory
     * @param context
     * @return
     */
    public static ViewModelFactory providerViewModelFactory(Context context){
        MovieDataSource dataSource=provideMovieDataSource(context);
        RetrofitClient retrofitClient=providerRetrofitClient();
        return  new ViewModelFactory(dataSource,retrofitClient);
    }

    /**
     * 创建Retrofit单例类
     * @return
     */
    public static RetrofitClient providerRetrofitClient(){
        return RetrofitClient.getInstance();
    }
}
