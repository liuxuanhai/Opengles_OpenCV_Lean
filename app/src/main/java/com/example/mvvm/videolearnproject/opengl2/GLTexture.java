package com.example.mvvm.videolearnproject.opengl2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES30;
import android.opengl.GLUtils;

/**
 * author : 90589
 * date   : 2020/6/9
 * desc   : 描述
 */
//贴图工具类
public class GLTexture {
    /**
     * 资源id 加载纹理,默认重复方式：RepeatType.REPEAT
     *
     * @param ctx   上下文
     * @param resId 资源id
     * @return 纹理id
     */
    public static int loadTexture(Context ctx, int resId) {
        return loadTexture(ctx, resId, RepeatType.REPEAT);
    }
    /**
     * 图片加载纹理,默认重复方式：RepeatType.REPEAT
     * @param bitmap 图片
     * @return 纹理id
     */
    public static int loadTexture(Bitmap bitmap) {
        return loadTexture(bitmap, RepeatType.REPEAT);
    }
    /**
     * 资源id 加载纹理
     *
     * @param ctx        上下文
     * @param resId      资源id
     * @param repeatType 重复方式 {@link RepeatType}
     * @return 纹理id
     */
    public static int loadTexture(Context ctx, int resId, RepeatType repeatType) {
        Bitmap bitmap = BitmapFactory.decodeResource(ctx.getResources(), resId);
        return loadTexture(bitmap, repeatType);
    }
    /**
     * bitmap 加载纹理
     *
     * @param bitmap     bitmap
     * @param repeatType 重复方式 {@link RepeatType}
     * @return 纹理id
     */
    public static int loadTexture(Bitmap bitmap, RepeatType repeatType) {
        //生成纹理ID
        int[] textures = new int[1];
        //(产生的纹理id的数量,纹理id的数组,偏移量)
        GLES30.glGenTextures(1, textures, 0);
        int textureId = textures[0];
        //绑定纹理id
        GLES30.glBindTexture(GLES30.GL_TEXTURE_2D, textureId);
        //采样方式MIN
        GLES30.glTexParameterf(GLES30.GL_TEXTURE_2D, GLES30.GL_TEXTURE_MIN_FILTER, GLES30.GL_NEAREST);
        GLES30.glTexParameterf(GLES30.GL_TEXTURE_2D, GLES30.GL_TEXTURE_MAG_FILTER, GLES30.GL_LINEAR);
        int wrapS = 0;
        int wrapT = 0;
        switch (repeatType) {
            case NONE:
                wrapS = GLES30.GL_CLAMP_TO_EDGE;
                wrapT = GLES30.GL_CLAMP_TO_EDGE;
                break;
            case REPEAT_X:
                wrapS = GLES30.GL_REPEAT;
                wrapT = GLES30.GL_CLAMP_TO_EDGE;
                break;
            case REPEAT_Y:
                wrapS = GLES30.GL_CLAMP_TO_EDGE;
                wrapT = GLES30.GL_REPEAT;
                break;
            case REPEAT:
                wrapS = GLES30.GL_REPEAT;
                wrapT = GLES30.GL_REPEAT;
                break;
        }
        //设置s轴拉伸方式---重复
        GLES30.glTexParameterf(GLES30.GL_TEXTURE_2D, GLES30.GL_TEXTURE_WRAP_S, wrapS);
        //设置t轴拉伸方式---重复
        GLES30.glTexParameterf(GLES30.GL_TEXTURE_2D, GLES30.GL_TEXTURE_WRAP_T, wrapT);
        //实际加载纹理(纹理类型,纹理的层次,纹理图像,纹理边框尺寸)
        GLUtils.texImage2D(GLES30.GL_TEXTURE_2D, 0, bitmap, 0);
        bitmap.recycle();          //纹理加载成功后释放图片
        return textureId;
    }
}

enum RepeatType {
    NONE,//不重复
    REPEAT_X,//仅x轴重复
    REPEAT_Y,//仅y轴重复
    REPEAT//x,y重复
}

