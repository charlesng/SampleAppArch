package com.cn29.aac.ui.location;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import com.cn29.aac.R;
import com.cn29.aac.ui.location.vm.LastLocationViewModel;
import com.cn29.aac.ui.location.vm.LastLocationViewModelFactory;
import com.cn29.aac.util.InjectUtils;

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
