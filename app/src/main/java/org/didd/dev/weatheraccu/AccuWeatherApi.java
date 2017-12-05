package org.didd.dev.weatheraccu;

import android.text.TextUtils;

import com.google.gson.Gson;

import org.didd.common.L;
import org.didd.dev.weather.httpx.CodeX;
import org.didd.dev.weatheraccu.data.AccuLocationData;
import org.didd.dev.weatheraccu.model.AccuBaseModel;
import org.didd.dev.weatheraccu.model.AccuLocationModel;
import org.didd.http.BaseModel;
import org.didd.http.HttpApi;
import org.didd.http.HttpResponse;
import org.didd.http.HttpResponseBody;
import org.didd.http.IHttpCallback;


/**
 * Created by Jiangxuewu on 2017/12/5.
 */

public class AccuWeatherApi {

    private static final String apiKey = "srRLeAmTroxPinDG8Aus3Ikl6tLGJd94";

    public void test() {

    }

    public void getLocation(AccuLocationData data, IHttpCallback callback) {
        BaseModel location = new AccuLocationModel(data, callback);
        HttpApi.getInstance().request(location);
    }

    public static <T> T parseJsonToObject(HttpResponse httpResponse, Class<T> cls) {
        if (null != httpResponse && httpResponse.code == 200) {
            HttpResponseBody body = httpResponse.getBody();
            if (null != body) {
                String str = body.getString();
                if (!TextUtils.isEmpty(str)) {
                    return new Gson().fromJson(str, cls);
                }
            }
        }
        return null;
    }
}
