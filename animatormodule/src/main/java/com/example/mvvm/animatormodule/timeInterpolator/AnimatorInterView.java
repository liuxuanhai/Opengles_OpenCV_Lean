package com.example.mvvm.animatormodule.timeInterpolator;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;

import androidx.annotation.Nullable;

/**
 * author : 90589
 * date   : 2020/6/17
 * desc   : 描述
 */
public class AnimatorInterView extends View {
    private static final String TAG = "AnimatorView";

    private Paint mPaint;
    private int mRadius = 50;
    private int dx[];
    private String[] mStrings;
    private TimeInterpolator[] mInterpolators;

    public AnimatorInterView(Context context) {
        this(context, null);
    }

    public AnimatorInterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(0xff94E1F7);
        mPaint.setTextSize(40);
        mStrings = new String[]{"Linear", "Bounce", "AOI", "OI", "D_sin", "D_log", "A_sin", "A_log"};
        mInterpolators = new TimeInterpolator[]{
                new LinearInterpolator(),
                new BounceInterpolator(),
                new AnticipateOvershootInterpolator(),
                new OvershootInterpolator(),
                new D_Sin_Inter(),
                new D_Log_Inter(),
                new A_Sin_Inter()};
        dx = new int[mInterpolators.length];
    }

    private ValueAnimator createAnimator(final int index, TimeInterpolator interpolator) {
        ValueAnimator mAnimator = ValueAnimator.ofInt(0, 800);
        mAnimator.setRepeatCount(1);//设置重复执行次数
        mAnimator.setRepeatMode(ValueAnimator.REVERSE);//反转开始100->300 300->100
        mAnimator.setDuration(3000);//设置时长
        mAnimator.setInterpolator(interpolator);
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                dx[index] = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        return mAnimator;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < dx.length; i++) {
            canvas.translate(0, 120);
            mPaint.setColor(0xff94E1F7);
            canvas.drawCircle(mRadius + dx[i], mRadius, mRadius, mPaint);
            mPaint.setColor(0xff000000);
            mPaint.setStrokeWidth(4);
            canvas.drawLine(mRadius, mRadius, 800 + mRadius, mRadius, mPaint);
            canvas.drawText(mStrings[i], 800 + 3 * mRadius, mRadius, mPaint);
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                for (int i = 0; i < mInterpolators.length; i++) {
                    createAnimator(i, mInterpolators[i]).start();
                }
                break;
            case MotionEvent.ACTION_UP:
        }
        return super.onTouchEvent(event);
    }
}

