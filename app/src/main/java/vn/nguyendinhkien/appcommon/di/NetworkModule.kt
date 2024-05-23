package vn.nguyendinhkien.appcommon.di

import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import vn.nguyendinhkien.appcommon.core.AppConstants
import vn.nguyendinhkien.appcommon.data.repository.AuthenticationRepositoryImpl
import vn.nguyendinhkien.appcommon.data.repository.NoteRepositoryImpl
import vn.nguyendinhkien.appcommon.data.source.remote.IAuthenticationApi
import vn.nguyendinhkien.appcommon.domain.preferences.PrefsHelper
import vn.nguyendinhkien.appcommon.domain.repository.IAuthenticationRepository
import vn.nguyendinhkien.appcommon.domain.repository.INoteRepository
import java.util.concurrent.TimeUnit

private val BASE_URL = "https://restful-booker.herokuapp.com/"

private val READ_TIMEOUT = 30
private val WRITE_TIMEOUT = 30
private val CONNECTION_TIMEOUT = 10
private val CACHE_SIZE_BYTES = 10 * 1024 * 1024L

private val clientModule = module {
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }
    single<OkHttpClient> {
        val mCache = Cache(androidContext().cacheDir, CACHE_SIZE_BYTES)
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder()
            .cache(mCache) // make your app offline-friendly without a database!
            .connectTimeout(CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .addNetworkInterceptor(interceptor)
            .addInterceptor { chain ->
                var request = chain.request()
                val token = get<PrefsHelper>().readString(AppConstants.PREF_KEY_ACCESS_TOKEN, null)
                request =
                    if (token != null) request
                        .newBuilder()
                        .header("accessToken", token)
                        .build()
                    else request
                        .newBuilder()
                        .build()
                chain.proceed(request)
            }
            .build()
    }
}

private val apiModule = module {
    single<IAuthenticationApi> { get<Retrofit>().create(IAuthenticationApi::class.java) }
}

private val repositoryModule = module {
    single<IAuthenticationRepository> { AuthenticationRepositoryImpl(get(), get()) }
    single<INoteRepository> {NoteRepositoryImpl()}
}

val networkModule = module {
    includes(clientModule, apiModule, repositoryModule)
}