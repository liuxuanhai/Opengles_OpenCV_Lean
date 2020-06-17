package com.example.mvvm.opengl2_0.day3;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.util.Log;

/**
 * author : 90589
 * date   : 2020/6/16
 * desc   : 描述
 */
public class World extends GLSurfaceView {
    private WorldRenderer mRenderer;
    int num = -1;

    public World(Context context, int num) {
        this(context, null);
        this.num = num;
        init();
    }

    public World(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        if (num == -1)
            return;

        setEGLContextClientVersion(2);//设置OpenGL ES 2.0 context
        Log.i("-->init", "num==>" + num);
        mRenderer = new WorldRenderer(getContext(), num);
        setRenderer(mRenderer);//设置渲染器
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    }
}
