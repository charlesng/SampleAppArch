package com.cn29.aac.ui.viewpager

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.cn29.aac.R
import com.cn29.aac.ui.viewpager.vm.PagerAgentViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class PagerActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var pagerAgentViewModel: PagerAgentViewModel

    private var mPager: ViewPager? = null

    private var mPagerAdapter: PagerAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pager)
        val toolbar = findViewById<Toolbar>(
                R.id.toolbar)
        setSupportActionBar(toolbar)
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener { view: View? ->
            Snackbar.make(view!!,
                          "Replace with your own action",
                          Snackbar.LENGTH_LONG)
                    .setAction(
                            "Action",
                            null)
                    .show()
        }
        mPager = findViewById(R.id.pager)
        mPagerAdapter = ScreenSlidePagerAdapter(supportFragmentManager)
        mPager?.adapter = mPagerAdapter
    }

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private inner class ScreenSlidePagerAdapter(fm: FragmentManager) :
            FragmentStatePagerAdapter(fm,
                                      BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getItem(position: Int): Fragment {
            return when (position) {
                Companion.FRAGMENT_A_POS -> BlankFragmentA.newInstance()
                Companion.FRAGMENT_B_POS -> BlankFragmentB.newInstance()
                else -> BlankFragmentB.newInstance()
            }
        }

        override fun getCount(): Int {
            return Companion.FRAGMENT_COUNT
        }

    }

    companion object {
        const val FRAGMENT_A_POS = 0
        const val FRAGMENT_B_POS = 1
        const val FRAGMENT_COUNT = 2
    }
}