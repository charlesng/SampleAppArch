package com.cn29.aac.repo.location

import android.Manifest.permission
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.lifecycle.LiveData
import javax.inject.Inject

/**
 * Created by Charles Ng on 5/9/2017.
 */
class LastLocationListener @Inject constructor(private val context: Context) :
        LiveData<Location?>() {
    private val locationManager: LocationManager = context.getSystemService(
            Context.LOCATION_SERVICE) as LocationManager
    private val listener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            Log.d("Location Msg", location.toString())
            value = location
        }

        override fun onStatusChanged(s: String,
                                     i: Int,
                                     bundle: Bundle) {
        }

        override fun onProviderEnabled(s: String) {}
        override fun onProviderDisabled(s: String) {}
    }

    override fun onActive() {
        if (ActivityCompat.checkSelfPermission(context,
                                               permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context,
                                                      permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                                               0,
                                               0f,
                                               listener)
    }

    override fun onInactive() {
        locationManager.removeUpdates(listener)
    }

}