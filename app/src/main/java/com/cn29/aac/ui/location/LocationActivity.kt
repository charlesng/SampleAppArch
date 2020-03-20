package com.cn29.aac.ui.location

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.cn29.aac.R
import com.cn29.aac.ui.base.BaseAppCompatActivity
import com.cn29.aac.ui.location.vm.LastLocationViewModel
import javax.inject.Inject

class LocationActivity : BaseAppCompatActivity() {
    @JvmField
    @Inject
    var lastLocationViewModel: LastLocationViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)
        val toolbar = findViewById<Toolbar>(
                R.id.toolbar)
        setSupportActionBar(toolbar)
    }
}