package com.example.retrofitexample.app;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.retrofitexample.R;
import com.example.retrofitexample.common.BaseActivity;
import com.example.retrofitexample.databinding.MainActivityBinding;

public class MainActivity extends BaseActivity<MainViewModel, MainActivityBinding> {

    public MainActivity(){
        super(MainViewModel.class, R.layout.main_activity);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getViewModel().getListMovie().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                getBinding().setText(s);
            }
        });
    }
}
