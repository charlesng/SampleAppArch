package com.cn29.aac.ui.masterdetail.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cn29.aac.repo.github.GitRepoRepository
import com.cn29.aac.repo.github.Repo
import com.cn29.aac.util.Result

/**
 * Created by charlesng0209 on 25/6/2017.
 */
class SimpleMasterDetailShareViewModel(private val gitRepoRepository: GitRepoRepository) :
        ViewModel() {
    fun loadRepos(owner: String,
                  name: String): LiveData<Result<Repo>> =
            gitRepoRepository.loadRepo(owner, name)

    fun loadRepos(owner: String?): LiveData<Result<List<Repo>>> =
            gitRepoRepository.loadRepo(owner!!)

}