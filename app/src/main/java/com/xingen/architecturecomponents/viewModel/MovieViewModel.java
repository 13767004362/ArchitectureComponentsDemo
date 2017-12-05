package com.xingen.architecturecomponents.viewModel;

import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.xingen.architecturecomponents.MovieDataSource;
import com.xingen.architecturecomponents.net.retrofit.RetrofitClient;
import com.xingen.architecturecomponents.room.bean.MovieBean;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by ${新根} on 2017/11/25.
 * blog:http://blog.csdn.net/hexingen
 * <p>
 * View Model对应一个Activity或者Fragment
 */
public class MovieViewModel extends ViewModel {
    private MovieDataSource movieDataSource;
    private RetrofitClient retrofitClient;
    private List<MovieBean> movieBeanList;
    private final String tag = MovieViewModel.class.getSimpleName();

    public MovieViewModel(MovieDataSource movieDataSource, RetrofitClient retrofitClient) {
        this.movieDataSource = movieDataSource;
        this.retrofitClient = retrofitClient;
    }

    /**
     * 采用内存，数据库，磁盘逐渐获取
     *
     * @return
     */
    public Flowable<List<MovieBean>> getMovieList() {
        Log.i(tag, "开始获取电影数据");
        return  searDB();
    }
    private Flowable<List<MovieBean>> searDB(){
        return movieDataSource.getMovieList().flatMap( list -> {
            Log.i(tag, "数据库中获取");
            if (list==null||list.size()==0){
                return  searchNet();
            }else{
                movieBeanList=list;
            }
            return Flowable.just(list);
        });
    }
    /**
     * 若是数据库中无，则从网络中获取
     * 最后，写入数据库和内存
     *
     * @return
     */
    private Flowable<List<MovieBean>> searchNet() {
        return retrofitClient.getMovieList().flatMap(movieBeen -> {
            Log.i(tag, "网络中获取");
            if (movieBeen.getSubjects().size() > 0) {
                movieBeanList = movieBeen.getSubjects();
                for (MovieBean bean : movieBeen.getSubjects()) {
                    bean.setImage(bean.getImages().getLarge());
                }
                movieDataSource.insertMovieList(movieBeen.getSubjects());
            }
            return Flowable.just(movieBeen.getSubjects());
        });
    }
}
