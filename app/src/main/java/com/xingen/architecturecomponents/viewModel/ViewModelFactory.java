package com.xingen.architecturecomponents.viewModel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.xingen.architecturecomponents.MovieDataSource;
import com.xingen.architecturecomponents.net.retrofit.RetrofitClient;

/**
 * Created by ${新根} on 2017/11/25.
 * blog:http://blog.csdn.net/hexingen
 *
 * 创建ViewModelProvider.Factory 的实现类，
 * 用于创建指定的 ViewModel
 */

public class ViewModelFactory implements ViewModelProvider.Factory {
    private final MovieDataSource movieDataSource;
    private final RetrofitClient retrofitClient;
    public ViewModelFactory(MovieDataSource movieDataSource,RetrofitClient retrofitClient) {
        this.movieDataSource = movieDataSource;
        this.retrofitClient=retrofitClient;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MovieViewModel.class)){
            return (T) new MovieViewModel(movieDataSource,retrofitClient);
        }
        return null;
    }
}
