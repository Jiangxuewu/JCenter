package org.didd.dev.service;

import java.io.Serializable;

/**
 * Created by Jiangxuewu on 2017/12/11.
 */

public class MyLocation implements Serializable {
    private double altitude;
    private double lon;
    private double lat;
    private long time;

    public MyLocation() {
    }

    public MyLocation(double altitude, double lon, double lat, long time) {
        this.altitude = altitude;
        this.lon = lon;
        this.lat = lat;
        this.time = time;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
