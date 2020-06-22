package com.example.mvvm.animatormodule.typeEvaluator;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * author : 90589
 * date   : 2020/6/17
 * desc   : 自定义view，实现路径，颜色变化
 */
public class AnimatorView extends View {
    private static final String TAG = "AnimatorView";
    private Paint mPaint;
    private int mRadius = 50;
    private int dx;
    private int dy;

    private ValueAnimator mAnimator;
    private ValueAnimator mColorAnimator;
    private ValueAnimator mObjAnimator;

    public AnimatorView(Context context) {
        this(context, null);
    }

    public AnimatorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(0xff94E1F7);
        Ball startBall = new Ball(50, 0xff94E1F7,0,0);
        Ball endBall = new Ball(100, 0xffF35519,500,1000);
        mObjAnimator = ValueAnimator.ofObject(new BallEvaluator(), startBall, endBall);

        mObjAnimator.setRepeatMode(ValueAnimator.REVERSE);//反转开始100->300 300->100
        mObjAnimator.setDuration(1000);//设置时长
        mObjAnimator.setRepeatCount(1);//设置重复执行次数
        mObjAnimator.setRepeatMode(ValueAnimator.REVERSE);

        mObjAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Ball ball = (Ball) animation.getAnimatedValue();
                mRadius = ball.r;
                mPaint.setColor(ball.color);
                dx=ball.x;
                dy=ball.y;
                Log.e(TAG, "onAnimationUpdate: "+dx+":"+dy);
                invalidate();
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(dx, dy);
        canvas.drawCircle(mRadius, mRadius, mRadius, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mObjAnimator.start();
                break;
            case MotionEvent.ACTION_UP:
        }

        return super.onTouchEvent(event);

    }
}


