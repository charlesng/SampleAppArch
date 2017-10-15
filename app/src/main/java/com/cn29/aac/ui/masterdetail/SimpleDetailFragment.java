package com.cn29.aac.ui.masterdetail;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.cn29.aac.R;
import com.cn29.aac.ui.base.BaseFragment;
import com.cn29.aac.ui.masterdetail.vm.SimpleMasterDetailShareViewModel;
import javax.inject.Inject;


/**
 * A fragment representing a single FeedEntry detail screen.
 * This fragment is either contained in a {@link SimpleListActivity}
 * in two-pane mode (on tablets) or a {@link SimpleDetailActivity}
 * on handsets.
 */
public class SimpleDetailFragment extends BaseFragment {

  public static final String REPO_NAME = "repo_name";
  public static final String OWNER_NAME = "owner_name";
  @Inject
  SimpleMasterDetailShareViewModel masterDetailShareViewModel;

  private View rootView;
  private String repoName;
  private String ownerName;

  /**
   * Mandatory empty constructor for the fragment manager to instantiate the
   * fragment (e.g. upon screen orientation changes).
   */
  public SimpleDetailFragment() {
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments().containsKey(REPO_NAME) && getArguments().containsKey(OWNER_NAME)) {
      repoName = getArguments().getString(REPO_NAME);
      ownerName = getArguments().getString(OWNER_NAME);
    }
    masterDetailShareViewModel.loadRepos(ownerName, repoName).observe(this, repoResource -> {
      Activity activity = getActivity();
      CollapsingToolbarLayout appBarLayout = activity
          .findViewById(R.id.toolbar_layout);
      TextView titleView = rootView
          .findViewById(R.id.feedentry_detail);
      assert repoResource != null;
      switch (repoResource.status) {
        case SUCCESS:
          progressDialogComponent.hideLoading();
          assert repoResource.data != null;
          if (appBarLayout != null) {
            appBarLayout.setTitle(repoResource.data.name);
          }
          titleView.setText(repoResource.data.fullName);
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

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    rootView = inflater.inflate(R.layout.fragment_simple_detail, container, false);
    return rootView;
  }
}
