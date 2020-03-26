package com.cn29.aac.ui.shopping

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.core.app.NavUtils
import com.cn29.aac.R
import com.cn29.aac.databinding.ActivityArtistDetailBinding
import com.cn29.aac.di.scope.AndroidDataBinding
import com.cn29.aac.di.scope.ViewModel
import com.cn29.aac.repo.itunes.Artist
import com.cn29.aac.ui.base.BaseAppCompatActivity
import com.cn29.aac.ui.shopping.vm.ArtistDetailActivityViewModel
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class ArtistDetailActivity : BaseAppCompatActivity() {
    @Inject
    @ViewModel
    lateinit var viewModel: ArtistDetailActivityViewModel

    @Inject
    @AndroidDataBinding
    lateinit var binding: ActivityArtistDetailBinding

    @Inject
    lateinit var artist: Artist
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.toolbar.title = artist.artistName
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        binding.artist = artist
        binding.fab.setOnClickListener { view: View ->
            addToKart(view)
        }
        val manager = supportFragmentManager
        val fragment = AlbumFragment()
        val bundle = Bundle()
        artist.artistId.toInt().let { bundle.putInt("artistId", it) }
        bundle.putString("entity", "album")
        fragment.arguments = bundle
        manager.beginTransaction()
                .add(binding.container.id, fragment)
                .commit()
    }

    private fun addToKart(view: View) {
        Snackbar.make(view,
                      "Replace with your own action",
                      Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> {
                NavUtils.navigateUpFromSameTask(this)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}