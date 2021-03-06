package com.example.mvvm.opengl2_0.day2;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.util.Log;

import com.example.mvvm.opengl2_0.day1.AssetsTriangle;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * author : 90589
 * date   : 2020/6/15
 * desc   : 描述
 */
public class SquareRender implements GLSurfaceView.Renderer{
    SquareView squareRender;
    Pentagon pentagon;
    IndexPentagon indexPentagon;
    Sexangle sexangle;
    int num;
    private Context context;

    //Model View Projection Matrix--模型视图投影矩阵
    private final float[] mMVPMatrix = new float[16];
    //投影矩阵 mProjectionMatrix
    private final float[] mProjectionMatrix = new float[16];
    //视图矩阵 mViewMatrix
    private final float[] mViewMatrix = new float[16];



    private int currDeg = 60;

    public SquareRender(Context context,int num) {
        this.context = context;
        this.num=num;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(1.0f, 0.0f, 0.0f, 1.0f);//rgba
        switch (num){
            case 1:
                squareRender=new SquareView(context);
                break;
            case 2:
                pentagon=new Pentagon(context);
                break;
            case 3:
                indexPentagon=new IndexPentagon(context);
                break;
            case 4:
                sexangle=new Sexangle(context);
                break;
        }


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

    //变换矩阵
    float[] mOpMatrix = new float[16];
    @Override
    public void onDrawFrame(GL10 gl) {
        Log.i("----onDrawFrame==>","执行了");


        //清除颜色缓存和深度缓存
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

        //围绕y轴旋转
        Matrix.setRotateM(mOpMatrix, 0, currDeg, 0, 1, 0);
        Matrix.translateM(mOpMatrix, 0, currDeg/360.f, 0,0);

        //使用mOpMatrix对mMVPMatrix进行变换
        Matrix.multiplyMM(
                mMVPMatrix, 0,
                mViewMatrix, 0,
                mOpMatrix, 0);

        Matrix.multiplyMM(
                mMVPMatrix, 0,
                mProjectionMatrix, 0,
                mMVPMatrix, 0);


        switch (num){
            case 1:
                squareRender.draw(mMVPMatrix);
                break;
            case 2:
               pentagon.draw(mMVPMatrix);
                break;
            case 3:
                indexPentagon.draw(mMVPMatrix);
                break;
            case 4:
                sexangle.draw(mMVPMatrix);
                break;
        }

        currDeg++;
        if (currDeg == 360) {
            currDeg = 0;
        }
    }
}
