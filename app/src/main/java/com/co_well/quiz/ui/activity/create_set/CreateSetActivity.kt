package com.co_well.quiz.ui.activity.create_set

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.co_well.quiz.R
import com.co_well.quiz.ui.activity.interf.OnTextClick
import kotlinx.android.synthetic.main.activity_create_set.*


class CreateSetActivity : AppCompatActivity(), OnTextClick {
    private lateinit var list: ArrayList<String>
    private lateinit var regex: String
    private lateinit var adapterTable: TextTableAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_set)

        list = intent.extras?.get("listArray") as ArrayList<String>
        regex = intent.extras?.get("regex") as String

        //recycler_view_show
        recycler_view_show.layoutManager = GridLayoutManager(this, 3, GridLayoutManager.HORIZONTAL, false)
        recycler_view_show.addItemDecoration(GridSpacingItemDecoration(20, 3)
        )
        recycler_view_show.adapter = TextSetAdapter(list, this)

        recycler_view_table.layoutManager = LinearLayoutManager(this)
        adapterTable = TextTableAdapter()
        adapterTable.addRegex(regex)
        recycler_view_table.adapter = adapterTable

        //floating action button
        addRow()
    }

    override fun textClick(string: String) {
        adapterTable.addText(string)
        recycler_view_table.scrollToPosition(0)
    }

    fun addRow(){
        flab_add.setOnClickListener {
            adapterTable.addText("")
            recycler_view_table.scrollToPosition(0)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.create_set_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_save -> {
                Toast.makeText(this, "save", Toast.LENGTH_SHORT).show()
                true
            }
            else ->super.onOptionsItemSelected(item)
        }
    }
}