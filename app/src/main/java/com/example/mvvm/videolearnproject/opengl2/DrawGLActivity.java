package com.example.mvvm.videolearnproject.opengl2;

import android.os.Bundle;

import com.example.mvvm.videolearnproject.BaseActivity;

/**
 * author : 90589
 * date   : 2020/6/9
 * desc   : 描述
 */
public class DrawGLActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new DrawGLWord(this));
    }
}
