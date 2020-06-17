
#include <android/bitmap.h>
#include <opencv2/opencv.hpp>
using namespace cv;

#include <vector>
#include "include/opencv2/core/types.hpp"
#include "include/opencv2/core/mat.hpp"
#include "../../../../../../sdk/ndk/21.0.6113669/toolchains/llvm/prebuilt/windows-x86_64/sysroot/usr/include/c++/v1/vector"

using std::vector;//有分号
#ifndef VIDEOLEARNPROJECT_FACEDETECTOR_H
#define VIDEOLEARNPROJECT_FACEDETECTOR_H
class FaceDetector{
public:
    //加载文件
    static void loadCascade(const char *filename);
    //识别矩阵，返回脸的矩形列表
    static vector<Rect> detectorFace(Mat &src);
};
#endif //VIDEOLEARNPROJECT_FACEDETECTOR_H