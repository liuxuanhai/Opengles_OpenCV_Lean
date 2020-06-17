package com.example.mvvm.opengl2_0.day3;

import androidx.appcompat.app.AppCompatActivity;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.Log;

public class Day3DisViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int num=getIntent().getIntExtra("num",-1);
        Log.i("-->Day3DisViewActivity","num==>"+num);
        World world=new World(Day3DisViewActivity.this,num);
        world.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
        setContentView(world);
    }
}
