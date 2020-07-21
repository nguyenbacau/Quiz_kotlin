package com.co_well.quiz.ui.navigation_fragment.importt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.co_well.quiz.MainActivity
import com.co_well.quiz.R
import kotlinx.android.synthetic.main.fragment_import.*

class ImportFragment : Fragment() {
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
        btn_scan.setOnClickListener(importViewModel.buttonScanClick)
        btn_import.setOnClickListener(importViewModel.buttonImportClick)
    }

}