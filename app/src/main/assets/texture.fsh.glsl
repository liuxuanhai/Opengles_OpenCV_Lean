#version 300 es
precision mediump float;
out vec4 outColor;

in vec2 vTexCoord;

uniform sampler2D sTexture;

void main(){
    outColor = texture(sTexture, vTexCoord);
}

