package com.cn29.aac.ui.shopping

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.SearchRecentSuggestions
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.cn29.aac.R
import com.cn29.aac.databinding.ActivityShoppingBinding
import com.cn29.aac.di.scope.AndroidDataBinding
import com.cn29.aac.di.scope.ViewModel
import com.cn29.aac.ui.base.BaseAppCompatActivity
import com.cn29.aac.ui.shopping.vm.ShoppingActivityViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*
import javax.inject.Inject

class ShoppingActivity : BaseAppCompatActivity() {

    @Inject
    @AndroidDataBinding
    lateinit var binding: ActivityShoppingBinding


    @Inject
    @ViewModel
    lateinit var viewModel: ShoppingActivityViewModel
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener label@{ item: MenuItem ->
        when (item.itemId) {
            R.id.navigation_popular_albums -> {
                binding.viewpager.currentItem = 0
                return@label true
            }
            R.id.navigation_artists -> {
                binding.viewpager.currentItem = 1
                return@label true
            }
        }
        false
    }
    private lateinit var searchView: SearchView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        binding.navigation.setOnNavigationItemSelectedListener(
                mOnNavigationItemSelectedListener)
        setupViewPager(binding.viewpager)
        handleIntent(intent)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent) {
        if (Intent.ACTION_SEARCH == intent.action) {
            val query = intent.getStringExtra(SearchManager.QUERY)
            //set the text on the search view without submission
            searchView.setQuery(query, false)
            searchView.clearFocus()
            //save search result
            val suggestions = SearchRecentSuggestions(
                    this,
                    ArtistsSearchRecentSuggestionsProvider.AUTHORITY,
                    ArtistsSearchRecentSuggestionsProvider.MODE)
            suggestions.saveRecentQuery(query, null)
            query?.let {
                viewModel.query(it)
            }
            //use the query to search your data somehow
            Toast.makeText(this, query, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)
        // Associate searchable configuration with the SearchView
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = menu.findItem(R.id.search)
                .actionView as SearchView
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(componentName))
        searchView.isIconifiedByDefault = false // Do not iconify the widget; expand it by default
        searchView.onActionViewExpanded()
        return true
    }

    /*
  TODO
  Bind the view pager and the bottom navigation bar
   */
    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = Adapter(
                supportFragmentManager)
        adapter.addFragment("Popular Albums")
        adapter.addFragment("Artists")
        viewPager.adapter = adapter
    }

    private class Adapter internal constructor(manager: FragmentManager?) :
            FragmentStatePagerAdapter(manager!!) {
        private val mFragmentTitleList: MutableList<String> = ArrayList()
        override fun getItem(position: Int): Fragment {
            when (position) {
                0 -> return AlbumFragment()
                1 -> return ArtistFragment()
                else -> {
                }
            }
            return AlbumFragment()
        }

        override fun getCount(): Int {
            return 2
        }

        fun addFragment(title: String) {
            mFragmentTitleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return mFragmentTitleList[position]
        }
    }
}