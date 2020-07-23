package com.co_well.quiz.data.database.mapper

import com.co_well.quiz.data.database.FlashCardEntity
import com.co_well.quiz.domain.entity.FlashCard
import com.co_well.quiz.domain.entity.Set

class FlashCardEntityToFlashCard {
    fun map(flashCardEntity: FlashCardEntity): FlashCard {
        return FlashCard(word = flashCardEntity.word, define = flashCardEntity.define)
    }
}