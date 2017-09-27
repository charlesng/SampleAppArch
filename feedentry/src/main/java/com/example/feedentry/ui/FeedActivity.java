package com.example.feedentry.ui;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
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
import android.widget.Button;
import com.example.feedentry.R;
import com.example.feedentry.databinding.DialogFeedentryBinding;
import com.example.feedentry.repository.bean.FeedEntry;
import com.example.feedentry.repository.bean.FeedEntryRepository;
import com.example.feedentry.ui.FeedEntryFragment.Mode;
import com.example.feedentry.viewmodel.FeedEntryListViewModel;
import com.example.feedentry.viewmodel.FeedEntryListViewModelFactory;
import dagger.Module;
import dagger.Provides;
import dagger.android.support.DaggerAppCompatActivity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

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
          FeedEntry inputFeedEntry = dialogFeedEntryBinding.getFeedEntry();
          Boolean[] validations = new Boolean[]{
              dialogFeedEntryBinding.imageUrlValidation.isErrorEnabled(),
              dialogFeedEntryBinding.titleValidation.isErrorEnabled(),
              dialogFeedEntryBinding.subTitleValidation.isErrorEnabled()
          };
          boolean isValid = true;
          for (Boolean validation : validations) {
            if (validation) {
              isValid = false;
            }
          }
          if (isValid) {
            new AsyncTask<FeedEntry, Void, Void>() {
              @Override
              protected Void doInBackground(FeedEntry... feedEntries) {
                viewModel.insert(feedEntries);
                return null;
              }
            }.execute(inputFeedEntry);
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

  /**
   * Created by Charles Ng on 26/9/2017.
   */

  @Module
  @Singleton
  public static class MyModule {

    @Provides
    FeedEntryListViewModelFactory provideFeedEntryListViewModel(
        FeedEntryRepository feedEntryRepository) {
          /*
    Use Code lab injection reference example
    https://codelabs.developers.google.com/codelabs/build-app-with-arch-components/index.html?index=..%2F..%2Findex#10
     */
      return new FeedEntryListViewModelFactory(feedEntryRepository);
    }

    @Provides
    FeedEntryListViewModel provideViewModel(FeedActivity feedActivity,
        FeedEntryListViewModelFactory factory) {
      return ViewModelProviders.of(feedActivity, factory)
          .get(FeedEntryListViewModel.class);
    }

  }
}
