//
// Created by ufsoft on 2021/4/16.
//

#include "Person.h"
#include <jni.h>
#include <string>
#include <android/log.h>
//1调用Log.e（）方法
#define LOG_TAG "tag"
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, __VA_ARGS__)
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR, LOG_TAG, __VA_ARGS__)
void Person::eat() {
    LOGE("person在吃饭");
}
char *Utilname = "1234";