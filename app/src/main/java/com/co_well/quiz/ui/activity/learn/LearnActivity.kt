package com.co_well.quiz.ui.activity.learn

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.co_well.quiz.InjectionUtil
import com.co_well.quiz.R
import com.co_well.quiz.domain.entity.FlashCard
import com.co_well.quiz.domain.interactor.GetSetUseCase
import com.co_well.quiz.ui.activity.interf.OnLearnClick
import com.co_well.quiz.ui.activity.learn.learns.CardLearnActivity
import kotlinx.android.synthetic.main.activity_learn.*


class LearnActivity : AppCompatActivity(), OnLearnClick {

    private lateinit var setName: String
    private lateinit var learnViewModel: LearnViewModel
    private lateinit var learnAdapter: LearnAdapter
    lateinit var tvSetName: TextView
    lateinit var tvSetSize: TextView
    lateinit var btnFlashCard: Button
    lateinit var getSetUseCase: GetSetUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        InjectionUtil.injectLearn(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn)
        tvSetName = findViewById(R.id.tv_set_name)
        tvSetSize = findViewById(R.id.tv_set_size)
        btnFlashCard = findViewById(R.id.btn_flashCard)

        learnViewModel = LearnViewModel(getSetUseCase)
        setName = intent.extras?.get("set_name") as String

        learnViewModel.getSet(setName)
        learnAdapter = LearnAdapter(this)
        recycler_view_learn.apply {
            layoutManager = LinearLayoutManager(this@LearnActivity, RecyclerView.HORIZONTAL, false)
            adapter = learnAdapter
        }

        learnViewModel.listFlashCard.observe(this, Observer {
            learnAdapter.addListSetCard(it)
        })

        learnViewModel.tvSetName.observe(this, Observer {
            tvSetName.text = it
        })

        learnViewModel.tvSetSize.observe(this, Observer {
            tvSetSize.text = it.toString()
        })

        onClick()
    }

    fun onClick() {
        btnFlashCard.setOnClickListener {
            startFlashCardActivity()
        }
    }

    override fun learnClick(flashCard: FlashCard, cardView: CardView, textView: TextView) {
        val oa1 = ObjectAnimator.ofFloat(cardView, "scaleY", 1f, 0f)
        val oa2 = ObjectAnimator.ofFloat(cardView, "scaleY", 0f, 1f)
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
        TODO("Not yet implemented")
    }

    override fun buttonFullScreenClick() {
        startFlashCardActivity()
    }

    fun startFlashCardActivity() {
        val intent = Intent(this, CardLearnActivity::class.java)
        intent.putExtra("set_name", setName)
        startActivity(intent)
    }

}