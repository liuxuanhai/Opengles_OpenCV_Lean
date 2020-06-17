package com.example.mvvm.opengl2_0.comm;

/**
 * author : 90589
 * date   : 2020/6/16
 * desc   : 描述
 */
public class Shape implements Cloneable{
    private float[] mVertex;//顶点
    private float[] mColor;//颜色
    private int mDrawType;//绘制类型


    public Shape(float[] mVertex, float[] mColor, int mDrawType) {
        this.mVertex = mVertex;
        this.mColor = mColor;
        this.mDrawType = mDrawType;
    }

    public float[] getmVertex() {
        return mVertex;
    }

    public float[] getmColor() {
        return mColor;
    }

    public int getmDrawType() {
        return mDrawType;
    }

    /**
     * 深拷贝
     * @return 形状副本
     */
    public Shape clone() {
        Shape clone = null;
        try {
            clone = (Shape) super.clone();
            float[] vertex = new float[mVertex.length];
            float[] color = new float[mColor.length];
            System.arraycopy(mVertex, 0, vertex, 0, mVertex.length);
            System.arraycopy(mColor, 0, color, 0, mColor.length);
            clone.mVertex = vertex;
            clone.mColor = color;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }


    /**
     * 移动并创建新图形
     * @param x
     * @param y
     * @param z
     * @return
     */
    public Shape moveAndCreate(float x, float y, float z) {
        Shape clone = clone();
        clone.move(x, y, z);
        return clone;
    }

    /**
     * 仅移动图形
     * @param x
     * @param y
     * @param z
     */
    public void move(float x, float y, float z) {
        for (int i = 0; i < mVertex.length; i++) {
            if (i % 3 == 0) {//x
                mVertex[i] += x;
            }
            if (i % 3 == 1) {//y
                mVertex[i] += y;
            }
            if (i % 3 == 2) {//y
                mVertex[i] += z;
            }
        }
    }

}
