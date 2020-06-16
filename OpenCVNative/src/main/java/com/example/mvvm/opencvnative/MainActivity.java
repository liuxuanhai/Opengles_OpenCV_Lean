package com.example.mvvm.opencvnative;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private File mCascadeFile;
    private Bitmap mFaceBitmap;

    static {
        System.loadLibrary("tolyCV");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView textView=findViewById(R.id.open_cv_tv);
        final ImageView imageView=findViewById(R.id.img);

        textView.setText(stringFromJNI());

//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                textView.setText(stringFromJNI());
//                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.g1);
//                imageView.setImageBitmap(opBitmap(bitmap,Bitmap.Config.ARGB_8888));
//            }
//        });
        copyCascadeFile(R.raw.lbpcascade_frontalface,"lbpcascade_frontalface.xml");

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFaceBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.girl);
                int count= TolyCV.faceDetector(mFaceBitmap,Bitmap.Config.ARGB_8888, mCascadeFile.getAbsolutePath());
                Toast.makeText(getApplication(),"人脸"+count,Toast.LENGTH_SHORT).show();
                imageView.setImageBitmap(mFaceBitmap);
            }
        });

    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    public native Bitmap opBitmap(Bitmap bitmap, Bitmap.Config argb8888);


    private void copyCascadeFile( int id,String name) {
        try {
            InputStream inputStream = getResources().openRawResource(id);
            File cascadeDir = getDir("cascade", Context.MODE_PRIVATE);
            mCascadeFile = new File(cascadeDir, name);
            if (mCascadeFile.exists()) return;

            FileOutputStream os = new FileOutputStream(mCascadeFile);
            byte[] buffer = new byte[4096];
            int bytesRead = inputStream.read(buffer);
            while (bytesRead != -1) {
                os.write(buffer, 0, bytesRead);
                bytesRead = inputStream.read(buffer);
            }
            inputStream.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
