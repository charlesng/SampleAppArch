package com.cn29.aac.ui.masterdetail.vm;

import com.cn29.aac.repo.github.GitRepoRepository;

import javax.inject.Inject;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * Created by Charles Ng on 9/10/2017.
 */

public class SimpleMasterDetailShareViewModelFactory extends ViewModelProvider.NewInstanceFactory {

  GitRepoRepository gitRepoRepository;

  @Inject
  public SimpleMasterDetailShareViewModelFactory(GitRepoRepository gitRepoRepository) {
    this.gitRepoRepository = gitRepoRepository;
  }

  @Override
  public <T extends ViewModel> T create(Class<T> modelClass) {
    return (T) new SimpleMasterDetailShareViewModel(gitRepoRepository);
  }
}
