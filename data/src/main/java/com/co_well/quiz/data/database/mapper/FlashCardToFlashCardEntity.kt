package com.co_well.quiz.data.database.mapper

import com.co_well.quiz.data.database.entity.FlashCardEntity
import com.co_well.quiz.domain.entity.FlashCard

class FlashCardToFlashCardEntity {

    fun map(flashCard: FlashCard): FlashCardEntity {
        return FlashCardEntity(
            cardId = flashCard.cardId,
            setName = flashCard.setName,
            word = flashCard.word,
            define = flashCard.define
        )
    }
}