package com.example.mvvm.videolearnproject.opengl2_1;

import android.content.Context;
import android.opengl.GLES30;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.util.AttributeSet;

import com.example.mvvm.videolearnproject.opengl2.GLCircle;
import com.example.mvvm.videolearnproject.opengl2.GLTieTuView;
import com.example.mvvm.videolearnproject.opengl2.GLTieTuView2;
import com.example.mvvm.videolearnproject.opengl2.GLView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * author : 90589
 * date   : 2020/6/10
 * desc   : 描述
 */
public class DisImgWord extends GLSurfaceView implements GLSurfaceView.Renderer {
    private static final String TAG = "GLWorld";
    private DisGLImg disGLImg;

    private Context context;

    //Model View Projection Matrix--模型视图投影矩阵
    private final float[] mMVPMatrix = new float[16];
    //投影矩阵 mProjectionMatrix
    private final float[] mProjectionMatrix = new float[16];
    //视图矩阵 mViewMatrix
    private final float[] mViewMatrix = new float[16];

    public DisImgWord(Context context) {

        this(context, null);
        this.context=context;
    }
    public DisImgWord(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        setEGLContextClientVersion(3);//设置OpenGL ES 3.0 context
        setRenderer(this);//设置渲染器
    }
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        disGLImg=new DisGLImg(context);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES30.glViewport(0, 0, width, height);//设置GL视口

        float ratio = (float) width / height;
        //透视投影矩阵--截锥
        Matrix.frustumM(mProjectionMatrix, 0,
                -ratio, ratio, -1, 1,
                3, 7);
        // 设置相机位置(视图矩阵)
        Matrix.setLookAtM(mViewMatrix, 0,
                0, 0, 4,
                0f, 0f, 0f,
                0f, 1.0f, 0.0f);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
//        glView.draw();

        Matrix.multiplyMM(
                mMVPMatrix, 0,
                mProjectionMatrix, 0,
                mViewMatrix, 0);

        disGLImg.draw(mMVPMatrix);
    }

    public void updateImg(float pos){
        disGLImg.mixture=pos;
    }
}


