package com.example.mvvm.opengl2_0.day3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mvvm.opengl2_0.R;
import com.example.mvvm.opengl2_0.day2.DisViewActivity;
import com.example.mvvm.opengl2_0.day2.ListActivity;

public class DisListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dis_list);
        final Intent intent=new Intent(DisListActivity.this, Day3DisViewActivity.class);
        findViewById(R.id.list_view_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("num",1);
                startActivity(intent);
            }
        });
        findViewById(R.id.list_view_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("num",2);
                startActivity(intent);
            }
        });
        findViewById(R.id.list_view_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("num",3);
                startActivity(intent);
            }
        });

        findViewById(R.id.list_view_4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("num",4);
                startActivity(intent);
            }
        });

        findViewById(R.id.list_view_5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("num",5);
                startActivity(intent);
            }
        });
    }
}
