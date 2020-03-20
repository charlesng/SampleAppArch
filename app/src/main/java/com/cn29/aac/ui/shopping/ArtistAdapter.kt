package com.cn29.aac.ui.shopping

import com.cn29.aac.R
import com.cn29.aac.databinding.ItemArtistGridBinding
import com.cn29.aac.repo.itunes.Artist
import com.cn29.aac.ui.common.BaseRecyclerViewAdapter

class ArtistAdapter internal constructor(private val mValues: List<Artist>,
                                         itemClickListener: OnItemClickListener<Artist>) :
        BaseRecyclerViewAdapter<Artist, ItemArtistGridBinding>(
                itemClickListener) {
    override fun getItemForPosition(position: Int): Artist {
        return mValues[position]
    }

    override fun getItemCount(): Int {
        return mValues.size
    }

    override fun getLayoutIdForPosition(position: Int): Int {
        return R.layout.item_artist_grid
    }

    override fun bind(binding: ItemArtistGridBinding,
                      item: Artist) {
        binding.artist = item
    }

}