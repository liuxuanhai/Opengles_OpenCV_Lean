package com.example.mvvm.selfdrawview.viewgroup;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.example.mvvm.selfdrawview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * author : 90589
 * date   : 2020/6/18
 * desc   : 描述
 */
public class FlowerLayout extends ViewGroup {
    private int mRadius;
    private static final String TAG = "FlowerLayout";
    private FlowerAdapter mAdapter;

    //    ---->[维护成员变量]-------------
    private int centerId = 0;//默认中心点
    private int swpId = 0;//默认中心点

    public void setAdapter(FlowerAdapter adapter) {
        mAdapter = adapter;
    }

    public FlowerLayout(Context context) {
        this(context, null);
    }

    public FlowerLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowerLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
        setBackgroundColor(0x55D3E8FD);
    }

    /**
     * 用来显示点阵的二维数组
     */
    public static final int[][] digit_test = new int[][]
            {
                    {0, 0, 0, 0, 0, 0, 0},
                    {0, 1, 1, 0, 1, 1, 0},
                    {1, 0, 0, 1, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 1},
                    {0, 1, 0, 0, 0, 1, 0},
                    {0, 0, 1, 0, 1, 0, 0},
                    {0, 0, 0, 1, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0},
            };

    /**
     * 点位解析器
     *
     * @param w 单体宽
     * @param h 单体高
     * @return 解析成的点位数组
     */
    private List<Point> renderDigit(int w, int h) {
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < digit_test.length; i++) {
            for (int j = 0; j < digit_test[j].length; j++) {//一行一行遍历，遇到1就画
                if (digit_test[i][j] == 1) {
                    int rX = (j * 2 + 1) * (w + 1);//第(i，j)个点圆心横坐标
                    int rY = (i * 2 + 1) * (h + 1);//第(i，j)个点圆心纵坐标
                    points.add(new Point(rX, rY));
                }
            }
        }
        return points;
    }

    private void init(AttributeSet attrs) {
    }

    ValueAnimator mAnimator;

    private void formFlower() {
        for (int i = 0; i < mAdapter.getCount(); i++) {
            View petal = mAdapter.getView(i, null, this);
            final int position = i;
            if (mOnItemClickListener != null) {
                petal.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        ObjectAnimator.ofFloat(v, "ScaleX", 1f, 0.8f, 1f).setDuration(200).start();
                        ObjectAnimator.ofFloat(v, "ScaleY", 1f, 0.8f, 1f).setDuration(200).start();
                        mOnItemClickListener.onClick(v, FlowerLayout.this, position);

                        if (mAnimator != null && mAnimator.isRunning())
                            return;

                        if (position == centerId) {
                            mAnimator = ValueAnimator.ofInt(0, 360);
                            mAnimator.setDuration(3000);
                            mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                                @Override
                                public void onAnimationUpdate(ValueAnimator animation) {
                                    int deg = (int) animation.getAnimatedValue();
                                    layoutCircle(0, deg);
                                }
                            });
                            mAnimator.start();

                        }else {
                            swapWithAnim(position, centerId);
                        }
                    }

                });
            }
            addView(petal);//填入花瓣
        }
    }


    @Override
    protected void onAttachedToWindow() {
        Log.e(TAG, "onAttachedToWindow: ");
        if (mAdapter != null) {
            formFlower();
        }
        super.onAttachedToWindow();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //TODO 布局子view
//        ---->[FlowerLayout#onLayout]---------1-------
     /*   int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            int childW = childView.getMeasuredWidth();
            int childH = childView.getMeasuredHeight();
            int topPos = (int) (childH * i*1.0f);
            int leftPos = i*childView.getMeasuredWidth();
            childView.layout(leftPos, topPos, leftPos + childW, topPos + childH);
        }*/
//        |-- 现在只要修改topPos和leftPos就可以改变子View布局


        //        ---->[FlowerLayout#onLayout]---------2圆形-------
        /*int count = mAdapter.getCount();
        for (int i = 0; i < count; i++) {
            View childView = getChildAt(i);
            int childW = childView.getMeasuredWidth();
            int childH = childView.getMeasuredHeight();
            mRadius = (getWidth() - childW) / 2;
            float posX = childW / 2 + mRadius - mRadius * cos(i * 360.f / count);
            float posY = childH / 2 + mRadius - mRadius * sin(i * 360.f / count);
            int leftPos = (int) (posX - childW / 2);
            int topPos = (int) (posY - childH / 2);
            Log.i("-->坐标》》","mRadius==>"+mRadius);
            Log.i("-->坐标》》","childW==>"+childW+"childH==>"+childH);
            Log.i("-->坐标》》","posX==>"+posX+"posY==>"+posY);
            Log.i("-->坐标》》","leftPos==>"+leftPos+"topPos==>"+topPos+"right==>"+leftPos + childW+"bottom==>"+topPos + childH);
            childView.layout(leftPos, topPos, leftPos + childW, topPos + childH);
        }*/

        //        ---->[FlowerLayout#onLayout]---------3根据矩阵显示-------
      /*  List<Point> points = renderDigit(
                getChildAt(0).getMeasuredWidth() / 2,
                getChildAt(0).getMeasuredHeight() / 2
        );
        int count = mAdapter.getCount();
        for (int i = 0; i < count; i++) {
            View childView = getChildAt(i);
            int childW = childView.getMeasuredWidth();
            int childH = childView.getMeasuredHeight();
            mRadius = (getWidth() - childW) / 2;
            int leftPos = (int) (points.get(i).x - childW / 2);
            int topPos = (int) (points.get(i).y - childH / 2);
            childView.layout(leftPos, topPos, leftPos + childW, topPos + childH);
        }*/

        //        ---->[FlowerLayout#onLayout]---------4根据图片像素显示-------
       /* Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.heart);
        List<Point> points = renderBitmap(mBitmap,
                getChildAt(0).getMeasuredWidth() / 2,
                getChildAt(0).getMeasuredHeight() / 2
        );
        int count = mAdapter.getCount();
        for (int i = 0; i < count; i++) {
            View childView = getChildAt(i);
            int childW = childView.getMeasuredWidth();
            int childH = childView.getMeasuredHeight();
            mRadius = (getWidth() - childW) / 2;
            int leftPos = (int) (points.get(i).x - childW / 2);
            int topPos = (int) (points.get(i).y - childH / 2);
            childView.layout(leftPos, topPos, leftPos + childW, topPos + childH);
        }*/

        //        ---->[FlowerLayout#onLayout]---------5添加动画-------
        layoutCircle(0, 0);

    }

    private float cos(float θ) {
        return (float) Math.cos(θ / 180 * Math.PI);
    }

    private float sin(float θ) {
        return (float) Math.sin(θ / 180 * Math.PI);
    }


    /**
     * @param start 第一个排成圆的View索引
     * @param dθ    旋转角度
     */
    private void layoutCircle(int start, float dθ) {
        int count = getChildCount();

        Log.i("-->layoutCircle", "centerId==>" + centerId);
        int num=0;
        for (int i = start; i < count; i++) {
            Log.i("-->layoutCircle", "centerId==>" + centerId);

            if (i != centerId) {
                View childView = getChildAt(i);
                int childW = childView.getMeasuredWidth();
                int childH = childView.getMeasuredHeight();
                int r = (getWidth() - childW) / 2;

                float posX = childW / 2 + r - r * cos(num * 360.f / (count - 1) + dθ);
                float posY = childH / 2 + r - r * sin(num* 360.f / (count - 1) + dθ);
                int leftPos = (int) (posX - childW / 2);
                int topPos = (int) (posY - childH / 2);
                Log.i("-->坐标》》","mRadius==>"+mRadius);
                Log.i("-->坐标》》","childW==>"+childW+"childH==>"+childH);
                Log.i("-->坐标》》","posX==>"+posX+"posY==>"+posY);
                Log.i("-->坐标》》","leftPos==>"+leftPos+"topPos==>"+topPos+"right==>"+leftPos + childW+"bottom==>"+topPos + childH);
                childView.layout(leftPos, topPos, leftPos + childW, topPos + childH);
                ++num;

            } else {
                View childView = getChildAt(centerId);
                int childW = childView.getMeasuredWidth();
                int childH = childView.getMeasuredHeight();
                int r = (getWidth() - childW) / 2;
                float posX = childW / 2 + r;
                float posY = childH / 2 + r;
                int leftPos = (int) (posX - childW / 2);
                int topPos = (int) (posY - childH / 2);
                childView.layout(leftPos, topPos, leftPos + childW, topPos + childH);

            }

        }


    }


    //----------------------------条目点击监听-------------------
    public interface OnItemClickListener {
        void onClick(View v, ViewGroup viewGroup, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }


    /**
     * 点位解析器
     *
     * @param bitmap bitmap
     * @param w      单体宽
     * @param h      单体高
     * @return 解析成的点位数组
     */
    public static List<Point> renderBitmap(Bitmap bitmap, int w, int h) {
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < bitmap.getWidth(); i++) {
            for (int j = 0; j < bitmap.getHeight(); j++) {
                int pixel = bitmap.getPixel(i, j);
                if (pixel != -1) {//此处过滤掉白颜色
                    int rX = (i * 2 + 1) * (w + 1);//第(i，j)个点圆心横坐标
                    int rY = (j * 2 + 1) * (h + 1);//第(i，j)个点圆心纵坐标
                    points.add(new Point(rX, rY));
                }
            }
        }
        return points;
    }

    /**
     * 交换两个View的位置
     *
     * @param positionMe 点击者
     * @param positionHe 目标
     */
    private void swap(int positionMe, int positionHe) {
        Log.i("--->swap", "positionMe" + positionMe + "positionHe" + positionHe);
        View me = getChildAt(positionMe);
        View he = getChildAt(positionHe);
        int TempMeLeft = me.getLeft();
        int TempMeTop = me.getTop();
        int TempMeRight = me.getRight();
        int TempMeBottom = me.getBottom();
        me.layout(he.getLeft(), he.getTop(), he.getRight(), he.getBottom());
        he.layout(TempMeLeft, TempMeTop, TempMeRight, TempMeBottom);
        centerId = positionMe;
    }


    /**
     * 交换两个View的位置
     *
     * @param positionMe 点击者
     * @param positionHe 目标
     */
    private void swapWithAnim(int positionMe, int positionHe) {
        Log.i("->swap", "positionMe" + positionMe + "positionHe" + positionHe);
        View me = getChildAt(positionMe);
        View he = getChildAt(positionHe);
        int TempMeLeft = me.getLeft();
        int TempMeTop = me.getTop();
        useLayoutAnimate(me, he.getLeft(), he.getTop());
        useLayoutAnimate(he, TempMeLeft, TempMeTop);
        centerId = positionMe;


    }

    private void useLayoutAnimate(View view, int x, int y) {
        ObjectAnimator.ofInt(view, "Left", x).setDuration(500).start();
        ObjectAnimator.ofInt(view, "Top", y).setDuration(500).start();
        ObjectAnimator.ofInt(view, "Right", x + view.getMeasuredWidth()).setDuration(500).start();
        ObjectAnimator.ofInt(view, "Bottom", y + view.getMeasuredHeight()).setDuration(500).start();
    }


}

