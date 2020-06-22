package com.example.mvvm.animatormodule.timeInterpolator;

import android.animation.TimeInterpolator;

/**
 * author : 90589
 * date   : 2020/6/17
 * desc   : sin型先满后快
 */

public class A_Sin_Inter implements TimeInterpolator {
    @Override
    public float getInterpolation(float input) {
        //input是一个从0~1均匀变化的值
        //从0到PI/2均匀变化的值
        float rad = (float) (Math.PI/2 * input+Math.PI/2);
        //返回这个弧度的sin值--sin曲线在PI/2~PI区域是降低越来越快
        return (float) (1-(Math.sin(rad)));//返回1-
    }
}
