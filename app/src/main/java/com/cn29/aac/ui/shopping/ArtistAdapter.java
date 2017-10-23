package com.cn29.aac.ui.shopping;

import com.cn29.aac.R;
import com.cn29.aac.databinding.ItemArtistGridBinding;
import com.cn29.aac.repo.itunes.Artist;
import com.cn29.aac.ui.common.BaseRecyclerViewAdapter;
import java.util.List;


public class ArtistAdapter extends
    BaseRecyclerViewAdapter<Artist, ItemArtistGridBinding> {

  private List<Artist> mValues;


  ArtistAdapter(List<Artist> compositeModel,
      OnItemClickListener<Artist> itemClickListener) {
    super(itemClickListener);
    this.mValues = compositeModel;
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
    return R.layout.item_artist_grid;
  }

  @Override
  protected void bind(ItemArtistGridBinding binding, Artist item) {
    binding.setArtist(item);
  }

}
