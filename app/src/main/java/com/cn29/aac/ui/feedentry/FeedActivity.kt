package com.cn29.aac.ui.feedentry

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.cn29.aac.R
import com.cn29.aac.databinding.DialogFeedentryBinding
import com.cn29.aac.repo.feedentry.FeedEntry
import com.cn29.aac.ui.base.BaseAppCompatActivity
import com.cn29.aac.ui.feedentry.vm.FeedEntryListViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import io.reactivex.Single
import io.reactivex.SingleEmitter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class FeedActivity : BaseAppCompatActivity() {
    @JvmField
    @Inject
    var viewModel: FeedEntryListViewModel? = null

    @JvmField
    @Inject
    var dialogFeedEntryBinding: DialogFeedentryBinding? = null

    @JvmField
    @Inject
    var inputFeedEntry: FeedEntry? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)
        val viewPager = findViewById<ViewPager>(R.id.viewpager)
        setupViewPager(viewPager)
        // Set Tabs inside Toolbar
        val tabs = findViewById<TabLayout>(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
        val toolbar = findViewById<Toolbar>(
                R.id.toolbar)
        setSupportActionBar(toolbar)
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            //insert sample data by button click
            val feedEntry = FeedEntry(
                    "",
                    "",
                    false,
                    null,
                    0)
            feedEntry.imageUrl = "http://i.imgur.com/DvpvklR.png"
            dialogFeedEntryBinding!!.feedEntry = feedEntry
            val dialog = dialog
            dialog.show()
        }
    }

    // TODO Do something
    //to trigger auto error enable
    //get the binding
    //check the validation
    //insert the record
    private val dialog: Dialog
        get() {
            val dialog = AlertDialog.Builder(
                            this@FeedActivity)
                    .setTitle("Create a new Feed Entry")
                    .setView(dialogFeedEntryBinding!!.root)
                    .setPositiveButton("Submit", null)
                    .setNegativeButton("Cancel"
                    ) { dialogInterface: DialogInterface, _: Int -> dialogInterface.dismiss() }
                    .create()
            dialog.setOnShowListener { dialogInterface: DialogInterface ->
                val button = dialog.getButton(
                        AlertDialog.BUTTON_POSITIVE)
                button.setOnClickListener {
                    inputFeedEntry = dialogFeedEntryBinding!!.feedEntry
                    val validations = arrayOf(
                            dialogFeedEntryBinding!!.imageUrlValidation.isErrorEnabled,
                            dialogFeedEntryBinding!!.titleValidation.isErrorEnabled,
                            dialogFeedEntryBinding!!.subTitleValidation.isErrorEnabled
                    )
                    var isValid = true
                    //check the validation
                    for (validation in validations) {
                        if (validation) {
                            isValid = false
                            break
                        }
                    }
                    //insert the record
                    if (isValid) {
                        Single.create { _: SingleEmitter<Any?>? ->
                                    viewModel?.insert(
                                            inputFeedEntry)
                                }
                                .subscribeOn(
                                        Schedulers.newThread())
                                .observeOn(
                                        AndroidSchedulers.mainThread())
                                .subscribe()
                        dialogInterface.dismiss()
                    }
                }
            }
            return dialog
        }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = Adapter(
                supportFragmentManager)
        val fragment1 = FeedEntryFragment()
        val fragment2 = FeedEntryFragment()
        val fragment3 = FeedEntryFragment()
        fragment1.setMode(FeedEntryFragment.Mode.LIST)
        fragment2.setMode(FeedEntryFragment.Mode.TILE)
        fragment3.setMode(FeedEntryFragment.Mode.GRID)
        adapter.addFragment("List")
        adapter.addFragment("Tile")
        adapter.addFragment("Grid")
        viewPager.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_feed, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId
        if (id == R.id.action_updateDefaultTitle) {
            viewModel!!.loadUserId("10233221")
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private class Adapter internal constructor(manager: FragmentManager?) :
            FragmentStatePagerAdapter(manager!!,
                                      BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        private val mFragmentTitleList: MutableList<String> = ArrayList()
        override fun getItem(position: Int): Fragment {
            when (position) {
                0 -> {
                    val fragment1 = FeedEntryFragment()
                    fragment1.setMode(FeedEntryFragment.Mode.LIST)
                    return fragment1
                }
                1 -> {
                    val fragment2 = FeedEntryFragment()
                    fragment2.setMode(FeedEntryFragment.Mode.TILE)
                    return fragment2
                }
                2 -> {
                    val fragment3 = FeedEntryFragment()
                    fragment3.setMode(FeedEntryFragment.Mode.GRID)
                    return fragment3
                }
                else -> {
                }
            }
            return FeedEntryFragment()
        }

        override fun getCount(): Int {
            return 3
        }

        fun addFragment(title: String) {
            mFragmentTitleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return mFragmentTitleList[position]
        }
    }
}