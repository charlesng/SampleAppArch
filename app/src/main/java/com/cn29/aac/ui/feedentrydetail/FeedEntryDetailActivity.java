package com.cn29.aac.ui.feedentrydetail;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.cn29.aac.databinding.ActivityFeedEntryDetailBinding;
import com.cn29.aac.databinding.DialogFeedentryBinding;
import com.cn29.aac.repo.feedentry.FeedEntry;
import com.cn29.aac.ui.feedentrydetail.vm.FeedEntryDetailViewModel;

import javax.inject.Inject;

import androidx.appcompat.app.AlertDialog.Builder;
import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class FeedEntryDetailActivity extends DaggerAppCompatActivity {

  public static final String EXTRA_POSITION = "position";
  @Inject
  FeedEntryDetailViewModel viewModel;

  @Inject
  int uid;

  @Inject
  FeedEntry feedEntry;

  @Inject
  ActivityFeedEntryDetailBinding binding;

  @Inject
  DialogFeedentryBinding dialogFeedEntryBinding;


  @SuppressLint("StaticFieldLeak")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setSupportActionBar(binding.toolbar);
    viewModel.getFeedEntry(uid).observe(this, feedEntry -> {
      this.feedEntry = feedEntry;
      binding.setFeedEntry(this.feedEntry);
    });
      binding.setFeedEntry(new FeedEntry("Testing Feed Title", "Testing Feed Sub Title", false, null, 0));
    binding.setImageUrl("http://i.imgur.com/DvpvklR.png");
    binding.collapsingToolbar.setTitle("Feed Entry Title");
    binding.fab.setOnClickListener(view -> {
      //insert sample data by button click
      dialogFeedEntryBinding.setFeedEntry(this.feedEntry);
      new Builder(FeedEntryDetailActivity.this)
          .setTitle("Create a new Feed Entry")
          .setView(dialogFeedEntryBinding.getRoot())
          .setPositiveButton("Submit", (dialogInterface, i) -> {
            FeedEntry inputFeedEntry = dialogFeedEntryBinding.getFeedEntry();
            Boolean[] validations = new Boolean[]{
                dialogFeedEntryBinding.imageUrlValidation.isErrorEnabled(),
                dialogFeedEntryBinding.titleValidation.isErrorEnabled(),
                dialogFeedEntryBinding.subTitleValidation.isErrorEnabled()
            };
            boolean isValid = true;
            for (Boolean validation : validations) {
              if (validation) {
                isValid = false;
              }
            }
            if (isValid) {
              Single.create(subscriber -> viewModel.update(inputFeedEntry))
                  .subscribeOn(Schedulers.newThread())
                  .observeOn(AndroidSchedulers.mainThread())
                  .subscribe(result -> binding.setFeedEntry(inputFeedEntry));
              dialogInterface.dismiss();
            }
          }).create().show();
    });
  }

}
