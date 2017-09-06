package com.example.feedentry.ui;

import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
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
import com.example.feedentry.datasources.room.AppDatabase;
import com.example.feedentry.datasources.room.FeedEntryDAO;
import com.example.feedentry.repository.bean.FeedEntry;

public class FeedActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_feed);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    FloatingActionButton fab = findViewById(R.id.fab);

    fab.setOnClickListener(view -> {
      //insert sample data by button click
      final DialogFeedentryBinding binding = DataBindingUtil.inflate(LayoutInflater.from(this),R.layout.dialog_feedentry,null,false);
      new AlertDialog.Builder(FeedActivity.this)
          .setTitle("Create a new Feed Entry")
          .setView(binding.getRoot())
          .setPositiveButton("OK", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
          }).create().show();
//      new AsyncTask() {
//
//        @Override
//        protected Void doInBackground(FeedEntry... feedEntries) {
//          AppDatabase db = Room.databaseBuilder(FeedActivity.this,
//              AppDatabase.class, "feedentry-db").build();
//          FeedEntryDAO feedEntryDAO = db.feedEntryDao();
//          feedEntryDAO.insertAll(feedEntries);
//          return null;
//        }
//      }.execute(new FeedEntry("Testing", "Testing2"));
    });
  }

}
