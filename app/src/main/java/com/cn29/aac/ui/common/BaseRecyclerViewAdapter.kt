package com.cn29.aac.ui.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.cn29.aac.ui.common.BaseRecyclerViewAdapter.MyViewHolder

/**
 * Created by Charles Ng on 6/9/2017.
 */
abstract class BaseRecyclerViewAdapter<T, B : ViewDataBinding>(private val itemClickListener: OnItemClickListener<T>) :
        RecyclerView.Adapter<MyViewHolder<T, B>>() {
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MyViewHolder<T, B> {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding: B = DataBindingUtil.inflate(
                layoutInflater, viewType, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder<T, B>,
                                  position: Int) {
        val item = getItemForPosition(position)
        holder.itemView.setOnClickListener { v: View? ->
            itemClickListener.onItemClick(
                    item)
        }
        bind(holder.binding, item)
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutIdForPosition(position)
    }

    protected abstract fun getItemForPosition(position: Int): T
    protected abstract fun getLayoutIdForPosition(position: Int): Int
    protected abstract fun bind(binding: B,
                                item: T)

    interface OnItemClickListener<T> {
        fun onItemClick(item: T)
    }

    class MyViewHolder<T, Binding : ViewDataBinding>(val binding: Binding) :
            ViewHolder(binding.root) {
        fun bind(binding: Binding,
                 item: T) {
            binding.executePendingBindings()
        }

    }

}