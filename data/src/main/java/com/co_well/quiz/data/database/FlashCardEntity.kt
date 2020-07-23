package com.co_well.quiz.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.co_well.quiz.domain.entity.FlashCard

@Entity(tableName = "FlashCard")
data class FlashCardEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "word") val word: String,
    @ColumnInfo(name = "define") val define: String
)