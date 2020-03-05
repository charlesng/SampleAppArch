package com.cn29.aac.ui.location;

import android.Manifest.permission;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.cn29.aac.R;
import com.cn29.aac.databinding.FragmentLocationBinding;
import com.cn29.aac.ui.common.FragmentPermissionComponent;
import com.cn29.aac.ui.common.FragmentPermissionComponent.PermissionCallback;
import com.cn29.aac.ui.common.PermissionComponentBuilder;
import com.cn29.aac.ui.location.vm.LastLocationViewModel;
import com.cn29.aac.ui.location.vm.LastLocationViewModelFactory;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import dagger.Module;
import dagger.Provides;

/**
 * Created by charlesng0209 on 2/10/2017.
 */

@Module
public class LocationFragmentModule {


  @Provides
  LastLocationViewModel provideLastLocationVM(LastLocationViewModelFactory factory,
                                              LocationFragment locationFragment) {
    return ViewModelProviders.of(locationFragment.getActivity(), factory)
            .get(LastLocationViewModel.class);
  }

  @Provides
  FragmentLocationBinding provideBinding(LocationFragment locationFragment) {
    return DataBindingUtil
            .inflate(LayoutInflater.from(locationFragment.getActivity()), R.layout.fragment_location,
                    null, false);
  }

  @Provides
  FragmentPermissionComponent providePermissionComponent(LocationFragment locationFragment) {
    return new PermissionComponentBuilder(locationFragment)
            .setPermissions(new String[]{
                    permission.ACCESS_COARSE_LOCATION})
            .setRequestCode(200).createPermissionComponent();
  }

  @Provides
  PermissionCallback permissionCallback(LocationFragment locationFragment) {
    locationFragment.permissionCallback = (requestCode, permissions, grantResults) -> {
      if (requestCode == 200) {// If request is cancelled, the result arrays are empty.
        if (grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
          Toast.makeText(locationFragment.getActivity(), "Rights Granted", Toast.LENGTH_SHORT)
                  .show();
          // permission was granted, yay! Do the
          // contacts-related task you need to do.
        }

        // other 'case' lines to check for other
        // permissions this app might request
      }
    };
    return locationFragment.permissionCallback;
  }


}
