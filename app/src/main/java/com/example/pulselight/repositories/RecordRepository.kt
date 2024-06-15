package com.example.pulselight.repositories

import androidx.lifecycle.LiveData
import com.example.pulselight.DAO.RecordDao
import com.example.pulselight.models.RecordEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecordRepository (
    private val recordDao: RecordDao
){
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    val recordList: LiveData<List<RecordEntity>> = recordDao.getAllRecords()

    fun addRecord(record: RecordEntity){
       coroutineScope.launch (Dispatchers.IO){
           recordDao.addRecord(record)
       }
    }

    fun deleteAllRecords(){
        coroutineScope.launch(Dispatchers.IO) {
            recordDao.deleteAllRecords()
        }
    }
}