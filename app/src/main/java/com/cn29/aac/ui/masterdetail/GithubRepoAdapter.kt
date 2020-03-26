package com.cn29.aac.ui.masterdetail

import com.cn29.aac.R
import com.cn29.aac.databinding.ItemGithubCardBinding
import com.cn29.aac.repo.github.Repo
import com.cn29.aac.ui.common.BaseRecyclerViewAdapter
import java.util.*

/**
 * Created by Charles Ng on 11/10/2017.
 */
class GithubRepoAdapter(
        itemClickListener: OnItemClickListener<Repo?>) :
        BaseRecyclerViewAdapter<Repo?, ItemGithubCardBinding>(
                itemClickListener) {
    private var repos: List<Repo> = ArrayList()
    fun setRepos(repos: List<Repo>) {
        this.repos = repos
    }

    override fun getItemForPosition(position: Int): Repo {
        return repos[position]
    }

    override fun getLayoutIdForPosition(position: Int): Int {
        return R.layout.item_github_card
    }

    override fun bind(binding: ItemGithubCardBinding,
                      item: Repo?) {
        binding.repo = item
    }

    override fun getItemCount(): Int {
        return repos.size
    }
}