package com.example.myfitnessapp

data class diet(
    val diets: List<dietClass>
    )

data class dietClass( val name:String,
                      val type:String,
                      val description:String,
                      val calories:String,
                      val image:String
)