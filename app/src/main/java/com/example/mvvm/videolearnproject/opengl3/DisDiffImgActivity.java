package com.example.mvvm.videolearnproject.opengl3;

import android.os.Bundle;

import com.example.mvvm.videolearnproject.BaseActivity;

/**
 * author : 90589
 * date   : 2020/6/10
 * desc   : 描述
 */
public class DisDiffImgActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new DisDiffWord(this));
    }
}
