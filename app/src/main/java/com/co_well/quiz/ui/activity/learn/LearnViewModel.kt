package com.co_well.quiz.ui.activity.learn

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.co_well.quiz.domain.entity.FlashCard
import com.co_well.quiz.domain.interactor.GetSetUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class LearnViewModel(
    private val getSetUseCase: GetSetUseCase
) : ViewModel() {
    private val _listFlashCard: MutableLiveData<MutableList<FlashCard>> = MutableLiveData()
    private val _tvSetName: MutableLiveData<String> = MutableLiveData()
    private val _tvSetSize: MutableLiveData<String> = MutableLiveData()
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    val listFlashCard: LiveData<MutableList<FlashCard>> get() = _listFlashCard
    val tvSetName: LiveData<String> get() = _tvSetName
    val tvSetSize: LiveData<String> get() = _tvSetSize

    fun getSet(name: String) {
        compositeDisposable.add(getSetUseCase(name).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    _listFlashCard.value = result.cardList.toMutableList()
                    _tvSetName.value = result.name
                    _tvSetSize.value = "${result.cardList.size} thuật ngữ"
                },
                { error -> error.printStackTrace() }
            ))
    }

}