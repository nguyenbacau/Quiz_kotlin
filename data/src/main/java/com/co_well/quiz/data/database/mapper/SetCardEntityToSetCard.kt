package com.co_well.quiz.data.database.mapper

import com.co_well.quiz.data.database.entity.FlashCardEntity
import com.co_well.quiz.data.database.entity.SetCardEntity
import com.co_well.quiz.domain.entity.FlashCard
import com.co_well.quiz.domain.entity.SetCard


class SetCardEntityToSetCard {
    fun map(setCardEntity: SetCardEntity): SetCard {
        return SetCard(
            name = setCardEntity.setEntity.name,
            cardList = mapList(setCardEntity.flashCardList)
        )
    }


    private fun mapList(listEntity: List<FlashCardEntity>): List<FlashCard> {
        val list = mutableListOf<FlashCard>()
        for (flashCardEntity in listEntity) {
            list.add(mapCard(flashCardEntity))
        }
        return list
    }

    private fun mapCard(flashCardEntity: FlashCardEntity): FlashCard {
        return FlashCard(
            cardId = flashCardEntity.cardId,
            setName = flashCardEntity.setName,
            word = flashCardEntity.word,
            define = flashCardEntity.define,
            flip = true,
            done = flashCardEntity.done
        )
    }
}