package com.co_well.quiz.domain.interactor

import com.co_well.quiz.domain.entity.FlashCard
import com.co_well.quiz.domain.repository.Repository
import io.reactivex.Observable

class GetRankCardUseCase(
    private val repository: Repository
) {
    operator fun invoke(rank: Int): Observable<List<FlashCard>>{
        return repository.getRank(rank)
    }

}