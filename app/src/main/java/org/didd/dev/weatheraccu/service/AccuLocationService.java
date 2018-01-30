package org.didd.dev.weatheraccu.service;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;

import org.didd.common.log.L;
import org.didd.dev.BuildConfig;
import org.didd.dev.service.MyLocation;

import java.util.List;

/**
 * @deprecated
 */
public class AccuLocationService extends Service implements LocationListener {
    public static final String ACTION_LOCATION_UPDATE = "ACTION_LOCATION_UPDATE";
    public static final String KEY_LOCATION_UPDATE = "KEY_LOCATION_UPDATE";
    private static final String TAG = AccuLocationService.class.getSimpleName();
    private LocationManager locationManager;
    private String locationType;
    private static final long minTime = 1000 * 10;
    private static final float minDistance = 1;

    public AccuLocationService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        starLocation();
        return START_STICKY;
    }

    private void initLocationManager() {
        if (null == locationManager) {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        }

        assert locationManager != null;

        boolean isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if (isGpsEnabled) {
            locationType = LocationManager.GPS_PROVIDER;
        } else {
            locationType = LocationManager.NETWORK_PROVIDER;
        }

    }

    private void starLocation() {
        if (BuildConfig.DEBUG) L.i(TAG, "starLocation, locationType = " + locationType);
        if (null == locationManager) initLocationManager();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            //request permission
            if (BuildConfig.DEBUG) L.i(TAG, "starLocation, permission disabled");
            return;
        }

        Location location = locationManager.getLastKnownLocation(locationType);
        if (null == location) {
            location = getLastKnownLocation();
        }

        updateLocation(location);

        locationManager.requestLocationUpdates(locationType, minTime, minDistance, this);
    }

    private Location getLastKnownLocation() {
        if (BuildConfig.DEBUG) L.i(TAG, "getLastKnownLocation, locationType = " + locationType);
        if (null == locationManager) return null;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            return null;
        }

        List<String> providers = locationManager.getProviders(true);
        Location bestLocation = null;
        for (String provider : providers) {
            if (BuildConfig.DEBUG) L.i(TAG, "getLastKnownLocation, provider = " + provider);
            Location l = locationManager.getLastKnownLocation(provider);
            if (l == null) {
                continue;
            }
            if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                // Found best last known location: %s", l);
                bestLocation = l;
                if (BuildConfig.DEBUG) L.i(TAG, "getLastKnownLocation, best provider = " + provider);
            }
        }
        return bestLocation;
    }

    private void stopLocation() {
        // TODO: 2017/12/5
    }

    private void updateLocation(Location location) {
        if (BuildConfig.DEBUG) L.i(TAG, "updateLocation");
        MyLocation accuLocation = new MyLocation(location.getAltitude(), location.getLongitude(), location.getLatitude(), location.getTime());
        Intent intent = new Intent(ACTION_LOCATION_UPDATE);
        intent.putExtra(KEY_LOCATION_UPDATE, accuLocation);
        intent.setPackage(getPackageName());
        sendBroadcast(intent);
    }

    @Override
    public void onLocationChanged(Location location) {
        if (BuildConfig.DEBUG) L.i(TAG, "onLocationChanged");
        updateLocation(location);
    }

    @Override
    public void onStatusChanged(String s, int status, Bundle bundle) {
        if (BuildConfig.DEBUG) L.i(TAG, "onStatusChanged, status = " + status);
        switch (status) {
            case LocationProvider.AVAILABLE:
                break;
            case LocationProvider.OUT_OF_SERVICE:
                break;
            case LocationProvider.TEMPORARILY_UNAVAILABLE:
                break;
            default:
                break;
        }
    }

    @Override
    public void onProviderEnabled(String provider) {
        if (BuildConfig.DEBUG) L.i(TAG, "onProviderEnabled, provider = " + provider);
        starLocation();

    }

    @Override
    public void onProviderDisabled(String provider) {
        if (BuildConfig.DEBUG) L.i(TAG, "onProviderDisabled, provider = " + provider);
        stopLocation();
    }

}
