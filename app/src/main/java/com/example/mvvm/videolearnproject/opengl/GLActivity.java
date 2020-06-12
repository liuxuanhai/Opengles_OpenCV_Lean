package com.example.mvvm.videolearnproject.opengl;

import android.os.Bundle;

import com.example.mvvm.videolearnproject.BaseActivity;

/**
 * author : 90589
 * date   : 2020/6/9
 * desc   : 描述
 */
public class GLActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(new GLWorld(this));
//        setContentView(new PointGLWorld(this));
        setContentView(new GLLineWorld(this));
    }
}
