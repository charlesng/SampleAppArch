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

class BlankFragmentA : DaggerFragment() {
    @Inject
    lateinit var pagerAgentViewModel: PagerAgentViewModel
    private var textView: TextView? = null
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //setup the listener for the fragment A
        val observer = Observer { msg: String? -> textView?.text = msg }
        pagerAgentViewModel.getMessageContainerA()
                ?.observe(viewLifecycleOwner, observer)
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_blank_a,
                                    container,
                                    false)
        textView = view.findViewById(R.id.fragment_textA)
        // set the onclick listener
        val button = view.findViewById<Button>(
                R.id.btnA)
        button.setOnClickListener {
            pagerAgentViewModel.sendMessageToB(
                    "Hello B")
        }
        return view
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(): BlankFragmentA {
            return BlankFragmentA()
        }
    }
}