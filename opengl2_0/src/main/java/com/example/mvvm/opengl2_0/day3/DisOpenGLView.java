package com.example.mvvm.opengl2_0.day3;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

import com.example.mvvm.opengl2_0.day2.GLRenderer;
import com.example.mvvm.opengl2_0.day2.SquareRender;
import com.example.mvvm.opengl2_0.day2.StereoscopicRender;

/**
 * author : 90589
 * date   : 2020/6/16
 * desc   : 描述
 */
public class DisOpenGLView extends GLSurfaceView {

    private  int num;
    private Context context;
    GLViewRenderer viewRenderer;

    public DisOpenGLView(Context context, int num) {
        super(context);
        this.num = num;
        this.context=context;
        init();
    }

    public DisOpenGLView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setEGLContextClientVersion(2);//设置OpenGL ES 2.0 context
        if (num<5){
            viewRenderer=new GLViewRenderer(context,num);
            setRenderer(viewRenderer);//设置渲染器
        }
    }
}

