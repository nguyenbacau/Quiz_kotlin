package com.co_well.quiz.ui.navigation_fragment.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.co_well.quiz.domain.entity.SetCard
import com.co_well.quiz.domain.interactor.GetAllSetUseCase

class HomeViewModel(
    private val getAllSetUseCase: GetAllSetUseCase
) : ViewModel() {
    private var _listSetCard: MutableLiveData<MutableList<SetCard>> = MutableLiveData()

    val listSetCar: LiveData<MutableList<SetCard>>
        get() = _listSetCard

    fun getAllSetCard() {
        _listSetCard.value = getAllSetUseCase().toMutableList()
    }
}