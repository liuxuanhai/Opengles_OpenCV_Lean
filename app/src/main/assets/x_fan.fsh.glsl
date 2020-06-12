#version 300 es
precision mediump float;
out vec4 outColor;
in vec2 vTexCoord;
uniform sampler2D sTexture;

void main(){

    vec2 pos = vTexCoord.xy;
    pos.x= 1.0- pos.x;
    outColor = texture(sTexture, pos);
}

