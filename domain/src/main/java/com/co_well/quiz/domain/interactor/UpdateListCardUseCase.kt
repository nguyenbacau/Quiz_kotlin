package com.co_well.quiz.domain.interactor

import com.co_well.quiz.domain.entity.FlashCard
import com.co_well.quiz.domain.repository.Repository

class UpdateListCardUseCase constructor(
    private val repository: Repository
){
    operator fun invoke(listCard: List<FlashCard>){
        return repository.updateListCard(listCard)
    }
}