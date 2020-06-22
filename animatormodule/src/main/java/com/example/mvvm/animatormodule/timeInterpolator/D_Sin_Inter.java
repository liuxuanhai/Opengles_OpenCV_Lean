package com.example.mvvm.animatormodule.timeInterpolator;

import android.animation.TimeInterpolator;

/**
 * author : 90589
 * date   : 2020/6/17
 * desc   : sin型先快后慢
 */
public class D_Sin_Inter implements TimeInterpolator {
    @Override
    public float getInterpolation(float input) {
        //input是一个从0~1均匀变化的值
        //从0到PI/2均匀变化的值
        float rad = (float) (Math.PI/2 * input);
        //返回这个弧度的sin值--sin曲线在0~PI/2区域是增长越来越缓慢，小球运动越来越缓慢
        return (float) (Math.sin(rad));
    }
}

