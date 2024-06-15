package com.example.pulselight.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.pulselight.models.RecordEntity

@Dao
interface RecordDao {

    @Query("SELECT*FROM records ORDER BY id DESC")
    fun getAllRecords(): LiveData<List<RecordEntity>>

    @Query("SELECT*FROM records WHERE id=:id LIMIT 1")
    fun getRecordById(id:Long):RecordEntity?

    @Insert
    fun addRecord(record: RecordEntity):Long

    @Query("DELETE FROM records")
    fun deleteAllRecords()
}