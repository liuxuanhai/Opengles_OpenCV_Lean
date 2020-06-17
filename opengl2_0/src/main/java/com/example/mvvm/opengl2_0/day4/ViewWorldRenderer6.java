package com.example.mvvm.opengl2_0.day4;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import com.example.mvvm.opengl2_0.comm.MatrixStackUtil;
import com.example.mvvm.opengl2_0.day3.RendererAble;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * author : 90589
 * date   : 2020/6/17
 * desc   : 描述绘制圆环
 */
public class ViewWorldRenderer6 implements GLSurfaceView.Renderer {
    private static final String TAG = "GLRenderer";
    private Context mContext;
    private RendererAble mWorldShape;
    private int num;

    public ViewWorldRenderer6(Context context, int num) {
        mContext = context;
        this.num = num;
    }

    private int currDeg = 0;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);//rgba

        mWorldShape = new RingShapeRenderer(mContext);

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0, 0, width, height);//GL视口
        float ratio = (float) width / height;
        MatrixStackUtil.frustum(-ratio, ratio, -1, 1,
                3, 9);
        MatrixStackUtil.lookAt(2f, 2f, -6.0f,
                0f, 0f, 0f,
                0f, 1.0f, 0.0f);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        //清除颜色缓存和深度缓存
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

        //打开深度检测
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
        MatrixStackUtil.rotate(currDeg, 0, 1, 0);
        mWorldShape.draw(MatrixStackUtil.peek());

//        MatrixStackUtil.save();
//        MatrixStackUtil.rotate(currDeg, -0f, 0, 0);
//        MatrixStackUtil.translate(-1.5f, 0, 0);
//        mWorldShape.draw(MatrixStackUtil.peek());
//        MatrixStackUtil.restore();

        currDeg++;
        if (currDeg == 360) {
            currDeg = 0;
        }
    }
}





