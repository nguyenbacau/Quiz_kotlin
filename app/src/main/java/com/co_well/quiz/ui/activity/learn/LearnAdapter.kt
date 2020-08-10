package com.co_well.quiz.ui.activity.learn

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
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
        list.clear()
        list.addAll(listFlashCard)
        notifyDataSetChanged()
    }

    fun getList(): List<FlashCard>{
        return list
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
        private var textView: TextView = view.findViewById(R.id.tv_learn)
        private var cardView: CardView = view.findViewById(R.id.card_view_learn)
        private var btnFullScreen: ImageView = view.findViewById(R.id.btn_fullScreen)

        fun bind(flashCard: FlashCard, onLearnClick: OnLearnClick) {
            if (flashCard.flip) {
                textView.text = flashCard.word
            } else {
                textView.text = flashCard.define
            }

            btnFullScreen.setOnClickListener{onLearnClick.buttonFullScreenClick()}
            textView.setOnClickListener { onLearnClick.learnClick(flashCard, cardView, textView) }

        }
    }
}