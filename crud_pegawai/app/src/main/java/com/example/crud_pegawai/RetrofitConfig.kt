package com.example.crud_pegawai

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConfig {
    fun getRetrofitInstance():Retrofit{
        val BASE_URL:String="http://192.168.1.6/crudpegawai/api/"
        val interceptor:HttpLoggingInterceptor=HttpLoggingInterceptor()
        interceptor.level=HttpLoggingInterceptor.Level.BODY
        val clientBuilder:OkHttpClient.Builder=OkHttpClient.Builder()
        clientBuilder.addInterceptor(interceptor)
        val client:OkHttpClient=clientBuilder.build()
        val retrofit:Retrofit=Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit
    }

    fun getService():RestApiService{
        return getRetrofitInstance().create(RestApiService::class.java)
    }
}