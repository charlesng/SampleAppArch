package com.cn29.aac.ui.shoppingkart;

import android.os.Bundle;

import com.cn29.aac.databinding.ActivityShoppingKartBinding;
import com.cn29.aac.di.scope.AndroidDataBinding;
import com.cn29.aac.di.scope.ViewModel;
import com.cn29.aac.ui.base.BaseAppCompatActivity;
import com.cn29.aac.ui.shoppingkart.vm.ShoppingKartActivityViewModel;
import com.google.android.material.snackbar.Snackbar;

import javax.inject.Inject;

public class ShoppingKartActivity extends BaseAppCompatActivity {

  @Inject
  @ViewModel
  ShoppingKartActivityViewModel viewModel;

  @Inject
  @AndroidDataBinding
  ActivityShoppingKartBinding binding;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setSupportActionBar(binding.toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    binding.fab.setOnClickListener(
        view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show());
  }

}
