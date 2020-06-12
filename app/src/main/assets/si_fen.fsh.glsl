#version 300 es
precision mediump float;
out vec4 outColor;
in vec2 vTexCoord;
uniform sampler2D sTexture;

void main(){
//    vec2 pos = vTexCoord.xy;
//    if (pos.y<= 1.0/4.0) {
//        pos.y = pos.y * 1.0;
//    }else if(pos.y<= 2.0/4.0){
//        pos.y = (pos.y - 1.0/4.0) * 1.0;
//    }else if(pos.y<= 3.0/4.0){
//        pos.y = (pos.y - 2.0/4.0) * 1.0;
//    }else{
//        pos.y = (pos.y - 3.0/4.0) * 1.0;
//    }
//    outColor = texture(sTexture, pos);

//    vec2 pos = vTexCoord.xy;
//    if(pos.x <= 0.5){
//        pos.x = pos.x ;
//    }else{
//        pos.x = pos.x - 0.5;
//    }
//
//    if (pos.y<= 0.5) {
//        pos.y = pos.y ;
//    }else{
//        pos.y = pos.y - 0.5;
//    }
//    outColor = texture(sTexture, pos);

    vec2 pos = vTexCoord.xy;
    if(pos.x <= 0.5){
        pos.x = pos.x * 2.0;
    }else{
        pos.x = (pos.x - 0.5) * 2.0;
    }

    if (pos.y<= 0.5) {
        pos.y = pos.y * 2.0;
    }else{
        pos.y = (pos.y - 0.5) * 2.0;
    }
    outColor = texture(sTexture, pos);

}

