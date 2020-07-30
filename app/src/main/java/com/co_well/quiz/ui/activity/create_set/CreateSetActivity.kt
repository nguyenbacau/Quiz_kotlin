package com.co_well.quiz.ui.activity.create_set

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.co_well.quiz.InjectionUtil
import com.co_well.quiz.R
import com.co_well.quiz.domain.entity.FlashCard
import com.co_well.quiz.domain.entity.Set
import com.co_well.quiz.domain.interactor.GetAllSetUseCase
import com.co_well.quiz.domain.interactor.InsertSetUseCase
import com.co_well.quiz.domain.interactor.InsertCardUseCase
import com.co_well.quiz.ui.activity.interf.OnTextClick
import kotlinx.android.synthetic.main.activity_create_set.*


class CreateSetActivity : AppCompatActivity(), OnTextClick {
    private lateinit var list: ArrayList<String>
    private lateinit var regex: String
    private lateinit var adapterTable: TextTableAdapter
    lateinit var insertCardUseCase: InsertCardUseCase
    lateinit var getAllSetUseCase: GetAllSetUseCase
    lateinit var insertSetUseCase: InsertSetUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        InjectionUtil.injectCreateSet(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_set)

        list = intent.extras?.get("listArray") as ArrayList<String>
        regex = intent.extras?.get("regex") as String
        //recycler_view_show
        recycler_view_show.layoutManager =
            GridLayoutManager(this, 3, GridLayoutManager.HORIZONTAL, false)
        recycler_view_show.addItemDecoration(
            GridSpacingItemDecoration(20, 3)
        )
        recycler_view_show.adapter = TextSetAdapter(list, this)

        //recycler_view_table
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

    fun addRow() {
        flab_add.setOnClickListener {
            adapterTable.addText("")
            recycler_view_table.scrollToPosition(0)
        }
    }

    fun saveData(list: ArrayList<String>) {
        val listFlashCard = arrayListOf<FlashCard>()
        for (string in list) {
            val str = string.split(regex).toTypedArray()
            val flashCard = FlashCard(0,"hi",str[0], str[1], 1,true)
            listFlashCard.add(flashCard)
        }
        insertCardUseCase(listFlashCard)

        val set = Set("hi")

        insertSetUseCase(set)


        val listGet = getAllSetUseCase()
        for (i in listGet){
            val a = i.cardList
            for (j in a){
                Log.e("zzz", "saveData: " + j.word + "---------" + j.define )
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.create_set_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_save -> {
                Toast.makeText(this, "save", Toast.LENGTH_SHORT).show()
                val list = adapterTable.getList()
                saveData(list)
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}