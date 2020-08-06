package com.co_well.quiz.ui.activity.create_set

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.co_well.quiz.Constant
import com.co_well.quiz.R
import com.co_well.quiz.ui.activity.interf.OnTextTableClick
import kotlinx.android.synthetic.main.layout_item_text_table.view.*

class TextTableAdapter() :
class TextTableAdapter(onTextTableClick: OnTextTableClick) :
    RecyclerView.Adapter<TextTableAdapter.TextTableViewHolder>() {
    private lateinit var regex: String
    private var list = arrayListOf<String>()
    private val textTableClick = onTextTableClick

    fun addText(string: String) {
        list.add(0, string)
        notifyItemInserted(0)
    }

    fun addRegex(regex: String) {
        this.regex = regex
    }

    fun getList(): ArrayList<String>{

    fun updateRow(position: Int, text: String) {
        list[position] = text
    }

    fun getList(): ArrayList<String> {
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
        holder.bind(list[position], regex, textTableClick, position)
    }

    class TextTableViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(
            inflater.inflate(R.layout.layout_item_text_table, parent, false)
        ) {
        private val edtWord: EditText = itemView.findViewById(R.id.edt_word)
        private val edtDefine: EditText = itemView.findViewById(R.id.edt_define)

        fun bind(string: String, regex: String) {
        fun bind(string: String, regex: String, onTextTableClick: OnTextTableClick, position: Int) {
            if (!regex.isEmpty()) {
                if (string.contains(regex)) {
                    var str = string.split(regex).toTypedArray()
                    itemView.edt_english.setText(str[0])
                    itemView.edt_vietnamese.setText(str[1])
                    edtWord.setText(str[0])
                    edtDefine.setText(str[1])
                } else if (string.contains(Constant.regex)) {
                    var str = string.split(Constant.regex).toTypedArray()
                    itemView.edt_english.setText(str[0])
                    itemView.edt_vietnamese.setText(str[1])
                    edtWord.setText(str[0])
                    edtDefine.setText(str[1])
                }
            } else {
                itemView.edt_english.setText("")
                itemView.edt_vietnamese.setText("")
                edtWord.setText("")
                edtDefine.setText("")
            }
//            edtWord.setOnClickListener {
//                onTextTableClick.onTextTableClick(edtWord, edtDefine, position, regex)
//            }

            edtWord.setOnFocusChangeListener { view, b ->
                onTextTableClick.onTextTableClick(edtWord, edtDefine, position, regex)
            }

//            edtDefine.setOnClickListener {
//                onTextTableClick.onTextTableClick(edtWord, edtDefine, position, regex)
//            }

            edtDefine.setOnFocusChangeListener { view, b ->
                onTextTableClick.onTextTableClick(edtWord, edtDefine, position, regex)
            }
        }
    }
}