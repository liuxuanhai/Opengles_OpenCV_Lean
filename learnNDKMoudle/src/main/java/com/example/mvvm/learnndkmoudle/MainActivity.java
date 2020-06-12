package com.example.mvvm.learnndkmoudle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.mvvm.learnndkmoudle.jni_creator.Facer;

public class MainActivity extends AppCompatActivity {
    static {//加载类库
        System.loadLibrary("toly_facer-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView=findViewById(R.id.dis_text);

        textView.setTextSize(30);
        //通过native接口getFacer使用类库中C++方法
        textView.setText(Facer.getFacer("-", "-", "~", "X"));
    }
}
