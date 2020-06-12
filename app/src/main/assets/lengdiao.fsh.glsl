#version 300 es
precision mediump float;
out vec4 outColor;

in vec2 vTexCoord;

uniform sampler2D sTexture;

const float threshold = 0.31;//阈值

void main(){
    vec4 color= texture(sTexture, vTexCoord);

    //    ---->[冷调]----
    float r = 0.393* color.r + 0.769 * color.g + 0.189*color.b;
    float g = 0.349 * color.r + 0.686 * color.g + 0.168 * color.b;
    float b = 0.272 * color.r + 0.534 * color.g + 0.131 * color.b;

    outColor = vec4(r, g, b, 1.0);
}
