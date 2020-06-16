package com.example.mvvm.opengl2_0.day2;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

import com.example.mvvm.opengl2_0.day1.AssetsTriangleRender;
import com.example.mvvm.opengl2_0.day1.AssetsTriangleRender2;
import com.example.mvvm.opengl2_0.day1.GLTriangleRenderer;

/**
 * author : 90589
 * date   : 2020/6/15
 * desc   : 描述
 */
public class DisGLView extends GLSurfaceView {

    private  int num;
    private Context context;
    SquareRender squareRender;
    StereoscopicRender stereoscopicRender;
    GLRenderer glRenderer;

    public DisGLView(Context context, int num) {
        super(context);
        this.num = num;
        this.context=context;
        init();
    }

    public DisGLView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setEGLContextClientVersion(2);//设置OpenGL ES 2.0 context
        if (num<5){
            squareRender = new SquareRender(context,num);
            setRenderer(squareRender);//设置渲染器
        }else if (num==5){
           stereoscopicRender=new StereoscopicRender(context,num);
            setRenderer(stereoscopicRender);//设置渲染器
        }else if (num==6){
            num=5;
            stereoscopicRender=new StereoscopicRender(context,num);
            setRenderer(stereoscopicRender);//设置渲染器
        }else if (num==7){
            glRenderer=new GLRenderer(context,num);
            setRenderer(glRenderer);
        }else if (num==8){
            glRenderer=new GLRenderer(context,num);
            setRenderer(glRenderer);
        }
    }
}
