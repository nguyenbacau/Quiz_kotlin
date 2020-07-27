package com.co_well.quiz.data.database.mapper

import com.co_well.quiz.data.database.entity.SetEntity
import com.co_well.quiz.domain.entity.Set

class SetToSetEntity {
    fun map(set: Set) : SetEntity{
        return SetEntity(set.name)
    }
}