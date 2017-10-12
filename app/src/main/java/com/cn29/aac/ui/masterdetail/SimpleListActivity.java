package com.cn29.aac.ui.masterdetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import com.cn29.aac.R;
import com.cn29.aac.ui.base.BaseAppCompatActivity;
import com.cn29.aac.ui.masterdetail.vm.SimpleMasterDetailShareViewModel;
import javax.inject.Inject;


/**
 * An activity representing a list of FeedEntrys. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link SimpleDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * https://developer.android.com/topic/libraries/architecture/viewmodel.html
 */
public class SimpleListActivity extends BaseAppCompatActivity {

  @Inject
  SimpleMasterDetailShareViewModel masterDetailShareViewModel;

  /**
   * Whether or not the activity is in two-pane mode, i.e. running on a tablet
   * device.
   */
  private boolean mTwoPane;

  private GithubRepoAdapter githubRepoAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_simple_list);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    toolbar.setTitle(getTitle());
    FloatingActionButton fab = findViewById(R.id.fab);
    fab.setOnClickListener(view -> loadRepos("googlesamples"));
    setupAdapter();
    loadRepos("googlesamples");
    RecyclerView recyclerView = findViewById(R.id.feedentry_list);
    assert recyclerView != null;
    recyclerView.setAdapter(githubRepoAdapter);
    if (findViewById(R.id.feedentry_detail_container) != null) {
      // The detail container view will be present only in the
      // large-screen layouts (res/values-w900dp).
      // If this view is present, then the
      // activity should be in two-pane mode.
      mTwoPane = true;
    }
  }

  private void loadRepos(String ownerName) {
    masterDetailShareViewModel
        .loadRepos(ownerName)
        .observe(SimpleListActivity.this, repos -> {
          assert repos != null;
          switch (repos.status) {
            case SUCCESS:
              progressDialogComponent.hideLoading();
              githubRepoAdapter.setRepos(repos.data);
              break;
            case ERROR:
              progressDialogComponent.hideLoading();
              break;
            case LOADING:
              progressDialogComponent.showLoading();
              break;
          }
          githubRepoAdapter.notifyDataSetChanged();
        });
  }

  private void setupAdapter() {
    githubRepoAdapter = new GithubRepoAdapter(
        repo -> {
          if (mTwoPane) {
            Bundle arguments = new Bundle();
            arguments.putString(SimpleDetailFragment.REPO_NAME, repo.name);
            arguments.putString(SimpleDetailFragment.OWNER_NAME, repo.owner.login);
            SimpleDetailFragment fragment = new SimpleDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                .replace(R.id.feedentry_detail_container, fragment)
                .commit();
          } else {
            Context context = SimpleListActivity.this;
            Intent intent = new Intent(context, SimpleDetailActivity.class);
            intent.putExtra(SimpleDetailFragment.REPO_NAME, repo.name);
            intent.putExtra(SimpleDetailFragment.OWNER_NAME, repo.owner.login);
            context.startActivity(intent);
          }
        });
  }
}
