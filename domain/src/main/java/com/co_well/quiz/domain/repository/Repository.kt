package com.co_well.quiz.domain.repository

import com.co_well.quiz.domain.entity.FlashCard
import com.co_well.quiz.domain.entity.Set

interface Repository {
    fun scanImage(imgUri: String): String

    fun insertCard(cardList: ArrayList<FlashCard>)

    fun getAll(): ArrayList<FlashCard>

    fun importFile()
}