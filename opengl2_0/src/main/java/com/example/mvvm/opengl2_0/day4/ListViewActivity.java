package com.example.mvvm.opengl2_0.day4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;

import com.example.mvvm.opengl2_0.R;
import com.example.mvvm.opengl2_0.comm.RvAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        RecyclerView recyclerView=findViewById(R.id.day4_recycler);

        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.onDraw(c, parent, state);
            }
        });//分割线
        List<String> data=new ArrayList<>();
        data.add("绘制立体中的矩形面");
        data.add("封装矩阵变换");
        data.add("操作矩阵的状态栈");
        data.add("操作矩阵的状态栈,使用状态恢复机制");
        data.add("总结移动旋转缩放");
        data.add("圆环上等分点");
        RvAdapter cmACAdapter = new RvAdapter(data);//初始化适配器
        recyclerView.setAdapter(cmACAdapter);//设置适配器
        cmACAdapter.setItemOnClick(new RvAdapter.ItemOnClick() {
            @Override
            public void onClick(View view, int position) {
                Intent intent=new Intent(ListViewActivity.this,DrawViewActivity.class);
                intent.putExtra("num",1+position);
                startActivity(intent);
            }
        });
    }
}
