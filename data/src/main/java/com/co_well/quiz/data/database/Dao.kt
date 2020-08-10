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

    @Transaction
    @Query("SELECT * FROM SetEntity WHERE name = :name")
    abstract fun getSet(name: String): Observable<SetCardEntity>

    @Query("SELECT * FROM FlashCardEntity WHERE done = :rank")
    abstract fun getRank(rank: Int): Observable<List<FlashCardEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertSet(setEntity: SetEntity)

    @Update
    abstract fun updateListCard(listCard: List<FlashCardEntity>)
}