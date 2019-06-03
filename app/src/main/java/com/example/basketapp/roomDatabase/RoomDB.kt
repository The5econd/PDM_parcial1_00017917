package com.example.basketapp.roomDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.basketapp.roomDatabase.dao.MatchDao
import com.example.basketapp.roomDatabase.entity.MatchEntity
import kotlinx.coroutines.CoroutineScope

@Database(entities = [MatchEntity::class], version = 3, exportSchema = false)
public abstract class RoomDB : RoomDatabase(){

    abstract fun mathcDao(): MatchDao

    companion object {
        //para notificar a todos los hilos
        @Volatile
        private var INSTANCE : RoomDB? = null

        fun getInstance(
                context: Context,
                scope: CoroutineScope
        ): RoomDB{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }

            synchronized(this){
                val instance = Room
                        .databaseBuilder(context, RoomDB::class.java, "Match_DB")
                        .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}