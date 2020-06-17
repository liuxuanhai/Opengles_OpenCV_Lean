package com.example.mvvm.opengl2_0.day2;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLES30;

import com.example.mvvm.opengl2_0.GLBuffer;
import com.example.mvvm.opengl2_0.GLUtil;

import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/**
 * author : 90589
 * date   : 2020/6/16
 * desc   : 描述
 */
public class Stereoscopic {
    private FloatBuffer vertexBuffer;//顶点缓冲

    private Context context;

    private final int mProgram;
    private int mPositionHandle;//位置句柄
    private int mColorHandle = 1;//颜色句柄
    private final int vertexCount = sCoo.length / COORDS_PER_VERTEX;//顶点个数
    private final int vertexStride = COORDS_PER_VERTEX * 4; // 3*4=12
    // 数组中每个顶点的坐标数
    static final int COORDS_PER_VERTEX = 3;

    static float[] sCoo = {
            //A面
            -0.5f, 0.5f, 0.5f,//p0
            -0.5f, -0.5f, 0.5f,//p1
            -0.5f, -0.5f, -0.5f,//p2
            -0.5f, 0.5f, -0.5f,//p3
            //B面
            -0.5f, 0.5f, 0.5f,//p4
            -0.5f, -0.5f, 0.5f,//p5
            0.5f, -0.5f, 0.5f,//p6
            0.5f, 0.5f, 0.5f,//p7
            //C面
            0.5f, 0.5f, 0.5f,//p8
            0.5f, -0.5f, 0.5f,//p9
            0.5f, -0.5f, -0.5f,//p10
            0.5f, 0.5f, -0.5f,//p11
            //D面
            -0.5f, 0.5f, 0.5f,//p12
            0.5f, 0.5f, 0.5f,//p13
            0.5f, 0.5f, -0.5f,//p14
            -0.5f, 0.5f, -0.5f,//p15
            //E面
            -0.5f, -0.5f, 0.5f,//p16
            0.5f, -0.5f, 0.5f,//p17
            0.5f, -0.5f, -0.5f,//p18
            -0.5f, -0.5f, -0.5f,//p19
            //F面
            -0.5f, 0.5f, -0.5f,//p20
            -0.5f, -0.5f, -0.5f,//p21
            0.5f, -0.5f, -0.5f,//p22
            0.5f, 0.5f, -0.5f,//p23
    };

    //索引数组
    private short[] idx = {
            0, 1, 3,//A
            1, 2, 3,
            0 + 4, 1 + 4, 3 + 4,//B
            1 + 4, 2 + 4, 3 + 4,
            0 + 4 * 2, 1 + 4 * 2, 3 + 4 * 2,//C
            1 + 4 * 2, 2 + 4 * 2, 3 + 4 * 2,
            0 + 4 * 3, 1 + 4 * 3, 3 + 4 * 3,//D
            1 + 4 * 3, 2 + 4 * 3, 3 + 4 * 3,
            0 + 4 * 4, 1 + 4 * 4, 3 + 4 * 4,//E
            1 + 4 * 4, 2 + 4 * 4, 3 + 4 * 4,
            0 + 4 * 5, 1 + 4 * 5, 3 + 4 * 5,//F
            1 + 4 * 5, 2 + 4 * 5, 3 + 4 * 5,
    };

