package org.didd.dev.weather.httpx.response;

import java.util.List;

/**
 * Created by Jiangxuewu on 2017/12/4.
 */

public class AddressFromNet {

    /**
     * ip_address : 52.27.12.253
     * country : United States
     * country_code : US
     * continent : North America
     * continent_code : NA
     * city : Boardman
     * county : Morrow County
     * region : Oregon
     * region_code : OR
     * timezone : America/Los_Angeles
     * owner : null
     * longitude : -119.688
     * latitude : 45.8696
     * currency : USD
     * languages : ["en-US","es-US","haw","fr"]
     */

    private String ip_address;
    private String country;
    private String country_code;
    private String continent;
    private String continent_code;
    private String city;
    private String county;
    private String region;
    private String region_code;
    private String timezone;
    private Object owner;
    private double longitude;
    private double latitude;
    private String currency;
    private List<String> languages;

    public String getIp_address() {
        return ip_address;
    }

    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getContinent_code() {
        return continent_code;
    }

    public void setContinent_code(String continent_code) {
        this.continent_code = continent_code;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRegion_code() {
        return region_code;
    }

    public void setRegion_code(String region_code) {
        this.region_code = region_code;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public Object getOwner() {
        return owner;
    }

    public void setOwner(Object owner) {
        this.owner = owner;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }
}
