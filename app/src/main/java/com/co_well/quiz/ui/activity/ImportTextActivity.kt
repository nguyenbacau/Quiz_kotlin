package com.co_well.quiz.ui.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.co_well.quiz.R
import com.co_well.quiz.ui.activity.create_set.CreateSetActivity
import kotlinx.android.synthetic.main.activity_import_file.*
import kotlinx.android.synthetic.main.activity_import_text.*
import kotlinx.android.synthetic.main.activity_import_text.btn_split
import kotlinx.android.synthetic.main.activity_import_text.edt_regex_split
import java.util.ArrayList

class ImportTextActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_import_text)
        btn_split.setOnClickListener {
            hideKeyBoard()
            splitText()
        }
    }

    fun splitText() {
        if(edt_regex_split.text.isEmpty()){
            Toast.makeText(this,"Input regex", Toast.LENGTH_SHORT).show()
        }else{
            if(edt_input_text.text.isEmpty()){
                Toast.makeText(this,"Input text",Toast.LENGTH_SHORT).show()
            }else {
                var regex = edt_regex_split.text.toString()
                var text = edt_input_text.text

                var array = text.split("\n").toTypedArray()

                var list: ArrayList<String> = ArrayList()

                for (i in 0 until array.size) {
                    list.add(array[i])
                }

                var intent = Intent(this, CreateSetActivity::class.java)
                intent.putExtra("listArray", list)
                intent.putExtra("regex", regex)
                startActivity(intent)
                finish()
            }
        }
    }

    fun hideKeyBoard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }
}