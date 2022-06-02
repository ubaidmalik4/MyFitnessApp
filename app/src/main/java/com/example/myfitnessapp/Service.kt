package com.example.myfitnessapp

import retrofit2.Call
import retrofit2.http.GET

interface Service {

    @GET("getallmaintainingdiet")
    fun getDiet(): Call<diet>


}