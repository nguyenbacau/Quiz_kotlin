package com.co_well.quiz.domain.entity

import jdk.nashorn.internal.ir.annotations.Ignore
import java.io.Serializable

data class FlashCard (
    val cardId: Int,
    val setName: String,
    val word: String,
    val define: String,
    @Ignore var flip: Boolean
) : Serializable
