package com.example.mvvm.videolearnproject.opengl3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.SeekBar;

import com.example.mvvm.videolearnproject.R;

public class EditPhotActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_phot);
        SeekBar progressBar=findViewById(R.id.progress_horizontal);
        final EditGLPhotoView editGLPhoto=findViewById(R.id.edit_gl_photo);


        progressBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
             editGLPhoto.changDraw((float) (progress*0.01));
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
