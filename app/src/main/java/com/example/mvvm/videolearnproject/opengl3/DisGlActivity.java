package com.example.mvvm.videolearnproject.opengl3;

import android.os.Bundle;

import com.example.mvvm.videolearnproject.BaseActivity;

/**
 * author : 90589
 * date   : 2020/6/9
 * desc   : 描述
 */
public class DisGlActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new DisGlViewWord(this));
    }
}
