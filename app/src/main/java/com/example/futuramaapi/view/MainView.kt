package com.example.futuramaapi.view

import com.example.futuramaapi.model.Character

interface MainView {
    fun showProgress()
    fun hideProgress()
    fun setData(arrUpdates:List<Character>)
    fun setDataError(strError:String)
}