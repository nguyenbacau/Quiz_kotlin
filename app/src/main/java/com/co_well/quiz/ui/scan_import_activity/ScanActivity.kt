package com.co_well.quiz.ui.scan_import_activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.co_well.quiz.R
import com.co_well.quiz.ui.scan_import_activity.create_set.CreateSetActivity
import com.google.android.gms.vision.Frame
import com.google.android.gms.vision.text.TextRecognizer
import kotlinx.android.synthetic.main.activity_scan.*

class ScanActivity : AppCompatActivity() {
    private val TAG = "ScanActivity"
    val GALLERY_REQUEST_CODE = 999

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan)
        onClickButton()
    }

    fun onClickButton() {
        btn_gallery.setOnClickListener { pickFromGallary() }
        btn_split.setOnClickListener {
            hideKeyBoard()
            if (edt_regex_split.text.isEmpty()) {
                Toast.makeText(this, "Input split", Toast.LENGTH_SHORT).show()
            } else {
                var regex = edt_regex_split.text.toString()
                var text = tv_text_scan.text

                var array = text.split("\n").toTypedArray()

                var list: ArrayList<String> = ArrayList()

                for (i in 0 until array.size) {
                    list.add(array[i])
                }

                var intent = Intent(this, CreateSetActivity::class.java)
                intent.putExtra("listArray", list)
                intent.putExtra("regex", regex)
                startActivity(intent)
            }
        }
    }

    fun hideKeyBoard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }

    fun pickFromGallary() {
        var intent = Intent(Intent.ACTION_PICK)
        intent.setType("image/*")
        startActivityForResult(intent, GALLERY_REQUEST_CODE)
    }

    fun dectect() {
        var recognizer = TextRecognizer.Builder(this).build()
        var bitmap = (img_image_scan.drawable as BitmapDrawable).bitmap

        var frame = Frame.Builder().setBitmap(bitmap).build()

        var sparseArray = recognizer.detect(frame)

        var stringBuilder = StringBuilder()

        for (i in 0 until sparseArray.size()) {
            val tx = sparseArray[i]
            val str = tx.value
            stringBuilder.append(str)
        }
        tv_text_scan.visibility = View.VISIBLE

        edt_regex_split.visibility = View.VISIBLE
        btn_split.visibility = View.VISIBLE
        tv_text_scan.text = stringBuilder


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GALLERY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            img_image_scan.setImageURI(data?.data)
            dectect()
        } else {
            Log.e(TAG, "onActivityResult: resultCode: " + resultCode)
        }
    }
}