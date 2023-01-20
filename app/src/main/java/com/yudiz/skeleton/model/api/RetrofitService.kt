package com.yudiz.skeleton.model.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.yudiz.skeleton.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
open class RetrofitService {
    private var gson: Gson = GsonBuilder()
        .setLenient()
        .create()

    @Provides
    fun provideHTTPClient(): OkHttpClient {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val httpClientBuilder = OkHttpClient().newBuilder().addInterceptor(interceptor)
        return httpClientBuilder
            .readTimeout(120, TimeUnit.SECONDS)
            .connectTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val newRequest = chain.request().newBuilder()
                chain.proceed(newRequest.build())
            }.build()
    }

    @Provides
    fun apiServices(okHttpClient: OkHttpClient): ApiServices = Retrofit.Builder()
        .baseUrl(BuildConfig.API_HOST)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
        .create(ApiServices::class.java)
}


