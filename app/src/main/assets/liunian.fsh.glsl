#version 300 es
precision mediump float;
out vec4 outColor;

in vec2 vTexCoord;

uniform sampler2D sTexture;

void main(){
    float arg = 1.9;

    vec4 color= texture(sTexture, vTexCoord);

    float r = color.r;
    float g = color.g;
    float b = color.b;

    b = sqrt(b)*arg;

//    if (b>1.0) b = 1.0;

    outColor = vec4(r, g, b, 1.0);
}

