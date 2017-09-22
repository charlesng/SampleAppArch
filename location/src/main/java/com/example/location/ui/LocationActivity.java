package com.example.location.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import com.example.location.R;
import com.example.location.util.InjectUtils;
import com.example.location.viewmodel.LastLocationViewModel;
import com.example.location.viewmodel.LastLocationViewModelFactory;

public class LocationActivity extends AppCompatActivity {

  private LastLocationViewModel lastLocationViewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_location);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    LastLocationViewModelFactory factory = InjectUtils.provideLastLocationViewModelFactory(this);
    lastLocationViewModel = ViewModelProviders.of(this, factory)
        .get(LastLocationViewModel.class);
  }
}
