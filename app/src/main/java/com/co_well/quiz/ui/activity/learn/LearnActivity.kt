package com.co_well.quiz.ui.activity.learn

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.co_well.quiz.InjectionUtil
import com.co_well.quiz.R
import com.co_well.quiz.domain.entity.FlashCard
import com.co_well.quiz.domain.entity.SetCard
import com.co_well.quiz.domain.interactor.UpdateListCardUseCase
import com.co_well.quiz.ui.activity.interf.OnLearnClick
import kotlinx.android.synthetic.main.activity_learn.*


class LearnActivity : AppCompatActivity(), OnLearnClick {

    private lateinit var setCard: SetCard
    private lateinit var learnViewModel: LearnViewModel
    private lateinit var learnAdapter: LearnAdapter
    lateinit var updateListCardUseCase: UpdateListCardUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        InjectionUtil.injectLearn(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn)

        learnViewModel = LearnViewModel()
        setCard = intent.extras?.get("setCard") as SetCard

        learnAdapter = LearnAdapter(this)
        recycler_view_learn.apply {
            layoutManager = LinearLayoutManager(this@LearnActivity)
            adapter = learnAdapter
        }

        learnViewModel.setList(setCard.cardList)
        learnViewModel.listFlashCard.observe(this, Observer {
            learnAdapter.addListSetCard(it)
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
                learnAdapter.notifyItemChanged(position)
            }
            R.id.btn_lv2 -> {
                flashCard.done = 2
                learnAdapter.notifyItemChanged(position)
            }
            R.id.btn_lv3 -> {
                flashCard.done = 3
                learnAdapter.notifyItemChanged(position)
            }
            R.id.btn_lv4 -> {
                flashCard.done = 4
                learnAdapter.notifyItemChanged(position)
            }
        }
    }

    override fun onBackPressed() {
        val list = learnAdapter.getList()
        updateListCardUseCase.invoke(list)
        super.onBackPressed()
    }

}