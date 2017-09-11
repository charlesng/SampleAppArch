package com.example.feedentry.ui;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import com.example.feedentry.R;
import com.example.feedentry.databinding.ActivityFeedEntryDetailBinding;
import com.example.feedentry.databinding.DialogFeedentryBinding;
import com.example.feedentry.repository.bean.FeedEntry;
import com.example.feedentry.viewmodel.FeedEntryDetailViewModel;

public class FeedEntryDetailActivity extends AppCompatActivity implements LifecycleRegistryOwner {

  FeedEntryDetailViewModel viewModel;
  private final LifecycleRegistry mRegistry = new LifecycleRegistry(this);
  private ActivityFeedEntryDetailBinding binding;

  public static final String EXTRA_POSITION = "position";
  private FeedEntry feedEntry;

  @Override
  public LifecycleRegistry getLifecycle() {
    return mRegistry;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //get the uid from the parameter first
    int uid = getIntent().getIntExtra(EXTRA_POSITION, 1);
    viewModel = ViewModelProviders.of(this).get(FeedEntryDetailViewModel.class);
    viewModel.init(this);
    viewModel.getFeedEntry(uid).observe(this, feedEntry -> {
        this.feedEntry = feedEntry;
        binding.setFeedEntry(this.feedEntry);
    });
    binding = DataBindingUtil
        .inflate(getLayoutInflater(), R.layout.activity_feed_entry_detail, null, false);
    setContentView(binding.getRoot());
    setSupportActionBar(binding.toolbar);
    binding.setFeedEntry(new FeedEntry("Testing Feed Title", "Testing Feed Sub Title"));
    binding.setImageUrl("http://i.imgur.com/DvpvklR.png");
    binding.collapsingToolbar.setTitle("Feed Entry Title");
    new ProgressBar(FeedEntryDetailActivity.this).setVisibility(View.VISIBLE);
    binding.fab.setOnClickListener(view -> {
      //insert sample data by button click
      final DialogFeedentryBinding dialogFeedEntryBinding = DataBindingUtil
          .inflate(LayoutInflater.from(this), R.layout.dialog_feedentry, null, false);
      dialogFeedEntryBinding.setFeedEntry(this.feedEntry);
      new AlertDialog.Builder(FeedEntryDetailActivity.this)
          .setTitle("Create a new Feed Entry")
          .setView(dialogFeedEntryBinding.getRoot())
          .setPositiveButton("Submit", (dialogInterface, i) -> {
            FeedEntry inputFeedEntry = dialogFeedEntryBinding.getFeedEntry();
            if (!TextUtils.isEmpty(inputFeedEntry.getTitle().trim()) && !TextUtils
                .isEmpty(inputFeedEntry.getSubTitle().trim())) {
              new AsyncTask<FeedEntry, Void, Void>() {

                private ProgressBar progressBar;

                @Override
                protected void onPreExecute() {
                  super.onPreExecute();
                  progressBar = new ProgressBar(FeedEntryDetailActivity.this);
                  progressBar.setVisibility(View.VISIBLE);

                }

                @Override
                protected Void doInBackground(FeedEntry... feedEntries) {
                  viewModel.update(feedEntries[0]);
                  return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                  super.onPostExecute(aVoid);
                  progressBar.setVisibility(View.GONE);
                }
              }.execute(inputFeedEntry);
            }
          }).create().show();
    });
  }

}
