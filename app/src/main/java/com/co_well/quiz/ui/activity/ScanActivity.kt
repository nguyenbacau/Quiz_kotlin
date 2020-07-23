package com.co_well.quiz.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.co_well.quiz.InjectionUtil
import com.co_well.quiz.R
import com.co_well.quiz.domain.interactor.ScanImageUseCase
import kotlinx.android.synthetic.main.activity_scan.*


class ScanActivity : AppCompatActivity() {
    private val GALLERY_REQUEST_CODE = 999
    lateinit var scanImageUseCase: ScanImageUseCase
    private lateinit var scanViewModel: ScanViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        InjectionUtil.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan)
        scanViewModel = ScanViewModel(scanImageUseCase)
        onClickButton()
    }

    fun onClickButton() {
        btn_gallery.setOnClickListener { pickFromGallary() }
        btn_split.setOnClickListener {
            scanViewModel.edtRegexSplit.value = edt_regex_split.text.toString()
            scanViewModel.tvScan.value = tv_text_scan.text.toString()
            scanViewModel.butonSplitCLick.onClick(it)
        }
    }

    fun pickFromGallary() {
        var intent = Intent(Intent.ACTION_PICK)
        var intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.setType("image/*")
        startActivityForResult(intent, GALLERY_REQUEST_CODE)
    }

    fun dection(imgUri: String) {
        tv_text_scan.visibility = View.VISIBLE
        edt_regex_split.visibility = View.VISIBLE
        btn_split.visibility = View.VISIBLE
        val text = scanViewModel.detect(imgUri)
        Log.d("TEXT",text)
        tv_text_scan.text = text
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GALLERY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            img_image_scan.setImageURI(data?.data)
            dection(data?.data.toString())
        } else {
            Toast.makeText(this, "load image error", Toast.LENGTH_SHORT).show()
        }
    }
}