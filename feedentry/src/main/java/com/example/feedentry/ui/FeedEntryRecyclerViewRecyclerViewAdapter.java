package com.example.feedentry.ui;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar.OnMenuItemClickListener;
import com.example.feedentry.R;
import com.example.feedentry.databinding.FragmentItemCardBinding;
import com.example.feedentry.repository.bean.FeedEntry;
import com.example.feedentry.ui.common.BaseRecyclerViewAdapter;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link FeedEntry} and makes a call to the
 * specified {@link }.
 * TODO: Replace the implementation with code for your data type.
 */
public class FeedEntryRecyclerViewRecyclerViewAdapter extends
    BaseRecyclerViewAdapter<FeedEntry, FragmentItemCardBinding> {

  private final List<FeedEntry> mValues;
  private OnMenuItemClickListener onMenuItemClickListener;

  FeedEntryRecyclerViewRecyclerViewAdapter(List<FeedEntry> mValues,
      OnItemClickListener<FeedEntry> itemClickListener,
      OnMenuItemClickListener onMenuItemClickListener) {
    super(itemClickListener);
    this.mValues = mValues;
    this.onMenuItemClickListener = onMenuItemClickListener;
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
    return R.layout.fragment_item_card;
  }

  @Override
  protected void bind(FragmentItemCardBinding binding, FeedEntry item) {
    binding.setFeedEntry(item);
    binding.setImageUrl("http://i.imgur.com/DvpvklR.png");
    binding.toolbar.inflateMenu(R.menu.menu_feed_item_card);
    binding.toolbar.setOnMenuItemClickListener(onMenuItemClickListener);
  }
}
