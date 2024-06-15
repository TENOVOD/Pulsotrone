package com.example.pulselight.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pulselight.DAO.RecordDao
import com.example.pulselight.models.RecordEntity


@Database(entities = [RecordEntity::class], version = 1)
abstract class PulseMeasuresDatabase:RoomDatabase() {
    abstract  fun recordDao():RecordDao

    companion object{
        private var INSTANCE:PulseMeasuresDatabase?=null

        fun getInstance(context: Context):PulseMeasuresDatabase{
            synchronized(this){
                var instance = INSTANCE
                if(instance==null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PulseMeasuresDatabase::class.java,
                        "measuresdb"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE=instance
                }
                return instance
            }
        }
    }
}