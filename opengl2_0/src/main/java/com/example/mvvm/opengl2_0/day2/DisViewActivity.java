package com.example.mvvm.opengl2_0.day2;

import androidx.appcompat.app.AppCompatActivity;

import android.opengl.GLSurfaceView;
import android.os.Bundle;

public class DisViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int num=getIntent().getIntExtra("num",-1);
        DisGLView disGLView=new DisGLView(DisViewActivity.this,num);
        disGLView.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
        setContentView(disGLView);
    }
}
