
#version 300 es
precision mediump float;
out vec4 outColor;

in vec2 vTexCoord;

uniform sampler2D sTexture;

uniform float uThreshold;//uniform入参

void main(){
    vec4 color= texture(sTexture, vTexCoord);
    float r = color.r;
    float g = color.g;
    float b = color.b;

    g = r * 0.3 + g * 0.59 + b * 0.11;
    g= g <= uThreshold ? 0.0 : 1.0;


    outColor = vec4(g, g, g, 1.0);
}

