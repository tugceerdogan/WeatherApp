package com.example.weatherapp.di

import com.example.weatherapp.BuildConfig
import com.example.weatherapp.model.remote.NetworkApiService
import com.example.weatherapp.model.remote.RemoteDataSource
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier

@Module
@InstallIn(ActivityRetainedComponent::class)
class NetworkModule {

    @Provides
    fun provideApiService(@NetworkRetrofit retrofit: Retrofit): NetworkApiService {
        return retrofit.create(NetworkApiService::class.java)
    }

    @Provides
    @NetworkRetrofit
    fun provideRetrofit(
        noAuthOkHttpClient: NoAuthOkHttpClient,
        gson: Gson,
        endPoint: EndPoint
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(endPoint.url)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(noAuthOkHttpClient.okHttpClient)
            .build()
    }

    @Provides
    fun provideOkHttpClient(): NoAuthOkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.interceptors().add(HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        })
        return provideNoAuthOkHttpClient(builder.build())
    }

    @Provides
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    fun provideRemoteDataSource(
        networkApiService: NetworkApiService,
    ): RemoteDataSource {
        return RemoteDataSource(networkApiService)
    }

    @Provides
    fun provideEndPoint(): EndPoint {
        return EndPoint("https://api.openweathermap.org/data/2.5/")
    }

    private fun provideNoAuthOkHttpClient(noAuthOkHttpClient: OkHttpClient): NoAuthOkHttpClient {
        return NoAuthOkHttpClient(noAuthOkHttpClient)
    }
}

data class EndPoint(val url: String)

data class NoAuthOkHttpClient(val okHttpClient: OkHttpClient)

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NetworkRetrofit
