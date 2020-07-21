package com.co_well.quiz

import android.content.Context
import com.co_well.quiz.data.CardRepositoryImpl
import com.co_well.quiz.domain.interactor.ImportFileUseCase
import com.co_well.quiz.domain.interactor.ScanImageUseCase
import com.co_well.quiz.domain.repository.Repository
import com.co_well.quiz.ui.activity.ScanActivity
import com.google.android.gms.vision.text.TextRecognizer

object InjectionUtil {
    lateinit var context: Context
    val textRecognizer: TextRecognizer by lazy {
        TextRecognizer.Builder(context).build()
    }

    val repoImpl: Repository by lazy {
        CardRepositoryImpl(textRecognizer, context)
    }

    val scanImage: ScanImageUseCase by lazy {
        ScanImageUseCase(repository = repoImpl)
    }

    val importFile: ImportFileUseCase by lazy {
        ImportFileUseCase()
    }

    fun inject(activity: ScanActivity){
        context = activity.applicationContext
        activity.scanImageUseCase = scanImage
    }
}