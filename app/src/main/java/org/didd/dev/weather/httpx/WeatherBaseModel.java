package org.didd.dev.weather.httpx;

import android.os.Build;
import android.text.TextUtils;

import org.didd.common.log.L;
import org.didd.dev.BuildConfig;
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
 * * <p>1,get apiKey from server</p>
 */

public class WeatherBaseModel extends BaseModel {
    private String params;
    private IHttpCallback callback;

    public WeatherBaseModel(String interfaceName, String params, IHttpCallback callback) {
        super(interfaceName);
        this.params = params;
        this.callback = callback;
    }

    @Override
    public HttpEntry toHttpEntry() {
        HttpEntry httpEntry = new HttpEntry();
        httpEntry.setType(Http.GET);
        httpEntry.setBaseUrl("http://wtapi.tohapp.com/api.php?param=" + params);
        httpEntry.setCallback(callback);
        Map<String, String> header = new HashMap<>();
        header.put("User-Agent", "Dalvik/2.1.0 (Linux; U; Android " + Build.VERSION.RELEASE + "; " + Build.MODEL + " Build/" + Build.ID);
        httpEntry.setHeader(header);
        return httpEntry;
    }

    public static String parseWeatherResponse(HttpResponse httpResponse) {
        return parseResponse(httpResponse);
    }

    public static String getApiKey(HttpResponse httpResponse) {
        return parseResponse(httpResponse);
    }

    public static String parseResponse(HttpResponse httpResponse) {
        if (null != httpResponse && httpResponse.code == 200) {
            HttpResponseBody body = httpResponse.getBody();
            if (null != body) {
                String str = body.getString();
                if (!TextUtils.isEmpty(str)) {
                    String res = CodeX.decode(str);
                    if (BuildConfig.DEBUG) {
                        L.i("Test", "res = " + res);
                    }
                    return res;
                }
            }

        }
        return "";
    }
}
