package com.co_well.quiz.domain.entity

import java.io.Serializable

data class SetCard (
    val name: String,
    val cardList: List<FlashCard>
) : Serializable