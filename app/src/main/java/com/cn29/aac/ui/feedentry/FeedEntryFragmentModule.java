package com.cn29.aac.ui.feedentry;

import com.cn29.aac.R;
import com.cn29.aac.databinding.FragmentFeedentryListBinding;
import com.cn29.aac.ui.feedentry.vm.FeedEntryListViewModel;
import com.cn29.aac.ui.feedentry.vm.FeedEntryListViewModelFactory;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import dagger.Module;
import dagger.Provides;

@Module
public class FeedEntryFragmentModule {

  @Provides
  FeedEntryListViewModel provideViewModel(FeedEntryFragment feedEntryFragment,
      FeedEntryListViewModelFactory factory) {
    return ViewModelProviders.of(feedEntryFragment.getActivity(), factory)
        .get(FeedEntryListViewModel.class);
  }

  @Provides
  FragmentFeedentryListBinding provideDataBinding(FeedEntryFragment feedEntryFragment) {

    return DataBindingUtil
        .inflate(feedEntryFragment.getActivity().getLayoutInflater(),
            R.layout.fragment_feedentry_list, null,
            false);
  }

}