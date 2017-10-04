package com.cn29.aac.repo.github;

import com.cn29.aac.datasource.api.GithubService;
import javax.inject.Singleton;

/**
 * Created by Charles Ng on 3/10/2017.
 */

@Singleton
public class GitRepoRepository {

  private GithubService githubService;

  public GitRepoRepository(GithubService githubService) {
    this.githubService = githubService;
  }


}
