package com.co_well.quiz.ui.activity.create_set

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.co_well.quiz.Constant
import com.co_well.quiz.R
import kotlinx.android.synthetic.main.layout_item_text_table.view.*

class TextTableAdapter() :
    RecyclerView.Adapter<TextTableAdapter.TextTableViewHolder>() {
    private lateinit var regex: String
    private var list = arrayListOf<String>()

    fun addText(string: String) {
        list.add(0, string)
        notifyItemInserted(0)
    }

    fun addRegex(regex: String) {
        this.regex = regex
    }

    fun getList(): ArrayList<String>{
        return list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextTableViewHolder {
        return TextTableViewHolder(
            LayoutInflater.from(parent.context),
            parent
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: TextTableViewHolder, position: Int) {
        holder.bind(list[position], regex)
    }

    class TextTableViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(
            inflater.inflate(R.layout.layout_item_text_table, parent, false)
        ) {

        fun bind(string: String, regex: String) {

            if (!regex.isEmpty()) {
                if (string.contains(regex)) {
                    var str = string.split(regex).toTypedArray()
                    itemView.edt_english.setText(str[0])
                    itemView.edt_vietnamese.setText(str[1])
                } else if (string.contains(Constant.regex)) {
                    var str = string.split(Constant.regex).toTypedArray()
                    itemView.edt_english.setText(str[0])
                    itemView.edt_vietnamese.setText(str[1])
                }
            } else {
                itemView.edt_english.setText("")
                itemView.edt_vietnamese.setText("")
            }
        }

    }
}