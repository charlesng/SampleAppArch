package com.cn29.aac.ui.shopping;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.cn29.aac.databinding.ActivityArtistDetailBinding;
import com.cn29.aac.di.scope.AndroidDataBinding;
import com.cn29.aac.di.scope.ViewModel;
import com.cn29.aac.repo.itunes.Artist;
import com.cn29.aac.ui.base.BaseAppCompatActivity;
import com.cn29.aac.ui.shopping.vm.ArtistDetailActivityViewModel;
import com.google.android.material.snackbar.Snackbar;

import javax.inject.Inject;

import androidx.core.app.NavUtils;
import androidx.fragment.app.FragmentManager;

public class ArtistDetailActivity extends BaseAppCompatActivity {

  @Inject
  @ViewModel
  ArtistDetailActivityViewModel viewModel;


  @Inject
  @AndroidDataBinding
  ActivityArtistDetailBinding binding;

  @Inject
  Artist artist;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding.toolbar.setTitle(artist.artistName);
    setSupportActionBar(binding.toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    binding.setArtist(artist);
    binding.fab.setOnClickListener(
        this::addToKart);
    FragmentManager manager = getSupportFragmentManager();
    AlbumFragment fragment = new AlbumFragment();
    Bundle bundle = new Bundle();
    bundle.putInt("artistId", (int) artist.artistId);
    bundle.putString("entity", "album");
    fragment.setArguments(bundle);
    manager.beginTransaction()
        .add(binding.container.getId(), fragment)
        .commit();

  }

  private void addToKart(View view) {
    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        .setAction("Action", null).show();
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      // Respond to the action bar's Up/Home button
      case android.R.id.home:
        NavUtils.navigateUpFromSameTask(this);
        return true;
    }
    return super.onOptionsItemSelected(item);
  }

}
