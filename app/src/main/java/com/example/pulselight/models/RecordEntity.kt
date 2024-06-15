package com.example.pulselight.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Date

@Entity(tableName = "records")
data class RecordEntity (
    @PrimaryKey(autoGenerate = true)
    val id:Long,

    val bpm:String,

    @ColumnInfo(name="record_time")
    val time:String,

    @ColumnInfo(name="record_date")
    val date:String
){
    constructor(bpm: String) : this(
        0,
        bpm=bpm,
        time = getCurrentTime(),
        date=getCurrentDate()
    )
    companion object{
        fun getCurrentTime(): String {
            val currentTimeMillis = System.currentTimeMillis()
            val date = Date(currentTimeMillis)
            val formatter = SimpleDateFormat("HH:mm")
            return formatter.format(date)
        }
        fun getCurrentDate(): String {
            val currentTimeMillis = System.currentTimeMillis()
            val date = Date(currentTimeMillis)
            val formatter = SimpleDateFormat("dd/MM/yyyy")
            return formatter.format(date)
        }
    }

}