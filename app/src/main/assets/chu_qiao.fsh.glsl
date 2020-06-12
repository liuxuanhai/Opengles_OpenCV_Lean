#version 300 es
precision highp float;
in vec2 vTexCoord;
out vec4 outColor;
uniform sampler2D sTexture;
//传递进来的时间
uniform float uProgress;

void main () {
    float t = 0.7; //周期
    float maxAlpha = 0.4;//第二图最大透明度
    float maxScale = 1.8;//第二图放大最大比率
    //进度
    float progress = mod(uProgress, t) / t; // 0~1
    //当前的透明度
    float alpha = maxAlpha * (1.0 - progress);
    //当前的放大比例
    float scale = 1.0 + (maxScale - 1.0) * progress;
    //根据放大比例获取新的图层纹理坐标
    vec2 weakPos = vec2(0.5 + (vTexCoord.x - 0.5) / scale, 0.5 + (vTexCoord.y - 0.5) / scale);

    //新图层纹理坐标对应的纹理像素值
    vec4 weakMask = texture(sTexture, weakPos);
    vec4 mask = texture(sTexture, vTexCoord);

    //纹理像素值的混合公式，获得混合后的实际颜色
    outColor = mask * (1.0 - alpha) + weakMask * alpha;
}

