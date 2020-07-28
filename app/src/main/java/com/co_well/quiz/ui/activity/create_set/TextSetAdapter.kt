package com.co_well.quiz.ui.activity.create_set

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.co_well.quiz.R
import com.co_well.quiz.ui.activity.interf.OnTextClick
import kotlinx.android.synthetic.main.layout_item_text.view.*

class TextSetAdapter(listText: ArrayList<String>, onTextClick: OnTextClick) :
    RecyclerView.Adapter<TextSetAdapter.TextViewHolder>() {
    private var list = listText
    var ontextClick = onTextClick

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_item_text,parent,false)
        return TextViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: TextViewHolder, position: Int) {
        holder.bind(list[position],ontextClick)
    }

    class TextViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {

        fun bind(text: String, onTextClick: OnTextClick) {
            itemView.tv_word.text = text
            itemView.setOnClickListener{
                onTextClick.textClick(text)
            }
        }

    }
}