package com.example.feedentry.ui;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import com.example.feedentry.R;
import com.example.feedentry.databinding.DialogFeedentryBinding;
import com.example.feedentry.repository.bean.FeedEntry;
import com.example.feedentry.viewmodel.FeedEntryListViewModel;

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
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    FloatingActionButton fab = findViewById(R.id.fab);
    fab.setOnClickListener(view -> {
      //insert sample data by button click
      final DialogFeedentryBinding dialogFeedEntryBinding = DataBindingUtil
          .inflate(LayoutInflater.from(this), R.layout.dialog_feedentry, null, false);
      dialogFeedEntryBinding.setFeedEntry(new FeedEntry("", ""));
      new AlertDialog.Builder(FeedActivity.this)
          .setTitle("Create a new Feed Entry")
          .setView(dialogFeedEntryBinding.getRoot())
          .setPositiveButton("Submit", (dialogInterface, i) -> {
            FeedEntry inputFeedEntry = dialogFeedEntryBinding.getFeedEntry();
            new AsyncTask<FeedEntry, Void, Void>() {
              @Override
              protected Void doInBackground(FeedEntry... feedEntries) {
                viewModel.insert(feedEntries);

                return null;
              }
            }.execute(inputFeedEntry);
          }).create().show();

    });
  }

}
