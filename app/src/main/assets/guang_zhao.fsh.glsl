#version 300 es
precision mediump float;
out vec4 outColor;
in vec2 vTexCoord;
uniform sampler2D sTexture;

void main(){
    float rate= 1000.0/1369.0;
    float centerX =0.5;
    float centerY =0.5/rate;
    float radius =0.1;
    float strength = 150.0/255.0;//#设置光照强度

    vec2 pos;
    pos.x = vTexCoord.x;
    pos.y= vTexCoord.y/rate;
    vec4 color= texture(sTexture, vTexCoord);
    float r = color.r;
    float g = color.g;
    float b = color.b;

    float distance = sqrt((pos.x-centerX)*(pos.x-centerX)+(pos.y-centerY)*(pos.y-centerY));

    if (distance<radius){ //表示在圆的区域内
        //按照距离大小计算增强的光照值
        float result = strength*( 1.0 - distance / radius );
        r =  r+result;
        g =  g+result;
        b =  b+result;
        outColor = vec4(r, g, b, 1.0);
    } else {
        outColor = vec4(r, g, b, 1.0);
    }
}

