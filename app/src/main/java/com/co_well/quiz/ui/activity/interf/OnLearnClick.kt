package com.co_well.quiz.ui.activity.interf

import android.widget.TextView
import androidx.cardview.widget.CardView
import com.co_well.quiz.domain.entity.FlashCard

interface OnLearnClick {
    fun learnClick(flashCard: FlashCard, cardView: CardView, textView: TextView)
}