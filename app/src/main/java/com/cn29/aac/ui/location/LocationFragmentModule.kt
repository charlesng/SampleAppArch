package com.cn29.aac.ui.location

import android.Manifest.permission
import android.content.pm.PackageManager
import android.view.LayoutInflater
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.cn29.aac.R
import com.cn29.aac.databinding.FragmentLocationBinding
import com.cn29.aac.ui.common.FragmentPermissionComponent
import com.cn29.aac.ui.common.FragmentPermissionComponent.PermissionCallback
import com.cn29.aac.ui.common.PermissionComponentBuilder
import com.cn29.aac.ui.location.vm.LastLocationViewModel
import com.cn29.aac.ui.location.vm.LastLocationViewModelFactory
import dagger.Module
import dagger.Provides

/**
 * Created by charlesng0209 on 2/10/2017.
 */
@Module
class LocationFragmentModule {
    @Provides
    fun provideLastLocationVM(factory: LastLocationViewModelFactory,
                              locationFragment: LocationFragment): LastLocationViewModel {
        return ViewModelProvider(locationFragment.requireActivity(), factory)
                .get(LastLocationViewModel::class.java)
    }

    @Provides
    fun provideBinding(locationFragment: LocationFragment): FragmentLocationBinding {
        return DataBindingUtil
                .inflate(LayoutInflater.from(locationFragment.activity),
                         R.layout.fragment_location,
                         null,
                         false)
    }

    @Provides
    fun providePermissionComponent(locationFragment: LocationFragment?): FragmentPermissionComponent {
        return PermissionComponentBuilder(locationFragment!!)
                .setPermissions(arrayOf(
                        permission.ACCESS_COARSE_LOCATION))
                .setRequestCode(200).createPermissionComponent()
    }

    @Provides
    fun permissionCallback(locationFragment: LocationFragment): PermissionCallback {
        return object : PermissionCallback {
            override fun onRequestPermissionsResult(requestCode: Int,
                                                    permissions: Array<String>,
                                                    grantResults: IntArray) {
                if (requestCode == 200) { // If request is cancelled, the result arrays are empty.
                    if (grantResults.isNotEmpty()
                            && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(locationFragment.activity,
                                       "Rights Granted",
                                       Toast.LENGTH_SHORT)
                                .show()
                    }
                }
            }
        }
    }
}