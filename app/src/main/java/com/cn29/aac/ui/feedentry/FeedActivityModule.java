package com.cn29.aac.ui.feedentry;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import com.cn29.aac.R;
import com.cn29.aac.databinding.DialogFeedentryBinding;
import com.cn29.aac.repo.feedentry.FeedEntry;
import com.cn29.aac.ui.feedentry.vm.FeedEntryListViewModel;
import com.cn29.aac.ui.feedentry.vm.FeedEntryListViewModelFactory;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;


@Module
@Singleton
public class FeedActivityModule {

  /**
   * Created by Charles Ng on 26/9/2017.
   */


  @Provides
  FeedEntryListViewModel provideViewModel(FeedEntry feedEntry, FeedActivity feedActivity,
      FeedEntryListViewModelFactory factory) {
    FeedEntryListViewModel viewModel = ViewModelProviders.of(feedActivity, factory)
        .get(FeedEntryListViewModel.class);
    viewModel.getFeedEntry().set(feedEntry);
    return viewModel;
  }


  @Provides
  DialogFeedentryBinding provideDialogBinding(FeedActivity activity,
      FeedEntryListViewModel feedEntryListViewModel) {
    DialogFeedentryBinding binding = DataBindingUtil
        .inflate(LayoutInflater.from(activity), R.layout.dialog_feedentry, null, false);
    binding.setFeedEntry(feedEntryListViewModel.getFeedEntry().get());
    return binding;
  }

  @Provides
  FeedEntry provideDefaultFeedEntry() {
    return new FeedEntry("abc", "");
  }

}