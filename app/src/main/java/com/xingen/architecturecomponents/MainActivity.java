package com.xingen.architecturecomponents;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.xingen.architecturecomponents.adapter.MovieListAdapter;
import com.xingen.architecturecomponents.factory.AppFactory;
import com.xingen.architecturecomponents.view.ScrollChildSwipeRefreshLayout;
import com.xingen.architecturecomponents.viewModel.MovieViewModel;
import com.xingen.architecturecomponents.viewModel.ViewModelFactory;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private ViewModelFactory viewModelFactory;
    private MovieViewModel movieViewModel;
    private RecyclerView recyclerView;
    private ScrollChildSwipeRefreshLayout refreshLayout;
    private MovieListAdapter movieListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initView();
    }
    /***
     * 初始化控件
     */
    private void initView() {
        this.recyclerView = findViewById(R.id.main_recyclerView);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.movieListAdapter = new MovieListAdapter();
        this.recyclerView.setAdapter(this.movieListAdapter);
        this.refreshLayout = findViewById(R.id.main_swipeRefreshLayout);
        this.refreshLayout.setDefalutColor();
        this.refreshLayout.showIndicator(true);
        this.refreshLayout.setScrollUpChild(recyclerView);
        this.refreshLayout.setOnRefreshListener(this);
        this.onRefresh();
    }
    /**
     * 初始化
     */
    private void init() {
        this.viewModelFactory = AppFactory.providerViewModelFactory(this);
        this.movieViewModel = ViewModelProviders.of(this, this.viewModelFactory).get(MovieViewModel.class);
    }
    /**
     * 加载数据
     */
    private void loadData() {
        Disposable disposable = this.movieViewModel.getMovieList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movieBeen -> {
                            this.movieListAdapter.changeData(movieBeen);
                            this.refreshLayout.showIndicator(false);
                        }, error -> {
                            this.refreshLayout.showIndicator(false);
                            showToast(error.getMessage());
                        },()->{
                          this.refreshLayout.showIndicator(false);
                         showToast("执行完成");
                        }
                );
        this.compositeDisposable.add(disposable);
    }
    /**
     * Toast弹窗提示
     *
     * @param content
     */
    private void showToast(String content) {
        Toast.makeText(getApplicationContext(), content, Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onStop() {
        super.onStop();
        compositeDisposable.clear();
    }
    @Override
    public void onRefresh() {
        loadData();
    }
}
