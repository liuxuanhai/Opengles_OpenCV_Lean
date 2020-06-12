
#include <jni.h>
#include <string>
#include "Facer.h"

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_mvvm_learnndkmoudle_jni_1creator_Facer_getFacer(JNIEnv *env, jclass clazz, jstring top,
                                              jstring bottom, jstring brow, jstring eyes) {
    Facer facer(//使用 env->GetStringUTFChars将jstring转化为string
            env->GetStringUTFChars(top, 0),
            env->GetStringUTFChars(bottom, 0),
            env->GetStringUTFChars(brow, 0),
            env->GetStringUTFChars(eyes, 0)
            );

    return env->NewStringUTF(facer.getFace().c_str());
}
