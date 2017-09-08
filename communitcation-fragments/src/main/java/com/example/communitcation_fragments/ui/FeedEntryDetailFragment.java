package com.example.communitcation_fragments.ui;

import android.app.Activity;
import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.communitcation_fragments.R;
import com.example.communitcation_fragments.repository.DummyContent;
import com.example.communitcation_fragments.viewmodel.MasterDetailShareViewModel;

/**
 * A fragment representing a single FeedEntry detail screen.
 * This fragment is either contained in a {@link FeedEntryListActivity}
 * in two-pane mode (on tablets) or a {@link FeedEntryDetailActivity}
 * on handsets.
 */
public class FeedEntryDetailFragment extends LifecycleFragment {

  /**
   * The fragment argument representing the item ID that this fragment
   * represents.
   */
  public static final String ARG_ITEM_ID = "item_id";

  /**
   * The dummy content this fragment is presenting.
   */
  private DummyContent.DummyItem mItem;
  private MasterDetailShareViewModel masterDetailShareViewModel;
  private View rootView;

  /**
   * Mandatory empty constructor for the fragment manager to instantiate the
   * fragment (e.g. upon screen orientation changes).
   */
  public FeedEntryDetailFragment() {
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    masterDetailShareViewModel = ViewModelProviders.of(getActivity()).get(MasterDetailShareViewModel.class);

    masterDetailShareViewModel.getSelectFeedEntry().observe(this, s -> {
      Activity activity = getActivity();
      CollapsingToolbarLayout appBarLayout = activity
          .findViewById(R.id.toolbar_layout);
      if (appBarLayout != null) {
        appBarLayout.setTitle(mItem.content);
      }
      ((TextView) rootView.findViewById(R.id.feedentry_detail)).setText(mItem.details);
    });
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if (getArguments().containsKey(ARG_ITEM_ID)) {
      // Load the dummy content specified by the fragment
      // arguments. In a real-world scenario, use a Loader
      // to load content from a content provider.
      mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    rootView = inflater.inflate(R.layout.feedentry_detail, container, false);
    return rootView;
  }
}
