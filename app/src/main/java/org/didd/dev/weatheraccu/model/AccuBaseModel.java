package org.didd.dev.weatheraccu.model;

import org.didd.http.BaseModel;
import org.didd.http.Http;
import org.didd.http.HttpEntry;
import org.didd.http.IHttpCallback;

/**
 * Created by Jiangxuewu on 2017/12/5.
 */

public class AccuBaseModel extends BaseModel {
    private IHttpCallback callback;
    private String url;

    public AccuBaseModel(String interfaceName, String url, IHttpCallback callback) {
        super(interfaceName);
        this.callback = callback;
        this.url = url;
    }

    @Override
    public HttpEntry toHttpEntry() {
        HttpEntry httpEntry = new HttpEntry();
        httpEntry.setType(Http.GET);
        httpEntry.setCallback(callback);
        httpEntry.setBaseUrl(url);
        return httpEntry;
    }
}
