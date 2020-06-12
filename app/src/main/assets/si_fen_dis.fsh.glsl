#version 300 es
precision mediump float;
out vec4 outColor;
in vec2 vTexCoord;
uniform sampler2D sTexture;

void main(){
    vec2 pos = vTexCoord.xy;
    vec4 result;
    if(pos.x <= 0.5 && pos.y<= 0.5){ //左上
        pos.x = pos.x * 2.0;
        pos.y = pos.y * 2.0;
        vec4 color = texture(sTexture, pos);
        result = vec4(color.g, color.g, color.g, 1.0);
    }else if (pos.x >= 0.5 && pos.y<= 0.5){//右上
        pos.x = (pos.x - 0.5) * 2.0;
        pos.y = (pos.y - 0.5) * 2.0;
        vec4 color= texture(sTexture, pos);
        float r = color.r;
        float g = color.g;
        float b = color.b;
        g = r * 0.3 + g * 0.59 + b * 0.11;
        g= g <= 0.4 ? 0.0 : 1.0;
        result = vec4(g, g, g, 1.0);
    }else if (pos.y> 0.5 && pos.x < 0.5) {//左下
        pos.y = pos.y * 2.0;
        pos.x = pos.x * 2.0;
        vec4 color= texture(sTexture, pos);
        float r = color.r;
        float g = color.g;
        float b = color.b;
        r = 0.393* r + 0.769 * g + 0.189* b;
        g = 0.349 * r + 0.686 * g + 0.168 * b;
        b = 0.272 * r + 0.534 * g + 0.131 * b;
        result = vec4(r, g, b, 1.0);
    }else  if (pos.y> 0.5 && pos.x > 0.5){//右下
        pos.y = (pos.y - 0.5) * 2.0;
        pos.x = (pos.x - 0.5) * 2.0;
        vec4 color= texture(sTexture, pos);
        float r = color.r;
        float g = color.g;
        float b = color.b;
        b = 0.393* r + 0.769 * g + 0.189* b;
        g = 0.349 * r + 0.686 * g + 0.168 * b;
        r = 0.272 * r + 0.534 * g + 0.131 * b;
        result = vec4(r, g, b, 1.0);
    }
    outColor = result;
}

