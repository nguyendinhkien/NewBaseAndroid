package vn.vnext.appcommon.di

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import vn.vnext.appcommon.data.source.preferences.PrefsHelperImpl
import vn.vnext.appcommon.domain.preferences.PrefsHelper
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class SharedPrefsModule {
    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)

    @Singleton
    @Provides
    fun providePrefsHelper(sharedPreferences: SharedPreferences): PrefsHelper {
        return PrefsHelperImpl(sharedPreferences)
    }

}