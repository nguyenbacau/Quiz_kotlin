package com.co_well.quiz.ui.activity.create_set

import android.content.Context
import android.content.SharedPreferences
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
import com.co_well.quiz.domain.interactor.InsertToDbUseCase
import com.co_well.quiz.ui.activity.interf.OnTextClick
import kotlinx.android.synthetic.main.activity_create_set.*


class CreateSetActivity : AppCompatActivity(), OnTextClick {
    private lateinit var list: ArrayList<String>
    private lateinit var regex: String
    private lateinit var adapterTable: TextTableAdapter
    lateinit var insertToDbUseCase: InsertToDbUseCase
    lateinit var getAllCardUseCase: GetAllSetUseCase
    lateinit var sharePref : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        InjectionUtil.injectCreateSet(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_set)

        list = intent.extras?.get("listArray") as ArrayList<String>
        regex = intent.extras?.get("regex") as String
//        sharePref = getPreferences(Context.MODE_PRIVATE)
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
            val flashCard = FlashCard(str[0], str[1])
            listFlashCard.add(flashCard)
        }
        insertToDbUseCase(listFlashCard)

        val list = getAllCardUseCase()
        for (i in list) {
                Log.e("zzz", "word: " + i.word + "define" + i.define)
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
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}