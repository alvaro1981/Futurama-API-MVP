package com.example.futuramaapi.model.repository.repo_implement

import android.util.Log
import com.example.futuramaapi.model.Character
import com.example.futuramaapi.model.repository.remote.RemoteDataSource

const val TAG = "futuramaMVP"

class RepositoryImpl(private val remoteDataSource: RemoteDataSource)
    :Repository {
    override suspend fun getDataFromApi(): List<Character> {
        val dataList:List<Character> = try {
            remoteDataSource.getRemoteData()
        }catch (e:Exception){
            Log.i(TAG,"Error al ejecutar remoteDataSource.getRemoteData() : $e ")
            emptyList()
        }
        return dataList
    }

}