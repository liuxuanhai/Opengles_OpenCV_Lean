package com.example.mvvm.animatormodule.timeInterpolator;

import android.animation.TimeInterpolator;

/**
 * author : 90589
 * date   : 2020/6/17
 * desc   : Log型先快后慢
 */

public class D_Log_Inter implements TimeInterpolator {
    @Override
    public float getInterpolation(float input) {
        return (float) (Math.log10(1 + 9 * input));
    }
}