package com.example.communitcation_fragments.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import com.example.communitcation_fragments.R;
import com.example.communitcation_fragments.ui.masterdetail.SimpleListActivity;
import com.example.communitcation_fragments.ui.viewpager.PagerActivity;

public class CommunicationFragmentActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_communication_fragment);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    Button btnViewPager = findViewById(R.id.btn_ViewPager);
    Button btnMasterDetail = findViewById(R.id.btn_MasterDetail);
    btnViewPager.setOnClickListener(v -> {
      Intent intent = new Intent(CommunicationFragmentActivity.this, PagerActivity.class);
      startActivity(intent);
    });
    btnMasterDetail.setOnClickListener(v -> {
      Intent intent = new Intent(CommunicationFragmentActivity.this, SimpleListActivity.class);
      startActivity(intent);
    });
  }
}
