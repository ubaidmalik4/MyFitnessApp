package com.example.myfitnessapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DietActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diet)
        getDiets()
    }
    private fun getDiets(){
        val diets = dietService.dietInstance.getDiet()
        diets.enqueue(object: Callback<diet>{
            override fun onResponse(call: Call<diet>, response: Response<diet>) {

                val diets = response.body()
                if(diets !=null){
                    Log.d("billu", diets.toString())
                }
            }

            override fun onFailure(call: Call<diet>, t: Throwable) {
                Log.d("billu", "error fetching diet", t)
            }

        })
    }

    private fun getalldites(){
        Api.getService("http://10.0.2.2:8080/api/").
        //get
            .enqueue(
                object : Callback<diet> {
                    override fun onResponse(call: Call<diet>, response: Response<diet>) {

                        if (response.isSuccessful) {


                            if (response.code() ==200){

                            }


                        }

                    }

                    override fun onFailure(call: Call<diet>, t: Throwable) {
                    }
                })
    }
}