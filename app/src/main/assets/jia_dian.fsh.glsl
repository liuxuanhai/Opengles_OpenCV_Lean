#version 300 es
precision mediump float;
out vec4 outColor;

in vec2 vTexCoord;

uniform sampler2D sTexture;

void main(){
    float rate= 1000.0/1369.0;
    float cellX= 1.0;
    float cellY=1.0;
    float rowCount=100.0;
    float space =4.0;

    vec2 sizeFmt=vec2(rowCount, rowCount/rate);
    vec2 sizeMsk=vec2(cellX, cellY/rate);
    vec2 posFmt = vec2(vTexCoord.x*sizeFmt.x, vTexCoord.y*sizeFmt.y);
    vec2 posMsk = vec2(floor(posFmt.x/sizeMsk.x)*sizeMsk.x, floor(posFmt.y/sizeMsk.y)*sizeMsk.y)+ 0.5*sizeMsk;

    float del = length(posMsk - posFmt);
    vec2 UVMosaic = vec2(posMsk.x/sizeFmt.x, posMsk.y/sizeFmt.y);

    vec4 result;
    if (del< cellX/space)
    result=vec4(0.2,0.2,0.2,0.1);
    else
    result = texture(sTexture, vTexCoord);
    outColor = vec4(result.g,result.g,result.g,1.0);
}
