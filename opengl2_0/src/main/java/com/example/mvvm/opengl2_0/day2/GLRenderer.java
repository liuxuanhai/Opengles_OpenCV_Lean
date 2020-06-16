package com.example.mvvm.opengl2_0.day2;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.util.Log;

import com.example.mvvm.opengl2_0.GLUtil;
import com.example.mvvm.opengl2_0.R;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * author : 90589
 * date   : 2020/6/16
 * desc   : 描述
 */
public class GLRenderer implements GLSurfaceView.Renderer {
    TextureRectangle mTextureRectangle;
    TextureRectangle2 mTextureRectangle2;
    int num;
    private Context context;

    //Model View Projection Matrix--模型视图投影矩阵
    private final float[] mMVPMatrix = new float[16];
    //投影矩阵 mProjectionMatrix
    private final float[] mProjectionMatrix = new float[16];
    //视图矩阵 mViewMatrix
    private final float[] mViewMatrix = new float[16];


    public GLRenderer(Context context, int num) {
        this.context = context;
        this.num = num;
    }
    private int textureId;


    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(1.0f, 0.0f, 0.0f, 1.0f);//rgba
        textureId = GLUtil.loadTexture(context, R.mipmap.p2);//初始化纹理

        switch (num) {
            case 7:
                mTextureRectangle = new TextureRectangle(context);
                break;
            case 8:
                mTextureRectangle2 = new TextureRectangle2(context);
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
                2, 2, -5,
                0f, 0f, 0f,
                0f, 1.0f, 0.0f);
    }

    //变换矩阵
    float[] mOpMatrix = new float[16];

    @Override
    public void onDrawFrame(GL10 gl) {
        Log.i("----onDrawFrame==>", "执行了");


        //清除颜色缓存和深度缓存
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

        //不旋转动画
        Matrix.multiplyMM(
                mMVPMatrix, 0,
                mProjectionMatrix, 0,
                mViewMatrix, 0);

        switch (num) {
            case 7:
                mTextureRectangle.draw(mMVPMatrix,textureId);//绘制时使用纹理
                break;
            case 8:
                mTextureRectangle2.draw(mMVPMatrix,textureId);//绘制时使用纹理
                break;
        }

    }
}


