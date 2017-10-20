package com.cn29.aac.ui.shopping;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.cn29.aac.databinding.FragmentAlbumBinding;
import com.cn29.aac.repo.itunes.Album;
import com.cn29.aac.ui.base.BaseFragment;
import com.cn29.aac.ui.common.BaseRecyclerViewAdapter.OnItemClickListener;
import com.cn29.aac.ui.shopping.vm.AlbumFragmentViewModel;
import javax.inject.Inject;

public class AlbumFragment extends BaseFragment {

  @Inject
  AlbumFragmentViewModel viewModel;

  @Inject
  FragmentAlbumBinding binding;


  private AlbumAdapter adapter;


  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    viewModel.getAlbum(909253, "album").observe(this, response ->
    {
      switch (response.status) {
        case SUCCESS:
          if (adapter == null) {
            adapter = new AlbumAdapter(response.data,
                getAlbumOnItemClickListener());
            binding.list.setAdapter(adapter);
          } else {
            adapter.notifyDataSetChanged();
          }
          break;
        case ERROR:
          break;
        case LOADING:
          break;
      }

    });
  }

  @NonNull
  private OnItemClickListener<Album> getAlbumOnItemClickListener() {
    return item -> {

    };
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return binding.getRoot();
  }

}
