package com.example.pulselight.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "records")
data class RecordEntity (
    @PrimaryKey(autoGenerate = true)
    val id:Int,

    val bpm:String,

    @ColumnInfo(name="record_time")
    val time:String,

    @ColumnInfo(name="record_date")
    val date:String
)