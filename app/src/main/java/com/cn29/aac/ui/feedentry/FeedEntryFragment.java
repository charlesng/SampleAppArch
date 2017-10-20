package com.cn29.aac.ui.feedentry;


import static com.cn29.aac.ui.feedentry.FeedEntryFragment.Mode.LIST;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.cn29.aac.R;
import com.cn29.aac.databinding.FragmentFeedentryListBinding;
import com.cn29.aac.repo.feedentry.FeedEntry;
import com.cn29.aac.ui.base.BaseFragment;
import com.cn29.aac.ui.common.BaseRecyclerViewAdapter.OnItemClickListener;
import com.cn29.aac.ui.feedentry.FeedEntryAdapter.MyMenuItemClickListener;
import com.cn29.aac.ui.feedentry.vm.FeedEntryListViewModel;
import com.cn29.aac.ui.feedentrydetail.FeedEntryDetailActivity;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Inject;


/**
 * A fragment representing a list of Items.
 * <p />
 * Activities containing this fragment MUST implement the {@link }
 * interface.
 */
public class FeedEntryFragment extends BaseFragment {

  @Inject
  FeedEntryListViewModel viewModel;

  @Inject
  FragmentFeedentryListBinding binding;

  Mode mode = LIST;

  private FeedEntryAdapter adapter;

  public void setMode(Mode mode) {
    this.mode = mode;
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    viewModel.getCompositeEntrys().observe(this, entries -> {
      //update the data
      if (adapter == null) {
        OnItemClickListener<FeedEntry> feedEntryOnItemClickListener = getFeedEntryOnItemClickListener();
        MyMenuItemClickListener menuItemClickListener = getMyMenuItemClickListener();
        adapter = new FeedEntryAdapter(entries,
            feedEntryOnItemClickListener, menuItemClickListener);
        binding.list.setAdapter(adapter);
      } else {
        adapter.notifyDataSetChanged();
      }
    });
  }

  @NonNull
  private MyMenuItemClickListener getMyMenuItemClickListener() {
    return new MyMenuItemClickListener() {
      @Override
      public boolean onMenuItemClick(MenuItem item, FeedEntry feedEntry) {
        int menuId = item.getItemId();
        if (menuId == R.id.action_entry_delete) {
          new AlertDialog.Builder(binding.getRoot().getContext())
              .setTitle("Warning").setMessage("Are you sure to delete the Feed Entry?")
              .setPositiveButton("OK",
                  (dialogInterface, j) ->
                      Single.create(subscriber -> viewModel.delete(feedEntry))
                          .subscribeOn(Schedulers.newThread())
                          .observeOn(AndroidSchedulers.mainThread())
                          .subscribe()
              )
              .setNegativeButton("Cancel", (dialogInterface, i) -> dialogInterface.dismiss())
              .create()
              .show();
        } else if (menuId == R.id.action_entry_favourite) {
          feedEntry.setFavourite(!feedEntry.isFavourite());
          Single.create(subscriber -> viewModel.update(feedEntry))
              .subscribeOn(Schedulers.newThread())
              .observeOn(AndroidSchedulers.mainThread())
              .subscribe();
        }
        return false;
      }
    };
  }

  @NonNull
  private OnItemClickListener<FeedEntry> getFeedEntryOnItemClickListener() {
    return item -> {
      Intent intent = new Intent(getActivity(), FeedEntryDetailActivity.class);
      intent.putExtra(FeedEntryDetailActivity.EXTRA_POSITION, item.getUid());
      startActivity(intent);
    };
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Set the adapter
    switch (mode) {
      case GRID:
        binding.list.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        break;
      case LIST:
        binding.list.setLayoutManager(new LinearLayoutManager(getActivity()));
        break;
      case TILE:
        binding.list.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        break;
    }
    return binding.getRoot();
  }

  public enum Mode {
    LIST, GRID, TILE
  }

}
