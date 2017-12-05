package org.didd.dev.weather.httpx;

import org.didd.http.BaseModel;
import org.didd.http.HttpEntry;
import org.didd.http.IHttpCallback;

/**
 * Created by Jiangxuewu on 2017/12/4.
 */

public class GetWeatherModel extends BaseModel {
    private double lon, lat;
    private IHttpCallback callback;

    public GetWeatherModel(String interfaceName, double lon, double lat, IHttpCallback callback) {
        super(interfaceName);
        this.lon = lon;
        this.lat = lat;
        this.callback = callback;
    }

    @Override
    public HttpEntry toHttpEntry() {
        HttpEntry httpEntry = new HttpEntry();
        return httpEntry;
    }
}
