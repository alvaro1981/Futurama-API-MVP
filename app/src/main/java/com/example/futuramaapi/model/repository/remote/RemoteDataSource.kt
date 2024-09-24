package com.example.futuramaapi.model.repository.remote

import com.example.futuramaapi.model.Character

interface RemoteDataSource {
    suspend fun getRemoteData():List<Character>
}