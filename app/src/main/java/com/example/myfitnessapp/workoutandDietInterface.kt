package com.example.myfitnessapp

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path

const val base_url="http://10.0.2.2:8080/api/"
interface workoutandDietInterface {

//    @GET("$base_url{type}")
//    fun getDiet(@Path("type") type: String): Call<diet>

    @GET("getallmaintainingdiet")
    fun getDiet(): Call<diet>


}
object dietService{
    val dietInstance: workoutandDietInterface
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        dietInstance = retrofit.create(workoutandDietInterface:: class.java)
    }
}