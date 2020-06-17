package com.example.mvvm.opengl2_0.day3;

import android.content.Context;
import android.opengl.GLES20;

import com.example.mvvm.opengl2_0.GLUtil;
import com.example.mvvm.opengl2_0.comm.Cons;

import java.nio.FloatBuffer;

/**
 * author : 90589
 * date   : 2020/6/16
 * desc   : 画线
 */
public class WorldShape2 extends RendererAble{
    private int mProgram;//OpenGL ES 程序
    private int mPositionHandle;//位置句柄
    private int mColorHandle;//颜色句柄
    private int muMVPMatrixHandle;//顶点变换矩阵句柄
    private FloatBuffer mColorBuffer;//颜色缓冲
    private final int vertexColorStride = Cons.DIMENSION_4 * 4; // 4*4=16
    private FloatBuffer mVertexBuffer;//顶点缓冲
    private final int vertexStride = Cons.DIMENSION_3 * 4; // 3*4=12

    private float[] mVertex = new float[]{
            -1.0f, 0.0f, -1.0f,//A
            -1.0f, 0.0f, 1.0f,//B
            1.0f, 0.0f, 1.0f,//C
            1.0f, 0.0f, -1.0f,//D
    };
    private float[] mColor = new float[]{
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f,
            0.21960784f,0.56078434f,0.92156863f,1.0f,
    };


    public WorldShape2(Context context) {
        super(context);
        mColorBuffer = GLUtil.getFloatBuffer(mColor);
        mVertexBuffer = GLUtil.getFloatBuffer(mVertex);
        initProgram();
    }

    private void initProgram() {
        //顶点着色
        int vertexShader = GLUtil.loadShaderAssets(mContext,
                GLES20.GL_VERTEX_SHADER, "world.vert");
        //片元着色
        int fragmentShader = GLUtil.loadShaderAssets(mContext,
                GLES20.GL_FRAGMENT_SHADER, "world.frag");
        mProgram = GLES20.glCreateProgram();//创建空的OpenGL ES 程序
        GLES20.glAttachShader(mProgram, vertexShader);//加入顶点着色器
        GLES20.glAttachShader(mProgram, fragmentShader);//加入片元着色器
        GLES20.glLinkProgram(mProgram);//创建可执行的OpenGL ES项目
        //获取顶点着色器的vPosition成员的句柄
        mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");
        //获取片元着色器的vColor成员的句柄
        mColorHandle = GLES20.glGetAttribLocation(mProgram, "aColor");
        //获取程序中总变换矩阵uMVPMatrix成员的句柄
        muMVPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uMVPMatrix");
    }

    @Override
    public void draw(float[] mvpMatrix) {
        // 将程序添加到OpenGL ES环境中
        GLES20.glUseProgram(mProgram);
        //启用顶点的句柄
        GLES20.glEnableVertexAttribArray(mPositionHandle);
        //启用顶点颜色的句柄
        GLES20.glEnableVertexAttribArray(mColorHandle);
        //顶点矩阵变换
        GLES20.glUniformMatrix4fv(muMVPMatrixHandle, 1, false, mvpMatrix, 0);
        //准备顶点坐标数据
        GLES20.glVertexAttribPointer(
                mPositionHandle,//int indx, 索引
                Cons.DIMENSION_3,//int size,大小
                GLES20.GL_FLOAT,//int type,类型
                false,//boolean normalized,//是否标准化
                vertexStride,// int stride,//跨度
                mVertexBuffer);// java.nio.Buffer ptr//缓冲
        //准备顶点颜色数据
        GLES20.glVertexAttribPointer(
                mColorHandle,
                Cons.DIMENSION_4,
                GLES20.GL_FLOAT,
                false,
                vertexColorStride,
                mColorBuffer);
        /*int count = mVertex.length / Cons.DIMENSION_3;
        GLES20.glDrawArrays(GLES20.GL_POINTS, 0, count);*/


        GLES20.glLineWidth(10);//设置线的宽度
        int count = mVertex.length / Cons.DIMENSION_3;
        //GLES20.glDrawArrays(GLES20.GL_POINTS, 0, count);
        //GLES20.glDrawArrays(GLES20.GL_LINES, 0, count);
        //GLES20.glDrawArrays(GLES20.GL_LINE_STRIP, 0, count);
        GLES20.glDrawArrays(GLES20.GL_LINE_LOOP, 0, count);
    }
}

