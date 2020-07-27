package com.co_well.quiz.ui.navigation_fragment.importt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.co_well.quiz.R
import com.co_well.quiz.ui.activity.create_set.TextTableAdapter
import kotlinx.android.synthetic.main.fragment_import.*
import kotlinx.android.synthetic.main.fragment_import.flab_add
import kotlinx.android.synthetic.main.fragment_import.recycler_view_table

class ImportFragment : Fragment() {
    private lateinit var importViewModel: ImportViewModel
//  private val sharePref = activity?.getSharedPreferences("Quiz", Context.MODE_PRIVATE)
    private lateinit var adapterTable: TextTableAdapter

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

//        recycler_view_table.layoutManager = LinearLayoutManager()
        recycler_view_table.layoutManager = LinearLayoutManager(activity)
        adapterTable = TextTableAdapter()
        adapterTable.addRegex("")
        recycler_view_table.adapter = adapterTable

        addRow()

        if(tv_title.text != null){
            Common.tittle = tv_title.text.toString();
        }
    }

    fun addRow() {
        flab_add.setOnClickListener {
            adapterTable.addText("")
            recycler_view_table.scrollToPosition(0)
        }
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