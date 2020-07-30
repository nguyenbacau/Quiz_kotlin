package com.co_well.quiz.ui.activity.learn

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.co_well.quiz.R
import com.co_well.quiz.domain.entity.FlashCard
import com.co_well.quiz.domain.entity.SetCard
import com.co_well.quiz.ui.activity.interf.OnLearnClick

class LearnAdapter(onLearnClick: OnLearnClick) :
    RecyclerView.Adapter<LearnAdapter.LearnViewHolder>() {
    private val list: MutableList<FlashCard> = mutableListOf()
    private val onClick = onLearnClick

    fun addListSetCard(listFlashCard: List<FlashCard>) {
        val count = list.size
        list.addAll(listFlashCard)
        notifyItemRangeInserted(count, listFlashCard.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LearnViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_item_learn, parent, false)
        return LearnViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: LearnViewHolder, position: Int) {
        holder.bind(list[position], onClick)
    }

    class LearnViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var textView: TextView
        private var cardView: CardView

        init {
            textView = view.findViewById(R.id.tv_learn)
            cardView = view.findViewById(R.id.card_view_learn)
        }

        fun bind(flashCard: FlashCard, onLearnClick: OnLearnClick) {
            if (flashCard.flip) {
                textView.text = flashCard.word
            } else{
                textView.text = flashCard.define
            }

            cardView.setOnClickListener {
                onLearnClick.learnClick(flashCard, cardView, textView)
            }
        }
    }
}