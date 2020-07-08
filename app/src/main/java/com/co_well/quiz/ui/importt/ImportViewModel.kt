package com.co_well.quiz.ui.importt

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ImportViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is import Fragment"
    }
    val text: LiveData<String> = _text
}