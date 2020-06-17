package com.example.mvvm.opengl2_0.day4;

import android.content.Context;
import android.opengl.GLES20;

import com.example.mvvm.opengl2_0.comm.Cons;
import com.example.mvvm.opengl2_0.comm.OP;
import com.example.mvvm.opengl2_0.comm.Shape;
import com.example.mvvm.opengl2_0.comm.SimpleShape;
import com.example.mvvm.opengl2_0.day3.RendererAble;

import java.util.ArrayList;
import java.util.List;

/**
 * author : 90589
 * date   : 2020/6/17
 * desc   : 描述
 */
public class RingShapeRenderer extends RendererAble implements OP<RendererAble> {
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


    private float[] mVertex2 = new float[]{
            1.0f, 1.0f, 1.0f,
            1.0f, -1.0f, 1.0f,

            -1.0f, 1.0f, 1.0f,
            -1.0f, -1.0f, 1.0f,

            -1.0f, 1.0f, -1.0f,
            -1.0f, -1.0f, -1.0f,

            1.0f, 1.0f, -1.0f,
            1.0f, -1.0f, -1.0f,
    };
    private float[] mColor2 = new float[]{
            1.0f, 0.0f, 0.0f, 1.0f,
            1.0f, 0.0f, 0.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f,
    };

    public RingShapeRenderer(Context ctx) {
        super(ctx);
        mRendererAbles = new ArrayList<>();

        Shape coo = new Shape(Cons.VERTEX_COO, Cons.COLOR_COO, GLES20.GL_LINES);
        Shape ground = new Shape(mVertex, mColor, GLES20.GL_LINE_LOOP);
        Shape top = ground.moveAndCreate(0, 1, 0);
        Shape bottom = ground.moveAndCreate(0, -1, 0);
        Shape side = new Shape(mVertex2, mColor2, GLES20.GL_LINES);

        Shape mian = new Shape(mVertex2, mColor2, GLES20.GL_TRIANGLES);
        add(    new SimpleShape(mContext,coo),
                new SimpleShape(mContext,top),
                new SimpleShape(mContext,bottom),
                new SimpleShape(mContext,ground),
                new SimpleShape(mContext,side),
                new RingShape(mContext,mian));

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


