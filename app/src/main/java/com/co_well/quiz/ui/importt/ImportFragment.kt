package com.co_well.quiz.ui.importt

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.co_well.quiz.R
import com.co_well.quiz.ui.activity.ImportFileActivity
import com.co_well.quiz.ui.navigation_fragment.importt.ImportViewModel
import com.co_well.quiz.ui.scan_import_activity.ScanActivity
import kotlinx.android.synthetic.main.fragment_import.*

class ImportFragment : Fragment() {
    private val TAG = "ImportFragment"

    private lateinit var importViewModel: ImportViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_import, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        importViewModel = ImportViewModel()
        btn_scan.setOnClickListener { openScan() }
        btn_import.setOnClickListener { openImport() }
    }

    fun openScan(){
        var intent = Intent(activity,ScanActivity::class.java)
        startActivity(intent)
    }

    fun openImport(){
        var intent = Intent(activity, ImportFileActivity::class.java)
        startActivity(intent)
    }


}