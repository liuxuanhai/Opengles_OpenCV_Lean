package com.example.mvvm.opengl2_0.day1;

import androidx.appcompat.app.AppCompatActivity;

import android.opengl.GLSurfaceView;
import android.os.Bundle;

public class Day1MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int num = getIntent().getIntExtra("num", -1);
        GLView glView = new GLView(this, num);



        setContentView(glView);
    }
}
