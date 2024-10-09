package com.example.data.api

import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

//object ApiManager {
//    private lateinit var retrofit: Retrofit
//
//    init {
//
//        val logging = HttpLoggingInterceptor(object :HttpLoggingInterceptor.Logger{
//            override fun log(message: String) {
//                Log.e("api->",message)
//            }
//        })
//        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
//        val client = OkHttpClient.Builder()
//            .addInterceptor(logging)
//            .build()
//
//
//        retrofit = Retrofit.Builder()
//            .baseUrl("https://newsapi.org/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }
//
//    fun getService(): WebServices {
//
//        return retrofit.create(WebServices::class.java)
//
//    }
//
//
//}

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule{

    @Provides
    fun provideOkHttp():OkHttpClient{
        return OkHttpClient()
    }

    @Provides
    fun provideConverterFactory():GsonConverterFactory{
        return GsonConverterFactory.create()
    }
    @Provides
    fun provideRetrofit(gsonconverterFactory: GsonConverterFactory,okHttpClient: OkHttpClient):Retrofit{
        return Retrofit.Builder().baseUrl("https://newsapi.org/")
            .addConverterFactory(gsonconverterFactory)
            .client(okHttpClient)
            .build()
    }
    @Provides
    fun getService(retrofit: Retrofit):WebServices{
        return retrofit.create(WebServices::class.java)
    }
}

