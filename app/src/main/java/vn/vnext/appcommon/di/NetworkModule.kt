package vn.vnext.appcommon.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import vn.vnext.appcommon.data.repository.AuthenticationRepositoryImpl
import vn.vnext.appcommon.data.source.remote.IAuthenticationApi
import vn.vnext.appcommon.domain.preferences.PrefsHelper
import vn.vnext.appcommon.domain.repository.IAuthenticationRepository
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    private val BASE_URL = "https://restful-booker.herokuapp.com/"

    private val READ_TIMEOUT = 30
    private val WRITE_TIMEOUT = 30
    private val CONNECTION_TIMEOUT = 10
    private val CACHE_SIZE_BYTES = 10 * 1024 * 1024L

    @Provides
    @Singleton
    internal fun provideRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(
        @ApplicationContext context: Context,
        prefsHelper: PrefsHelper
    ): OkHttpClient {
        val mCache = Cache(context.cacheDir, CACHE_SIZE_BYTES)
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .cache(mCache) // make your app offline-friendly without a database!
            .connectTimeout(CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .addNetworkInterceptor(interceptor)
            .addInterceptor { chain ->
                var request = chain.request()
                val token = prefsHelper.readString("token", null)
                request =
                    if (token != null) request
                        .newBuilder()
                        .header("token", token)
                        .build()
                    else request
                        .newBuilder()
                        .build()
                chain.proceed(request)
            }
        return client.build()
    }

    //API di
    @Provides
    @Singleton
    fun providesLoginApi(retrofit: Retrofit): IAuthenticationApi {
        return retrofit.create(IAuthenticationApi::class.java)
    }

    //REPOSITORY di
    @Provides
    @Singleton
    fun providesAuthenticationRepository(
        authenticationApi: IAuthenticationApi,
        prefsHelper: PrefsHelper
    ): IAuthenticationRepository {
        return AuthenticationRepositoryImpl(authenticationApi, prefsHelper)
    }
}