package com.example.mvvm.videolearnproject.opengl;

import android.content.Context;
import android.opengl.GLES30;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.util.Log;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * author : 90589
 * date   : 2020/6/9
 * desc   : 描述
 */
public class GLWorld extends GLSurfaceView implements GLSurfaceView.Renderer {
    private static final String TAG = "GLWorld";
    public GLWorld(Context context) {
        this(context,null);
    }

    public GLWorld(Context context, AttributeSet attrs) {
        super(context, attrs);
        setEGLContextClientVersion(3);//设置OpenGL ES 3.0 context
        setRenderer(this);//设置渲染器
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        Log.e(TAG, "onSurfaceCreated: " );
        GLES30.glClearColor(1.0f, 0.0f, 0.0f, 1.0f);//rgba
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        Log.e(TAG, "onSurfaceChanged: " );
        GLES30.glViewport(0, 0, width, height);//设置GL视口
    }

    @Override
    public void onDrawFrame(GL10 gl) {

//        日志如下: 默认onDrawFrame约隔16ms会不断刷新
        Log.e(TAG, "onDrawFrame: " );
    }
}
