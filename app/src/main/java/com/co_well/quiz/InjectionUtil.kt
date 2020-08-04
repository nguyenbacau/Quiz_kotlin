package com.co_well.quiz

import android.content.Context
import androidx.room.Room
import com.co_well.quiz.data.CardRepositoryImpl
import com.co_well.quiz.data.database.Dao
import com.co_well.quiz.data.database.MyDatabase
import com.co_well.quiz.data.database.mapper.FlashCardEntityToFlashCard
import com.co_well.quiz.data.database.mapper.FlashCardToFlashCardEntity
import com.co_well.quiz.data.database.mapper.SetCardEntityToSetCard
import com.co_well.quiz.domain.interactor.*
import com.co_well.quiz.domain.repository.Repository
import com.co_well.quiz.ui.activity.ScanActivity
import com.co_well.quiz.ui.activity.create_set.CreateSetActivity
import com.co_well.quiz.ui.activity.learn.LearnActivity
import com.co_well.quiz.ui.activity.learn.learns.CardLearnActivity
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
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    val dao: Dao by lazy {
        database.dao()
    }
    val repoImpl: Repository by lazy {
        CardRepositoryImpl(
            dao = dao,
            flashCardToFlashCardEntity = FlashCardToFlashCardEntity(),
            flashCardEntityToFlashCard = FlashCardEntityToFlashCard(),
            setCardEntityToSetCard = SetCardEntityToSetCard(),
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

    val insertCard: InsertCardUseCase by lazy {
        InsertCardUseCase(repoImpl)
    }

    val getAllSet: GetAllSetUseCase by lazy {
        GetAllSetUseCase(repoImpl)
    }

    val insertSet: InsertSetUseCase by lazy {
        InsertSetUseCase(repoImpl)
    }

    val updateListCard: UpdateListCardUseCase by lazy {
        UpdateListCardUseCase(repoImpl)
    }

    val getSet: GetSetUseCase by lazy {
        GetSetUseCase(repoImpl)
    }

    fun injectMain(activity: MainActivity) {
        context = activity.applicationContext
        activity.getAllSetUseCase = getAllSet
    }

    fun inject(activity: ScanActivity) {
        context = activity.applicationContext
        activity.scanImageUseCase = scanImage
    }

    fun injectCreateSet(activity: CreateSetActivity) {
        context = activity.applicationContext
        activity.insertCardUseCase = insertCard
        activity.getAllSetUseCase = getAllSet
        activity.insertSetUseCase = insertSet
    }

    fun injectLearns(activity: CardLearnActivity) {
        context = activity.applicationContext
        activity.updateListCardUseCase = updateListCard
        activity.getSetUseCase = getSet
    }

    fun injectLearn(activity: LearnActivity) {
        context = activity.applicationContext
        activity.getSetUseCase = getSet
    }

}