package com.example.location.repository;

import android.Manifest.permission;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

/**
 * Created by Charles Ng on 5/9/2017.
 */

public class LastLocationListener extends LiveData<Location> {

  private LocationManager locationManager;

  private Context context;

  private LocationListener listener = new LocationListener() {
    @Override
    public void onLocationChanged(Location location) {

      Log.d("Location Msg", location.toString());
      setValue(location);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
  };

  public LastLocationListener(Context context) {
    this.context = context;
    locationManager = (LocationManager) context.getSystemService(
        Context.LOCATION_SERVICE);
  }

  @Override
  protected void onActive() {
    if (ActivityCompat.checkSelfPermission(context, permission.ACCESS_FINE_LOCATION)
        != PackageManager.PERMISSION_GRANTED
        && ActivityCompat.checkSelfPermission(context, permission.ACCESS_COARSE_LOCATION)
        != PackageManager.PERMISSION_GRANTED) {
      // TODO: Consider calling
      //    ActivityCompat#requestPermissions
      // here to request the missing permissions, and then overriding
      //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
      //                                          int[] grantResults)
      // to handle the case where the user grants the permission. See the documentation
      // for ActivityCompat#requestPermissions for more details.
      return;
    }
    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, listener);
  }

  @Override
  protected void onInactive() {
    locationManager.removeUpdates(listener);
  }


}