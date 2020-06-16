package com.example.mvvm.opengl2_0.day1;

import android.content.Context;
import android.opengl.GLES20;
import android.util.Log;

import com.example.mvvm.opengl2_0.GLBuffer;
import com.example.mvvm.opengl2_0.GLUtil;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * author : 90589
 * date   : 2020/6/15
 * desc   : 描述
 */
public class AssetsTriangle2 {
    private FloatBuffer vertexBuffer;//顶点缓冲

    private Context context;

    private final int mProgram;
    private int mPositionHandle;//位置句柄
    private int mColorHandle;//颜色句柄
    private final int vertexCount = sCoo.length / COORDS_PER_VERTEX;//顶点个数
    private final int vertexStride = COORDS_PER_VERTEX * 4; // 3*4=12

    //成员变量
    private FloatBuffer mColorBuffer;//颜色缓冲
    static final int COLOR_PER_VERTEX = 4;//向量维度
    private final int vertexColorStride = COLOR_PER_VERTEX * 4; // 4*4=16
    // 数组中每个顶点的坐标数
    static final int COORDS_PER_VERTEX = 3;
    static float sCoo[] = {   //以逆时针顺序
            0.0f, 0.0f, 0.0f, // 顶部
            -1.0f, -1.0f, 0.0f, // 左下
            1.0f, -1.0f, 0.0f  // 右下
    };
    // 颜色，rgba
    float color[] = new float[]{
            1.0f, 1.0f, 1.0f, 1.0f,//白色
//            1.0f, 0.5f, 0.5f, 1.0f,//红色
//            0.0f, 1.0f, 0.0f, 1.0f,//绿色
    };

    private int muMVPMatrixHandle;//顶点变换矩阵句柄

    public AssetsTriangle2(Context context) {
        this.context = context;
        //初始化顶点字节缓冲区
        bufferData();

        int vertexShader = GLUtil.loadShaderAssets(context, GLES20.GL_VERTEX_SHADER, "tri_3.vert");
        int fragmentShader = GLUtil.loadShaderAssets(context, GLES20.GL_FRAGMENT_SHADER, "tri.frag");

        Log.i("--->", "vertexShader==>" + vertexShader + "   fragmentShader====>" + fragmentShader);
        mProgram = GLES20.glCreateProgram();//创建空的OpenGL ES 程序
        GLES20.glAttachShader(mProgram, vertexShader);//加入顶点着色器
        GLES20.glAttachShader(mProgram, fragmentShader);//加入片元着色器
        GLES20.glLinkProgram(mProgram);//创建可执行的OpenGL ES项目

        //获取程序中总变换矩阵uMVPMatrix成员的句柄
        muMVPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uMVPMatrix");
    }

    public void draw(float[] mvpMatrix) {
        // 将程序添加到OpenGL ES环境中
        GLES20.glUseProgram(mProgram);
        GLES20.glUniformMatrix4fv(muMVPMatrixHandle, 1, false, mvpMatrix, 0);

        //获取顶点着色器的vPosition成员的句柄
        mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");
        //启用三角形顶点的句柄
        GLES20.glEnableVertexAttribArray(mPositionHandle);
        //准备三角坐标数据
        GLES20.glVertexAttribPointer(
                mPositionHandle, COORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                vertexStride, vertexBuffer);
        //注意颜色句柄不是uniform了,获取片元着色器的vColor成员的句柄
//        mColorHandle = GLES20.glGetAttribLocation(mProgram, "vColor");
//
//        //启用三角形顶点颜色的句柄
//        GLES20.glEnableVertexAttribArray(mColorHandle);
//
//
//        //准备三角顶点颜色数据
//        GLES20.glVertexAttribPointer(
//                mColorHandle,
//                COLOR_PER_VERTEX,
//                GLES20.GL_FLOAT,
//                false,
//                vertexColorStride,
//                mColorBuffer);
        // 获取片元着色器的vColor成员的句柄
        mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");
        //为三角形设置颜色
        GLES20.glUniform4fv(mColorHandle, 1, color, 0);

        //绘制三角形
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount);
        //禁用顶点数组
        GLES20.glDisableVertexAttribArray(mPositionHandle);
    }

    /**
     * 缓冲数据
     */
    private void bufferData() {
        ByteBuffer bb = ByteBuffer.allocateDirect(sCoo.length * 4);//每个浮点数:坐标个数* 4字节
        bb.order(ByteOrder.nativeOrder());//使用本机硬件设备的字节顺序
        vertexBuffer = bb.asFloatBuffer();// 从字节缓冲区创建浮点缓冲区
        vertexBuffer.put(sCoo);// 将坐标添加到FloatBuffer
        vertexBuffer.position(0);//设置缓冲区以读取第一个坐标

        mColorBuffer = GLBuffer.getFloatBuffer(color);
    }
}

