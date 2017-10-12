package com.cn29.aac.ui.masterdetail;

import com.cn29.aac.R;
import com.cn29.aac.databinding.ItemGithubCardBinding;
import com.cn29.aac.repo.github.Repo;
import com.cn29.aac.ui.common.BaseRecyclerViewAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Charles Ng on 11/10/2017.
 */

public class GithubRepoAdapter extends BaseRecyclerViewAdapter<Repo, ItemGithubCardBinding> {

  private List<Repo> repos = new ArrayList<>();

  public GithubRepoAdapter(
      OnItemClickListener<Repo> itemClickListener) {
    super(itemClickListener);
  }

  public void setRepos(List<Repo> repos) {
    this.repos = repos;
  }

  @Override
  protected Repo getItemForPosition(int position) {
    return repos.get(position);
  }

  @Override
  protected int getLayoutIdForPosition(int position) {
    return R.layout.item_github_card;
  }

  @Override
  protected void bind(ItemGithubCardBinding binding, Repo item) {
    binding.setRepo(item);
  }

  @Override
  public int getItemCount() {
    return repos.size();
  }
}
