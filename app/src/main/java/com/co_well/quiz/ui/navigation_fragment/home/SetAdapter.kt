package com.co_well.quiz.ui.navigation_fragment.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.co_well.quiz.R
import com.co_well.quiz.domain.entity.SetCard
import com.co_well.quiz.ui.activity.interf.OnSetClick

class SetAdapter(onSetClick: OnSetClick) : RecyclerView.Adapter<SetAdapter.SetViewHolder>() {
    private val list: MutableList<SetCard> = mutableListOf()
    private val onclick = onSetClick

    fun addListSetCard(listSetCard: List<SetCard>) {
        list.clear()
        list.addAll(listSetCard)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SetViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_item_set, parent, false)
        return SetViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: SetViewHolder, position: Int) {
        holder.bind(list[position], onclick)
    }

    class SetViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var tvSetName: TextView
        private var tvSetSize: TextView
        private var cardViewSet: CardView

        init {
            tvSetName = view.findViewById(R.id.tv_set_name)
            tvSetSize = view.findViewById(R.id.tv_set_size)
            cardViewSet = view.findViewById(R.id.card_view_set)
        }

        fun bind(setCard: SetCard, onSetClick: OnSetClick) {
            tvSetName.text = setCard.name
            var text = setCard.cardList.size.toString()
            text = text + " thuật ngữ"
            tvSetSize.text = text

            cardViewSet.setOnClickListener { onSetClick.setClick(setCard) }
        }
    }
}