package com.example.mvvm.opengl2_0.comm;

import android.opengl.Matrix;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2019/1/14/014:11:29<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：矩阵变化栈
 */

public class MatrixStack {
    //Model View Projection Matrix--模型视图投影矩阵
    private static float[] mMVPMatrix = new float[16];
    //投影矩阵 mProjectionMatrix
    private static final float[] mProjectionMatrix = new float[16];
    //视图矩阵 mViewMatrix
    private static final float[] mViewMatrix = new float[16];
    //变换矩阵
    private static float[] mOpMatrix = new float[16];

    /**
     * 获取不变换初始矩阵
     */
    public static void reset() {
        //mOpMatrix转化为单位阵
        mOpMatrix = new float[]{
                1, 0, 0, 0,
                0, 1, 0, 0,
                0, 0, 1, 0,
                0, 0, 0, 1,
        };
    }
    /**
     * 设置沿xyz轴移动
     *
     * @param x 移动的 x 分量
     * @param y 移动的 y 分量
     * @param z 移动的 z 分量
     */
    public static void translate(float x, float y, float z) {
        Matrix.translateM(mOpMatrix, 0, x, y, z);
    }

    /**
     * 设置沿(x,y,z)点旋转
     *
     * @param deg 角度
     * @param x   旋转点的 x 分量
     * @param y   旋转点的 y 分量
     * @param z   旋转点的 z 分量
     */
    public static void rotate(float deg, float x, float y, float z) {
        Matrix.setRotateM(mOpMatrix, 0, deg, x, y, z);
    }

    /**
     * 设置缩放
     * @param x   缩放的 x 分量
     * @param y   缩放的 y 分量
     * @param z   缩放的 z 分量
     */
    public static void scale(float x, float y, float z) {
        Matrix.scaleM(mOpMatrix, 0, x, y, z);
    }

    /**
     * 相机的位置
     * @param cx  摄像机位置x
     * @param cy  摄像机位置y
     * @param cz  摄像机位置z
     * @param tx  摄像机目标点x
     * @param ty  摄像机目标点y
     * @param tz  摄像机目标点z
     * @param upx 摄像机UP向量X分量
     * @param upy 摄像机UP向量Y分量
     * @param upz 摄像机UP向量Z分量
     */
    public static void lookAt(float cx, float cy, float cz,
                              float tx, float ty, float tz,
                              float upx, float upy, float upz) {
        Matrix.setLookAtM(mViewMatrix, 0,
                cx, cy, cz,
                tx, ty, tz,
                upx, upy, upz);
    }

    /**
     *  设置透视投影参数
     * @param left   near面的left
     * @param right  near面的right
     * @param bottom near面的bottom
     * @param top    near面的top
     * @param near   near面距顶点长
     * @param far    far面距顶点长
     */
    public static void frustum(
            float left, float right, float bottom,
            float top, float near, float far) {
        Matrix.frustumM(mProjectionMatrix, 0,
                left, right, bottom,
                top, near, far);
    }

    /**
     * 查看栈顶的变换矩阵
     *
     * @return mMVPMatrix
     */
    public static float[] peek() {
        submit();
        return mMVPMatrix;
    }

    /**
     * 提交变换
     */
    private static void submit() {
        Matrix.multiplyMM(
                mMVPMatrix, 0,
                mViewMatrix, 0,
                mOpMatrix, 0);

        Matrix.multiplyMM(
                mMVPMatrix, 0,
                mProjectionMatrix, 0,
                mMVPMatrix, 0);
    }

    //获取具体物体的变换矩阵
    public static float[] getOpMatrix() {
        return mOpMatrix;
    }
}

