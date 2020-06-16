package com.example.mvvm.opengl2_0.day2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mvvm.opengl2_0.R;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        final Intent intent=new Intent(ListActivity.this,DisViewActivity.class);
        findViewById(R.id.list_view_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("num",1);
                startActivity(intent);
            }
        });
    }
}
