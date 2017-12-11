package org.didd.dev.weatheraccu;

import android.location.Location;

import java.io.Serializable;

/**
 * Created by Jiangxuewu on 2017/12/5.
 *
 * @deprecated
 */

public class AccuLocation implements Serializable {

    private double altitude;
    private double lon;
    private double lat;
    private long time;

    public AccuLocation(Location location) {
        if (null == location) return;
        this.time = location.getTime();
        this.lat = location.getLatitude();
        this.lon = location.getLongitude();
        this.altitude = location.getAltitude();
    }

    public double getAltitude() {
        return altitude;
    }

    public double getLon() {
        return lon;
    }

    public double getLat() {
        return lat;
    }

    public long getTime() {
        return time;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
