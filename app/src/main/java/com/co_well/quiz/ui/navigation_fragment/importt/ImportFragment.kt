package com.co_well.quiz.ui.navigation_fragment.importt

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.co_well.quiz.R
import com.co_well.quiz.ui.activity.ImportFileActivity
import com.co_well.quiz.ui.activity.ScanActivity
import com.co_well.quiz.ui.activity.create_set.TextTableAdapter
import com.co_well.quiz.ui.activity.interf.OnTextTableClick
import kotlinx.android.synthetic.main.fragment_import.*
import kotlinx.android.synthetic.main.fragment_import.flab_add
import kotlinx.android.synthetic.main.fragment_import.recycler_view_table

class ImportFragment : Fragment() {
class ImportFragment : Fragment(), OnTextTableClick {
//    private lateinit var importViewModel: ImportViewModel
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
//        importViewModel = ImportViewModel()
//        onEdtListener()
        btn_scan.setOnClickListener{
            startActivity(Intent(context, ScanActivity::class.java))

            if(!edt_set_name.text.isEmpty()){
                Common.tittle = edt_set_name.text.toString();
            }

        }
        btn_import.setOnClickListener{
            startActivity(Intent(context, ImportFileActivity::class.java))

            if(!edt_set_name.text.isEmpty()){
                Common.tittle = edt_set_name.text.toString();
            }
        }

//        recycler_view_table.layoutManager = LinearLayoutManager()
        recycler_view_table.layoutManager = LinearLayoutManager(activity)
        adapterTable = TextTableAdapter()
        adapterTable = TextTableAdapter(this)
        adapterTable.addRegex("")
        recycler_view_table.adapter = adapterTable
        addRow()

    }

    fun addRow() {
        flab_add.setOnClickListener {
            adapterTable.addText("")
            recycler_view_table.scrollToPosition(0)
        }
    }

    override fun onTextTableClick(edtWord: EditText, edtDefine: EditText, position: Int, regex: String) {
        TODO("Not yet implemented")
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