package com.example.mvvm.selfdrawview.viewgroup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mvvm.selfdrawview.R;

import java.util.ArrayList;

public class DrawViewGroupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_view_group);

        FlowerLayout flowerLayout = findViewById(R.id.id_fl);
        final ArrayList<Petal> petals = new ArrayList<>();
        petals.add(new Petal(R.mipmap.img1, "么么哒"));
        petals.add(new Petal(R.mipmap.img2, "点我呀"));
        petals.add(new Petal(R.mipmap.img3, "好坏呀"));
        petals.add(new Petal(R.mipmap.img4, "想我没"));
        petals.add(new Petal(R.mipmap.img5, "好棒呦"));
        petals.add(new Petal(R.mipmap.img6, "好厉害"));
        petals.add(new Petal(R.mipmap.img7, "嘿嘿嘿"));
        petals.add(new Petal(R.mipmap.img8, "好嗨呀"));
        petals.add(new Petal(R.mipmap.img9, "停不下"));
        petals.add(new Petal(R.mipmap.img10, "动起来"));
        petals.add(new Petal(R.mipmap.img11, "好炫啊"));
        petals.add(new Petal(R.mipmap.img12, "来一个"));
        petals.add(new Petal(R.mipmap.img13, "走起来"));
//        petals.add(new Petal(R.mipmap.icon_10, "icon_14"));
        flowerLayout.setAdapter(new FlowerAdapter(petals));
        flowerLayout.setOnItemClickListener(new FlowerLayout.OnItemClickListener() {
            @Override
            public void onClick(View v, ViewGroup viewGroup, int position) {
                Toast.makeText(getApplicationContext(),petals.get(position).info,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
