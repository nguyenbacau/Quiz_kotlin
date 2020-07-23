package com.co_well.quiz.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FlashCardEntity::class], version = 1)
abstract class MyDatabase: RoomDatabase() {
    abstract fun dao(): FlashCardDao

}