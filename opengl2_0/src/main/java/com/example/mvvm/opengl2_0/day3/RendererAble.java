package com.example.mvvm.opengl2_0.day3;

import android.content.Context;

/**
 * author : 90589
 * date   : 2020/6/16
 * desc   : 描述
 */
public class RendererAble{
    public Context mContext;

    public RendererAble(Context mContext) {
        this.mContext = mContext;
    }

    public void draw(float[] mvpMatrix){};
}
