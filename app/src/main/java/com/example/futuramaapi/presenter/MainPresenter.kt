package com.example.futuramaapi.presenter

import android.util.Log
import com.example.futuramaapi.model.Character
import com.example.futuramaapi.model.MainInteractor
import com.example.futuramaapi.view.MainView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

const val TAG = "futuramaMVP"

class MainPresenter (private var mainView: MainView?,
                     private var mainInteractor: MainInteractor)
    :MainInteractor.OnFinishedListener{

    fun getData(){
        CoroutineScope(Dispatchers.Main).launch {
            mainInteractor.requestGetDataAPI(this@MainPresenter)
        }
    }

    override fun onResultSucces(arrUpdate: List<Character>) {
        Log.i(TAG, " $arrUpdate")
        mainView?.hideProgress()
        mainView?.setData(arrUpdate)
    }

    override fun onResultFail(strError: String) {
        mainView?.hideProgress()
        mainView?.setDataError(strError)
    }

    fun onDestroy(){
        mainView = null
    }
}