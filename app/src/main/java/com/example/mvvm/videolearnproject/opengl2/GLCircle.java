package com.example.mvvm.videolearnproject.opengl2;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLES30;

import com.example.mvvm.videolearnproject.opengl.comm.GLBuffer;
import com.example.mvvm.videolearnproject.opengl.comm.LoadProgram;

import java.nio.FloatBuffer;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * author : 90589
 * date   : 2020/6/9
 * desc   : 画圆
 */
public class GLCircle {

    //顶点数组
    private float vertexes[] = {   //以逆时针顺序
            1.0f, 1.0f, 0.0f,//原点
            -1.0f, 1.0f, 0.0f,
            -1.0f, -1.0f, 0.0f,
            1.0f, -1.0f, 0.0f,
    };

    // 颜色数组
    private float colors[] = new float[]{
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

    //---->[变换矩阵相关代码]----
    private  int uMVPMatrix ;//顶点变换矩阵句柄

    public GLCircle(Context context) {
        initData();
        program = LoadProgram.initProgramByAssets(context, "glline.vsh.glsl", "glline.fsh.glsl");;
        vertBuffer = GLBuffer.getFloatBuffer(vertexes);
        colorBuffer = GLBuffer.getFloatBuffer(colors);
        //构造方法中获取句柄
        uMVPMatrix = GLES30.glGetUniformLocation(program, "uMVPMatrix");
    }

    private void initData() {
        //顶点坐标数据的初始化
        int splitCount=32;
        float r= (float) 0.5;
        int verticeCount = splitCount + 2;
        vertexes = new float[verticeCount * 3];//坐标数据
        colors = new float[verticeCount * 4];//颜色数据
        float thta = 360.f / splitCount;
        vertexes[0] = 0;
        vertexes[1] = 0;
        vertexes[2] = 0;
        colors[0] = 1;
        colors[1] = 1;
        colors[2] = 1;
        colors[3] = 1;

        for (int n = 1; n <= verticeCount - 1; n++) {
            vertexes[n * 3] = (float) (r * cos((n - 1) * thta));//x
            vertexes[n * 3 + 1] = (float) (r * sin((n - 1) * thta));//y
            vertexes[n * 3 + 2] = 0;//z

            colors[4 * n] = 1;
            colors[4 * n + 1] = 0;
            colors[4 * n + 2] = 0;
            colors[4 * n + 3] = 1.0f;
        }
    }


    //进行比例矫正
    public void draw(float[] mvpMatrix) {
        //清除颜色缓存和深度缓存
        GLES30.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
        // 将程序添加到OpenGL ES环境中
        GLES30.glUseProgram(program);

        GLES30.glUniformMatrix4fv(uMVPMatrix, 1, false,mvpMatrix, 0);

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
//        GLES30.glDrawArrays(GLES30.GL_TRIANGLE_FAN , 0, vertexes.length / VERTEX_DIMENSION);
//        GLES30.glDrawArrays(GLES30.GL_TRIANGLES , 0, vertexes.length / VERTEX_DIMENSION);

        for (int pos=0;pos<vertexes.length;pos+=6){
            float array[]=new float[6];
            array[0]=vertexes[pos+0];
            array[1]=vertexes[pos+1];
            array[2]=vertexes[pos+2];
            array[3]=vertexes[pos+3];
            array[4]=vertexes[pos+4];
            array[5]=vertexes[pos+5];
            GLES30.glDrawArrays(GLES20.GL_LINE_STRIP  , 0, vertexes.length / VERTEX_DIMENSION);

        }


        //禁用顶点数组
        GLES30.glDisableVertexAttribArray(aPosition);
        GLES30.glDisableVertexAttribArray(aColor);
    }
}
