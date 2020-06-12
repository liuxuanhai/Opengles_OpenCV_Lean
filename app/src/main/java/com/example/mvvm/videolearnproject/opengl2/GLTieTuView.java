package com.example.mvvm.videolearnproject.opengl2;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLES30;

import com.example.mvvm.videolearnproject.R;
import com.example.mvvm.videolearnproject.opengl.comm.GLBuffer;
import com.example.mvvm.videolearnproject.opengl.comm.GLLoader;
import com.example.mvvm.videolearnproject.opengl.comm.LoadProgram;

import java.nio.FloatBuffer;

/**
 * author : 90589
 * date   : 2020/6/9
 * desc   : 描述
 */
public class GLTieTuView {
    //顶点数组
    private final float vertexes[] = {   //以逆时针顺序
            1.0f, 1.0f, 0.0f,//原点
            -1.0f, 1.0f, 0.0f,
            -1.0f, -1.0f, 0.0f,
            1.0f, -1.0f, 0.0f,
    };

    // 贴图坐标
    private final float textureCoo[] = new float[]{
            1.0f,0.0f,
            0.0f,0.0f,
            0.0f,1.0f,
            1.0f,1.0f,
    };

    private int program;
    private static final int VERTEX_DIMENSION = 3;
    private static final int TEXTURE_DIMENSION = 2;

    private FloatBuffer vertBuffer;
    private FloatBuffer textureCooBuffer;

    private int aPosition = 0;//位置的句柄
    private int aTexCoord = 1;//颜色的句柄
    private int uMVPMatrix;//顶点变换矩阵句柄
    private int textureId;//贴图id


    public GLTieTuView(Context context) {
        textureId= GLTexture.loadTexture(context, R.mipmap.tietu);

        program = LoadProgram.initProgramByAssets(context, "texture.vsh.glsl", "texture.fsh.glsl");
        vertBuffer = GLBuffer.getFloatBuffer(vertexes);
        textureCooBuffer = GLBuffer.getFloatBuffer(textureCoo);
        uMVPMatrix = GLES30.glGetUniformLocation(program, "uMVPMatrix");
    }

    public void draw(float[] mvpMatrix) {
        //清除颜色缓存和深度缓存
        GLES30.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

        // 将程序添加到OpenGL ES环境中
        GLES30.glUseProgram(program);
        GLES30.glUniformMatrix4fv(uMVPMatrix, 1, false, mvpMatrix, 0);

        //启用三角形顶点的句柄
        GLES30.glEnableVertexAttribArray(aPosition);
        //启用三角形顶点颜色的句柄
        GLES30.glEnableVertexAttribArray(aTexCoord);
        //准备三角坐标数据
        GLES30.glVertexAttribPointer(
                aPosition, VERTEX_DIMENSION,
                GLES30.GL_FLOAT, false,
                VERTEX_DIMENSION * 4, vertBuffer);

        //准备顶点颜色数据
        GLES30.glVertexAttribPointer(
                aTexCoord, TEXTURE_DIMENSION,
                GLES30.GL_FLOAT, false,
                TEXTURE_DIMENSION * 4, textureCooBuffer);


        //绑定纹理
        GLES30.glActiveTexture(GLES30.GL_TEXTURE0);
        GLES30.glBindTexture(GLES30.GL_TEXTURE_2D, textureId);

        //绘制点
        GLES30.glLineWidth(10);
        GLES30.glDrawArrays(GLES30.GL_TRIANGLE_FAN, 0, vertexes.length / VERTEX_DIMENSION);
        //禁用顶点数组
        GLES30.glDisableVertexAttribArray(aPosition);
        GLES30.glDisableVertexAttribArray(aTexCoord);
    }
}


