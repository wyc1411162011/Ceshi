#include <jni.h>
#include <string>
#include <android/log.h>
//1调用Log.e（）方法
#define LOG_TAG "tag"
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, __VA_ARGS__)
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR, LOG_TAG, __VA_ARGS__)
#include "Util.cpp"
#include"son/Person.h"
extern "C" JNIEXPORT jstring JNICALL
Java_com_yonyou_jni_JniTest_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "在项目中添加的";

    return env->NewStringUTF(hello.c_str());
}
//3获取调用的java类的静态成员变量的方法
extern "C" JNIEXPORT void JNICALL callJavaStaticFiled(JNIEnv *env, jobject instance){
    jclass  clazz;
    clazz=env->GetObjectClass(instance);
    jfieldID  staticid= env->GetStaticFieldID(clazz,"staticField","Ljava/lang/String;");
    jstring filedValue = (jstring)env->GetStaticObjectField(clazz,staticid);
    const char *cValue = env->GetStringUTFChars(filedValue,0);
    LOGE("静态变量的..... %s",cValue);
}
//4调用java的成员方法
extern "C" JNIEXPORT void JNICALL callJavaMethod(JNIEnv *env, jobject instance){
    jclass  clazz;
    clazz=env->GetObjectClass(instance);

    jmethodID  jmethodId = env->GetMethodID(clazz,"instanceMethod","()Ljava/lang/String;");
    jstring methodResult = (jstring)env->CallObjectMethod(instance,jmethodId);
    LOGE("java方法 .....%s",env->GetStringUTFChars(methodResult,0));
}
//5调用java的静态方法
extern "C" JNIEXPORT void JNICALL callJavaStaticMethod(JNIEnv *env, jobject instance){
    jclass  clazz;
    clazz=env->GetObjectClass(instance);

    jmethodID  jmethodId = env->GetStaticMethodID(clazz,"staticMethod","()Ljava/lang/String;");
    jstring methodResult = (jstring)env->CallStaticObjectMethod(clazz,jmethodId);
    LOGE("java的静态方法调用..... %s",env->GetStringUTFChars(methodResult,0));
}
//全局引用
jclass  globalClazz=NULL;
//全局弱引用
jclass weakGlobalClazz=NULL;
//7引用的用法
extern "C" JNIEXPORT void JNICALL yinyon(JNIEnv *env, jobject instance){
    //局部变量
    jclass localClazz=env->FindClass("java/lang/String");
    weakGlobalClazz = (jclass)env->NewWeakGlobalRef(localClazz);
    //全局变量
    if(globalClazz==NULL){
         globalClazz=(jclass)env->NewGlobalRef(localClazz);

    }
    if(globalClazz !=NULL){
        env->DeleteGlobalRef(globalClazz);
        globalClazz=NULL;
    }
    if(JNI_FALSE==env->IsSameObject(weakGlobalClazz,NULL)){
        //对象处于活动状态可以使用的情况
        LOGE("弱引用存在...");
    }else{
        //对象呗垃圾回收期回收
        LOGE("弱引用销毁...");
    }
    if(weakGlobalClazz != NULL){
        env->DeleteWeakGlobalRef(weakGlobalClazz);
        weakGlobalClazz=NULL;
    }
    if(localClazz != NULL){
        env->DeleteLocalRef(localClazz);
        localClazz=NULL;
    }
    static jclass hah;

}
extern "C"
JNIEXPORT void JNICALL
Java_com_yonyou_jni_JniTest_test(JNIEnv *env, jobject instance, jstring name) {
    const char * str;
    jboolean  isCopy;
    jboolean hah;
    str = env->GetStringUTFChars(name,&isCopy);
    //1.调用Android的log方法
    LOGE("调用成功了方法.....   %s %b",str,hah);

    //2.调用java的成员变量
    jclass  clazz;
    clazz=env->GetObjectClass(instance);
    jfieldID instanceFiedId = env->GetFieldID(clazz,"instanceField","Ljava/lang/String;");
     jstring  instanceFieldStr=(jstring)env->GetObjectField(instance,instanceFiedId);
     const char * cInstaceFiled =env->GetStringUTFChars(instanceFieldStr,0);
    LOGE("获取的成员变量的值 %s",cInstaceFiled);
    //3获取调用的java类的静态成员变量的方法
    callJavaStaticFiled(env,instance);
    //4调用java的成员方法
    callJavaMethod(env,instance);
    //5调用java的静态方法
    callJavaStaticMethod(env,instance);
    //6调用Util的方法
    Util *util = new Util();
    util->print();
    Person *person = new Person();
    person->eat();
    LOGE("全局变量的值%s",Utilname);
    //7引用的调用
    yinyon(env,instance);
}
 const char *className = "com/yonyou/jni/JniTest";

 void sayHello(JNIEnv *env, jobject, jlong handle) {
    LOGE( "native: say hello ###");
}

 JNINativeMethod gJni_Methods_table[] = {
        {"sayHello", "(J)V", (void*)sayHello},
};

 int jniRegisterNativeMethods(JNIEnv* env, const char* className,
                                    const JNINativeMethod* gMethods, int numMethods)
{
    jclass clazz;

    LOGE("Registering %s natives\n", className);
    clazz = (env)->FindClass( className);
    if (clazz == NULL) {
        LOGE("Native registration unable to find class '%s'\n", className);
        return -1;
    }

    int result = 0;
    if ((result=(env)->RegisterNatives(clazz, gJni_Methods_table, numMethods)) < 0) {
        LOGE("RegisterNatives failed for '%s'\n", className);
        result = -1;
    }

    (env)->DeleteLocalRef(clazz);
    return result;
}

jint JNI_OnLoad(JavaVM* vm, void* reserved){
    LOGE( "enter jni_onload");

    JNIEnv* env = NULL;
    jint result = -1;

    if (vm->GetEnv((void**) &env, JNI_VERSION_1_4) != JNI_OK) {
        return result;
    }

    jniRegisterNativeMethods(env, className, gJni_Methods_table, sizeof(gJni_Methods_table) / sizeof(JNINativeMethod));

    return JNI_VERSION_1_4;
}
