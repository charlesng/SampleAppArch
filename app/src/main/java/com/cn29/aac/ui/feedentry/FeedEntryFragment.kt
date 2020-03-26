package com.cn29.aac.ui.feedentry

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.cn29.aac.R
import com.cn29.aac.databinding.FragmentFeedentryListBinding
import com.cn29.aac.repo.feedentry.FeedEntry
import com.cn29.aac.ui.base.BaseFragment
import com.cn29.aac.ui.common.BaseRecyclerViewAdapter
import com.cn29.aac.ui.feedentry.FeedEntryAdapter.MyMenuItemClickListener
import com.cn29.aac.ui.feedentry.vm.FeedEntryListViewModel
import com.cn29.aac.ui.feedentry.vm.FeedEntryListViewModel.CompositeModel
import com.cn29.aac.ui.feedentrydetail.FeedEntryDetailActivity
import io.reactivex.Single
import io.reactivex.SingleEmitter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * A fragment representing a list of Items.
 *
 *
 * Activities containing this fragment MUST implement the []
 * interface.
 */
class FeedEntryFragment : BaseFragment() {
    @JvmField
    @Inject
    var viewModel: FeedEntryListViewModel? = null

    @JvmField
    @Inject
    var binding: FragmentFeedentryListBinding? = null
    private var mode = Mode.LIST
    private var adapter: FeedEntryAdapter? = null
    fun setMode(mode: Mode) {
        this.mode = mode
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel!!.compositeEntrys.observe(viewLifecycleOwner,
                                            Observer { entries: CompositeModel? ->
                                                //update the data
                                                if (adapter == null) {
                                                    val feedEntryOnItemClickListener = feedEntryOnItemClickListener
                                                    val menuItemClickListener = myMenuItemClickListener
                                                    adapter = FeedEntryAdapter(
                                                            entries!!,
                                                            feedEntryOnItemClickListener,
                                                            menuItemClickListener)
                                                    binding!!.list.adapter = adapter
                                                } else {
                                                    adapter!!.notifyDataSetChanged()
                                                }
                                            })
    }

    private val myMenuItemClickListener: MyMenuItemClickListener
        get() = object : MyMenuItemClickListener() {
            override fun onMenuItemClick(item: MenuItem?,
                                         feedEntry: FeedEntry?): Boolean {
                val menuId = item!!.itemId
                if (menuId == R.id.action_entry_delete) {
                    AlertDialog.Builder(binding!!.root
                                                .context)
                            .setTitle("Warning")
                            .setMessage("Are you sure to delete the Feed Entry?")
                            .setPositiveButton("OK"
                            ) { _: DialogInterface?, _: Int ->
                                Single.create { _: SingleEmitter<Any?>? ->
                                            viewModel!!.delete(
                                                    feedEntry)
                                        }
                                        .subscribeOn(
                                                Schedulers.newThread())
                                        .observeOn(
                                                AndroidSchedulers.mainThread())
                                        .subscribe()
                            }
                            .setNegativeButton("Cancel"
                            ) { dialogInterface: DialogInterface, _: Int -> dialogInterface.dismiss() }
                            .create()
                            .show()
                } else if (menuId == R.id.action_entry_favourite) {
                    feedEntry!!.isFavourite = !feedEntry.isFavourite
                    Single.create { _: SingleEmitter<Any?>? ->
                                viewModel!!.update(feedEntry)
                            }
                            .subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe()
                }
                return false
            }
        }

    private val feedEntryOnItemClickListener: BaseRecyclerViewAdapter.OnItemClickListener<FeedEntry?>
        get() = object :
                BaseRecyclerViewAdapter.OnItemClickListener<FeedEntry?> {
            override fun onItemClick(item: FeedEntry?) {

                val intent = Intent(
                        activity,
                        FeedEntryDetailActivity::class.java)
                intent.putExtra(
                        FeedEntryDetailActivity.EXTRA_POSITION,
                        item!!.uid)
                startActivity(
                        intent)
            }
        }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Set the adapter
        when (mode) {
            Mode.GRID -> binding!!.list.layoutManager = GridLayoutManager(
                    activity, 4)
            Mode.LIST -> binding!!.list.layoutManager = LinearLayoutManager(
                    activity)
            Mode.TILE -> binding!!.list.layoutManager = GridLayoutManager(
                    activity, 2)
        }
        return binding!!.root
    }

    enum class Mode {
        LIST, GRID, TILE
    }
}