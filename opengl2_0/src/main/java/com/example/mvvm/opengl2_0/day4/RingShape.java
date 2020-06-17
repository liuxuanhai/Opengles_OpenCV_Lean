package com.example.mvvm.opengl2_0.day4;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLES30;

import com.example.mvvm.opengl2_0.GLBuffer;
import com.example.mvvm.opengl2_0.GLUtil;
import com.example.mvvm.opengl2_0.comm.Shape;
import com.example.mvvm.opengl2_0.day3.RendererAble;

import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/**
 * author : 90589
 * date   : 2020/6/17
 * desc   : 描述
 */
public class RingShape extends RendererAble {
    private FloatBuffer vertexBuffer;//顶点缓冲



    //顶点坐标
    private float mVertex[] = {   //以逆时针顺序
            -1f, 1f, 1.0f, // p0
            -1f, -1f, 1.0f, // p1
            1f, -1f, 1.0f, // p2
            1f, 1f, 1.0f, //p3
    };

    //索引数组
    private short[] idx = {
            0, 1, 3,
            1, 2, 3
    };

    //顶点颜色
    float colors[] = new float[]{
            1f, 1f, 0.0f, 1f,//黄
            0.05882353f, 0.09411765f, 0.9372549f,1f,//蓝
            0.19607843f, 1.0f, 0.02745098f, 1f,//绿
            1.0f, 0.0f, 1.0f,1f,//紫色
    };

    private Shape mShape;

    public RingShape(Context mContext, Shape mShape) {
        super(mContext);
        this.mShape = mShape;
        this.context = mContext;
        initProgram();
    }

    private Context context;

    private int mProgram;
    private int mPositionHandle;//位置句柄
    private int mColorHandle = 1;//颜色句柄
    private final int vertexCount = mVertex.length / COORDS_PER_VERTEX;//顶点个数
    private final int vertexStride = COORDS_PER_VERTEX * 4; // 3*4=12
    // 数组中每个顶点的坐标数
    static final int COORDS_PER_VERTEX = 3;
    private int muMVPMatrixHandle;//顶点变换矩阵句柄
    static final int COLOR_PER_VERTEX = 4;//向量维度
    private final int vertexColorStride = COLOR_PER_VERTEX * 4; // 4*4=16

    //成员变量
    private FloatBuffer mColorBuffer;//颜色缓冲

    private ShortBuffer idxBuffer;

    private void initProgram() {
        //初始化顶点字节缓冲区
//        bufferData();
        initVertex(28,1, (float) 0.5);
        int vertexShader = GLUtil.loadShaderAssets(context, GLES20.GL_VERTEX_SHADER, "tri_4.vert");
        int fragmentShader = GLUtil.loadShaderAssets(context, GLES20.GL_FRAGMENT_SHADER, "tri_4.frag");

        mProgram = GLES20.glCreateProgram();//创建空的OpenGL ES 程序
        GLES20.glAttachShader(mProgram, vertexShader);//加入顶点着色器
        GLES20.glAttachShader(mProgram, fragmentShader);//加入片元着色器
        GLES20.glLinkProgram(mProgram);//创建可执行的OpenGL ES项目

        //获取程序中总变换矩阵uMVPMatrix成员的句柄
        muMVPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uMVPMatrix");
        //索引缓冲
        idxBuffer = GLUtil.getShortBuffer(idx);
    }

    @Override
    public void draw(float[] mvpMatrix) {
        // 将程序添加到OpenGL ES环境中
        GLES20.glUseProgram(mProgram);
        GLES30.glUniformMatrix4fv(muMVPMatrixHandle, 1, false, mvpMatrix, 0);
        //获取顶点着色器的vPosition成员的句柄
        mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");
        //启用三角形顶点的句柄
        GLES20.glEnableVertexAttribArray(mPositionHandle);
        //准备三角坐标数据
        GLES20.glVertexAttribPointer(
                mPositionHandle, COORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                vertexStride, vertexBuffer);
        // 获取片元着色器的vColor成员的句柄
        mColorHandle = GLES20.glGetAttribLocation(mProgram, "aColor");

        //启用三角形顶点的句柄
        GLES20.glEnableVertexAttribArray(mColorHandle);


        //准备三角顶点颜色数据
        GLES20.glVertexAttribPointer(
                mColorHandle,
                COLOR_PER_VERTEX,
                GLES20.GL_FLOAT,
                false,
                vertexColorStride,
                mColorBuffer);

        //绘制
//        GLES20.glDrawElements(GLES20.GL_TRIANGLES, idx.length,
//                GLES20.GL_UNSIGNED_SHORT, idxBuffer);

        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, verticeCount);


        //绘制三角形
//        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, 5);
        //禁用顶点数组
        GLES20.glDisableVertexAttribArray(mPositionHandle);
    }


    /**
     * 缓冲数据
     */
    private void bufferData() {
        mColorBuffer = GLBuffer.getFloatBuffer(colors);
        vertexBuffer = GLBuffer.getFloatBuffer(mVertex);
    }

    int verticeCount;
    /**
     *  初始化顶点坐标与颜色
     * @param splitCount 分割点数
     * @param r 内圆半径
     * @param R 外圈半径
     */
    public void initVertex(int splitCount, float r, float R) {
        //顶点坐标数据的初始化
        verticeCount = splitCount * 2 + 2;
        float[] vertices = new float[verticeCount * 3];//坐标数据
        float thta = 360.f / splitCount;
        for (int i = 0; i < vertices.length; i += 3) {
            int n = i / 3;
            if (n % 2 == 0) {//偶数点--内圈
                vertices[i] = (float) (r * Math.cos((n / 2) * thta));//x
                vertices[i + 1] = (float) (r * Math.sin((n / 2) * thta));//y
                vertices[i + 2] = 0;//z
            } else {//奇数点--外圈
                vertices[i] = (float) (R * Math.cos((n / 2) * thta));//x
                vertices[i + 1] = (float) (R * Math.sin((n / 2) * thta));//y
                vertices[i + 2] = 0;//z
            }
        }
        //i+8 表示 每次跨度两个点
        //橙色：0.972549f,0.5019608f,0.09411765f,1.0f
        float colors[] = new float[verticeCount * 4];
        for (int i = 0; i < colors.length; i += 8) {
            colors[i + 0] = 1;
            colors[i + 1] = 1;
            colors[i + 2] = 1;
            colors[i + 3] = 1;
            colors[i + 4] = 0.972549f;
            colors[i + 5] = 0.5019608f;
            colors[i + 6] = 0.09411765f;
            colors[i + 7] = 1.0f;
        }
        vertexBuffer = GLUtil.getFloatBuffer(vertices);
        mColorBuffer = GLUtil.getFloatBuffer(colors);
    }


}