    float[] color = new float[]{
            //A
            1f, 1f, 0.0f, 1.0f,//黄
            0.05882353f, 0.09411765f, 0.9372549f, 1.0f,//蓝
            0.19607843f, 1.0f, 0.02745098f, 1.0f,//绿
            1.0f, 0.0f, 1.0f, 1.0f,//紫色
            //B
            1f, 1f, 0.0f, 1.0f,//黄
            0.05882353f, 0.09411765f, 0.9372549f, 1.0f,//蓝
            0.19607843f, 1.0f, 0.02745098f, 1.0f,//绿
            1.0f, 0.0f, 1.0f, 1.0f,//紫色
            //C
            1f, 1f, 0.0f, 1.0f,//黄
            0.05882353f, 0.09411765f, 0.9372549f, 1.0f,//蓝
            0.19607843f, 1.0f, 0.02745098f, 1.0f,//绿
            1.0f, 0.0f, 1.0f, 1.0f,//紫色
            //D
            1f, 1f, 0.0f, 1.0f,//黄
            0.05882353f, 0.09411765f, 0.9372549f, 1.0f,//蓝
            0.19607843f, 1.0f, 0.02745098f, 1.0f,//绿
            1.0f, 0.0f, 1.0f, 1.0f,//紫色
            //E
            1f, 1f, 0.0f, 1.0f,//黄
            0.05882353f, 0.09411765f, 0.9372549f, 1.0f,//蓝
            0.19607843f, 1.0f, 0.02745098f, 1.0f,//绿
            1.0f, 0.0f, 1.0f, 1.0f,//紫色
            //F
            1f, 1f, 0.0f, 1.0f,//黄
            0.05882353f, 0.09411765f, 0.9372549f, 1.0f,//蓝
            0.19607843f, 1.0f, 0.02745098f, 1.0f,//绿
            1.0f, 0.0f, 1.0f, 1.0f,//紫色
    };


    private int muMVPMatrixHandle;//顶点变换矩阵句柄
    static final int COLOR_PER_VERTEX = 4;//向量维度
    private final int vertexColorStride = COLOR_PER_VERTEX * 4; // 4*4=16

    //成员变量
    private FloatBuffer mColorBuffer;//颜色缓冲

    private ShortBuffer idxBuffer;

    public Stereoscopic(Context context) {
        this.context = context;
        //初始化顶点字节缓冲区
        bufferData();

        int vertexShader = GLUtil.loadShaderAssets(context, GLES20.GL_VERTEX_SHADER, "tri_4.vert");
        int fragmentShader = GLUtil.loadShaderAssets(context, GLES20.GL_FRAGMENT_SHADER, "tri_4.frag");

        mProgram = GLES20.glCreateProgram();//创建空的OpenGL ES 程序
        GLES20.glAttachShader(mProgram, vertexShader);//加入顶点着色器
        GLES20.glAttachShader(mProgram, fragmentShader);//加入片元着色器
        GLES20.glLinkProgram(mProgram);//创建可执行的OpenGL ES项目

        //获取程序中总变换矩阵uMVPMatrix成员的句柄
        muMVPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uMVPMatrix");
        //索引缓冲
        idxBuffer = GLUtil.getShortBuffer(idx);
    }


    public void draw(float[] mvpMatrix) {
        // 将程序添加到OpenGL ES环境中
        GLES20.glUseProgram(mProgram);
        GLES30.glUniformMatrix4fv(muMVPMatrixHandle, 1, false, mvpMatrix, 0);
        //获取顶点着色器的vPosition成员的句柄
        mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");
        //启用三角形顶点的句柄
        GLES20.glEnableVertexAttribArray(mPositionHandle);
        //准备三角坐标数据
        GLES20.glVertexAttribPointer(
                mPositionHandle, COORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                vertexStride, vertexBuffer);
        // 获取片元着色器的vColor成员的句柄
        mColorHandle = GLES20.glGetAttribLocation(mProgram, "aColor");

        //启用三角形顶点的句柄
        GLES20.glEnableVertexAttribArray(mColorHandle);


        //准备三角顶点颜色数据
        GLES20.glVertexAttribPointer(
                mColorHandle,
                COLOR_PER_VERTEX,
                GLES20.GL_FLOAT,
                false,
                vertexColorStride,
                mColorBuffer);

        //绘制
        GLES20.glDrawElements(GLES20.GL_TRIANGLES, idx.length,
                GLES20.GL_UNSIGNED_SHORT, idxBuffer);

        //绘制三角形
//        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, 5);
        //禁用顶点数组
        GLES20.glDisableVertexAttribArray(mPositionHandle);
    }


    /**
     * 缓冲数据
     */
    private void bufferData() {
        mColorBuffer = GLBuffer.getFloatBuffer(color);
        vertexBuffer = GLBuffer.getFloatBuffer(sCoo);
    }
}
