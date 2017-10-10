package com.cn29.aac.ui.feedentry;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar.OnMenuItemClickListener;
import android.view.MenuItem;
import com.cn29.aac.R;
import com.cn29.aac.databinding.FragmentItemCardBinding;
import com.cn29.aac.repo.feedentry.FeedEntry;
import com.cn29.aac.ui.common.BaseRecyclerViewAdapter;
import com.cn29.aac.ui.feedentry.vm.FeedEntryListViewModel.CompositeModel;


/**
 * {@link RecyclerView.Adapter} that can display a {@link FeedEntry} and makes a call to the
 * specified {@link }.
 * TODO: Replace the implementation with code for your data type.
 */
public class FeedEntryRecyclerViewRecyclerViewAdapter extends
    BaseRecyclerViewAdapter<FeedEntry, FragmentItemCardBinding> {

  private CompositeModel mValues;
  private MyMenuItemClickListener myMenuItemClickListener;

  FeedEntryRecyclerViewRecyclerViewAdapter(CompositeModel compositeModel,
      OnItemClickListener<FeedEntry> itemClickListener,
      MyMenuItemClickListener myMenuItemClickListener) {
    super(itemClickListener);
    this.mValues = compositeModel;
    this.myMenuItemClickListener = myMenuItemClickListener;
  }

  @Override
  protected FeedEntry getItemForPosition(int position) {
    return mValues.getFeedEntries().get(position);
  }

  @Override
  public int getItemCount() {
    return mValues.getFeedEntries().size();
  }

  @Override
  protected int getLayoutIdForPosition(int position) {
    return R.layout.fragment_item_card;
  }

  @Override
  protected void bind(FragmentItemCardBinding binding, FeedEntry item) {
    binding.setFeedEntry(item);
    binding.setImageUrl("http://i.imgur.com/DvpvklR.png");//default value
    binding.setUserId(mValues.getUserId());
    binding.toolbar.getMenu().clear();
    binding.toolbar.inflateMenu(R.menu.menu_feed_item_card);

    if (item.isFavourite()) {
      MenuItem favoriteItem = binding.toolbar.getMenu().findItem(R.id.action_entry_favourite);
      Drawable newIcon = favoriteItem.getIcon();
      newIcon.mutate()
          .setColorFilter(binding.getRoot().getContext().getResources().getColor(R.color.yellow),
              PorterDuff.Mode.SRC_IN);
    }
    binding.toolbar.setOnMenuItemClickListener(
        menuItem -> myMenuItemClickListener.onMenuItemClick(menuItem, item));
  }

  public static abstract class MyMenuItemClickListener implements OnMenuItemClickListener {

    @Override
    public boolean onMenuItemClick(MenuItem item) {
      return false;
    }

    public abstract boolean onMenuItemClick(MenuItem item, FeedEntry feedEntry);
  }
}
