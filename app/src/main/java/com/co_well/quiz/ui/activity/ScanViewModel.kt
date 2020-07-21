package com.co_well.quiz.ui.activity

import android.content.Context
import android.content.Intent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.co_well.quiz.domain.interactor.ScanImageUseCase
import com.co_well.quiz.ui.activity.create_set.CreateSetActivity

class ScanViewModel(
    private val scanImageUseCase: ScanImageUseCase
) : ViewModel() {

    val edtRegexSplit: MutableLiveData<String> = MutableLiveData()
    val textScan: MutableLiveData<String> = MutableLiveData()

    val butonSplitCLick = View.OnClickListener { view ->
        val context = view.context
        hideKeyBoard(view)
        val list: ArrayList<String> = ArrayList()
        val array = textScan.value.toString().split("\n").toTypedArray()
        for (i in 0 until array.size) {
            list.add(array[i])
        }
        val intent = Intent(context, CreateSetActivity::class.java)
        intent.putExtra("listArray", list)
        intent.putExtra("regex", edtRegexSplit.value.toString())
        context.startActivity(intent)
    }

    fun detect(imgUri: String): String {
        return scanImageUseCase(imgUri)
    }

    private fun hideKeyBoard(view: View) {
        val context = view.context
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

}