package com.vadzim.yeumushkou.core.network.di

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    fun provideOkHttp(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        httpClient.addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("apikey", "4aiHsXMQiQqNWFulrNPvJfAVxYsdoLRn")
                .build()

            chain.proceed(request)
        }

        return httpClient.build()
    }

    @Provides
    fun provideRetrofit(httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.apilayer.com/exchangerates_data/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
    }
}