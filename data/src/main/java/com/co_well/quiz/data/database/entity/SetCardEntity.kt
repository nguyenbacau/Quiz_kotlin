package com.co_well.quiz.data.database.entity

import androidx.room.Embedded
import androidx.room.Relation

data class SetCardEntity (
    @Embedded val setEntity: SetEntity,
    @Relation(
        parentColumn = "name",
        entityColumn = "setName"
    )
    val flashCardList: List<FlashCardEntity>
)