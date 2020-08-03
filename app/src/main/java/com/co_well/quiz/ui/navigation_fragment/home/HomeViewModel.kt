package com.co_well.quiz.ui.navigation_fragment.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.co_well.quiz.domain.entity.SetCard
import com.co_well.quiz.domain.interactor.GetAllSetUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class HomeViewModel(
    private val getAllSetUseCase: GetAllSetUseCase
) : ViewModel() {
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    private var _listSetCard: MutableLiveData<MutableList<SetCard>> = MutableLiveData()

    val listSetCar: LiveData<MutableList<SetCard>>
        get() = _listSetCard

    fun getAllSetCard() {
        compositeDisposable.add(getAllSetUseCase().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> _listSetCard.value = result.toMutableList() },
                { error -> error.printStackTrace() }
            ))
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}