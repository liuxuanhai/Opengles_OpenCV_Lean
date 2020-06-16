package com.example.mvvm.opencvnative;

import android.graphics.Bitmap;

/**
 * author : 90589
 * date   : 2020/6/11
 * desc   : 描述
 */
public class TolyCV {
    public static native int faceDetector(Bitmap bitmap, Bitmap.Config argb8888, String path);
}
