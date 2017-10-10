package com.cn29.aac.ui.masterdetail.vm;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import com.cn29.aac.repo.github.Contributor;
import com.cn29.aac.repo.github.GitRepoRepository;
import com.cn29.aac.repo.github.Repo;
import com.cn29.aac.vo.Resource;
import java.util.List;

/**
 * Created by charlesng0209 on 25/6/2017.
 */

public class SimpleMasterDetailShareViewModel extends ViewModel {


  GitRepoRepository gitRepoRepository;

  private MutableLiveData<List<Contributor>> contributors;


  private MutableLiveData<String> selectFeedEntryName;


  public SimpleMasterDetailShareViewModel(GitRepoRepository gitRepoRepository) {
    this.gitRepoRepository = gitRepoRepository;
  }

  public void init() {
    selectFeedEntryName = new MutableLiveData<>();
  }

  public void selectFeedEntry(String feedEntry) {
    selectFeedEntryName.setValue(feedEntry);
  }

  public LiveData<String> getSelectFeedEntry() {
    return this.selectFeedEntryName;
  }

  public LiveData<Resource<Repo>> loadRepos(String owner, String name) {
    return gitRepoRepository.loadRepo(owner, name);
  }
}
