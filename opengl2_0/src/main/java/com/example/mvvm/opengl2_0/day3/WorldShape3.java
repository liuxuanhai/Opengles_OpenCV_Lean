package com.example.mvvm.opengl2_0.day3;

import android.content.Context;
import android.opengl.GLES20;

import com.example.mvvm.opengl2_0.comm.Cons;
import com.example.mvvm.opengl2_0.comm.OP;
import com.example.mvvm.opengl2_0.comm.Shape;
import com.example.mvvm.opengl2_0.comm.SimpleShape;

import java.util.ArrayList;
import java.util.List;

/**
 * author : 90589
 * date   : 2020/6/16
 * desc   : 合并，组合图形
 */
public class WorldShape3 extends RendererAble implements OP<RendererAble> {
    List<RendererAble> mRendererAbles;
    private float[] mVertex = new float[]{
            -1.0f, 0.0f, -1.0f,//A
            -1.0f, 0.0f, 1.0f,//B
            1.0f, 0.0f, 1.0f,//C
            1.0f, 0.0f, -1.0f,//D
    };
    private float[] mColor = new float[]{
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f,
            0.21960784f, 0.56078434f, 0.92156863f, 1.0f,
    };
    public WorldShape3(Context ctx) {
        super(ctx);
        mRendererAbles = new ArrayList<>();

        Shape coo = new Shape(Cons.VERTEX_COO, Cons.COLOR_COO, GLES20.GL_LINES);
        Shape ground = new Shape(mVertex, mColor, GLES20.GL_LINE_LOOP);
        add(new SimpleShape(mContext,coo), new SimpleShape(mContext,ground));
    }
    @Override
    public void draw(float[] mvpMatrix) {
        for (RendererAble rendererAble : mRendererAbles) {
            rendererAble.draw(mvpMatrix);
        }
    }
    @Override
    public void add(RendererAble... rendererAbles) {
        for (RendererAble rendererAble : rendererAbles) {
            mRendererAbles.add(rendererAble);
        }
    }
    @Override
    public void remove(int id) {
        if (id>=mRendererAbles.size()) {
            return;
        }
        mRendererAbles.remove(id);
    }
}

