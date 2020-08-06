package com.co_well.quiz.ui.activity.interf

import android.widget.EditText

interface OnTextTableClick {
    fun onTextTableClick(edtWord: EditText, edtDefine: EditText, position: Int, regex: String)
}