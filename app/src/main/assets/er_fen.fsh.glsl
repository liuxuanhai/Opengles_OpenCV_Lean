#version 300 es
precision mediump float;
out vec4 outColor;

in vec2 vTexCoord;

uniform sampler2D sTexture;


void main(){

    vec2 pos = vTexCoord.xy;
    if (pos.y<= 0.5) {
        pos.y = pos.y ;
    }else{
        pos.y = pos.y - 0.5;
    }
    outColor = texture(sTexture, pos);

}

