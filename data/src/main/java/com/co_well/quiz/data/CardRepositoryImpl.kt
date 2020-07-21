package com.co_well.quiz.data

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.Toast
import com.co_well.quiz.domain.repository.Repository
import com.google.android.gms.vision.Frame
import com.google.android.gms.vision.text.TextRecognizer
import java.io.FileNotFoundException
import java.net.URI


class CardRepositoryImpl(
    private val textRecognizer: TextRecognizer,
    private val context : Context
) : Repository {

    override fun scanImage(imgUri: String): String {
        val stringBuilder = StringBuilder()
        val targetUri = Uri.parse(imgUri)
        val bitmap: Bitmap
        try {
            bitmap = BitmapFactory.decodeStream(context.contentResolver.openInputStream(targetUri!!))

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

    override fun importFile() {
        TODO("Not yet implemented")
    }
}