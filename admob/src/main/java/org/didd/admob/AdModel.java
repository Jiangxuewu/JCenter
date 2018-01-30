package org.didd.admob;



import com.google.gson.Gson;

import org.didd.http.BaseModel;
import org.didd.http.Http;
import org.didd.http.HttpEntry;
import org.didd.http.IHttpCallback;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xuewu.jiang@afmobigroup.com on 2017/11/21.
 */

/*public*/ class AdModel extends BaseModel {

    private IHttpCallback callback;
    private AdList data;

    private Map<String, String> header = new HashMap<>();

    public AdModel(AdList data, IHttpCallback callback) {
        super("adListNoPage");
        this.data = data;
        this.callback = callback;
        header.put("Content-Type", "application/json");
    }

    public IHttpCallback getCallback() {
        return callback;
    }

    @Override
    public HttpEntry toHttpEntry() {
        HttpEntry httpEntry = new HttpEntry();
        httpEntry.setCallback(callback);
        httpEntry.setType(Http.POST);
        httpEntry.setBodyJson(toJson(data));
        httpEntry.setBaseUrl(AdmobApi.adHttpUrl);
        return httpEntry;
    }

    private String toJson(AdList data) {
        return new Gson().toJson(data);
    }
}
