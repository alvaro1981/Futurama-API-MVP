package com.example.futuramaapi.view

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.futuramaapi.R
import com.example.futuramaapi.databinding.ActivityMainBinding
import com.example.futuramaapi.model.Character
import com.example.futuramaapi.model.MainInteractor
import com.example.futuramaapi.model.repository.remote.RemotaDataSourceImpl
import com.example.futuramaapi.model.repository.remote.RemoteDataSource
import com.example.futuramaapi.model.repository.remote.RetrofitInstance
import com.example.futuramaapi.model.repository.repo_implement.Repository
import com.example.futuramaapi.model.repository.repo_implement.RepositoryImpl
import com.example.futuramaapi.presenter.MainPresenter

const val TAG = "futuramaMVP"

class MainActivity : AppCompatActivity(),MainView {
    private lateinit var binding:ActivityMainBinding
    private lateinit var mainPresenter: MainPresenter
    private lateinit var charactersFragment: FuturamaCharacters
    private lateinit var characterAdapter: CharacterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolBar)
        charactersFragment = FuturamaCharacters()
        val remoteDataSource = RemotaDataSourceImpl(RetrofitInstance.api)
        val repository = RepositoryImpl(remoteDataSource)
        setRecyclerView()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(binding.FragmentContainerViewFuturama.id, charactersFragment).commit()
        mainPresenter = MainPresenter(this, MainInteractor(repository))
        mainPresenter.getData()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menutoolbar,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.title){
            "SALIR"-> finishAffinity()
            else   -> Log.i(TAG," Se presiono el  item : $item  ," +
                    " item.title ${item.title} , itemid = ${item.itemId}")
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setRecyclerView() = binding.recyclerViewFuturama.apply {
        characterAdapter = CharacterAdapter {
             charactersFragment.setData(it)
        }
        adapter = characterAdapter
        layoutManager = LinearLayoutManager(this@MainActivity)
    }

    override fun showProgress() {
        binding.progressbar.isVisible = true
    }

    override fun hideProgress() {
        binding.progressbar.isVisible = false
    }

    private fun returnRandomTodo(arrUpdates :List<Character>):Character{
        return arrUpdates.random()
    }
    override fun setData(arrUpdates: List<Character>) {
        charactersFragment.setData(returnRandomTodo(arrUpdates))
        characterAdapter.characters = arrUpdates
    }

    override fun setDataError(strError: String) {
        Log.i(TAG,strError)
    }

    override fun onDestroy() {
        mainPresenter.onDestroy()
        super.onDestroy()
    }
}