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
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.widget.Button;
import com.example.feedentry.R;
import com.example.feedentry.databinding.DialogFeedentryBinding;
import com.example.feedentry.repository.bean.FeedEntry;
import com.example.feedentry.viewmodel.FeedEntryListViewModel;
import java.util.ArrayList;
import java.util.List;

public class FeedActivity extends AppCompatActivity {

  private FeedEntryListViewModel viewModel;

  @SuppressLint("StaticFieldLeak")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_feed);
    viewModel = ViewModelProviders.of(this)
        .get(FeedEntryListViewModel.class);
    viewModel.init(this);
    ViewPager viewPager = findViewById(R.id.viewpager);
    setupViewPager(viewPager);
    // Set Tabs inside Toolbar
    TabLayout tabs = findViewById(R.id.tabs);
    tabs.setupWithViewPager(viewPager);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    FloatingActionButton fab = findViewById(R.id.fab);
    fab.setOnClickListener(view -> {
      //insert sample data by button click
      final DialogFeedentryBinding dialogFeedEntryBinding = DataBindingUtil
          .inflate(LayoutInflater.from(this), R.layout.dialog_feedentry, null, false);
      dialogFeedEntryBinding.inputSubtitle.setError("Cannot be empty");
      dialogFeedEntryBinding.inputTitle.setError("Cannot be empty");
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
          FeedEntry inputFeedEntry = dialogFeedEntryBinding.getFeedEntry();
          if (!TextUtils.isEmpty(inputFeedEntry.getTitle().trim()) && !TextUtils
              .isEmpty(inputFeedEntry.getSubTitle().trim())) {
            new AsyncTask<FeedEntry, Void, Void>() {
              @Override
              protected Void doInBackground(FeedEntry... feedEntries) {
                viewModel.insert(feedEntries);
                return null;
              }
            }.execute(inputFeedEntry);
            dialogInterface.dismiss();
          } else {
            dialogFeedEntryBinding.titleValidation.setErrorEnabled(true);
            dialogFeedEntryBinding.inputTitle.setError("Cannot be empty");
          }
        });
      });
      dialog.show();

    });
  }

  private void setupViewPager(ViewPager viewPager) {
    Adapter adapter = new Adapter(getSupportFragmentManager());
    adapter.addFragment(new FeedEntryFragment(), "List");
    adapter.addFragment(new FeedEntryFragment(), "Tile");
    adapter.addFragment(new FeedEntryFragment(), "Grid");
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
}
