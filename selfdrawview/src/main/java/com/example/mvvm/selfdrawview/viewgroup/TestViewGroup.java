package com.example.mvvm.selfdrawview.viewgroup;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

/**
 * author : 90589
 * date   : 2020/6/18
 * desc   : 描述
 */
public class TestViewGroup extends ViewGroup {
    View mChild;
    public TestViewGroup(Context context) {
        super(context);
    }
    public TestViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
    }
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        mChild = getChildAt(0);
        mChild.layout(0, 0, mChild.getMeasuredWidth(), mChild.getMeasuredHeight());
    }

//    ---->[TestViewGroup#onTouchEvent]-------------
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                int x = (int) event.getX();
                int y = (int) event.getY();
//                useLayout(mChild, x, y);
                useTranslation(mChild, x, y);
        }
        return super.onTouchEvent(event);
    }

    private void useLayout(View view, int x, int y) {
        view.layout(x, y,
                x + view.getMeasuredWidth(), y + view.getMeasuredHeight());
        //以下四行等价上一行
//                mChild.setLeft(x);
//                mChild.setTop(y);
//                mChild.setRight(x + mChild.getMeasuredWidth());
//                mChild.setBottom(y + mChild.getMeasuredHeight());
    }

    private void useTranslation(View view, int x, int y) {
        view.setTranslationX(x);
        view.setTranslationY(y);
    }

}
