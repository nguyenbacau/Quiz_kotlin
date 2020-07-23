package com.co_well.quiz.ui.navigation_fragment.importt

import android.content.Intent
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.co_well.quiz.domain.interactor.ScanImageUseCase
import com.co_well.quiz.ui.activity.ImportFileActivity
import com.co_well.quiz.ui.activity.ScanActivity

class ImportViewModel : ViewModel() {

    private val _title = MutableLiveData<String>()

    val title: LiveData<String> get() = _title

    fun setTitle(title: String){
        _title.value = title
    }

    val buttonScanClick = View.OnClickListener { view ->
        val context = view.context
        context.startActivity(Intent(context, ScanActivity::class.java))
    }

    val buttonImportClick = View.OnClickListener { view ->
        val context = view.context
        context.startActivity(Intent(context, ImportFileActivity::class.java))
    }


}