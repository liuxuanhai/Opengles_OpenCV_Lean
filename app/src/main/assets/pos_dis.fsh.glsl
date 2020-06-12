//#version 300 es
//precision mediump float;
//out vec4 outColor;
//in vec2 vTexCoord;
//uniform sampler2D sTexture;
//
//void main(){
//
//    float centerX =0.5;
//    float centerY =0.5;
//    float raduius =0.1;
//
//    vec2 pos = vTexCoord.xy;
//
//    vec4 color= texture(sTexture, vTexCoord);
//    float r = color.r;
//    float g = color.g;
//    float b = color.b;
//
//    if((pos.x-centerX)*(pos.x-centerX)+(pos.y-centerY)*(pos.y-centerY)<raduius*raduius){//表示在圆的区域内
//        outColor = vec4(g, g, g, 1.0);
//    }else{
//        outColor = vec4(r, g, b, 1.0);
//    }
//}
//
#version 300 es
precision mediump float;
out vec4 outColor;
in vec2 vTexCoord;
uniform sampler2D sTexture;

void main(){
    float rate= 1000.0/1369.0;
    float centerX =0.4;
    float centerY =0.3/rate;
    float raduius =0.1;

    vec2 pos;
    pos.x = vTexCoord.x;
    pos.y= vTexCoord.y/rate;
    vec4 color= texture(sTexture, vTexCoord);
    float r = color.r;
    float g = color.g;
    float b = color.b;

    if ((pos.x-centerX)*(pos.x-centerX)+(pos.y-centerY)*(pos.y-centerY)<raduius*raduius){ //表示在圆的区域内
        outColor = vec4(g, g, g, 1.0);
    } else {
        outColor = vec4(r, g, b, 1.0);
    }
}

