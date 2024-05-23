package vn.nguyendinhkien.appcommon

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import vn.nguyendinhkien.appcommon.di.appModule
import vn.nguyendinhkien.appcommon.di.databaseModule
import vn.nguyendinhkien.appcommon.di.networkModule
import vn.nguyendinhkien.appcommon.di.sharedPrefsModule

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        startKoin {
            androidContext(this@MyApplication)
            modules(appModule, networkModule, sharedPrefsModule, databaseModule)
        }
    }

    companion object {
        lateinit var INSTANCE: MyApplication
    }
}