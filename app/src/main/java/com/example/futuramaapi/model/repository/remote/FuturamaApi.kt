package com.example.futuramaapi.model.repository.remote

import com.example.futuramaapi.model.Character
import retrofit2.Response
import retrofit2.http.GET

interface FuturamaApi {
    @GET("characters/")
    suspend fun getCharacters(): Response<List<Character>>
}