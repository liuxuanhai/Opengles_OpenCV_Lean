#version 300 es
precision mediump float;
out vec4 outColor;
in vec2 vTexCoord;
uniform sampler2D sTexture;

void main(){
    vec2 pos = vTexCoord.xy;
    if (pos.y<= 1.0/3.0) {
        pos.y = pos.y * 1.0;
    }else if(pos.y<= 2.0/3.0){
        pos.y = (pos.y - 1.0/3.0) * 1.0;
    }else{
        pos.y = (pos.y - 2.0/3.0) * 1.0;
    }
    outColor = texture(sTexture, pos);
}
