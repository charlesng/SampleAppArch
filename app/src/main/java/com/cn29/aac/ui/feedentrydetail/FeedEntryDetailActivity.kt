package com.cn29.aac.ui.feedentrydetail

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import com.cn29.aac.databinding.ActivityFeedEntryDetailBinding
import com.cn29.aac.databinding.DialogFeedentryBinding
import com.cn29.aac.repo.feedentry.FeedEntry
import com.cn29.aac.ui.feedentrydetail.vm.FeedEntryDetailViewModel
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.Single
import io.reactivex.SingleEmitter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FeedEntryDetailActivity : DaggerAppCompatActivity() {
    @JvmField
    @Inject
    var viewModel: FeedEntryDetailViewModel? = null

    @JvmField
    @Inject
    var uid = 0

    @JvmField
    @Inject
    var feedEntry: FeedEntry? = null

    @JvmField
    @Inject
    var binding: ActivityFeedEntryDetailBinding? = null

    @JvmField
    @Inject
    var dialogFeedEntryBinding: DialogFeedentryBinding? = null

    @SuppressLint("StaticFieldLeak")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding!!.toolbar)
        viewModel!!.getFeedEntry(uid).observe(this,
                                              Observer { feedEntry: FeedEntry? ->
                                                  this.feedEntry = feedEntry
                                                  binding!!.feedEntry = this.feedEntry
                                              })
        binding!!.feedEntry = FeedEntry("Testing Feed Title",
                                        "Testing Feed Sub Title",
                                        false,
                                        null,
                                        0)
        binding!!.imageUrl = "http://i.imgur.com/DvpvklR.png"
        binding!!.collapsingToolbar.title = "Feed Entry Title"
        binding!!.fab.setOnClickListener { view: View? ->
            //insert sample data by button click
            dialogFeedEntryBinding!!.feedEntry = feedEntry
            AlertDialog.Builder(
                            this@FeedEntryDetailActivity)
                    .setTitle(
                            "Create a new Feed Entry")
                    .setView(
                            dialogFeedEntryBinding!!.root)
                    .setPositiveButton(
                            "Submit"
                    ) { dialogInterface: DialogInterface, i: Int ->
                        val inputFeedEntry = dialogFeedEntryBinding!!.feedEntry
                        val validations = arrayOf(
                                dialogFeedEntryBinding!!.imageUrlValidation.isErrorEnabled,
                                dialogFeedEntryBinding!!.titleValidation.isErrorEnabled,
                                dialogFeedEntryBinding!!.subTitleValidation.isErrorEnabled
                        )
                        var isValid = true
                        for (validation in validations) {
                            if (validation) {
                                isValid = false
                            }
                        }
                        if (isValid) {
                            Single.create { _: SingleEmitter<Any?>? ->
                                        viewModel!!.update(
                                                inputFeedEntry)
                                    }
                                    .subscribeOn(
                                            Schedulers.newThread())
                                    .observeOn(
                                            AndroidSchedulers.mainThread())
                                    .subscribe { result: Any? ->
                                        binding!!.feedEntry = inputFeedEntry
                                    }
                            dialogInterface.dismiss()
                        }
                    }
                    .create()
                    .show()
        }
    }

    companion object {
        const val EXTRA_POSITION = "position"
    }
}