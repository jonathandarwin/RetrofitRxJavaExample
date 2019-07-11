package com.example.retrofitexample.app.movie;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import com.example.retrofitexample.R;
import com.example.retrofitexample.base.BaseActivity;
import com.example.retrofitexample.databinding.MainActivityBinding;
import com.example.retrofitexample.model.Movie;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MovieActivity extends BaseActivity<MovieViewModel, MainActivityBinding> {

    MovieAdapter adapter;
    List<Movie> listMovie;

    public MovieActivity(){
        super(MovieViewModel.class, R.layout.main_activity);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestMovie("social");
    }

    @Override
    protected void setListener() {
        super.setListener();
        getBinding().txtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                requestMovie(s.toString());
            }
        });
    }

    @Override
    protected void setAdapter() {
        super.setAdapter();
        listMovie = new ArrayList<>();
        adapter = new MovieAdapter(this, listMovie);
        getBinding().recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getBinding().recyclerView.setHasFixedSize(true);
        getBinding().recyclerView.setAdapter(adapter);
    }

    private void requestMovie(String search){
        getBinding().loading.setVisibility(View.VISIBLE);
        listMovie.clear();
        adapter.notifyDataSetChanged();

        getViewModel().getListMovieObservable(search)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(movies -> {
                listMovie.clear();
                getBinding().loading.setVisibility(View.GONE);
                getBinding().setResult("Show " + movies.size() + " result(s)");
                if(movies.size() > 0){
                    listMovie.addAll(movies);
                    adapter.notifyDataSetChanged();
                }
            }, error -> {
                listMovie.clear();
                getBinding().loading.setVisibility(View.GONE);
                getBinding().setResult("Show 0 result(s)");
            });
    }
}