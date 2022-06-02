package com.example.myfitnessapp

import android.app.Service
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Api {
    private var retrofit: Retrofit? = null
    private fun getRetrofit(baseUrl: String): Retrofit? {
        val interceptor = Interceptor { chain ->
            val request = chain.request()
            val accept = request.newBuilder()
                .addHeader("ACCEPT", "application/json")
                .build()
            chain.proceed(accept)
        }
        val client = OkHttpClient.Builder()
            .connectTimeout(90, TimeUnit.SECONDS)
            .writeTimeout(90, TimeUnit.SECONDS)
            .readTimeout(90, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor(interceptor)
            .build()
        val gson = GsonBuilder()
            .setLenient()
            .create()
        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
        return retrofit
    }

    fun getService(baseUrl: String): Service {
        return getRetrofit(baseUrl)!!.create(Service::class.java)
    }


}