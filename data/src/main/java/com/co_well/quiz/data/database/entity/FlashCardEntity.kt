package com.co_well.quiz.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.co_well.quiz.domain.entity.FlashCard

@Entity
data class FlashCardEntity(
    @PrimaryKey(autoGenerate = true) val cardId: Int,
    val setName: String,
    val word: String,
    val define: String,
    val done: Int,
    val flip: Boolean
)