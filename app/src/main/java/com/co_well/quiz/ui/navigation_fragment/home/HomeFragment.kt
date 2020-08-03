package com.co_well.quiz.ui.navigation_fragment.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.co_well.quiz.MainActivity
import com.co_well.quiz.R
import com.co_well.quiz.domain.entity.SetCard
import com.co_well.quiz.ui.activity.interf.OnSetClick
import com.co_well.quiz.ui.activity.learn.LearnActivity
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), OnSetClick {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel = HomeViewModel((activity as MainActivity).getAllSetUseCase)
        recycler_view_set.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = SetAdapter(this@HomeFragment)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        homeViewModel.getAllSetCard()
        homeViewModel.listSetCar.observe(viewLifecycleOwner, Observer {
            (recycler_view_set.adapter as SetAdapter).addListSetCard(it)
        })
    }

    override fun setClick(setCard: SetCard) {
        val intent = Intent(context, LearnActivity::class.java)
        intent.putExtra("set_name", setCard.name)
        startActivity(intent)
    }

}