#include <jni.h>
#include <string>
#include <android/log.h>
//1调用Log.e（）方法
#define LOG_TAG "tag"
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, __VA_ARGS__)
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR, LOG_TAG, __VA_ARGS__)
class Util{
public:
     void  print(){
        LOGE("util的打印的方法");
    }
};
