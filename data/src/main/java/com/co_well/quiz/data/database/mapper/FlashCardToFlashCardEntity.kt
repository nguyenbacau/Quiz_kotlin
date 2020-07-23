package com.co_well.quiz.data.database.mapper

import com.co_well.quiz.data.database.FlashCardEntity
import com.co_well.quiz.domain.entity.FlashCard
import com.co_well.quiz.domain.entity.Set

class FlashCardToFlashCardEntity {

    fun map(flashCard: FlashCard): FlashCardEntity {
        return FlashCardEntity(id = 0, word = flashCard.word, define = flashCard.define)
    }
}