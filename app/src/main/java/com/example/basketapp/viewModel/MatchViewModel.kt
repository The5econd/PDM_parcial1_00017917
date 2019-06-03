package com.example.basketapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.basketapp.repository.MatchRepository
import com.example.basketapp.roomDatabase.RoomDB
import com.example.basketapp.roomDatabase.entity.MatchEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MatchViewModel(application: Application) : AndroidViewModel(application) {

    private val repositorio : MatchRepository
    val allMatch : LiveData<List<MatchEntity>>

    init {
        val matchDao = RoomDB.getInstance(application,viewModelScope).mathcDao()
        repositorio = MatchRepository(matchDao)
        allMatch = repositorio.allMatches()
    }

    fun getFinished(finished : Int) = repositorio.getFinished(finished)

    fun insertMatch(match : MatchEntity) = viewModelScope.launch(Dispatchers.IO) {
        repositorio.insertMatch(match)
    }
    fun getMatchByName(name : String) = repositorio.getByName(name)

    fun getMatchByID(id : Long) = repositorio.getByID(id)

    fun agregar3a1(score : Int, id : Long) = viewModelScope.launch(Dispatchers.IO) {
        repositorio.add3TO1(score, id)
    }
    fun agregar2a1(score : Int, id : Long) = viewModelScope.launch(Dispatchers.IO) {
        repositorio.add2TO1(score, id)
    }
    fun agregar1a1(score : Int, id : Long) = viewModelScope.launch(Dispatchers.IO) {
        repositorio.add1TO1(score, id)
    }
    fun agregar3a2(score : Int, id : Long) = viewModelScope.launch(Dispatchers.IO) {
        repositorio.add3TO2(score, id)
    }
    fun agregar2a2(score : Int, id : Long) = viewModelScope.launch(Dispatchers.IO) {
        repositorio.add2TO2(score, id)
    }
    fun agregar1a2(score : Int, id : Long) = viewModelScope.launch(Dispatchers.IO) {
        repositorio.add1TO2(score, id)
    }
}