package com.co_well.quiz.domain.interactor

import com.co_well.quiz.domain.repository.Repository

class ScanImageUseCase constructor(
    private val repository: Repository
) {
    operator fun invoke(imgUri: String): String {
        return repository.scanImage(imgUri)
    }
}