package com.co_well.quiz.ui.navigation_fragment.search

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.*
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.cardview.widget.CardView
import androidx.core.view.MenuItemCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.co_well.quiz.MainActivity
import com.co_well.quiz.R
import com.co_well.quiz.domain.entity.FlashCard
import com.co_well.quiz.ui.activity.interf.OnLearnClick

class SearchFragment : Fragment(), OnLearnClick {

    private lateinit var searchViewModel: SearchViewModel
    private lateinit var recyclerView1: RecyclerView
    private lateinit var recyclerView2: RecyclerView
    private lateinit var recyclerView3: RecyclerView
    private lateinit var recyclerView4: RecyclerView
    private lateinit var adapter1: CardSearchAdapter
    private lateinit var adapter2: CardSearchAdapter
    private lateinit var adapter3: CardSearchAdapter
    private lateinit var adapter4: CardSearchAdapter
    private lateinit var textView1: TextView
    private lateinit var textView2: TextView
    private lateinit var textView3: TextView
    private lateinit var textView4: TextView

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView1 = view.findViewById(R.id.recycler_view_1)
        recyclerView2 = view.findViewById(R.id.recycler_view_2)
        recyclerView3 = view.findViewById(R.id.recycler_view_3)
        recyclerView4 = view.findViewById(R.id.recycler_view_4)
        textView1 = view.findViewById(R.id.tv1)
        textView2 = view.findViewById(R.id.tv2)
        textView3 = view.findViewById(R.id.tv3)
        textView4 = view.findViewById(R.id.tv4)
        adapter1 = CardSearchAdapter(this)
        adapter2 = CardSearchAdapter(this)
        adapter3 = CardSearchAdapter(this)
        adapter4 = CardSearchAdapter(this)

        searchViewModel = SearchViewModel((activity as MainActivity).getRankCardUseCase)
        recyclerView1.apply {
            layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL,false)
            adapter = adapter1
        }

        recyclerView2.apply {
            layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL,false)
            adapter = adapter2
        }

        recyclerView3.apply {
            layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL,false)
            adapter = adapter3
        }

        recyclerView4.apply {
            layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL,false)
            adapter = adapter4
        }
    }

    override fun onResume() {
        super.onResume()
        var actionBar = (activity as MainActivity).supportActionBar
        actionBar!!.title = "Hello"
        actionBar.setHomeButtonEnabled(true)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        searchViewModel.getRank(1)
        searchViewModel.getRank(2)
        searchViewModel.getRank(3)
        searchViewModel.getRank(4)

        searchViewModel.listFlashCard1.observe(viewLifecycleOwner, Observer { adapter1.addListSetCard(it) })
        searchViewModel.listFlashCard2.observe(viewLifecycleOwner, Observer { adapter2.addListSetCard(it) })
        searchViewModel.listFlashCard3.observe(viewLifecycleOwner, Observer { adapter3.addListSetCard(it) })
        searchViewModel.listFlashCard4.observe(viewLifecycleOwner, Observer { adapter4.addListSetCard(it) })

        searchViewModel.tv1.observe(viewLifecycleOwner, Observer { textView1.text = it })
        searchViewModel.tv2.observe(viewLifecycleOwner, Observer { textView2.text = it })
        searchViewModel.tv3.observe(viewLifecycleOwner, Observer { textView3.text = it })
        searchViewModel.tv4.observe(viewLifecycleOwner, Observer { textView4.text = it })
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
        TODO("Not yet implemented")
    }

    override fun buttonFullScreenClick() {
        TODO("Not yet implemented")
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.search_menu,menu)
    }
}