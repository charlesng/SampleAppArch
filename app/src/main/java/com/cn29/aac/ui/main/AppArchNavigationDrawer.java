package com.cn29.aac.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.cn29.aac.R;
import com.cn29.aac.databinding.ActivityAppArchNavigationDrawerBinding;
import com.cn29.aac.ui.base.BaseAppCompatActivity;
import com.cn29.aac.ui.feedentry.FeedActivity;
import com.cn29.aac.ui.location.LocationActivity;
import com.cn29.aac.ui.login.LoginActivity;
import com.cn29.aac.ui.main.vm.AppArchNavViewModel;
import com.cn29.aac.ui.masterdetail.SimpleListActivity;
import com.cn29.aac.ui.setting.SettingsActivity;
import com.cn29.aac.ui.shopping.ShoppingKartActivity;
import com.cn29.aac.ui.viewpager.PagerActivity;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;

public class AppArchNavigationDrawer extends BaseAppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {

  @Inject
  AppArchNavViewModel appArchNavViewModel;

  @Inject
  ActivityAppArchNavigationDrawerBinding binding;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    FloatingActionButton fab = findViewById(R.id.fab);
    fab.setOnClickListener(
        view -> Snackbar.make(view, "Want to set email ?", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show());
    DrawerLayout drawer = findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.addDrawerListener(toggle);
    toggle.syncState();
    NavigationView navigationView = findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);


  }

  @Override
  public void onBackPressed() {
    DrawerLayout drawer = findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.app_arch_navigation_drawer, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      startActivity(new Intent(this, SettingsActivity.class));
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @SuppressWarnings("StatementWithEmptyBody")
  @Override
  public boolean onNavigationItemSelected(@NonNull MenuItem item) {
    // Handle navigation view item clicks here.
    int id = item.getItemId();
    switch (id) {
      case R.id.nav_fragment_communication:
        startActivity(new Intent(this, SimpleListActivity.class));
        break;
      case R.id.nav_storage:
        startActivity(new Intent(this, FeedActivity.class));
        break;
      case R.id.nav_connectivity:
        startActivity(new Intent(this, PagerActivity.class));
        break;
      case R.id.nav_location:
        startActivity(new Intent(this, LocationActivity.class));
        break;
      case R.id.nav_shopping:
        startActivity(new Intent(this, ShoppingKartActivity.class));
        ;
        break;
      case R.id.nav_shopping_history:
        viewHistory();
        break;
      case R.id.nav_ordering:
        ordering();
        break;
      case R.id.nav_logout:
        logout();
        break;
    }
    DrawerLayout drawer = findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }

  private void viewHistory() {

  }

  private void ordering() {
  }

  private void logout() {
    progressDialogComponent.showLoading();
    //clear the data first
    appArchNavViewModel.logout();
    Single.timer(3, TimeUnit.SECONDS)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(s -> {
          //finish the current activity and go back to the login activity
          progressDialogComponent.hideLoading();
          finish();
          startActivity(new Intent(this, LoginActivity.class));
        });
  }
}
