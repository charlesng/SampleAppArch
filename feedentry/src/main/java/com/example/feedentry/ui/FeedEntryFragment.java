package com.example.feedentry.ui;


import android.Manifest;
import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.ViewModelProviders;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.feedentry.R;
import com.example.feedentry.databinding.FragmentItemListBinding;
import com.example.feedentry.viewmodel.FeedEntryListViewModel;

/**
 * A fragment representing a list of Items.
 * <p />
 * Activities containing this fragment MUST implement the {@link }
 * interface.
 */
public class FeedEntryFragment extends LifecycleFragment {

  // TODO: Customize parameters
  private int mColumnCount = 1;
  private FragmentItemListBinding binding;
  private FeedEntryRecyclerViewRecyclerViewAdapter adapter;

  /**
   * Mandatory empty constructor for the fragment manager to instantiate the
   * fragment (e.g. upon screen orientation changes).
   */
  public FeedEntryFragment() {
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
    FeedEntryListViewModel viewModel = ViewModelProviders.of(getActivity())
        .get(FeedEntryListViewModel.class);
    viewModel.getFeedEntrys().observe(this, entries -> {
      RecyclerView recyclerView = binding.list;
      //update the data
      adapter = new FeedEntryRecyclerViewRecyclerViewAdapter(entries,
          item -> Toast.makeText(getActivity(), item.getTitle(), Toast.LENGTH_SHORT).show());
      recyclerView.setAdapter(adapter);
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
        .inflate(inflater, R.layout.fragment_item_list, null, false);
    // Set the adapter

    RecyclerView recyclerView = binding.list;
    if (mColumnCount <= 1) {
      recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    } else {
      recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), mColumnCount));
    }
    return binding.getRoot();
  }


}
