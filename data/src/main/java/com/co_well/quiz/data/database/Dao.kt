package com.co_well.quiz.data.database

import androidx.room.*
import androidx.room.Dao
import com.co_well.quiz.data.database.entity.FlashCardEntity
import com.co_well.quiz.data.database.entity.SetCardEntity
import com.co_well.quiz.data.database.entity.SetEntity
import io.reactivex.Observable

@Dao
abstract class Dao {

    @Query("SELECT * FROM FlashCardEntity")
    abstract fun getAll(): List<FlashCardEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertCard(flashCardEntity: FlashCardEntity)

    @Transaction
    @Query("SELECT * FROM SetEntity")
    abstract fun getAllSet(): Observable<List<SetCardEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertSet(setEntity: SetEntity)

    @Update
    abstract fun updateListCard(listCard: List<FlashCardEntity>)
}