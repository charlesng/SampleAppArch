package com.example.feedentry.ui;

import android.support.v7.widget.RecyclerView;
import com.example.feedentry.R;
import com.example.feedentry.databinding.FragmentItemBinding;
import com.example.feedentry.repository.bean.FeedEntry;
import com.example.feedentry.ui.common.BaseRecyclerViewAdapter;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link FeedEntry} and makes a call to the
 * specified {@link }.
 * TODO: Replace the implementation with code for your data type.
 */
public class FeedEntryRecyclerViewRecyclerViewAdapter extends
    BaseRecyclerViewAdapter<FeedEntry, FragmentItemBinding> {

  private final List<FeedEntry> mValues;

  FeedEntryRecyclerViewRecyclerViewAdapter(List<FeedEntry> mValues,
      OnItemClickListener<FeedEntry> itemClickListener) {
    super(itemClickListener);
    this.mValues = mValues;
  }

  @Override
  protected FeedEntry getItemForPosition(int position) {
    return mValues.get(position);
  }

  @Override
  public int getItemCount() {
    return mValues.size();
  }

  @Override
  protected int getLayoutIdForPosition(int position) {
    return R.layout.fragment_item;
  }

  @Override
  protected void bind(FragmentItemBinding binding, FeedEntry item) {
    binding.setFeedEntry(item);
  }
}
