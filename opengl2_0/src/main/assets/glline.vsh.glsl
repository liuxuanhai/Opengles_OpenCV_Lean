#version 300 es

layout (location = 0) in vec3 vPosition;
layout (location = 1) in vec4 aColor;
uniform mat4 uMVPMatrix;
out vec4 vColor;

void main() {
    gl_Position = uMVPMatrix*vec4(aPosition.x, aPosition.y, aPosition.z, 1.0);
    vColor = aColor;
}
