#version 300 es
precision mediump float;
out vec4 outColor;

in vec2 vTexCoord;

uniform sampler2D sTexture;

void main(){
    float rate= 1000.0/1369.0;
    float cellX= 1.0;
    float cellY= 1.0;
    float rowCount=100.0;

    vec2 pos = vTexCoord;
    pos.x = pos.x*rowCount;
    pos.y = pos.y*rowCount/rate;

    pos = vec2(floor(pos.x/cellX)*cellX/rowCount, floor(pos.y/cellY)*cellY/(rowCount/rate))+ 0.5/rowCount*vec2(cellX, cellY);
    outColor = texture(sTexture, pos);
}

