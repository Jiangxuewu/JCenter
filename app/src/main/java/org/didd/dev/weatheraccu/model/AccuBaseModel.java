package org.didd.dev.weatheraccu.model;

import org.didd.dev.weatheraccu.data.AccuBaseData;
import org.didd.dev.weatheraccu.data.AccuLocationData;
import org.didd.http.BaseModel;
import org.didd.http.Http;
import org.didd.http.HttpEntry;
import org.didd.http.IHttpCallback;

/**
 * Created by Jiangxuewu on 2017/12/5.
 */

public class AccuBaseModel extends BaseModel {
    private final static String url = "https://api.accuweather.com/";
    private AccuBaseData data;
    private IHttpCallback callback;

    public AccuBaseModel(AccuBaseData data, IHttpCallback callback) {
        super(data.interfaceName);
        this.data = data;
        this.callback = callback;
    }

    @Override
    public HttpEntry toHttpEntry() {
        HttpEntry httpEntry = new HttpEntry();
        httpEntry.setType(Http.GET);
        httpEntry.setCallback(callback);
        httpEntry.setBody(toMap(data));
        httpEntry.setBaseUrl(url + data.interfaceName);
        return httpEntry;
    }
}
