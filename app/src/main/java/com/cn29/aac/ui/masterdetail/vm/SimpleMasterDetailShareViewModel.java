package com.cn29.aac.ui.masterdetail.vm;

import com.cn29.aac.repo.github.GitRepoRepository;
import com.cn29.aac.repo.github.Repo;
import com.cn29.aac.repo.util.Resource;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

/**
 * Created by charlesng0209 on 25/6/2017.
 */

public class SimpleMasterDetailShareViewModel extends ViewModel {

  private GitRepoRepository gitRepoRepository;

  public SimpleMasterDetailShareViewModel(GitRepoRepository gitRepoRepository) {
    this.gitRepoRepository = gitRepoRepository;
  }

  public LiveData<Resource<Repo>> loadRepos(String owner, String name) {
    return gitRepoRepository.loadRepo(owner, name);
  }

  public LiveData<Resource<List<Repo>>> loadRepos(String owner) {
    return gitRepoRepository.loadRepos(owner);
  }
}
