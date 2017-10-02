package com.cn29.aac.ui.location;


import android.Manifest.permission;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.cn29.aac.R;
import com.cn29.aac.databinding.FragmentLocationBinding;
import com.cn29.aac.ui.location.vm.LastLocationViewModel;
import com.example.common.components.binding.CustomConverter;
import dagger.android.support.DaggerFragment;
import javax.inject.Inject;


/**
 * Use the {@link LocationFragment#} factory method to
 * create an instance of this fragment.
 */
public class LocationFragment extends DaggerFragment {

  @Inject
  LastLocationViewModel lastLocationViewModel;
  private FragmentLocationBinding binding;

  public LocationFragment() {
    // Required empty public constructor
  }


  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    if (ContextCompat.checkSelfPermission(getActivity(),
        permission.ACCESS_COARSE_LOCATION)
        != PackageManager.PERMISSION_GRANTED) {
      // Should we show an explanation?
      if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
          permission.ACCESS_COARSE_LOCATION)) {
        // Show an explanation to the user *asynchronously* -- don't block
        // this thread waiting for the user's response! After the user
        // sees the explanation, try again to request the permission.

      } else {

        // No explanation needed, we can request the permission.

        ActivityCompat.requestPermissions(getActivity(),
            new String[]{permission.ACCESS_COARSE_LOCATION},
            200);

        // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
        // app-defined int constant. The callback method gets the
        // result of the request.
      }
    }
    //get the viewmodel from activity
    lastLocationViewModel.getLastKnowLocation().observe(this, location ->{
        binding.setLocation(location);

    });

  }

  @Override
  public void onRequestPermissionsResult(int requestCode,
      String permissions[], int[] grantResults) {
    switch (requestCode) {
      case 200: {
        // If request is cancelled, the result arrays are empty.
        if (grantResults.length > 0
            && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
          Toast.makeText(getActivity(), "Rights Granted", Toast.LENGTH_SHORT).show();
          // permission was granted, yay! Do the
          // contacts-related task you need to do.
        } else {
          // permission denied, boo! Disable the
          // functionality that depends on this permission.
        }
        return;
      }

      // other 'case' lines to check for other
      // permissions this app might request
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    binding = DataBindingUtil
        .inflate(LayoutInflater.from(getActivity()), R.layout.fragment_location, null, false);
    binding.setConverter(new CustomConverter());
    binding.btnLocation.setOnClickListener(view -> {
      Location location = binding.getLocation();
      if (location != null) {
        Toast.makeText(getActivity(),
            String.format("Lat : %f, Long : %f", location.getLatitude(), location.getLongitude()),
            Toast.LENGTH_SHORT).show();
      }
    });
    return binding.getRoot();
  }

}
