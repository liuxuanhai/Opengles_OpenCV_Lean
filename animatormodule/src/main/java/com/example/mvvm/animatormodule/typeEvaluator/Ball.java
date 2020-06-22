package com.example.mvvm.animatormodule.typeEvaluator;

/**
 * author : 90589
 * date   : 2020/6/17
 * desc   : 描述
 */
public class Ball {
    public int color;
    public int r;
    public int x;
    public int y;

    public Ball() {
    }

    public Ball(int r, int color) {
        this.color = color;
        this.r = r;
    }

    public Ball(int r, int color, int x, int y) {
        this.color = color;
        this.r = r;
        this.x = x;
        this.y = y;
    }
}

