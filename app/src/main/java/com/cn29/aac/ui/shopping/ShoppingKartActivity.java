package com.cn29.aac.ui.shopping;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import com.cn29.aac.R;
import com.cn29.aac.databinding.ActivityShoppingKartBinding;
import com.cn29.aac.ui.base.BaseAppCompatActivity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class ShoppingKartActivity extends BaseAppCompatActivity {

  @Inject
  ActivityShoppingKartBinding binding;


  private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
      = item -> {
    switch (item.getItemId()) {
      case R.id.navigation_artists:
        binding.viewpager.setCurrentItem(0);
        return true;
      case R.id.navigation_albums:
        binding.viewpager.setCurrentItem(1);
        return true;
    }
    return false;
  };


  private void setupViewPager(ViewPager viewPager) {
    Adapter adapter = new Adapter(getSupportFragmentManager());
    adapter.addFragment("Artists");
    adapter.addFragment("Albums");
    viewPager.setAdapter(adapter);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding.navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    setupViewPager(binding.viewpager);
  }

  private static class Adapter extends FragmentStatePagerAdapter {

    private final List<String> mFragmentTitleList = new ArrayList<>();

    Adapter(FragmentManager manager) {
      super(manager);
    }

    @Override
    public Fragment getItem(int position) {
      switch (position) {
        case 0:
          return new ArtistFragment();
        case 1:
          return new AlbumFragment();
        default:
          break;
      }
      return new AlbumFragment();
    }

    @Override
    public int getCount() {
      return 2;
    }


    void addFragment(String title) {
      mFragmentTitleList.add(title);
    }


    @Override
    public CharSequence getPageTitle(int position) {
      return mFragmentTitleList.get(position);
    }
  }

}
