package com.co_well.quiz.domain.interactor

import com.co_well.quiz.domain.entity.SetCard
import com.co_well.quiz.domain.repository.Repository
import io.reactivex.Observable

class GetAllSetUseCase constructor(
    private val repository: Repository
) {
    operator fun invoke(): Observable<List<SetCard>> {
        return repository.getAllSet()
    }
}