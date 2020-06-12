package com.example.mvvm.videolearnproject.opengl.comm;

import android.opengl.GLES30;

/**
 * author : 90589
 * date   : 2020/6/9
 * desc   : 描述
 */
public class GLLoader {

    public static int loadShader(int type ,String code) {
        int shader = GLES30.glCreateShader(type);
        GLES30.glShaderSource(shader,code);
        GLES30.glCompileShader(shader);
        return shader;
    }
}
