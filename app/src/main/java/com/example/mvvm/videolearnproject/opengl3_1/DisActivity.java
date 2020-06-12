package com.example.mvvm.videolearnproject.opengl3_1;

import android.os.Bundle;
import android.widget.SeekBar;

import com.example.mvvm.videolearnproject.BaseActivity;
import com.example.mvvm.videolearnproject.R;
import com.example.mvvm.videolearnproject.opengl3.DisGlViewWord;

/**
 * author : 90589
 * date   : 2020/6/10
 * desc   : 描述
 */
public class DisActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dis_photo);
        SeekBar seekBar=findViewById(R.id.seek_horizontal);
        final DisViewWorld disGlViewWord=findViewById(R.id.dis_gl_photo);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                disGlViewWord.chengNum((float) (progress*0.01));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
