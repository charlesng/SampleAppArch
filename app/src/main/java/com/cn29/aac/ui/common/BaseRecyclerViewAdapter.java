package com.cn29.aac.ui.common;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;


/**
 * Created by Charles Ng on 6/9/2017.
 */

public abstract class BaseRecyclerViewAdapter<T, B extends ViewDataBinding>
    extends RecyclerView.Adapter<BaseRecyclerViewAdapter<T, B>.MyViewHolder<B>> {

  private final OnItemClickListener<T> itemClickListener;

  public BaseRecyclerViewAdapter(OnItemClickListener<T> itemClickListener) {
    this.itemClickListener = itemClickListener;
  }

  public BaseRecyclerViewAdapter<T, B>.MyViewHolder<B> onCreateViewHolder(ViewGroup parent,
      int viewType) {
    LayoutInflater layoutInflater =
        LayoutInflater.from(parent.getContext());
    B binding = DataBindingUtil.inflate(
        layoutInflater, viewType, parent, false);
    return new BaseRecyclerViewAdapter<T, B>.MyViewHolder<>(binding);
  }

  @Override
  public void onBindViewHolder(BaseRecyclerViewAdapter<T, B>.MyViewHolder<B> holder,
      int position) {
    final T item = getItemForPosition(position);
    holder.itemView.setOnClickListener(v -> itemClickListener.onItemClick(item));
    bind(holder.binding, item);
  }

  @Override
  public int getItemViewType(int position) {
    return getLayoutIdForPosition(position);
  }

  protected abstract T getItemForPosition(int position);

  protected abstract int getLayoutIdForPosition(int position);

  protected abstract void bind(B binding, T item);

  public interface OnItemClickListener<T> {

    void onItemClick(T item);
  }

  public class MyViewHolder<Binding extends ViewDataBinding> extends RecyclerView.ViewHolder {

    private final Binding binding;

    public MyViewHolder(Binding binding) {
      super(binding.getRoot());
      this.binding = binding;
    }


    public void bind(Binding binding, T item) {
      binding.executePendingBindings();
    }
  }
}