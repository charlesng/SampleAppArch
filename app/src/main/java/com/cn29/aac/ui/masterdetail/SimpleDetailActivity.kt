package com.cn29.aac.ui.masterdetail

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.core.app.NavUtils
import com.cn29.aac.R
import com.cn29.aac.ui.masterdetail.vm.SimpleMasterDetailShareViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

/**
 * An activity representing a single FeedEntry detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a [SimpleListActivity].
 */
class SimpleDetailActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var masterDetailShareViewModel: SimpleMasterDetailShareViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_detail)
        val toolbar = findViewById<Toolbar>(
                R.id.detail_toolbar)
        setSupportActionBar(toolbar)
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener { view: View? ->
            Snackbar.make(view!!,
                          "Replace with your own detail action",
                          Snackbar.LENGTH_LONG)
                    .setAction("Action",
                               null)
                    .show()
        }

        // Show the Up button in the action bar.
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            val arguments = Bundle()
            arguments.putString(SimpleDetailFragment.OWNER_NAME,
                                intent.getStringExtra(SimpleDetailFragment.OWNER_NAME))
            arguments.putString(SimpleDetailFragment.REPO_NAME,
                                intent.getStringExtra(SimpleDetailFragment.REPO_NAME))
            val fragment = SimpleDetailFragment()
            fragment.arguments = arguments
            supportFragmentManager.beginTransaction()
                    .add(R.id.feedentry_detail_container, fragment)
                    .commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            NavUtils.navigateUpTo(this,
                                  Intent(this, SimpleListActivity::class.java))
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}