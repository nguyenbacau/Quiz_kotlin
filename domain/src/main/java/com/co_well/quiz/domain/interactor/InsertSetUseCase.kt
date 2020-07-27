package com.co_well.quiz.domain.interactor

import com.co_well.quiz.domain.entity.Set
import com.co_well.quiz.domain.repository.Repository

class InsertSetUseCase constructor(
    private val repository: Repository
) {
    operator fun invoke(set: Set) {
        repository.insertSet(set)
    }
}