package com.example.futuramaapi.model

import com.example.futuramaapi.model.repository.repo_implement.Repository

class MainInteractor(val repository: Repository) {

    interface OnFinishedListener{
        fun onResultSucces(arrUpdate:List<Character>)
        fun onResultFail(strError:String)
    }

    suspend fun requestGetDataAPI(onFinishedListener: OnFinishedListener){
        val characters = repository.getDataFromApi()

        if(characters.isNotEmpty()){
            onFinishedListener.onResultSucces(characters)
        }else {
            val errorRepository = "No se pudo obtener datos desde la API"
            onFinishedListener.onResultFail(errorRepository)
        }

    }
}