package com.co_well.quiz.domain.entity

import jdk.nashorn.internal.ir.annotations.Ignore
import java.io.Serializable

data class FlashCard (
    val cardId: Int,
    var setName: String,
    var word: String,
    var define: String,
    var done: Int,
    @Ignore var flip: Boolean
) : Serializable
