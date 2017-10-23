package com.cn29.aac.ui.shopping;


import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.cn29.aac.databinding.FragmentArtistBinding;
import com.cn29.aac.repo.itunes.Artist;
import com.cn29.aac.ui.base.BaseFragment;
import com.cn29.aac.ui.common.BaseRecyclerViewAdapter.OnItemClickListener;
import com.cn29.aac.ui.shopping.vm.ArtistFragmentViewModel;
import javax.inject.Inject;

public class ArtistFragment extends BaseFragment {

  @Inject
  ArtistFragmentViewModel viewModel;

  @Inject
  FragmentArtistBinding binding;


  private ArtistAdapter adapter;


  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    viewModel.getArtist("jackson").observe(this, response ->
    {
      switch (response.status) {
        case SUCCESS:
          if (adapter == null) {
            OnItemClickListener<Artist> feedEntryOnItemClickListener = getFeedEntryOnItemClickListener();
            adapter = new ArtistAdapter(response.data,
                feedEntryOnItemClickListener);
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

  private OnItemClickListener<Artist> getFeedEntryOnItemClickListener() {
    return item ->
    {
      //open the artist detail
      Toast.makeText(getActivity(), "Testing", Toast.LENGTH_SHORT).show();
    };
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    binding.list.setLayoutManager(new GridLayoutManager(getActivity(), 4));
    return binding.getRoot();
  }

}
