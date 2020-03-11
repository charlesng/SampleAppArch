package com.cn29.aac.ui.feedentry;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.cn29.aac.R;
import com.cn29.aac.databinding.DialogFeedentryBinding;
import com.cn29.aac.repo.feedentry.FeedEntry;
import com.cn29.aac.ui.base.BaseAppCompatActivity;
import com.cn29.aac.ui.feedentry.FeedEntryFragment.Mode;
import com.cn29.aac.ui.feedentry.vm.FeedEntryListViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class FeedActivity extends BaseAppCompatActivity {

  @Inject
  FeedEntryListViewModel viewModel;

  @Inject
  DialogFeedentryBinding dialogFeedEntryBinding;

  @Inject
  FeedEntry inputFeedEntry;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_feed);
    ViewPager viewPager = findViewById(R.id.viewpager);
    setupViewPager(viewPager);
    // Set Tabs inside Toolbar
    TabLayout tabs = findViewById(R.id.tabs);
    tabs.setupWithViewPager(viewPager);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    FloatingActionButton fab = findViewById(R.id.fab);
    fab.setOnClickListener(view -> {
      //insert sample data by button click
        FeedEntry feedEntry = new FeedEntry("", "", false, null, 0);
      feedEntry.imageUrl = "http://i.imgur.com/DvpvklR.png";
      dialogFeedEntryBinding.setFeedEntry(feedEntry);
      final Dialog dialog = getDialog();
      dialog.show();
    });
  }

  @NonNull
  private Dialog getDialog() {
    final AlertDialog dialog = new AlertDialog.Builder(FeedActivity.this)
        .setTitle("Create a new Feed Entry")
        .setView(dialogFeedEntryBinding.getRoot())
        .setPositiveButton("Submit", null)
        .setNegativeButton("Cancel", (dialogInterface, i) -> dialogInterface.dismiss())
        .create();
    dialog.setOnShowListener(dialogInterface -> {
      Button button = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
      button.setOnClickListener(view1 -> {
        // TODO Do something
        //to trigger auto error enable
        //get the binding
        inputFeedEntry = dialogFeedEntryBinding.getFeedEntry();
        Boolean[] validations = new Boolean[]{
            dialogFeedEntryBinding.imageUrlValidation.isErrorEnabled(),
            dialogFeedEntryBinding.titleValidation.isErrorEnabled(),
            dialogFeedEntryBinding.subTitleValidation.isErrorEnabled()
        };
        boolean isValid = true;
        //check the validation
        for (Boolean validation : validations) {
          if (validation) {
            isValid = false;
            break;
          }
        }
        //insert the record
        if (isValid) {
          Single.create(subscriber -> viewModel.insert(inputFeedEntry))
              .subscribeOn(Schedulers.newThread())
              .observeOn(AndroidSchedulers.mainThread())
              .subscribe();
          dialogInterface.dismiss();
        }
      });
    });
    return dialog;
  }

  private void setupViewPager(ViewPager viewPager) {
    Adapter adapter = new Adapter(getSupportFragmentManager());
    FeedEntryFragment fragment1 = new FeedEntryFragment();
    FeedEntryFragment fragment2 = new FeedEntryFragment();
    FeedEntryFragment fragment3 = new FeedEntryFragment();
    fragment1.setMode(Mode.LIST);
    fragment2.setMode(Mode.TILE);
    fragment3.setMode(Mode.GRID);
    adapter.addFragment("List");
    adapter.addFragment("Tile");
    adapter.addFragment("Grid");
    viewPager.setAdapter(adapter);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_feed, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_updateDefaultTitle) {
      viewModel.loadUserId("10233221");
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  private static class Adapter extends FragmentStatePagerAdapter {

    private final List<String> mFragmentTitleList = new ArrayList<>();

    Adapter(FragmentManager manager) {
      super(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
      switch (position) {
        case 0:
          FeedEntryFragment fragment1 = new FeedEntryFragment();
          fragment1.setMode(Mode.LIST);
          return fragment1;
        case 1:
          FeedEntryFragment fragment2 = new FeedEntryFragment();
          fragment2.setMode(Mode.TILE);
          return fragment2;
        case 2:
          FeedEntryFragment fragment3 = new FeedEntryFragment();
          fragment3.setMode(Mode.GRID);
          return fragment3;
        default:
          break;
      }
      return new FeedEntryFragment();
    }

    @Override
    public int getCount() {
      return 3;
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
