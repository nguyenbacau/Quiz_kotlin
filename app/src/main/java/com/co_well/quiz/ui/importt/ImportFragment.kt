package com.co_well.quiz.ui.importt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.co_well.quiz.R

class ImportFragment : Fragment() {

    private lateinit var importViewModel: ImportViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        importViewModel =
                ViewModelProviders.of(this).get(ImportViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_import, container, false)
        val textView: TextView = root.findViewById(R.id.text_import)
        importViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}