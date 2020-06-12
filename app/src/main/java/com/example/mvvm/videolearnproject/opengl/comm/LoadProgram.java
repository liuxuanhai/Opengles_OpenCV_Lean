package com.example.mvvm.videolearnproject.opengl.comm;

import android.content.Context;
import android.opengl.GLES30;

import java.io.InputStream;

import static com.example.mvvm.videolearnproject.opengl.comm.GLLoader.loadShader;

/**
 * author : 90589
 * date   : 2020/6/9
 * desc   : 描述
 */
public class LoadProgram {
    public static  int initProgramByAssets(Context ctx, String vertName, String fragName){
        int program=-1;
        ////顶点着色
        int vertexShader = loadShaderAssets(ctx, GLES30.GL_VERTEX_SHADER, vertName);
        //片元着色
        int fragmentShader = loadShaderAssets(ctx, GLES30.GL_FRAGMENT_SHADER, fragName);
        program = GLES30.glCreateProgram();//创建空的OpenGL ES 程序
        GLES30.glAttachShader(program, vertexShader);//加入顶点着色器
        GLES30.glAttachShader(program, fragmentShader);//加入片元着色器
        GLES30.glLinkProgram(program);//创建可执行的OpenGL ES项目
        return program;
    }
    //从sh脚本中加载shader内容的方法
    private static int loadShaderAssets(Context ctx, int type, String name) {
        byte[] buf = new byte[1024];
        StringBuilder sb = new StringBuilder();
        int len;
        try (InputStream is = ctx.getAssets().open(name)) {
            while ((len = is.read(buf)) != -1) {
                sb.append(new String(buf, 0, len));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loadShader(type, sb.toString());
    }
}
