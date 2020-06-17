#include <jni.h>
#include <string>
#include <opencv2/imgproc/types_c.h>
#include "bitmap_utils.h"
#include "FaceDetector.h"


extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_mvvm_opencvnative_MainActivity_stringFromJNI(JNIEnv *env, jobject thiz) {
    // TODO: implement stringFromJNI()

    std::string hello = "Hello from C++ author is xingshao";
    return env->NewStringUTF(hello.c_str());
}

extern "C"
JNIEXPORT jobject JNICALL
Java_com_example_mvvm_opencvnative_MainActivity_opBitmap(JNIEnv *env, jobject thiz, jobject bitmap,
                                                         jobject argb8888) {
    // TODO: implement opBitmap()

    Mat srcMat;
    Mat dstMat;
    bitmap2Mat(env, bitmap, &srcMat);
    cvtColor(srcMat, dstMat, CV_BGR2GRAY);//将图片的像素信息灰度化盛放在dstMat
    return createBitmap(env,dstMat,argb8888);//使用dstMat创建一个Bitmap对象

}
extern "C"
JNIEXPORT jint JNICALL
Java_com_example_mvvm_opencvnative_TolyCV_faceDetector(JNIEnv *env, jclass clazz, jobject bitmap,
                                                       jobject argb8888, jstring path_) {
    // TODO: implement faceDetector()

    const char *path = env->GetStringUTFChars(path_, 0);//文件路径
    FaceDetector::loadCascade(path);//加载文件

    Mat srcMat;//图片源矩阵
    bitmap2Mat(env, bitmap, &srcMat);//图片源矩阵初始化
    auto faces = FaceDetector::detectorFace(srcMat);//识别图片源矩阵，返回矩形集

    for (Rect faceRect : faces) {// 在人脸部分画矩形
        rectangle(srcMat, faceRect, Scalar(0, 253, 255), 5);//在srcMat上画矩形
        mat2Bitmap(env, srcMat, bitmap);// 把mat放回bitmap中
    }
    env->ReleaseStringUTFChars(path_, path);//释放指针
    return faces.size();//返回尺寸
}

CascadeClassifier cascadeClassifier;
//人脸检测
vector<Rect> FaceDetector::detectorFace(Mat &src) {
    vector<Rect> faces;//脸的数组
    Mat temp_mat;//用于存放识别到的图像临时矩阵
    cvtColor(src, temp_mat, COLOR_BGRA2GRAY);//灰度图,加快解析速度
    equalizeHist(temp_mat, temp_mat);//直方图均衡化
    //多尺度人脸检测
    cascadeClassifier.detectMultiScale(temp_mat, faces,  1.1,3,0, Size(300,300));
    return faces;
}

void FaceDetector::loadCascade(const char *filename) {
    cascadeClassifier.load(filename);
}