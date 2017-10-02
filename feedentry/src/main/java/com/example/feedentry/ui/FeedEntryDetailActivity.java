package com.example.feedentry.ui;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import com.example.feedentry.R;
import com.example.feedentry.databinding.ActivityFeedEntryDetailBinding;
import com.example.feedentry.databinding.DialogFeedentryBinding;
import com.example.feedentry.repository.bean.FeedEntry;
import com.example.feedentry.repository.bean.FeedEntryRepository;
import com.example.feedentry.viewmodel.FeedEntryDetailViewModel;
import com.example.feedentry.viewmodel.FeedEntryDetailViewModelFactory;
import dagger.Module;
import dagger.Provides;
import dagger.android.support.DaggerAppCompatActivity;
import javax.inject.Inject;
import javax.inject.Singleton;

public class FeedEntryDetailActivity extends DaggerAppCompatActivity {

  @Inject
  FeedEntryDetailViewModel viewModel;
  @Inject
  int uid;

  private ActivityFeedEntryDetailBinding binding;

  public static final String EXTRA_POSITION = "position";
  private FeedEntry feedEntry;


  @SuppressLint("StaticFieldLeak")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    viewModel.getFeedEntry(uid).observe(this, feedEntry -> {
      this.feedEntry = feedEntry;
      binding.setFeedEntry(this.feedEntry);
    });
    binding = DataBindingUtil.setContentView(this, R.layout.activity_feed_entry_detail);
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
                  viewModel.update(feedEntries[0]);
                  return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                  super.onPostExecute(aVoid);
                  binding.setFeedEntry(inputFeedEntry);
                }
              }.execute(inputFeedEntry);
              dialogInterface.dismiss();
            }
          }).create().show();
    });
  }

  @Module
  @Singleton
  public static class MyModule {

    @Provides
    int getUid(FeedEntryDetailActivity activity)
    {
      return  activity.getIntent().getIntExtra(EXTRA_POSITION, 1);
    }

    @Provides
    FeedEntryDetailViewModelFactory provideFeedEntryDetailViewModel(FeedEntryDetailActivity activity, int uid,
        FeedEntryRepository feedEntryRepository) {
          /*
    Use Code lab injection reference example
    https://codelabs.developers.google.com/codelabs/build-app-with-arch-components/index.html?index=..%2F..%2Findex#10
     */
      return new FeedEntryDetailViewModelFactory(feedEntryRepository,uid);
    }

    @Provides
    FeedEntryDetailViewModel provideViewModel(FeedEntryDetailActivity feedActivity,
        FeedEntryDetailViewModelFactory factory) {
      return ViewModelProviders.of(feedActivity, factory)
          .get(FeedEntryDetailViewModel.class);
    }

  }

}
