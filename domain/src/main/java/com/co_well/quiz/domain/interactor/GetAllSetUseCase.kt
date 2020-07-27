package com.co_well.quiz.domain.interactor

import com.co_well.quiz.domain.entity.FlashCard
import com.co_well.quiz.domain.entity.Set
import com.co_well.quiz.domain.entity.SetCard
import com.co_well.quiz.domain.repository.Repository

class GetAllSetUseCase constructor(
    private val repository: Repository
) {
    operator fun invoke(): ArrayList<SetCard> {
        return repository.getAllSet()
    }
}