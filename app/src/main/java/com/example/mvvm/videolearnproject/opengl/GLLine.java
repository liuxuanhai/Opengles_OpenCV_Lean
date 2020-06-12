package com.example.mvvm.videolearnproject.opengl;

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
public class GLLine {

    //顶点着色代码
    final String vsh = "#version 300 es\n" +
            "layout (location = 0) in vec3 aPosition; \n" +
            "layout (location = 1) in vec4 aColor;\n" +
            "\n" +
            "out vec4 color2frag;\n" +
            "\n" +
            "void main(){\n" +
            "    gl_Position = vec4(aPosition.x,aPosition.y, aPosition.z, 1.0);\n" +
            "    color2frag = aColor;\n" +
            "gl_PointSize=20.0;" +
            "}";

    //线绘制与比例校正
    final String vsh2 ="#version 300 es\n" +
            "layout (location = 0) in vec3 aPosition; \n" +
            "layout (location = 1) in vec4 aColor;\n" +
            "uniform mat4 uMVPMatrix;\n" +
            "out vec4 color2frag;\n" +
            "\n" +
            "void main(){\n" +
            "    gl_Position = uMVPMatrix*vec4(aPosition.x,aPosition.y, aPosition.z, 1.0);\n" +
            "    color2frag = aColor;\n" +
            "gl_PointSize=10.0;"+
            "}";


    //片段着色代码
    final String fsh = "#version 300 es\n" +
            "precision mediump float;\n" +
            "out vec4 outColor;\n" +
            "in vec4 color2frag;\n" +
            "\n" +
            "void main(){\n" +
            "    outColor = color2frag;\n" +
            "}";

    //顶点数组
    private final float vertexes[] = {   //以逆时针顺序
            0.0f, 0.0f, 0.0f,//原点
            0.5f, 0.0f, 0.0f,
            0.5f, 0.5f, 0.0f,
            0.0f, 0.5f, 0.0f,
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

    //---->[变换矩阵相关代码]----
    private  int uMVPMatrix ;//顶点变换矩阵句柄

    public GLLine() {
        program = initProgram();
        vertBuffer = GLBuffer.getFloatBuffer(vertexes);
        colorBuffer = GLBuffer.getFloatBuffer(colors);
        //构造方法中获取句柄
        uMVPMatrix = GLES30.glGetUniformLocation(program, "uMVPMatrix");
    }

    public GLLine(Context context) {

        program = LoadProgram.initProgramByAssets(context, "glline.vsh.glsl", "glline.fsh.glsl");;
        vertBuffer = GLBuffer.getFloatBuffer(vertexes);
        colorBuffer = GLBuffer.getFloatBuffer(colors);
        //构造方法中获取句柄
        uMVPMatrix = GLES30.glGetUniformLocation(program, "uMVPMatrix");
    }

    private int initProgram() {
        int program;
        ////顶点shader代码加载
        int vertexShader = GLLoader.loadShader(GLES30.GL_VERTEX_SHADER, vsh);
        //片段shader代码加载
        int fragmentShader = GLLoader.loadShader(GLES30.GL_FRAGMENT_SHADER, fsh);
        program = GLES30.glCreateProgram();//创建空的OpenGL ES 程序
        GLES30.glAttachShader(program, vertexShader);//加入顶点着色器
        GLES30.glAttachShader(program, fragmentShader);//加入片元着色器
        GLES30.glLinkProgram(program);//创建可执行的OpenGL ES项目
        return program;
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

        //绘制点
        GLES30.glDrawArrays(GLES30.GL_POINTS, 0, vertexes.length / VERTEX_DIMENSION);


        GLES30.glLineWidth(10);
//        GLES30.GL_POINTS绘制点
//        GLES30.glDrawArrays(GLES30.GL_POINTS, 0, vertexes.length / VERTEX_DIMENSION);

        //绘制两条平行线
//        GLES30.glDrawArrays(GLES30.GL_LINES, 0, vertexes.length / VERTEX_DIMENSION);

//        GLES30.glDrawArrays(GLES30.GL_LINE_STRIP, 0, vertexes.length / VERTEX_DIMENSION);

        GLES30.glDrawArrays(GLES30.GL_LINE_LOOP, 0, vertexes.length / VERTEX_DIMENSION);

        //禁用顶点数组
        GLES30.glDisableVertexAttribArray(aPosition);
        GLES30.glDisableVertexAttribArray(aColor);
    }


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

        //绘制点
//        GLES30.glDrawArrays(GLES30.GL_POINTS, 0, vertexes.length / VERTEX_DIMENSION);


        GLES30.glLineWidth(10);
//        GLES30.GL_POINTS绘制点
        GLES30.glDrawArrays(GLES30.GL_POINTS, 0, vertexes.length / VERTEX_DIMENSION);

        //绘制两条平行线
//        GLES30.glDrawArrays(GLES30.GL_LINES, 0, vertexes.length / VERTEX_DIMENSION);

//        GLES30.glDrawArrays(GLES30.GL_LINE_STRIP, 0, vertexes.length / VERTEX_DIMENSION);

//        GLES30.glDrawArrays(GLES30.GL_LINE_LOOP, 0, vertexes.length / VERTEX_DIMENSION);

        //禁用顶点数组
        GLES30.glDisableVertexAttribArray(aPosition);
        GLES30.glDisableVertexAttribArray(aColor);
    }
}
