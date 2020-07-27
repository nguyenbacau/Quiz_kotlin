package com.co_well.quiz.domain.repository

import com.co_well.quiz.domain.entity.FlashCard
import com.co_well.quiz.domain.entity.Set
import com.co_well.quiz.domain.entity.SetCard

interface Repository {
    fun scanImage(imgUri: String): String

    fun insertCard(cardList: ArrayList<FlashCard>)

    fun insertSet(set: Set)

    fun getAllSet(): ArrayList<SetCard>

    fun importFile()
}