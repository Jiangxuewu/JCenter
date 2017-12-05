package org.didd.dev.weather.httpx.response.weather;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Jiangxuewu on 2017/12/5.
 */

public class FlagsBean {

    /**
     * sources : ["isd","cmc","gfs","madis"]
     * isd-stations : ["450010-99999","450030-99999","450040-99999","450050-99999","450070-99999","450090-99999","450110-99999","450320-99999","450330-99999","450350-99999","450390-99999","450440-99999","450450-99999","592970-99999","592980-99999","594930-99999"]
     * units : us
     */

    private String units;
    private List<String> sources;
    @SerializedName("isd-stations")
    private List<String> isdstations;

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public List<String> getSources() {
        return sources;
    }

    public void setSources(List<String> sources) {
        this.sources = sources;
    }

    public List<String> getIsdstations() {
        return isdstations;
    }

    public void setIsdstations(List<String> isdstations) {
        this.isdstations = isdstations;
    }
}
