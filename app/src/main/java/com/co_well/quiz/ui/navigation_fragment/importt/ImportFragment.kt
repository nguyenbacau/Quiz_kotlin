package com.co_well.quiz.ui.navigation_fragment.importt

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.*
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import com.co_well.quiz.R
import kotlinx.android.synthetic.main.fragment_import.*

class ImportFragment : Fragment() {
    private lateinit var importViewModel: ImportViewModel
//    private val sharePref = activity?.getSharedPreferences("Quiz", Context.MODE_PRIVATE)

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
//        onEdtListener()
        btn_scan.setOnClickListener(importViewModel.buttonScanClick)
        btn_import.setOnClickListener(importViewModel.buttonImportClick)
    }

//    fun onEdtListener() {
//        edt_set_name.addTextChangedListener(object : TextWatcher {
//            override fun afterTextChanged(p0: Editable?) {
//                Log.e("zzz", "afterTextChanged: " + p0 )
//            }
//
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                Log.e("zzz", "beforeTextChanged: " +p0 )
//            }
//
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                Log.e("zzz", "onTextChanged: -----" + p0  )
//                if (p0!!.isEmpty()) {
//                    btn_scan.visibility = View.INVISIBLE
//                    btn_import.visibility = View.INVISIBLE
//                } else {
//                    btn_scan.visibility = View.VISIBLE
//                    btn_import.visibility = View.VISIBLE
//                }
//            }
//        })
//    }
}