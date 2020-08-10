package com.co_well.quiz.ui.navigation_fragment.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.co_well.quiz.domain.entity.FlashCard
import com.co_well.quiz.domain.interactor.GetRankCardUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SearchViewModel(
    private val getRankCardUseCase: GetRankCardUseCase
) : ViewModel() {

    private val _listFlashCard1: MutableLiveData<MutableList<FlashCard>> = MutableLiveData()
    private val _listFlashCard2: MutableLiveData<MutableList<FlashCard>> = MutableLiveData()
    private val _listFlashCard3: MutableLiveData<MutableList<FlashCard>> = MutableLiveData()
    private val _listFlashCard4: MutableLiveData<MutableList<FlashCard>> = MutableLiveData()

    private val _tv1: MutableLiveData<String> = MutableLiveData()
    private val _tv2: MutableLiveData<String> = MutableLiveData()
    private val _tv3: MutableLiveData<String> = MutableLiveData()
    private val _tv4: MutableLiveData<String> = MutableLiveData()
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    val listFlashCard1: LiveData<MutableList<FlashCard>> get() = _listFlashCard1
    val listFlashCard2: LiveData<MutableList<FlashCard>> get() = _listFlashCard2
    val listFlashCard3: LiveData<MutableList<FlashCard>> get() = _listFlashCard3
    val listFlashCard4: LiveData<MutableList<FlashCard>> get() = _listFlashCard4

    val tv1: LiveData<String> get() = _tv1
    val tv2: LiveData<String> get() = _tv2
    val tv3: LiveData<String> get() = _tv3
    val tv4: LiveData<String> get() = _tv4

    fun getRank(rank: Int) {
        compositeDisposable.add(getRankCardUseCase(rank).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    when (rank) {
                        1 -> {
                            _listFlashCard1.value = result.toMutableList()
                            _tv1.value = " Khó nhớ ${result.size}"
                        }
                        2 -> {
                            _listFlashCard2.value = result.toMutableList()
                            _tv2.value = " Đã nhớ ${result.size}"
                        }
                        3 -> {
                            _listFlashCard3.value = result.toMutableList()
                            _tv3.value = " Nhớ rất rõ ${result.size}"
                        }
                        4 -> {
                            _listFlashCard4.value = result.toMutableList()
                            _tv4.value = " Đã hiểu ${result.size}"
                        }
                    }
                },
                { error -> error.printStackTrace() }
            ))
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}