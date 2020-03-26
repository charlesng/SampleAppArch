package com.cn29.aac.ui.shopping

import com.cn29.aac.R
import com.cn29.aac.databinding.ItemAlbumListBinding
import com.cn29.aac.repo.itunes.Album
import com.cn29.aac.ui.common.BaseRecyclerViewAdapter


class AlbumAdapter(private val values: List<Album>,
                   albumOnItemClickListener: OnItemClickListener<Album>) :
        BaseRecyclerViewAdapter<Album, ItemAlbumListBinding>(
                albumOnItemClickListener) {
    override fun getItemForPosition(position: Int): Album {
        return values[position]
    }

    override fun getLayoutIdForPosition(position: Int): Int {
        return R.layout.item_album_list
    }

    override fun bind(binding: ItemAlbumListBinding,
                      item: Album) {
        binding.album = item
    }

    override fun getItemCount(): Int {
        return values.size
    }

}