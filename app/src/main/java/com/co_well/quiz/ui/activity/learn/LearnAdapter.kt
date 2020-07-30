package com.co_well.quiz.ui.activity.learn

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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
        holder.bind(list[position], onClick, position)
    }


    class LearnViewHolder(view: View) : RecyclerView.ViewHolder(view) {
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

            when(flashCard.done){
                1 -> {
                    btnlv1.setBackgroundResource(R.color.colorAccent)
                    btnlv2.setBackgroundResource(R.color.colorPrimary)
                    btnlv3.setBackgroundResource(R.color.colorPrimary)
                    btnlv4.setBackgroundResource(R.color.colorPrimary)
                }

                2 -> {
                    btnlv1.setBackgroundResource(R.color.colorPrimary)
                    btnlv2.setBackgroundResource(R.color.colorAccent)
                    btnlv3.setBackgroundResource(R.color.colorPrimary)
                    btnlv4.setBackgroundResource(R.color.colorPrimary)
                }

                3 -> {
                    btnlv1.setBackgroundResource(R.color.colorPrimary)
                    btnlv2.setBackgroundResource(R.color.colorPrimary)
                    btnlv3.setBackgroundResource(R.color.colorAccent)
                    btnlv4.setBackgroundResource(R.color.colorPrimary)
                }

                4 -> {
                    btnlv1.setBackgroundResource(R.color.colorPrimary)
                    btnlv2.setBackgroundResource(R.color.colorPrimary)
                    btnlv3.setBackgroundResource(R.color.colorPrimary)
                    btnlv4.setBackgroundResource(R.color.colorAccent)
                }
            }

            textView.setOnClickListener { onLearnClick.learnClick(flashCard, cardView, textView) }
            btnlv1.setOnClickListener {onLearnClick.buttonClick(flashCard, btnlv1, position) }
            btnlv2.setOnClickListener {onLearnClick.buttonClick(flashCard, btnlv2, position) }
            btnlv3.setOnClickListener {onLearnClick.buttonClick(flashCard, btnlv3, position) }
            btnlv4.setOnClickListener {onLearnClick.buttonClick(flashCard, btnlv4, position) }

        }
    }
}