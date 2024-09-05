package com.example.postapicallexample

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RetrofitClient {

    private const val BASE_URL = "https://api.escuelajs.co/api/v1/"

    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY // You can set it to BASIC, HEADERS, or BODY
    }

    // Build OkHttpClient with the logging interceptor
    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()


    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return  Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideServiceApi(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}