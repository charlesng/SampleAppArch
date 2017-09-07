package com.example.feedentry.ui;


import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

  // TODO: Customize parameter argument names
  private static final String ARG_COLUMN_COUNT = "column-count";
  // TODO: Customize parameters
  private int mColumnCount = 2;
  private FragmentItemListBinding binding;
  private FeedEntryRecyclerViewRecyclerViewAdapter adapter;

  /**
   * Mandatory empty constructor for the fragment manager to instantiate the
   * fragment (e.g. upon screen orientation changes).
   */
  public FeedEntryFragment() {
  }

  // TODO: Customize parameter initialization
  @SuppressWarnings("unused")
  public static FeedEntryFragment newInstance(int columnCount) {
    FeedEntryFragment fragment = new FeedEntryFragment();
    Bundle args = new Bundle();
    args.putInt(ARG_COLUMN_COUNT, columnCount);
    fragment.setArguments(args);
    return fragment;
  }


  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    //get the view model from the activity
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
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
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
