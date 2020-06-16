package com.example.mvvm.opengl2_0.day1;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

/**
 * author : 90589
 * date   : 2020/6/15
 * desc   : 描述
 */
public class GLView extends GLSurfaceView {
    private int num;
    GLRenderer mRenderer;
    GLTriangleRenderer triangleRenderer;
    AssetsTriangleRender assetsTriangleRender;
    AssetsTriangleRender2 assetsTriangleRender2;
    private Context context;




    public GLView(Context context,int num) {
        super(context);
        this.num=num;
        this.context=context;
        init();
    }

    public GLView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setEGLContextClientVersion(2);//设置OpenGL ES 2.0 context
        switch (num){
            case 1:
                mRenderer = new GLRenderer();
                setRenderer(mRenderer);//设置渲染器
                break;
            case 2:
                triangleRenderer=new GLTriangleRenderer();
                setRenderer(triangleRenderer);
                break;
            case 3:
                assetsTriangleRender=new AssetsTriangleRender(context);
                setRenderer(assetsTriangleRender);
                break;
            case 4:
                assetsTriangleRender2=new AssetsTriangleRender2(context);
                setRenderer(assetsTriangleRender2);
                setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
                break;
        }

    }
}
