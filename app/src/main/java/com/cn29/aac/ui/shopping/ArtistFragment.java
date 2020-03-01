package com.cn29.aac.ui.shopping;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cn29.aac.databinding.FragmentArtistBinding;
import com.cn29.aac.di.scope.AndroidDataBinding;
import com.cn29.aac.di.scope.ViewModel;
import com.cn29.aac.repo.itunes.Artist;
import com.cn29.aac.ui.base.BaseFragment;
import com.cn29.aac.ui.common.BaseRecyclerViewAdapter.OnItemClickListener;
import com.cn29.aac.ui.shopping.vm.ArtistFragmentViewModel;
import com.cn29.aac.ui.shopping.vm.ShoppingActivityViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.recyclerview.widget.GridLayoutManager;

public class ArtistFragment extends BaseFragment {

  @Inject
  @ViewModel
  ArtistFragmentViewModel viewModel;

  @Inject
  @ViewModel
  ShoppingActivityViewModel shoppingActivityViewModel;

  @Inject
  @AndroidDataBinding
  FragmentArtistBinding binding;

  private ArtistAdapter adapter;

  private List<Artist> list = new ArrayList<>();

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    shoppingActivityViewModel.getQueryData().observe(this, query -> {
          viewModel.getArtist(query).observe(this, response -> {
            switch (response.status) {
              case CHECKING:
                break;
              case SUCCESS:
                progressDialogComponent.hideLoading();
                list = response.data;
                OnItemClickListener<Artist> feedEntryOnItemClickListener = getFeedEntryOnItemClickListener();
                adapter = new ArtistAdapter(list,
                    feedEntryOnItemClickListener);
                binding.list.setAdapter(adapter);
                break;
              case ERROR:
                progressDialogComponent.hideLoading();
                break;
              case LOADING:
                progressDialogComponent.showLoading();
                break;
            }
          });
        }
    );
  }

  private OnItemClickListener<Artist> getFeedEntryOnItemClickListener() {
    return item ->
    {
      //open the artist detail
      Intent intent = new Intent(ArtistFragment.this.getActivity(), ArtistDetailActivity.class);
      intent.putExtra("artist", item);
      startActivity(intent);
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
