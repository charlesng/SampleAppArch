package com.cn29.aac.ui.feedentry

import android.graphics.PorterDuff
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.cn29.aac.R
import com.cn29.aac.databinding.ItemFeedentryCardBinding
import com.cn29.aac.repo.feedentry.FeedEntry
import com.cn29.aac.ui.common.BaseRecyclerViewAdapter
import com.cn29.aac.ui.feedentry.vm.FeedEntryListViewModel.CompositeModel

class FeedEntryAdapter internal constructor(private val mValues: CompositeModel,
                                            itemClickListener: OnItemClickListener<FeedEntry?>,
                                            private val myMenuItemClickListener: MyMenuItemClickListener) :
        BaseRecyclerViewAdapter<FeedEntry?, ItemFeedentryCardBinding>(
                itemClickListener) {
    override fun getItemForPosition(position: Int): FeedEntry {
        return mValues.feedEntries!![position]!!
    }

    override fun getItemCount(): Int {
        return mValues.feedEntries!!.size
    }

    override fun getLayoutIdForPosition(position: Int): Int {
        return R.layout.item_feedentry_card
    }

    override fun bind(binding: ItemFeedentryCardBinding,
                      item: FeedEntry?) {
        binding.feedEntry = item
        binding.imageUrl = "http://i.imgur.com/DvpvklR.png" //default value
        binding.userId = mValues.userId
        binding.toolbar.menu.clear()
        binding.toolbar.inflateMenu(R.menu.menu_feed_item_card)
        if (item?.isFavourite == true) {
            val favoriteItem = binding.toolbar.menu
                    .findItem(R.id.action_entry_favourite)
            val newIcon = favoriteItem.icon
            newIcon.mutate()
                    .setColorFilter(binding.root.context
                                            .resources
                                            .getColor(R.color.yellow),
                                    PorterDuff.Mode.SRC_IN)
        }
        binding.toolbar.setOnMenuItemClickListener { menuItem: MenuItem? ->
            myMenuItemClickListener.onMenuItemClick(
                    menuItem,
                    item)
        }
    }

    abstract class MyMenuItemClickListener :
            Toolbar.OnMenuItemClickListener {
        override fun onMenuItemClick(item: MenuItem): Boolean {
            return false
        }

        abstract fun onMenuItemClick(item: MenuItem?,
                                     feedEntry: FeedEntry?): Boolean
    }

}