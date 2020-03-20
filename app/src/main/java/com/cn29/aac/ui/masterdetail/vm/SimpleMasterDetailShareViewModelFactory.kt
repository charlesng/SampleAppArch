package com.cn29.aac.ui.masterdetail.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import com.cn29.aac.repo.github.GitRepoRepository
import javax.inject.Inject

/**
 * Created by Charles Ng on 9/10/2017.
 */
class SimpleMasterDetailShareViewModelFactory @Inject constructor(var gitRepoRepository: GitRepoRepository) :
        NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SimpleMasterDetailShareViewModel(gitRepoRepository) as T
    }

}