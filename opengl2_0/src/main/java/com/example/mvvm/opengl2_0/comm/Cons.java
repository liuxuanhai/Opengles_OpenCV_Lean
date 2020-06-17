package com.example.mvvm.opengl2_0.comm;

/**
 * author : 90589
 * date   : 2020/6/16
 * desc   : 描述
 */
public class Cons {
    //维度：独立参数的数目
    public static final int DIMENSION_2 = 2;//2维度
    public static final int DIMENSION_3 = 3;//3维度
    public static final int DIMENSION_4 = 4;//4维度

    public static final float[] VERTEX_COO = {//坐标轴
            0.0f, 0.0f, 0.0f,//Z轴
            0.0f, 0.0f, 1.0f,
            0.0f, 0.0f, 0.0f,//X轴
            1.0f, 0.0f, 0.0f,
            0.0f, 0.0f, 0.0f,//Y轴
            0.0f, 1.0f, 0.0f,
    };
    public static final float[] COLOR_COO = {//坐标轴颜色
            0.0f, 0.0f, 1.0f, 1.0f,//Z轴:蓝色
            0.0f, 0.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 0.0f, 1.0f,//X轴：黄色
            1.0f, 1.0f, 0.0f, 1.0f,
            0.0f, 1.0f, 0.0f, 1.0f,//Y轴：绿色
            0.0f, 1.0f, 0.0f, 1.0f,
    };
}

