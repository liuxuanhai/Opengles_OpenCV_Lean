package com.example.mvvm.videolearnproject.opengl2;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLES30;

import com.example.mvvm.videolearnproject.opengl.comm.GLBuffer;
import com.example.mvvm.videolearnproject.opengl.comm.GLLoader;
import com.example.mvvm.videolearnproject.opengl.comm.LoadProgram;

import java.nio.FloatBuffer;

/**
 * author : 90589
 * date   : 2020/6/9
 * desc   : 描述
 */
public class GLView {

    //顶点数组
    private final float vertexes[] = {   //以逆时针顺序
            1.0f, 1.0f, 0.0f,//原点
            -1.0f, 1.0f, 0.0f,
            -1.0f, -1.0f, 0.0f,
            1.0f, -1.0f, 0.0f,
    };

    // 颜色数组
    private final float colors[] = new float[]{
            1.0f, 1.0f, 1.0f, 1.0f,//白色
            1.0f, 0.0f, 0.0f, 1.0f,//红色
            0.0f, 1.0f, 0.0f, 1.0f,//绿色
            0.0f, 0.0f, 1.0f, 1.0f,//蓝色
    };

    private int program;
    private static final int VERTEX_DIMENSION = 3;
    private static final int COLOR_DIMENSION = 4;

    private FloatBuffer vertBuffer;
    private FloatBuffer colorBuffer;

    private int aPosition = 0;//位置的句柄
    private int aColor = 1;//颜色的句柄


    public GLView(Context context) {
        program = LoadProgram.initProgramByAssets(context, "sanjiao.vsh.glsl", "glline.fsh.glsl");;
        vertBuffer = GLBuffer.getFloatBuffer(vertexes);
        colorBuffer = GLBuffer.getFloatBuffer(colors);
    }


    //进行比例矫正
    public void draw() {
        //清除颜色缓存和深度缓存
        GLES30.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
        // 将程序添加到OpenGL ES环境中
        GLES30.glUseProgram(program);
        //启用顶点句柄
        GLES30.glEnableVertexAttribArray(aPosition);
        //启用颜色句柄
        GLES30.glEnableVertexAttribArray(aColor);
        //准备坐标数据
        GLES30.glVertexAttribPointer(
                aPosition, VERTEX_DIMENSION,
                GLES30.GL_FLOAT, false,
                VERTEX_DIMENSION * 4, vertBuffer);

        //准备颜色数据
        GLES30.glVertexAttribPointer(
                aColor, COLOR_DIMENSION,
                GLES30.GL_FLOAT, false,
                COLOR_DIMENSION * 4, colorBuffer);

        GLES30.glLineWidth(10);
//        GLES30.glDrawArrays(GLES30.GL_TRIANGLE_STRIP , 0, vertexes.length / VERTEX_DIMENSION);
        GLES30.glDrawArrays(GLES30.GL_TRIANGLE_FAN , 0, vertexes.length / VERTEX_DIMENSION);
//        GLES30.glDrawArrays(GLES30.GL_TRIANGLE_STRIP , 0, vertexes.length / VERTEX_DIMENSION);

        //禁用顶点数组
        GLES30.glDisableVertexAttribArray(aPosition);
        GLES30.glDisableVertexAttribArray(aColor);
    }

}
