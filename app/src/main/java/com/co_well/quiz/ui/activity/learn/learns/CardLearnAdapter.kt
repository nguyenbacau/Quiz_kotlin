package com.co_well.quiz.ui.activity.learn.learns

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.co_well.quiz.R
import com.co_well.quiz.domain.entity.FlashCard
import com.co_well.quiz.ui.activity.interf.OnLearnClick

class CardLearnAdapter(onLearnClick: OnLearnClick) :
    RecyclerView.Adapter<CardLearnAdapter.CardLearnViewHolder>() {
    private val list: MutableList<FlashCard> = mutableListOf()
    private val onClick = onLearnClick

    fun addListSetCard(listFlashCard: List<FlashCard>) {
        list.clear()
        list.addAll(listFlashCard)
        notifyDataSetChanged()
    }

    fun getList(): List<FlashCard> {
        return list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardLearnViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_item_learns, parent, false)
        return CardLearnViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: CardLearnViewHolder, position: Int) {
        holder.bind(list[position], onClick, position)
    }


    class CardLearnViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var textView: TextView
        private var cardView: CardView
        private var btnlv1: Button
        private var btnlv2: Button
        private var btnlv3: Button
        private var btnlv4: Button

        init {
            textView = view.findViewById(R.id.tv_learn)
            cardView = view.findViewById(R.id.card_view_learn)
            btnlv1 = view.findViewById(R.id.btn_lv1)
            btnlv2 = view.findViewById(R.id.btn_lv2)
            btnlv3 = view.findViewById(R.id.btn_lv3)
            btnlv4 = view.findViewById(R.id.btn_lv4)
        }

        fun bind(flashCard: FlashCard, onLearnClick: OnLearnClick, position: Int) {
            if (flashCard.flip) {
                textView.text = flashCard.word
            } else {
                textView.text = flashCard.define
            }

            when (flashCard.done) {
                1 -> {
                    btnlv1.setBackgroundResource(R.drawable.border_circle_blue)
                    btnlv2.setBackgroundResource(R.drawable.border_circle_white)
                    btnlv3.setBackgroundResource(R.drawable.border_circle_white)
                    btnlv4.setBackgroundResource(R.drawable.border_circle_white)
                }

                2 -> {
                    btnlv1.setBackgroundResource(R.drawable.border_circle_white)
                    btnlv2.setBackgroundResource(R.drawable.border_circle_blue)
                    btnlv3.setBackgroundResource(R.drawable.border_circle_white)
                    btnlv4.setBackgroundResource(R.drawable.border_circle_white)
                }

                3 -> {
                    btnlv1.setBackgroundResource(R.drawable.border_circle_white)
                    btnlv2.setBackgroundResource(R.drawable.border_circle_white)
                    btnlv3.setBackgroundResource(R.drawable.border_circle_blue)
                    btnlv4.setBackgroundResource(R.drawable.border_circle_white)
                }

                4 -> {
                    btnlv1.setBackgroundResource(R.drawable.border_circle_white)
                    btnlv2.setBackgroundResource(R.drawable.border_circle_white)
                    btnlv3.setBackgroundResource(R.drawable.border_circle_white)
                    btnlv4.setBackgroundResource(R.drawable.border_circle_blue)
                }
            }

            textView.setOnClickListener { onLearnClick.learnClick(flashCard, cardView, textView) }
            btnlv1.setOnClickListener { onLearnClick.buttonClick(flashCard, btnlv1, position) }
            btnlv2.setOnClickListener { onLearnClick.buttonClick(flashCard, btnlv2, position) }
            btnlv3.setOnClickListener { onLearnClick.buttonClick(flashCard, btnlv3, position) }
            btnlv4.setOnClickListener { onLearnClick.buttonClick(flashCard, btnlv4, position) }

        }
    }

}