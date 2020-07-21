package com.co_well.quiz.ui.scan_import_activity

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.co_well.quiz.R
import com.co_well.quiz.ui.scan_import_activity.create_set.CreateSetActivity
import kotlinx.android.synthetic.main.activity_import_file.*
import kotlinx.android.synthetic.main.activity_import_file.btn_split
import kotlinx.android.synthetic.main.activity_import_file.edt_regex_split
import kotlinx.android.synthetic.main.activity_scan.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.*

class ImportFileActivity : AppCompatActivity() {
    val PICK_FILE_REQUEST =10
    val PERMISSION_REQUEST_STORAGE = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_import_file)
        //check version and permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                PERMISSION_REQUEST_STORAGE
            )
        }

        //event click button
        onClickButton()
    }

    fun onClickButton(){
        btn_openfile.setOnClickListener(){
            readFile()
        }

//        btn_write.setOnClickListener{
//            writeText()
//        }

        btn_split.setOnClickListener {
            hideKeyBoard()
            splitText()
        }
    }

    fun splitText() {
        if(edt_regex_split.text.isEmpty()){
            Toast.makeText(this,"Input regex",Toast.LENGTH_SHORT).show()
        }else{
            var regex = edt_regex_split.text.toString()
            var text = tv_text_choose.text

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

    fun readFile(){
        var intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.setType("*/*")
        startActivityForResult(intent, PICK_FILE_REQUEST)
    }

    override fun onActivityResult(
        requestCode: Int, resultCode: Int, resultData: Intent?) {

        if (requestCode == PICK_FILE_REQUEST
            && resultCode == Activity.RESULT_OK) {
            // The result data contains a URI for the document or directory that
            // the user selected.
            resultData?.data?.also { uri ->
                // Perform operations on the document using its URI.
                tv_text_choose.setText(readTextFromUri(uri))
            }
        }

        detect()

        super.onActivityResult(requestCode, resultCode, resultData)
    }

    fun hideKeyBoard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }

    @Throws(IOException::class)
    private fun readTextFromUri(uri: Uri): String {
        val stringBuilder = StringBuilder()
        contentResolver.openInputStream(uri)?.use { inputStream ->
            BufferedReader(InputStreamReader(inputStream)).use { reader ->
                var line: String? = reader.readLine()
                while (line != null) {
                    stringBuilder.append(line)
                    stringBuilder.append("\n")
                    line = reader.readLine()
                }
            }
        }
        return stringBuilder.toString()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(requestCode == PERMISSION_REQUEST_STORAGE){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"Permission granted",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,"Permission not granted",Toast.LENGTH_SHORT).show();
                finish();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    fun detect(){
//        tv_text_scan.visibility = View.VISIBLE

        edt_regex_split.visibility = View.VISIBLE
        btn_split.visibility = View.VISIBLE
    }

}