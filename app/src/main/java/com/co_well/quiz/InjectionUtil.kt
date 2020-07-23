package com.co_well.quiz

import android.content.Context
import androidx.room.Room
import com.co_well.quiz.data.CardRepositoryImpl
import com.co_well.quiz.data.database.FlashCardDao
import com.co_well.quiz.data.database.MyDatabase
import com.co_well.quiz.data.database.mapper.FlashCardEntityToFlashCard
import com.co_well.quiz.data.database.mapper.FlashCardToFlashCardEntity
import com.co_well.quiz.domain.interactor.GetAllSetUseCase
import com.co_well.quiz.domain.interactor.ImportFileUseCase
import com.co_well.quiz.domain.interactor.InsertToDbUseCase
import com.co_well.quiz.domain.interactor.ScanImageUseCase
import com.co_well.quiz.domain.repository.Repository
import com.co_well.quiz.ui.activity.ScanActivity
import com.co_well.quiz.ui.activity.create_set.CreateSetActivity
import com.google.android.gms.vision.text.TextRecognizer

object InjectionUtil {
    private lateinit var context: Context
    val textRecognizer: TextRecognizer by lazy {
        TextRecognizer.Builder(context).build()
    }

    val database: MyDatabase by lazy {
        Room.databaseBuilder(
            context.applicationContext,
            MyDatabase::class.java, "Sample.db"
        )
            .allowMainThreadQueries()
            .build()
    }

    val dao: FlashCardDao by lazy {
        database.dao()
    }
    val repoImpl: Repository by lazy {
        CardRepositoryImpl(
            dao = dao,
            flashCardToFlashCardEntity = FlashCardToFlashCardEntity(),
            flashCardEntityToFlashCard = FlashCardEntityToFlashCard(),
            textRecognizer = textRecognizer,
            context = context
        )
    }

    val scanImage: ScanImageUseCase by lazy {
        ScanImageUseCase(repository = repoImpl)
    }

    val importFile: ImportFileUseCase by lazy {
        ImportFileUseCase()
    }

    val insertDB : InsertToDbUseCase by lazy {
        InsertToDbUseCase(repoImpl)
    }

    val getAll : GetAllSetUseCase by lazy {
        GetAllSetUseCase(repoImpl)
    }

    fun inject(activity: ScanActivity) {
        context = activity.applicationContext
        activity.scanImageUseCase = scanImage
    }

    fun injectCreateSet(activity: CreateSetActivity){
        context = activity.applicationContext
        activity.insertToDbUseCase = insertDB
        activity.getAllCardUseCase = getAll
    }


}