package com.cn29.aac.ui.viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import com.cn29.aac.R
import com.cn29.aac.ui.viewpager.vm.PagerAgentViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class BlankFragmentB : DaggerFragment() {
    @Inject
    lateinit var pagerAgentViewModel: PagerAgentViewModel
    private var textView: TextView? = null
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //setup the listener for the fragment B
        val observer = Observer { msg: String? -> textView!!.text = msg }
        pagerAgentViewModel.getMessageContainerB()
                ?.observe(viewLifecycleOwner, observer)
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_blank_b,
                                    container,
                                    false)
        textView = view.findViewById(R.id.fragment_textB)
        val button = view.findViewById<Button>(
                R.id.btnB)
        button.setOnClickListener { _: View? ->
            pagerAgentViewModel.sendMessageToA(
                    "Hello A")
        }
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(): BlankFragmentB {
            return BlankFragmentB()
        }
    }
}