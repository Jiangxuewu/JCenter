package org.didd.version;

import com.google.gson.Gson;

import org.didd.http.BaseModel;
import org.didd.http.Http;
import org.didd.http.HttpEntry;
import org.didd.http.IHttpCallback;

/**
 * Created by Jiangxuewu on 2018/1/23.
 */

public class NewVersionModel extends BaseModel {

    private IHttpCallback callback;

    private VersionReq data;


    public NewVersionModel(VersionReq data, IHttpCallback callback) {
        super("checkUpGrade");
        this.data = data;
        this.callback = callback;
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
        httpEntry.setBaseUrl(VersionApi.versionHttpUrl);
        return httpEntry;
    }

    private String toJson(VersionReq data) {
        return new Gson().toJson(data);
    }
}
