package com.cn29.aac.ui.masterdetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.cn29.aac.R
import com.cn29.aac.repo.github.Repo
import com.cn29.aac.ui.base.BaseAppCompatActivity
import com.cn29.aac.ui.common.BaseRecyclerViewAdapter.OnItemClickListener
import com.cn29.aac.ui.masterdetail.vm.SimpleMasterDetailShareViewModel
import com.cn29.aac.util.Result
import com.google.android.material.floatingactionbutton.FloatingActionButton
import javax.inject.Inject

class SimpleListActivity : BaseAppCompatActivity() {
    @Inject
    lateinit var masterDetailShareViewModel: SimpleMasterDetailShareViewModel

    private var mTwoPane = false
    private lateinit var githubRepoAdapter: GithubRepoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_list)
        val toolbar = findViewById<Toolbar>(
                R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.title = title
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            loadRepos("googlesamples")
        }
        setupAdapter()
        loadRepos("googlesamples")
        val recyclerView = findViewById<RecyclerView>(R.id.feedentry_list)!!
        recyclerView.adapter = githubRepoAdapter
        if (findViewById<View?>(R.id.feedentry_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true
        }
    }

    private fun loadRepos(ownerName: String) {
        masterDetailShareViewModel
                .loadRepos(ownerName)
                .observe(this, Observer {
                    when (it) {
                        Result.Loading -> {
                            progressDialogComponent?.showLoading()
                            Toast.makeText(this, "Loading", Toast.LENGTH_SHORT)
                                    .show()
                        }
                        is Result.Success -> {
                            progressDialogComponent?.hideLoading()
                            githubRepoAdapter.setRepos(it.data)
                            Toast.makeText(this, "Success", Toast.LENGTH_SHORT)
                                    .show()
                        }
                        is Result.Error -> progressDialogComponent?.hideLoading()
                        else -> progressDialogComponent?.hideLoading()
                    }
                    githubRepoAdapter.notifyDataSetChanged()
                })
    }

    private fun setupAdapter() {
        val listener = object : OnItemClickListener<Repo?> {
            override fun onItemClick(item: Repo?) {

                if (mTwoPane) {
                    val arguments = Bundle()
                    arguments.putString(
                            SimpleDetailFragment.REPO_NAME,
                            item!!.name)
                    arguments.putString(
                            SimpleDetailFragment.OWNER_NAME,
                            item.owner.login)
                    val fragment = SimpleDetailFragment()
                    fragment.arguments = arguments
                    supportFragmentManager.beginTransaction()
                            .replace(
                                    R.id.feedentry_detail_container,
                                    fragment)
                            .commit()
                } else {
                    val context: Context = this@SimpleListActivity
                    val intent = Intent(
                            context,
                            SimpleDetailActivity::class.java)
                    intent.putExtra(
                            SimpleDetailFragment.REPO_NAME,
                            item!!.name)
                    intent.putExtra(
                            SimpleDetailFragment.OWNER_NAME,
                            item.owner.login)
                    context.startActivity(
                            intent)
                }

            }
        }
        githubRepoAdapter = GithubRepoAdapter(listener)
    }
}