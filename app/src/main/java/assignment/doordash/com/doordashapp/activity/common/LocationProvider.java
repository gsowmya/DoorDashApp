package assignment.doordash.com.doordashapp.activity.common;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import javax.inject.Inject;

/**
 * Created by Sowmya on 1/13/19.
 */

public class LocationProvider {

    public static final String TAG = LocationProvider.class.getName();

    FragmentActivity mActivity;

    @Inject
    public LocationProvider(FragmentActivity mActivity){
        this.mActivity = mActivity;
    }

    public void requestLocationPermission(){
        if (!hasLocationPermission()) {
            ActivityCompat.requestPermissions(mActivity, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);
        }
    }

    public boolean hasLocationPermission(){
        return !(ContextCompat.checkSelfPermission(mActivity, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mActivity, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED);
    }

    public Location getLatLngFromCurrentLocation(){
        Location location = null;
        try {
            LocationManager lm = (LocationManager) mActivity.getSystemService(Context.LOCATION_SERVICE);
            location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }catch (SecurityException e){
            Log.d(TAG,e.getMessage());
        }
        return location;
    }
}
