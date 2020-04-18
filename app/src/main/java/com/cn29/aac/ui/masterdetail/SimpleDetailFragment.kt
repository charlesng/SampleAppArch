package com.cn29.aac.ui.masterdetail

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import com.cn29.aac.R
import com.cn29.aac.ui.base.BaseFragment
import com.cn29.aac.ui.masterdetail.vm.SimpleMasterDetailShareViewModel
import com.cn29.aac.util.Result
import com.google.android.material.appbar.CollapsingToolbarLayout
import javax.inject.Inject


class SimpleDetailFragment
    : BaseFragment() {
    @Inject
    lateinit var masterDetailShareViewModel: SimpleMasterDetailShareViewModel
    private lateinit var rootView: View
    private lateinit var repoName: String
    private lateinit var ownerName: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments?.containsKey(REPO_NAME) == true && arguments?.containsKey(
                        OWNER_NAME) == true) {
            repoName = arguments?.getString(REPO_NAME).toString()
            ownerName = arguments?.getString(OWNER_NAME).toString()
        }
        masterDetailShareViewModel.loadRepos(ownerName, repoName)
                .observe(this, Observer {
                    val activity: Activity = requireActivity()
                    val appBarLayout: CollapsingToolbarLayout = activity
                            .findViewById(
                                    R.id.toolbar_layout)
                    val titleView = rootView
                            .findViewById<TextView>(
                                    R.id.feedentry_detail)
                    when (it) {
                        is Result.Success -> {
                            progressDialogComponent?.hideLoading()
                            appBarLayout.title = it.data.name
                            titleView.text = it.data.fullName
                        }
                        is Result.Error -> progressDialogComponent?.hideLoading()
                        is Result.Loading -> progressDialogComponent?.showLoading()
                        else -> Unit
                    }
                })
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_simple_detail,
                                    container,
                                    false)
        return rootView
    }

    companion object {
        const val REPO_NAME = "repo_name"
        const val OWNER_NAME = "owner_name"
    }
}