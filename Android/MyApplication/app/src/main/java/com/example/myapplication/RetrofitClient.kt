package com.example.myapplication

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


object RetrofitClient {
    private const val BASE_URL = "http://192.168.1.34:8080/"
    var gson = GsonBuilder()
        .setLenient()
        .create()

    private val builder = OkHttpClient.Builder()

    private val retrofit:Retrofit=Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(builder.build())
        .addConverterFactory(ScalarsConverterFactory.create()) //important
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    fun getRetrofitInstance():Retrofit{
        return retrofit
    }
}