package com.co_well.quiz.data.database.mapper

import com.co_well.quiz.data.database.entity.SetEntity
import com.co_well.quiz.domain.entity.Set

class SetEntityToSet {
    fun map(setEntity: SetEntity): Set{
        return Set(setEntity.name)
    }
}