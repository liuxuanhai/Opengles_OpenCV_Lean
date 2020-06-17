package com.example.mvvm.opengl2_0.day3;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.util.Log;

import com.example.mvvm.opengl2_0.comm.Cons;
import com.example.mvvm.opengl2_0.comm.Shape;
import com.example.mvvm.opengl2_0.comm.SimpleShape;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * author : 90589
 * date   : 2020/6/16
 * desc   : 描述
 */
public class WorldRenderer implements GLSurfaceView.Renderer {
    private static final String TAG = "GLRenderer";
    //Model View Projection Matrix--模型视图投影矩阵
    private static float[] mMVPMatrix = new float[16];
    //投影矩阵 mProjectionMatrix
    private static final float[] mProjectionMatrix = new float[16];
    //视图矩阵 mViewMatrix
    private static final float[] mViewMatrix = new float[16];
    //变换矩阵
    private float[] mOpMatrix = new float[16];
    private Context mContext;
    private RendererAble mWorldShape;
    private int num;
    Shape shape;
    SimpleShape mCoo;

    public WorldRenderer(Context context,int num) {
        mContext = context;
        this.num=num;
    }
    private int currDeg = 0;
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(0.0f,0.0f,0.0f,1.0f);//rgba
        Log.i("-->","num==>"+num);
        Shape shape = new Shape(Cons.VERTEX_COO, Cons.COLOR_COO, GLES20.GL_LINES);
        mCoo = new SimpleShape(mContext, shape);

        switch (num){
            case 1:
                mWorldShape = new WorldShape(mContext);
                break;
            case 2:
                mWorldShape = new WorldShape2(mContext);
                break;
            case 3:
                mWorldShape = new WorldShape3(mContext);
                break;
            case 4:
                mWorldShape=new WorldShape4(mContext);
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
                3, 9);
        // 设置相机位置(视图矩阵)
        Matrix.setLookAtM(mViewMatrix, 0,
                2f, 2f, -6.0f,
                0f, 0f, 0f,
                0f, 1.0f, 0.0f);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        //清除颜色缓存和深度缓存
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
        //初始化变换矩阵
        Matrix.setRotateM(mOpMatrix, 0, currDeg+130, 0, 1, 0);
        Matrix.multiplyMM(mMVPMatrix, 0,
                mViewMatrix, 0,
                mOpMatrix, 0);
        Matrix.multiplyMM(mMVPMatrix, 0,
                mProjectionMatrix, 0,
                mMVPMatrix, 0);
        Log.i("-->","mWorldShape==>"+mWorldShape);
        if (num==3){
            mWorldShape.draw(mMVPMatrix);
        }else if (num<3){
            mWorldShape.draw(mMVPMatrix);
            mCoo.draw(mMVPMatrix);
        }else if (num==4){
            mWorldShape.draw(mMVPMatrix);
        }


        //打开深度检测
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);

        currDeg++;
        if (currDeg == 360) {
            currDeg = 0;
        }
    }
}

