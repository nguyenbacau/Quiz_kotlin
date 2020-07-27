package com.co_well.quiz.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SetEntity(
    @PrimaryKey val name: String

)