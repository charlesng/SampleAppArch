package com.cn29.aac.ui.shopping;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.cn29.aac.R;
import com.cn29.aac.databinding.FragmentArtistBinding;
import com.cn29.aac.repo.itunes.Artist;
import com.cn29.aac.ui.base.BaseFragment;
import com.cn29.aac.ui.common.BaseRecyclerViewAdapter.OnItemClickListener;
import com.cn29.aac.ui.shopping.ArtistAdapter.MyMenuItemClickListener2;
import com.cn29.aac.ui.shopping.vm.ArtistFragmentViewModel;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
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
                feedEntryOnItemClickListener, getMyMenuItemClickListener());
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
  private MyMenuItemClickListener2<Artist> getMyMenuItemClickListener() {
    return new MyMenuItemClickListener2<Artist>() {
      @Override
      public boolean onMenuItemClick(MenuItem item, Artist artist) {
        switch (item.getItemId()) {
          case R.id.action_artist_add_kart:
            //add to shopping kart
            break;
          case R.id.action_artist_star:
            //star the artist
            artist.setFavourite(!artist.isFavourite());
            Single.create(subscriber -> viewModel.starArtist(artist))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
            break;
          default:
            break;
        }
        return false;
      }
    };
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
    binding.list.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    return binding.getRoot();
  }

}
