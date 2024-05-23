package vn.nguyendinhkien.appcommon.di

import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import vn.nguyendinhkien.appcommon.data.source.preferences.PrefsHelperImpl
import vn.nguyendinhkien.appcommon.domain.preferences.PrefsHelper

val sharedPrefsModule = module {
    single<SharedPreferences> { PreferenceManager.getDefaultSharedPreferences(androidContext()) }
    single<PrefsHelper> { PrefsHelperImpl(get()) }
}