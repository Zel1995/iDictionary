package com.example.idictionary.model.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HistoryDao {
    @Query("SELECT * FROM HistoryEntity")
    suspend fun getAll():List<HistoryEntity>

    @Query("SELECT * From HistoryEntity WHERE word LIKE :word")
    suspend fun getHistoryByWord(word:String):HistoryEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(historyEntity: HistoryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(historyEntities: List<HistoryEntity>)

}