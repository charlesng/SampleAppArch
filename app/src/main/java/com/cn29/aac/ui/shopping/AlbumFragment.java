package com.cn29.aac.ui.shopping;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.cn29.aac.databinding.FragmentAlbumBinding;
import com.cn29.aac.di.scope.AndroidDataBinding;
import com.cn29.aac.di.scope.ViewModel;
import com.cn29.aac.repo.itunes.Album;
import com.cn29.aac.ui.base.BaseFragment;
import com.cn29.aac.ui.common.BaseRecyclerViewAdapter.OnItemClickListener;
import com.cn29.aac.ui.shopping.vm.AlbumFragmentViewModel;
import com.cn29.aac.ui.shopping.vm.ShoppingActivityViewModel;
import java.util.List;
import javax.inject.Inject;

public class AlbumFragment extends BaseFragment {

  @Inject
  @ViewModel
  AlbumFragmentViewModel viewModel;

  @Inject
  @ViewModel
  ShoppingActivityViewModel shoppingActivityViewModel;

  @Inject
  @AndroidDataBinding
  FragmentAlbumBinding binding;


  private AlbumAdapter adapter;

  private List<Album> albums;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    int artistId = 909253; //default value
    String entity = "album"; //default value
    if (getArguments() != null && getArguments().containsKey("artistId") && getArguments()
        .containsKey("entity")) {
      artistId = getArguments().getInt("artistId");
      entity = getArguments().getString("entity");
    }
    viewModel.getAlbumResult(artistId, entity).observe(this, response ->
    {
      switch (response.status) {
        case CHECKING:
          break;
        case SUCCESS:
          progressDialogComponent.hideLoading();
          albums = response.data;
          adapter = new AlbumAdapter(albums,
              getAlbumOnItemClickListener());
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

  @NonNull
  private OnItemClickListener<Album> getAlbumOnItemClickListener() {
    return item -> {
      //add to the kart
      Toast.makeText(getActivity(), "Implement your own", Toast.LENGTH_SHORT).show();
    };
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return binding.getRoot();
  }

}
