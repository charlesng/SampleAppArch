package com.cn29.aac.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.cn29.aac.R
import com.cn29.aac.databinding.ActivityAppArchNavigationDrawerBinding
import com.cn29.aac.ui.base.BaseAppCompatActivity
import com.cn29.aac.ui.feedentry.FeedActivity
import com.cn29.aac.ui.location.LocationActivity
import com.cn29.aac.ui.login.LoginActivity
import com.cn29.aac.ui.main.vm.AppArchNavViewModel
import com.cn29.aac.ui.masterdetail.SimpleListActivity
import com.cn29.aac.ui.setting.SettingsActivity
import com.cn29.aac.ui.shopping.ShoppingActivity
import com.cn29.aac.ui.shoppingkart.ShoppingKartActivity
import com.cn29.aac.ui.viewpager.PagerActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class AppArchNavigationDrawer : BaseAppCompatActivity(),
        NavigationView.OnNavigationItemSelectedListener {
    @JvmField
    @Inject
    var appArchNavViewModel: AppArchNavViewModel? = null

    @JvmField
    @Inject
    var binding: ActivityAppArchNavigationDrawerBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val toolbar = findViewById<Toolbar>(
                R.id.toolbar)
        setSupportActionBar(toolbar)
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener { view: View? ->
            Snackbar.make(view!!,
                          "Want to set email ?",
                          Snackbar.LENGTH_LONG)
                    .setAction("Action",
                               null)
                    .show()
        }
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(
                this,
                drawer,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId
        when (id) {
            R.id.nav_fragment_communication -> startActivity(Intent(this,
                                                                    SimpleListActivity::class.java))
            R.id.nav_storage -> startActivity(Intent(this,
                                                     FeedActivity::class.java))
            R.id.nav_connectivity -> startActivity(Intent(this,
                                                          PagerActivity::class.java))
            R.id.nav_location -> startActivity(Intent(this,
                                                      LocationActivity::class.java))
            R.id.nav_shopping -> startActivity(Intent(this,
                                                      ShoppingActivity::class.java))
            R.id.nav_shopping_kart -> startActivity(Intent(this,
                                                           ShoppingKartActivity::class.java))
            R.id.nav_shopping_history -> viewHistory()
            R.id.nav_ordering -> ordering()
            R.id.nav_settings -> openSettings()
            R.id.nav_logout -> logout()
        }
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    private fun openSettings() {
        startActivity(Intent(this, SettingsActivity::class.java))
    }

    private fun viewHistory() {}
    private fun ordering() {}
    private fun logout() {
        progressDialogComponent!!.showLoading()
        //clear the data first
        appArchNavViewModel!!.logout()
        val subscribe = Single.timer(3, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { _: Long? ->
                    //finish the current activity and go back to the login activity
                    progressDialogComponent!!.hideLoading()
                    finish()
                    startActivity(
                            Intent(this,
                                   LoginActivity::class.java))
                }
    }
}