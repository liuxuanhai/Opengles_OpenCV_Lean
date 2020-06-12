#version 300 es
precision mediump float;
out vec4 outColor;

in vec2 vTexCoord;

uniform sampler2D sTexture;

void main(){
    outColor = texture(sTexture, vTexCoord);
}

//1.2 片段着色器:texture.fsh
//precision 表示精度 lowp低、mediump中、highp高
//很容易想到，精度越↑，效果越↑，但着色器速度↓
//in vec2 vTexCoord; 表示接受顶点的输入的vTexCoord变量
//uniform 统一变量,在着色器执行期间它的值是不变的
//sampler2D 类型:2D纹理