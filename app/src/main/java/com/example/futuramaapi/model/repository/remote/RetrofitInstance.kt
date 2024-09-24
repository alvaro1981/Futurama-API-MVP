package com.example.futuramaapi.model.repository.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api:FuturamaApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.sampleapis.com/futurama/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FuturamaApi::class.java)
    }
}