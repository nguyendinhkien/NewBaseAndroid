package vn.nguyendinhkien.appcommon.utils

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings

@SuppressLint("HardwareIds")
fun getDeviceId(context: Context): String = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID);

//inline fun inReleaseMode(func: () -> Unit) {
//    if (BuildConfig.BUILD_TYPE == "release") {
//        func()
//    }
//}
//
//inline fun inDebugMode(func: () -> Unit) {
//    if (BuildConfig.BUILD_TYPE == "debug") {
//        func()
//    }
//}