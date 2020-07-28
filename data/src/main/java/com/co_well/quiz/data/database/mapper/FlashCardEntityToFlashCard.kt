package com.co_well.quiz.data.database.mapper

import com.co_well.quiz.data.database.entity.FlashCardEntity
import com.co_well.quiz.domain.entity.FlashCard

class FlashCardEntityToFlashCard {
    fun map(flashCardEntity: FlashCardEntity): FlashCard {
        return FlashCard(
            cardId = flashCardEntity.cardId,
            setName = flashCardEntity.setName,
            word = flashCardEntity.word,
            define = flashCardEntity.define,
            flip = true
        )
    }
}