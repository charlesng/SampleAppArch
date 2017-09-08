package com.example.location.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import com.example.location.R;
import com.example.location.viewmodel.LastLocationViewModel;

public class LocationActivity extends AppCompatActivity {

  private LastLocationViewModel lastLocationViewModel;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_location);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    lastLocationViewModel = ViewModelProviders.of(this)
        .get(LastLocationViewModel.class);
    lastLocationViewModel.init(this);
  }
}
