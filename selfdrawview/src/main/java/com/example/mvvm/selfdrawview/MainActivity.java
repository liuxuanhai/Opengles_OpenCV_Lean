package com.example.mvvm.selfdrawview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mvvm.selfdrawview.viewgroup.DrawViewGroupActivity;
import com.example.mvvm.selfdrawview.viewgroup.MoveActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.view1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DrawViewGroupActivity.class));
            }
        });
        findViewById(R.id.view2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MoveActivity.class));
            }
        });
        startActivity(new Intent(MainActivity.this, MoveActivity.class));
    }
}
