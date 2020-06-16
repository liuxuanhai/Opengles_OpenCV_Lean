package com.example.mvvm.opengl2_0.day1;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.util.Log;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * author : 90589
 * date   : 2020/6/15
 * desc   : 描述
 */
public class AssetsTriangleRender2 implements GLSurfaceView.Renderer {
    AssetsTriangle2 triangle;
    private Context context;

    private int currDeg = 0;


    //Model View Projection Matrix--模型视图投影矩阵
    private final float[] mMVPMatrix = new float[16];
    //投影矩阵 mProjectionMatrix
    private final float[] mProjectionMatrix = new float[16];
    //视图矩阵 mViewMatrix
    private final float[] mViewMatrix = new float[16];
    //变换矩阵
    private float[] mOpMatrix = new float[16];

    public AssetsTriangleRender2(Context context) {
        this.context = context;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(1.0f, 0.0f, 0.0f, 1.0f);//rgba
        triangle=new AssetsTriangle2(context);
    }
    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0, 0, width, height);//GL视口



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
        //清除颜色缓存和深度缓存
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
       /* //mOpMatrix旋转变换
        Matrix.setRotateM(mOpMatrix, 0, 30, 0, 0, -1);

       //使用mOpMatrix对mMVPMatrix进行变换
        Matrix.multiplyMM(
                mMVPMatrix, 0,
                mViewMatrix, 0,
                mOpMatrix, 0);

        Matrix.multiplyMM(
                mMVPMatrix, 0,
                mProjectionMatrix, 0,
                mMVPMatrix, 0);
        triangle.draw(mMVPMatrix);*/
        Log.i("onDrawFrame","currDeg====>"+currDeg);

        //初始化变换矩阵
        Matrix.setRotateM(mOpMatrix, 0, currDeg, 0, 0, -1);

        //设置沿Z轴位移
//        Matrix.translateM(mOpMatrix, 0, 0, 0, currDeg/90.f);

        Matrix.multiplyMM(mMVPMatrix, 0,
                mViewMatrix, 0,
                mOpMatrix, 0);
        Matrix.multiplyMM(mMVPMatrix, 0,
                mProjectionMatrix, 0,
                mMVPMatrix, 0);
        triangle.draw(mMVPMatrix);

        currDeg++;
        if (currDeg == 360) {
            currDeg = 0;
        }
    }
}