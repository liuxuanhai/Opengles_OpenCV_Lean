#version 300 es
precision mediump float;
out vec4 outColor;

in vec2 vTexCoord;

uniform sampler2D sTexture;

const float threshold = 0.31;//阈值

void main(){
    vec4 color= texture(sTexture, vTexCoord);
    float r = 1.0 - color.r;
    float g = 1.0 - color.g;
    float b = 1.0 - color.b;

    outColor = vec4(r, g, b, 1.0);
}
