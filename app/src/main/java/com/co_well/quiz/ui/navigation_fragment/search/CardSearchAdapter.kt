package com.co_well.quiz.ui.navigation_fragment.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.co_well.quiz.R
import com.co_well.quiz.domain.entity.FlashCard
import com.co_well.quiz.ui.activity.interf.OnLearnClick

class CardSearchAdapter(onLearnClick: OnLearnClick) :
    RecyclerView.Adapter<CardSearchAdapter.CardSearchViewHolder>() {
    private val list: MutableList<FlashCard> = mutableListOf()
    private val onClick = onLearnClick

    fun addListSetCard(listFlashCard: List<FlashCard>) {
        list.clear()
        list.addAll(listFlashCard)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardSearchViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_item_learn_search, parent, false)
        return CardSearchViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: CardSearchViewHolder, position: Int) {
        holder.bind(list[position], onClick)
    }

    class CardSearchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var textView: TextView = view.findViewById(R.id.tv_learn)
        private var cardView: CardView = view.findViewById(R.id.card_view_learn)

        fun bind(flashCard: FlashCard, onLearnClick: OnLearnClick) {
            if (flashCard.flip) {
                textView.text = flashCard.word
            } else {
                textView.text = flashCard.define
            }

            textView.setOnClickListener { onLearnClick.learnClick(flashCard, cardView, textView) }

        }
    }
}