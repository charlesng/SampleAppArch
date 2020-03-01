package com.cn29.aac.ui.masterdetail;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.cn29.aac.R;
import com.cn29.aac.ui.masterdetail.vm.SimpleMasterDetailShareViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import javax.inject.Inject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;
import dagger.android.support.DaggerAppCompatActivity;


/**
 * An activity representing a single FeedEntry detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link SimpleListActivity}.
 */
public class SimpleDetailActivity extends DaggerAppCompatActivity {

  @Inject
  SimpleMasterDetailShareViewModel masterDetailShareViewModel;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_simple_detail);
    Toolbar toolbar = findViewById(R.id.detail_toolbar);
    setSupportActionBar(toolbar);

    FloatingActionButton fab = findViewById(R.id.fab);
    fab.setOnClickListener(
        view -> Snackbar.make(view, "Replace with your own detail action", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show());

    // Show the Up button in the action bar.
    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(true);
    }

    // savedInstanceState is non-null when there is fragment state
    // saved from previous configurations of this activity
    // (e.g. when rotating the screen from portrait to landscape).
    // In this case, the fragment will automatically be re-added
    // to its container so we don't need to manually add it.
    // For more information, see the Fragments API guide at:
    //
    // http://developer.android.com/guide/components/fragments.html
    //
    if (savedInstanceState == null) {
      // Create the detail fragment and add it to the activity
      // using a fragment transaction.
      Bundle arguments = new Bundle();
      arguments.putString(SimpleDetailFragment.OWNER_NAME,
          getIntent().getStringExtra(SimpleDetailFragment.OWNER_NAME));
      arguments.putString(SimpleDetailFragment.REPO_NAME,
          getIntent().getStringExtra(SimpleDetailFragment.REPO_NAME));
      SimpleDetailFragment fragment = new SimpleDetailFragment();
      fragment.setArguments(arguments);
      getSupportFragmentManager().beginTransaction()
          .add(R.id.feedentry_detail_container, fragment)
          .commit();
    }
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == android.R.id.home) {
      // This ID represents the Home or Up button. In the case of this
      // activity, the Up button is shown. Use NavUtils to allow users
      // to navigate up one level in the application structure. For
      // more details, see the Navigation pattern on Android Design:
      //
      // http://developer.android.com/design/patterns/navigation.html#up-vs-back
      //
      NavUtils.navigateUpTo(this, new Intent(this, SimpleListActivity.class));
      return true;
    }
    return super.onOptionsItemSelected(item);
  }
}
