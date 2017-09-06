package com.example.feedentry.ui.common;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by Charles Ng on 6/9/2017.
 */

public abstract class BaseRecyclerViewAdapter<T, B extends  ViewDataBinding>
    extends RecyclerView.Adapter<BaseRecyclerViewAdapter<T,B>.MyViewHolder<B>> {

  public BaseRecyclerViewAdapter(OnItemClickListener<T> itemClickListener) {
    this.itemClickListener = itemClickListener;
  }

  public interface OnItemClickListener<T> {

    void onItemClick(T item);
  }

  private final OnItemClickListener<T> itemClickListener;

  public BaseRecyclerViewAdapter<T,B>.MyViewHolder<B> onCreateViewHolder(ViewGroup parent,
      int viewType) {
    LayoutInflater layoutInflater =
        LayoutInflater.from(parent.getContext());
    B binding = DataBindingUtil.inflate(
        layoutInflater, viewType, parent, false);
    return new BaseRecyclerViewAdapter<T,B>.MyViewHolder<>(binding);
  }

  @Override
  public void onBindViewHolder(BaseRecyclerViewAdapter<T,B>.MyViewHolder<B> holder,
      int position) {
    final T item = getItemForPosition(position);
    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        itemClickListener.onItemClick(item);
      }
    });
    bind(holder.binding,item);
//    holder.bind(holder.binding,item);
  }

  @Override
  public int getItemViewType(int position) {
    return getLayoutIdForPosition(position);
  }

  protected abstract T getItemForPosition(int position);

  protected abstract int getLayoutIdForPosition(int position);

  protected abstract void bind(B binding, T item);


  public  class MyViewHolder<Binding extends ViewDataBinding> extends RecyclerView.ViewHolder {

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