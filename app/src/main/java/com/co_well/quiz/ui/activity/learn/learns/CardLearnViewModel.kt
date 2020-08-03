package com.co_well.quiz.ui.activity.learn.learns

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.co_well.quiz.domain.entity.FlashCard
import com.co_well.quiz.domain.interactor.GetSetUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CardLearnViewModel(
    private val getSetUseCase: GetSetUseCase
) : ViewModel() {
    private val _listFlashCard: MutableLiveData<MutableList<FlashCard>> = MutableLiveData()
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    val listFlashCard: LiveData<MutableList<FlashCard>>
        get() = _listFlashCard

    fun getSet(name: String) {
        compositeDisposable.add(getSetUseCase(name).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> _listFlashCard.value = result.cardList.toMutableList() },
                { error -> error.printStackTrace() }
            ))
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}