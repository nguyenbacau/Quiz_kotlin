package com.co_well.quiz.ui.activity.learn.learns

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.co_well.quiz.InjectionUtil
import com.co_well.quiz.R
import com.co_well.quiz.domain.entity.FlashCard
import com.co_well.quiz.domain.entity.SetCard
import com.co_well.quiz.domain.interactor.GetSetUseCase
import com.co_well.quiz.domain.interactor.UpdateListCardUseCase
import com.co_well.quiz.ui.activity.interf.OnLearnClick
import kotlinx.android.synthetic.main.activity_card_learn.*

class CardLearnActivity : AppCompatActivity(), OnLearnClick {
    private lateinit var setName: String
    private lateinit var cardLearnViewModel: CardLearnViewModel
    private lateinit var cardLearnAdapter: CardLearnAdapter
    lateinit var updateListCardUseCase: UpdateListCardUseCase
    lateinit var getSetUseCase: GetSetUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        InjectionUtil.injectLearns(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_learn)

        cardLearnViewModel = CardLearnViewModel(getSetUseCase)
        setName = intent.extras?.get("set_name") as String

        cardLearnViewModel.getSet(setName)
        cardLearnAdapter = CardLearnAdapter(this)

        recycler_view_learns.apply {
            layoutManager =
                LinearLayoutManager(this@CardLearnActivity)
            adapter = cardLearnAdapter
        }

        cardLearnViewModel.listFlashCard.observe(this, Observer {
            cardLearnAdapter.addListSetCard(it)
        })
    }

    override fun learnClick(flashCard: FlashCard, cardView: CardView, textView: TextView) {
        val oa1 = ObjectAnimator.ofFloat(cardView, "scaleX", 1f, 0f)
        val oa2 = ObjectAnimator.ofFloat(cardView, "scaleX", 0f, 1f)
        oa1.setDuration(100);
        oa2.setDuration(100);
        oa1.interpolator = DecelerateInterpolator()
        oa2.interpolator = AccelerateDecelerateInterpolator()
        oa1.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                if (flashCard.flip) {
                    textView.text = flashCard.define
                    flashCard.flip = false
                } else {
                    textView.text = flashCard.word
                    flashCard.flip = true
                }
                oa2.start()
            }
        })
        oa1.start()
    }

    override fun buttonClick(flashCard: FlashCard, button: Button, position: Int) {
        when (button.id) {
            R.id.btn_lv1 -> {
                flashCard.done = 1
                cardLearnAdapter.notifyItemChanged(position)
            }
            R.id.btn_lv2 -> {
                flashCard.done = 2
                cardLearnAdapter.notifyItemChanged(position)
            }
            R.id.btn_lv3 -> {
                flashCard.done = 3
                cardLearnAdapter.notifyItemChanged(position)
            }
            R.id.btn_lv4 -> {
                flashCard.done = 4
                cardLearnAdapter.notifyItemChanged(position)
            }
        }
    }

    override fun buttonFullScreenClick() {
        TODO("Not yet implemented")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.create_set_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_save -> {
                Toast.makeText(this, "save", Toast.LENGTH_SHORT).show()
                val list = cardLearnAdapter.getList()
                updateListCardUseCase(list)
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}



