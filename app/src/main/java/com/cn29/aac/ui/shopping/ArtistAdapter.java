package com.cn29.aac.ui.shopping;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.Toolbar.OnMenuItemClickListener;
import android.view.MenuItem;
import com.cn29.aac.R;
import com.cn29.aac.databinding.ItemArtistCardBinding;
import com.cn29.aac.repo.itunes.Artist;
import com.cn29.aac.ui.common.BaseRecyclerViewAdapter;
import java.util.List;


public class ArtistAdapter extends
    BaseRecyclerViewAdapter<Artist, ItemArtistCardBinding> {

  private List<Artist> mValues;
  private MyMenuItemClickListener2<Artist> myMenuItemClickListener;

  ArtistAdapter(List<Artist> compositeModel,
      OnItemClickListener<Artist> itemClickListener,
      MyMenuItemClickListener2<Artist> myMenuItemClickListener) {
    super(itemClickListener);
    this.mValues = compositeModel;
    this.myMenuItemClickListener = myMenuItemClickListener;
  }

  @Override
  protected Artist getItemForPosition(int position) {
    return mValues.get(position);
  }

  @Override
  public int getItemCount() {
    return mValues.size();
  }

  @Override
  protected int getLayoutIdForPosition(int position) {
    return R.layout.item_artist_card;
  }

  @Override
  protected void bind(ItemArtistCardBinding binding, Artist item) {
    binding.setArtist(item);
    binding.toolbar.getMenu().clear();
    binding.toolbar.inflateMenu(R.menu.menu_artist_card);
    if (item.isFavourite()) {
      MenuItem favoriteItem = binding.toolbar.getMenu().findItem(R.id.action_artist_star);
      Drawable newIcon = favoriteItem.getIcon();
      newIcon.mutate()
          .setColorFilter(binding.getRoot().getContext().getResources().getColor(R.color.yellow),
              PorterDuff.Mode.SRC_IN);
    }
    binding.toolbar.setOnMenuItemClickListener(
        menuItem -> myMenuItemClickListener.onMenuItemClick(menuItem, item));
  }

  public static abstract class MyMenuItemClickListener2<Type> implements OnMenuItemClickListener {

    @Override
    public boolean onMenuItemClick(MenuItem item) {
      return false;
    }

    public abstract boolean onMenuItemClick(MenuItem item, Type feedEntry);
  }
}
