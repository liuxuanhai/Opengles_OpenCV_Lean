package com.example.mvvm.videolearnproject.opengl;

import android.content.Context;
import android.opengl.GLES30;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * author : 90589
 * date   : 2020/6/9
 * desc   : 描述
 */
public class PointGLWorld extends GLSurfaceView implements GLSurfaceView.Renderer {
    private static final String TAG = "GLWorld";
    private GLPoint glPoint;

    private GLManyPoint glManyPoint;

    private GLLine glLine;

    public PointGLWorld(Context context) {
        this(context, null);
    }

    public PointGLWorld(Context context, AttributeSet attrs) {
        super(context, attrs);
        setEGLContextClientVersion(3);//设置OpenGL ES 3.0 context
        setRenderer(this);//设置渲染器
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        glPoint = new GLPoint();
        glManyPoint = new GLManyPoint();
        glLine=new GLLine();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES30.glViewport(0, 0, width, height);//设置GL视口
    }

    @Override
    public void onDrawFrame(GL10 gl) {
//            单点绘制
//            glPoint.draw();
//            多点绘制
//        glManyPoint.draw();
//        绘制线
        glLine.draw();
    }
}

