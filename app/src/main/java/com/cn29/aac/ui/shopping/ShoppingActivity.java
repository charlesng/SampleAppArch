package com.cn29.aac.ui.shopping;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SearchRecentSuggestions;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.SearchView;
import android.widget.Toast;
import com.cn29.aac.R;
import com.cn29.aac.databinding.ActivityShoppingBinding;
import com.cn29.aac.di.scope.AndroidDataBinding;
import com.cn29.aac.di.scope.ViewModel;
import com.cn29.aac.ui.base.BaseAppCompatActivity;
import com.cn29.aac.ui.shopping.vm.ShoppingActivityViewModel;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class ShoppingActivity extends BaseAppCompatActivity {

  @Inject
  @AndroidDataBinding
  ActivityShoppingBinding binding;

  @Inject
  @ViewModel
  ShoppingActivityViewModel viewModel;


  private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
      = item -> {
    switch (item.getItemId()) {

      case R.id.navigation_popular_albums:
        binding.viewpager.setCurrentItem(0);
        return true;
      case R.id.navigation_artists:
        binding.viewpager.setCurrentItem(1);
        return true;
    }
    return false;
  };
  private SearchView searchView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setSupportActionBar(binding.toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    binding.navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    setupViewPager(binding.viewpager);
    handleIntent(getIntent());

  }


  @Override
  protected void onNewIntent(Intent intent) {
    handleIntent(intent);
  }

  private void handleIntent(Intent intent) {
    if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
      String query = intent.getStringExtra(SearchManager.QUERY);
      //set the text on the search view without submission
      searchView.setQuery(query, false);
      searchView.clearFocus();
      //save search result
      SearchRecentSuggestions suggestions = new SearchRecentSuggestions(this,
          ArtistsSearchRecentSuggestionsProvider.AUTHORITY,
          ArtistsSearchRecentSuggestionsProvider.MODE);
      suggestions.saveRecentQuery(query, null);
      viewModel.query(query);
      //use the query to search your data somehow
      Toast.makeText(this, query, Toast.LENGTH_SHORT).show();
    }
  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.options_menu, menu);
    // Associate searchable configuration with the SearchView
    SearchManager searchManager =
        (SearchManager) getSystemService(Context.SEARCH_SERVICE);
    searchView = (SearchView) menu.findItem(R.id.search).getActionView();
    searchView.setSearchableInfo(
        searchManager.getSearchableInfo(getComponentName()));
    searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default
    searchView.onActionViewExpanded();
    return true;
  }

  /*
  TODO
  Bind the view pager and the bottom navigation bar
   */
  private void setupViewPager(ViewPager viewPager) {
    Adapter adapter = new Adapter(getSupportFragmentManager());
    adapter.addFragment("Popular Albums");
    adapter.addFragment("Artists");

    viewPager.setAdapter(adapter);
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
          return new AlbumFragment();
        case 1:
          return new ArtistFragment();
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
