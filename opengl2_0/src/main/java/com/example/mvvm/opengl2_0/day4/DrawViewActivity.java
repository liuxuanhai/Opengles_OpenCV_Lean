package com.example.mvvm.opengl2_0.day4;

import androidx.appcompat.app.AppCompatActivity;

import android.opengl.GLSurfaceView;
import android.os.Bundle;

import com.example.mvvm.opengl2_0.R;

public class DrawViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int num=getIntent().getIntExtra("num",-1);

        ViewWorld world=new ViewWorld(DrawViewActivity.this,num);
        world.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
        setContentView(world);
    }
}
