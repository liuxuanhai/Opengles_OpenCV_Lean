package com.example.mvvm.opengl2_0.day4;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.util.Log;

import com.example.mvvm.opengl2_0.day3.WorldRenderer;

/**
 * author : 90589
 * date   : 2020/6/16
 * desc   : 描述
 */
public class ViewWorld extends GLSurfaceView {
    private ViewWorldRenderer mRenderer;
    private ViewWorldRenderer2 mRenderer2;
    private ViewWorldRenderer3 mRenderer3;
    private ViewWorldRenderer4 mRenderer4;
    private ViewWorldRenderer5 mRenderer5;
    private ViewWorldRenderer6 mRenderer6;
    int num = -1;

    public ViewWorld(Context context, int num) {
        this(context, null);
        this.num = num;
        init();
    }

    public ViewWorld(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        if (num == -1)
            return;

        setEGLContextClientVersion(2);//设置OpenGL ES 2.0 context
        Log.i("-->init", "num==>" + num);
        if (num==1){
            mRenderer = new ViewWorldRenderer(getContext(), num);
            setRenderer(mRenderer);//设置渲染器
        }else if (num==2){
            mRenderer2 = new ViewWorldRenderer2(getContext(), num);
            setRenderer(mRenderer2);//设置渲染器
        }else if (num==3){
            mRenderer3 = new ViewWorldRenderer3(getContext(), num);
            setRenderer(mRenderer3);//设置渲染器
        }else if (num==4){
            mRenderer4 = new ViewWorldRenderer4(getContext(), num);
            setRenderer(mRenderer4);//设置渲染器
        }else if (num==5){
            mRenderer5 = new ViewWorldRenderer5(getContext(), num);
            setRenderer(mRenderer5);//设置渲染器
        }else if (num==6){
            mRenderer6 = new ViewWorldRenderer6(getContext(), num);
            setRenderer(mRenderer6);//设置渲染器
        }

        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    }
}
