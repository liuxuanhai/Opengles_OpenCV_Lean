package com.example.mvvm.opengl2_0.day1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mvvm.opengl2_0.R;

public class DisActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dis);
        final Intent intent=new Intent(this,Day1MainActivity.class);

        findViewById(R.id.dis_view_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("num",1);
                startActivity(intent);
            }
        });
        findViewById(R.id.dis_view_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("num",2);
                startActivity(intent);
            }
        });

        findViewById(R.id.dis_view_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("num",3);
                startActivity(intent);
            }
        });

        findViewById(R.id.dis_view_4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("num",4);
                startActivity(intent);
            }
        });
    }
}
