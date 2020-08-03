package com.co_well.quiz.ui.activity.learn

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.co_well.quiz.domain.entity.FlashCard

class LearnViewModel : ViewModel() {
    companion object {
        private var _listFlashCard: MutableLiveData<MutableList<FlashCard>> = MutableLiveData()
    }

    val listFlashCard: LiveData<MutableList<FlashCard>>
        get() = _listFlashCard

    fun setList(list: List<FlashCard>) {
        _listFlashCard.value = list.toMutableList()
    }
}