package com.example.mvvm.videolearnproject;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

/**
 * author : 90589
 * date   : 2020/6/9
 * desc   : 描述
 */
public  class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getContentViewId()!=-1){
            setContentView(getContentViewId());
        }else {
            setContentView(R.layout.activity_main);
        }

    }

    public int getContentViewId(){
        return -1;
    }
}
