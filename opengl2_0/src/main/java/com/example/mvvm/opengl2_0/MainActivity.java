package com.example.mvvm.opengl2_0;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mvvm.opengl2_0.day1.DisActivity;
import com.example.mvvm.opengl2_0.day2.ListActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.opengl_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, DisActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.opengl_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });
    }
}
