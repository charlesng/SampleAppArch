package com.cn29.aac.ui.location;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import com.cn29.aac.R;
import com.cn29.aac.ui.base.BaseAppCompatActivity;
import com.cn29.aac.ui.location.vm.LastLocationViewModel;
import javax.inject.Inject;

public class LocationActivity extends BaseAppCompatActivity {

  @Inject
  LastLocationViewModel lastLocationViewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_location);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
  }


}
