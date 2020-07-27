package com.co_well.quiz.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.co_well.quiz.data.database.entity.FlashCardEntity
import com.co_well.quiz.data.database.entity.SetCardEntity
import com.co_well.quiz.data.database.entity.SetEntity

@Database(entities = [FlashCardEntity::class, SetEntity::class],
    version = 2)
abstract class MyDatabase: RoomDatabase() {
    abstract fun dao(): Dao

}