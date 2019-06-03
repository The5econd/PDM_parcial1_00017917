package com.example.basketapp.roomDatabase.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "match_table")
data class MatchEntity (
        @ColumnInfo(name = "name_match")
        var name : String = "N/A",
        @ColumnInfo(name = "score1_match")
        var score1 : Int = 0,
        @ColumnInfo(name = "score2_match")
        var score2 : Int = 0,
        @ColumnInfo(name = "terminado_match")
        var terminado : Int = 0,
        @ColumnInfo(name = "fecha_match")
        var fecha : String = "N/A",
        @ColumnInfo(name = "hora_match")
        var hora : String = "N/A"
)
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_match")
    var id_match : Long = 0
}