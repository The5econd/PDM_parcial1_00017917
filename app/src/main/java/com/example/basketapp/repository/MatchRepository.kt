package com.example.basketapp.repository

import android.content.IntentSender
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.basketapp.roomDatabase.dao.MatchDao
import com.example.basketapp.roomDatabase.entity.MatchEntity

class MatchRepository (private val matchDao: MatchDao){

    fun allMatches() : LiveData<List<MatchEntity>> = matchDao.getAll()

    fun markASfinished(id : Long) = matchDao.finishMatch(id)
    fun unmarkASfinished(id : Long) = matchDao.editMatch(id)
    fun getFinished(finished : Int) = matchDao.getFinished(finished)
    fun getByName(name : String) = matchDao.getByName(name)
    fun getByID(id : Long) = matchDao.getById(id)



    @WorkerThread
    fun add3TO1(score : Int, id : Long) = matchDao.add3_to_1(score, id)
    @WorkerThread
    fun add2TO1(score : Int, id : Long) = matchDao.add2_to_1(score, id)
    @WorkerThread
    fun add1TO1(score : Int, id : Long) = matchDao.add1_to_1(score, id)
    @WorkerThread
    fun add3TO2(score : Int, id : Long) = matchDao.add3_to_2(score, id)
    @WorkerThread
    fun add2TO2(score : Int, id : Long) = matchDao.add2_to_2(score, id)
    @WorkerThread
    fun add1TO2(score : Int, id : Long) = matchDao.add1_to_2(score, id)


    @WorkerThread
    suspend fun insertMatch(matchEntity: MatchEntity) = matchDao.insert(matchEntity)
}