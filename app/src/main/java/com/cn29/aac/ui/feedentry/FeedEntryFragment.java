package com.cn29.aac.ui.feedentry;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.cn29.aac.R;
import com.cn29.aac.databinding.FragmentFeedentryListBinding;
import com.cn29.aac.repo.feedentry.FeedEntry;
import com.cn29.aac.ui.base.BaseFragment;
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

  Mode mode;

  private FragmentFeedentryListBinding binding;
  private FeedEntryAdapter adapter;


  public void setMode(Mode mode) {
    this.mode = mode;
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    //get the view model from the activity
    if (ContextCompat.checkSelfPermission(getActivity(),
        Manifest.permission.INTERNET)
        != PackageManager.PERMISSION_GRANTED) {
      // Should we show an explanation?
      if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
          Manifest.permission.INTERNET)) {
        // Show an explanation to the user *asynchronously* -- don't block
        // this thread waiting for the user's response! After the user
        // sees the explanation, try again to request the permission.

      } else {

        // No explanation needed, we can request the permission.

        ActivityCompat.requestPermissions(getActivity(),
            new String[]{Manifest.permission.INTERNET},
            200);

        // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
        // app-defined int constant. The callback method gets the
        // result of the request.
      }
    }
    viewModel.getCompositeEntrys().observe(this, entries -> {
      //update the data
      if (adapter == null) {
        adapter = new FeedEntryAdapter(entries,
            item -> {
              Intent intent = new Intent(getActivity(), FeedEntryDetailActivity.class);
              intent.putExtra(FeedEntryDetailActivity.EXTRA_POSITION, item.getUid());
              startActivity(intent);
            }, new MyMenuItemClickListener() {
          @SuppressLint("StaticFieldLeak")
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
        });
        binding.list.setAdapter(adapter);
      } else {
        adapter.notifyDataSetChanged();
      }
    });
  }

  @Override
  public void onRequestPermissionsResult(int requestCode,
      String permissions[], int[] grantResults) {
    switch (requestCode) {
      case 200: {
        // If request is cancelled, the result arrays are empty.
        if (grantResults.length > 0
            && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
          Toast.makeText(getActivity(), "Rights Granted", Toast.LENGTH_SHORT).show();
          // permission was granted, yay! Do the
          // contacts-related task you need to do.
        } else {
          // permission denied, boo! Disable the
          // functionality that depends on this permission.
        }
        return;
      }

      // other 'case' lines to check for other
      // permissions this app might request
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    binding = DataBindingUtil
        .inflate(inflater, R.layout.fragment_feedentry_list, null, false);
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
