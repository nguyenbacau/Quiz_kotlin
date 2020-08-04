package com.co_well.quiz.data

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import com.co_well.quiz.data.database.Dao
import com.co_well.quiz.data.database.entity.FlashCardEntity
import com.co_well.quiz.data.database.entity.SetCardEntity
import com.co_well.quiz.data.database.entity.SetEntity
import com.co_well.quiz.data.database.mapper.FlashCardEntityToFlashCard
import com.co_well.quiz.data.database.mapper.FlashCardToFlashCardEntity
import com.co_well.quiz.data.database.mapper.SetCardEntityToSetCard
import com.co_well.quiz.domain.entity.FlashCard
import com.co_well.quiz.domain.entity.Set
import com.co_well.quiz.domain.entity.SetCard
import com.co_well.quiz.domain.repository.Repository
import com.google.android.gms.vision.Frame
import com.google.android.gms.vision.text.TextRecognizer
import io.reactivex.Observable
import io.reactivex.functions.Function
import java.io.FileNotFoundException


class CardRepositoryImpl(
    private val dao: Dao,
    private val flashCardToFlashCardEntity: FlashCardToFlashCardEntity,
    private val flashCardEntityToFlashCard: FlashCardEntityToFlashCard,
    private val setCardEntityToSetCard: SetCardEntityToSetCard,
    private val textRecognizer: TextRecognizer,
    private val context: Context
) : Repository {
    override fun scanImage(imgUri: String): String {
        val stringBuilder = StringBuilder()
        val targetUri = Uri.parse(imgUri)
        val bitmap: Bitmap
        try {
            bitmap =
                BitmapFactory.decodeStream(context.contentResolver.openInputStream(targetUri!!))

            var frame = Frame.Builder().setBitmap(bitmap).build()
            var sparseArray = textRecognizer.detect(frame)

            for (i in 0 until sparseArray.size()) {
                val tx = sparseArray[i]
                val str = tx.value
                stringBuilder.append(str)
            }

        } catch (e: FileNotFoundException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }

        return stringBuilder.toString()
    }

    override fun insertCard(cardList: ArrayList<FlashCard>) {
        for (flashCard in cardList) {
            dao.insertCard(flashCardToFlashCardEntity.map(flashCard))
        }
    }

    override fun insertSet(set: Set) {
        dao.insertSet(SetEntity(set.name))
    }

    override fun getAllSet(): Observable<List<SetCard>> {
        return dao.getAllSet().flatMap { list ->
            Observable.fromIterable(list)
                .map { cardEntity -> setCardEntityToSetCard.map(cardEntity) }
                .toList()
                .toObservable()
        }
    }

    override fun getSet(name: String): Observable<SetCard> {
        return dao.getSet(name).map { cardEntity ->
            setCardEntityToSetCard.map(cardEntity)
        }
    }

    override fun updateListCard(listCard: List<FlashCard>) {
        val list = ArrayList<FlashCardEntity>()
        for (flashCard in listCard) {
            list.add(flashCardToFlashCardEntity.map(flashCard))
        }
        dao.updateListCard(list)
    }

    override fun importFile() {
        TODO("Not yet implemented")
    }
}