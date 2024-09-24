package com.example.futuramaapi.model.repository.remote

import com.example.futuramaapi.model.Character

class RemotaDataSourceImpl(private val api:FuturamaApi) :RemoteDataSource{
    override suspend fun getRemoteData(): List<Character> {
        val response = api.getCharacters()
        return response.body()!!
    }
}