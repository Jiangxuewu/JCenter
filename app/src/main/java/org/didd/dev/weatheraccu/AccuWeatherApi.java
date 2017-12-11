package org.didd.dev.weatheraccu;

import android.content.Context;
import android.content.res.Configuration;
import android.text.TextUtils;

import com.google.gson.Gson;

import org.didd.dev.service.MyLocation;
import org.didd.dev.weatheraccu.data.AccuBaseData;
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

    /**
     *
     * @param data
     * @param callback
     */
    public void locations(Context context, MyLocation data, IHttpCallback callback) {
        Configuration config = context.getResources().getConfiguration();
        String country = config.locale.getCountry();
        String language = config.locale.getLanguage();

        AccuLocationData info = new AccuLocationData();
        info.q = "" + data.getLat() + "," + data.getLon();
        info.language = language.toLowerCase() + "-" + country.toLowerCase();
        info.details = true;
        info.toplevel = false;
        info.apikey = apiKey;
        info.interfaceName = "locations/v1/cities/geoposition/search.json";

        request(info, callback);
    }

    public void currentconditions(Context context){

    }

    private void request(AccuBaseData data, IHttpCallback callback){
        BaseModel location = new AccuBaseModel(data, callback);
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
