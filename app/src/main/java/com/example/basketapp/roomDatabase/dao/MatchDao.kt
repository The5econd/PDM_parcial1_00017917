package com.example.basketapp.roomDatabase.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.basketapp.roomDatabase.entity.MatchEntity

@Dao
interface MatchDao {

    @Query("SELECT * FROM MATCH_TABLE")
    fun getAll() : LiveData<List<MatchEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(table : MatchEntity)

    @Query("SELECT * FROM MATCH_TABLE WHERE name_match = :name")
    fun getByName(name : String) : LiveData<MatchEntity>

    @Query("SELECT * FROM MATCH_TABLE WHERE id_match = :id")
    fun getById(id : Long) : LiveData<MatchEntity>

    @Query("UPDATE MATCH_TABLE SET terminado_match = 1 WHERE id_match = :id")
    fun finishMatch(id : Long)

    @Query("UPDATE MATCH_TABLE SET terminado_match = 0 where id_match = :id")
    fun editMatch(id : Long)

    @Query("SELECT * FROM match_table WHERE terminado_match = :finished")
    fun getFinished(finished : Int) : LiveData<List<MatchEntity>>

    @Query("DELETE FROM MATCH_TABLE")
    fun nuke()
    ///////////////////////////ADDING TO SCORES//////////////////////////
    @Query("UPDATE MATCH_TABLE SET score1_match = :actualScore +3 WHERE id_match = :id")
    fun add3_to_1(actualScore : Int, id : Long)
    @Query("UPDATE MATCH_TABLE SET score1_match = :actualScore +2 WHERE id_match = :id")
    fun add2_to_1(actualScore : Int, id : Long)
    @Query("UPDATE MATCH_TABLE SET score1_match = :actualScore +1 WHERE id_match = :id")
    fun add1_to_1(actualScore : Int, id : Long)

    @Query("UPDATE MATCH_TABLE SET score2_match = :actualScore +3 WHERE id_match = :id")
    fun add3_to_2(actualScore : Int, id : Long)
    @Query("UPDATE MATCH_TABLE SET score2_match = :actualScore +2 WHERE id_match = :id")
    fun add2_to_2(actualScore : Int, id : Long)
    @Query("UPDATE MATCH_TABLE SET score2_match = :actualScore +1 WHERE id_match = :id")
    fun add1_to_2(actualScore : Int, id : Long)


}