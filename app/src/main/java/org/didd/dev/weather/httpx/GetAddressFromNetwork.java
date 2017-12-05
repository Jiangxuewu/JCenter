package org.didd.dev.weather.httpx;

import android.os.Build;
import android.text.TextUtils;

import com.google.gson.Gson;

import org.didd.dev.weather.httpx.response.AddressFromNet;
import org.didd.http.BaseModel;
import org.didd.http.Http;
import org.didd.http.HttpEntry;
import org.didd.http.HttpResponse;
import org.didd.http.HttpResponseBody;
import org.didd.http.IHttpCallback;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jiangxuewu on 2017/12/4.
 */

public class GetAddressFromNetwork extends BaseModel {
    private String apiKey;
    private IHttpCallback callback;
    public GetAddressFromNetwork(String apiKey, IHttpCallback callback) {
        super("");
        this.apiKey = apiKey;
        this.callback = callback;
    }

    @Override
    public HttpEntry toHttpEntry() {
        HttpEntry httpEntry = new HttpEntry();
        httpEntry.setType(Http.GET);
        httpEntry.setBaseUrl("https://ipfind.co/me?auth=" + apiKey);
        httpEntry.setCallback(callback);
        Map<String, String> header = new HashMap<>();
        header.put("User-Agent", "Dalvik/2.1.0 (Linux; U; Android " + Build.VERSION.RELEASE + "; " + Build.MODEL + " Build/" + Build.ID);
        httpEntry.setHeader(header);
        return httpEntry;
    }


    public static AddressFromNet parseResponse(HttpResponse httpResponse){
        if (null != httpResponse && httpResponse.code == 200){
            HttpResponseBody body = httpResponse.getBody();
            if (null != body){
                String str = body.getString();
                if (!TextUtils.isEmpty(str)){
                    return new Gson().fromJson(str, AddressFromNet.class);
                }
            }
        }
        return null;
    }
}
