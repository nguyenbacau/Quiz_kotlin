package com.co_well.quiz.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
abstract class FlashCardDao {

    @Query("SELECT * FROM FlashCard")
    abstract fun getAll(): List<FlashCardEntity>

    @Insert
    abstract fun insertCard(flashCardEntity: FlashCardEntity)
}