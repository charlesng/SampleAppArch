package com.cn29.aac.ui.feedentry;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import com.cn29.aac.R;
import com.cn29.aac.databinding.DialogFeedentryBinding;
import com.cn29.aac.repo.feedentry.FeedEntry;
import com.cn29.aac.ui.feedentry.FeedEntryFragment.Mode;
import com.cn29.aac.ui.feedentry.vm.FeedEntryListViewModel;
import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class FeedActivity extends DaggerAppCompatActivity {

  @Inject
  FeedEntryListViewModel viewModel;


  @SuppressLint("StaticFieldLeak")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_feed);
    ViewPager viewPager = findViewById(R.id.viewpager);
    setupViewPager(viewPager);
    // Set Tabs inside Toolbar
    TabLayout tabs = findViewById(R.id.tabs);
    tabs.setupWithViewPager(viewPager);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    FloatingActionButton fab = findViewById(R.id.fab);
    fab.setOnClickListener(view -> {
      final DialogFeedentryBinding dialogFeedEntryBinding = DataBindingUtil
          .inflate(LayoutInflater.from(FeedActivity.this), R.layout.dialog_feedentry, null, false);
      //insert sample data by button click
      FeedEntry feedEntry = new FeedEntry("", "");
      feedEntry.setImageUrl("http://i.imgur.com/DvpvklR.png");
      dialogFeedEntryBinding.setFeedEntry(feedEntry);
      final Dialog dialog = new AlertDialog.Builder(FeedActivity.this)
          .setTitle("Create a new Feed Entry")
          .setView(dialogFeedEntryBinding.getRoot())
          .setPositiveButton("Submit", null)
          .setNegativeButton("Cancel", (dialogInterface, i) -> dialogInterface.dismiss())
          .create();
      dialog.setOnShowListener(dialogInterface -> {
        Button button = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
        button.setOnClickListener(view1 -> {
          // TODO Do something
          //to trigger auto error enable
          //get the binding
          FeedEntry inputFeedEntry = dialogFeedEntryBinding.getFeedEntry();
          Boolean[] validations = new Boolean[]{
              dialogFeedEntryBinding.imageUrlValidation.isErrorEnabled(),
              dialogFeedEntryBinding.titleValidation.isErrorEnabled(),
              dialogFeedEntryBinding.subTitleValidation.isErrorEnabled()
          };
          boolean isValid = true;
          //check the validation
          for (Boolean validation : validations) {
            if (validation) {
              isValid = false;
            }
          }
          //insert the record
          if (isValid) {
            Single.create(subscriber -> viewModel.insert(inputFeedEntry))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
            dialogInterface.dismiss();
          }
        });
      });
      dialog.show();

    });
  }

  private void setupViewPager(ViewPager viewPager) {
    Adapter adapter = new Adapter(getSupportFragmentManager());
    FeedEntryFragment fragment1 = new FeedEntryFragment();
    FeedEntryFragment fragment2 = new FeedEntryFragment();
    FeedEntryFragment fragment3 = new FeedEntryFragment();
    fragment1.setMode(Mode.LIST);
    fragment2.setMode(Mode.TILE);
    fragment3.setMode(Mode.GRID);
    adapter.addFragment(fragment1, "List");
    adapter.addFragment(fragment2, "Tile");
    adapter.addFragment(fragment3, "Grid");
    viewPager.setAdapter(adapter);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_feed, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_updateDefaultTitle) {
      viewModel.loadUserId("10233221");
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  private static class Adapter extends FragmentPagerAdapter {

    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    Adapter(FragmentManager manager) {
      super(manager);
    }

    @Override
    public Fragment getItem(int position) {
      return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
      return mFragmentList.size();
    }

    void addFragment(Fragment fragment, String title) {
      mFragmentList.add(fragment);
      mFragmentTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
      return mFragmentTitleList.get(position);
    }
  }


}
