package com.cn29.aac.ui.shopping;

import com.cn29.aac.R;
import com.cn29.aac.databinding.ItemAlbumListBinding;
import com.cn29.aac.repo.itunes.Album;
import com.cn29.aac.ui.common.BaseRecyclerViewAdapter;
import java.util.List;

/**
 * Created by Charles Ng on 20/10/2017.
 */

public class AlbumAdapter extends BaseRecyclerViewAdapter<Album, ItemAlbumListBinding> {

  private List<Album> values;

  public AlbumAdapter(List<Album> values, OnItemClickListener<Album> albumOnItemClickListener) {
    super(albumOnItemClickListener);
    this.values = values;
  }

  @Override
  protected Album getItemForPosition(int position) {
    return values.get(position);
  }

  @Override
  protected int getLayoutIdForPosition(int position) {
    return R.layout.item_album_list;
  }

  @Override
  protected void bind(ItemAlbumListBinding binding, Album item) {
    binding
        .setAlbum(item);
  }

  @Override
  public int getItemCount() {
    return values.size();
  }
}
