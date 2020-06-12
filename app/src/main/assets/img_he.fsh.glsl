#version 300 es
precision mediump float;
out vec4 outColor;

in vec2 vTexCoord;

uniform sampler2D uTexture;
uniform sampler2D uTexture2;//多加一个纹理量

uniform float uMixture;//uniform入参

void main(){
    vec4 color3=texture(uTexture, vTexCoord);

    vec2 pos = vTexCoord.xy;
    pos.x= 1.0- pos.x;
    vec4 color = texture(uTexture, pos);


    vec4 color2 = texture(uTexture2, vTexCoord);//从纹理中采样出颜色值2
    outColor = color*(1.0-uMixture) + color2*uMixture+ color3*uMixture;// 混合两个颜色值


//    vec4 color=texture(uTexture, vTexCoord);
//    vec4 color2 = texture(uTexture2, vTexCoord);//从纹理中采样出颜色值2
//    outColor = color*(1.0-uMixture) + color2*uMixture;// 混合两个颜色值

}
