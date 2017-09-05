package com.example.feedentry.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;
import com.example.feedentry.R;
import com.example.feedentry.repository.bean.FeedEntry;
import com.example.feedentry.ui.ItemFragment.OnListFragmentInteractionListener;

public class FeedActivity extends AppCompatActivity implements OnListFragmentInteractionListener {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_feed);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    FloatingActionButton fab = findViewById(R.id.fab);
    fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        .setAction("Action", null).show());
  }

  @Override
  public void onListFragmentInteraction(FeedEntry item) {
    Toast.makeText(this, item.content  , Toast.LENGTH_SHORT).show();
  }
}
