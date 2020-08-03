package com.co_well.quiz.domain.interactor

import com.co_well.quiz.domain.entity.SetCard
import com.co_well.quiz.domain.repository.Repository
import io.reactivex.Observable

class GetSetUseCase(
    private val repository: Repository
) {
    operator fun invoke(name: String): Observable<SetCard>{
        return repository.getSet(name)
    }
}