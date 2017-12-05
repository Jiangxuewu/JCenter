package org.didd.dev.weather.httpx;

import android.text.TextUtils;

import com.google.gson.Gson;

import org.didd.common.L;
import org.didd.dev.weather.httpx.response.AddressFromNet;
import org.didd.dev.weather.httpx.response.weather.WeatherBean;
import org.didd.http.BaseModel;
import org.didd.http.HttpApi;
import org.didd.http.HttpResponse;
import org.didd.http.IHttpCallback;

import java.text.DecimalFormat;

/**
 * Created by Jiangxuewu on 2017/12/4.
 * <p>1,get apiKey from server</p>
 * <p>2,get Address from network</p>
 */

public class WeatherApi {

    String apiKey;

    public void test() {
        getApiKey();
    }

    /**
     * 获取apiKey
     */
    public void getApiKey() {
        BaseModel key = new WeatherBaseModel("", getApiKeyParams(), new IHttpCallback() {
            @Override
            public void result(HttpResponse httpResponse) {
                apiKey = WeatherBaseModel.getApiKey(httpResponse);
                if (!TextUtils.isEmpty(apiKey)) {
                    getAddressFromNet(apiKey);
                }
            }
        });

        HttpApi.getInstance().request(key);
    }

    /**
     * 根据网络ip，获取用户当前位置。
     * @param apiKey
     */
    public void getAddressFromNet(String apiKey) {
        BaseModel model = new GetAddressFromNetwork(apiKey, new IHttpCallback() {
            @Override
            public void result(HttpResponse httpResponse) {
                AddressFromNet addressFromNetwork = GetAddressFromNetwork.parseResponse(httpResponse);
                if (null != addressFromNetwork) {
                    getWeather(addressFromNetwork.getLatitude(), addressFromNetwork.getLongitude());
                }
            }
        });
        HttpApi.getInstance().request(model);
    }

    /**
     * 根据经纬度获取天气信息
     * @param lat
     * @param lon
     */
    public void getWeather(double lat, double lon) {
        BaseModel weather = new WeatherBaseModel("", getWeatherParams(lat, lon), new IHttpCallback() {
            @Override
            public void result(HttpResponse httpResponse) {
                String res = WeatherBaseModel.parseWeatherResponse(httpResponse);
                if (!TextUtils.isEmpty(res)){
                    WeatherBean weatherBean = new Gson().fromJson(res, WeatherBean.class);

                }
            }
        });
        HttpApi.getInstance().request(weather);
    }


    /**
     * 根据经纬度以及时间获取天气预报信息
     * @param lat
     * @param lon
     * @param time
     */
    public void getWeather(double lat, double lon, long time){
        BaseModel weather = new WeatherBaseModel("", getWeatherParams(lat, lon, time), new IHttpCallback() {
            @Override
            public void result(HttpResponse httpResponse) {
                String str = WeatherBaseModel.parseResponse(httpResponse);
            }
        });
        HttpApi.getInstance().request(weather);
    }


    /**
     * 搜索城市天气信息
     * @param city
     */
    public void searchCity(String city){
        BaseModel searchCity = new WeatherBaseModel("", getSearchCityParams(city), new IHttpCallback() {
            @Override
            public void result(HttpResponse httpResponse) {
                String res = WeatherBaseModel.parseResponse(httpResponse);
            }
        });
        HttpApi.getInstance().request(searchCity);
    }


    /**
     * 根据gps定位的经纬度获取用户当前位置的天气信息
     * @param lat
     * @param lon
     */
    public void getAddressFromLatLng(double lat, double lon){
        BaseModel latlngAddress = new WeatherBaseModel("", getAddressFromLatLngParams(lat, lon), new IHttpCallback() {
            @Override
            public void result(HttpResponse httpResponse) {
                String res = WeatherBaseModel.parseResponse(httpResponse);
            }
        });

        HttpApi.getInstance().request(latlngAddress);
    }


    private String getApiKeyParams() {
        StringBuilder sb = new StringBuilder("version=1&");
        sb.append("type=1");
        sb.append("&app_id=").append("com.graph.weather.forecast.channel");
        sb.append("&request=https://ipfind.co/me?auth=TOH_KEY");
        if (L.debug) L.d("test", "" + sb.toString());
        return CodeX.encode(sb.toString());
    }


    private String getWeatherParams(double lat, double lon) {
        DecimalFormat format = new DecimalFormat("#.00");
        format.format(lat).replaceAll("\\,", ".");
        format.format(lon).replaceAll("\\,", ".");
        StringBuilder paramSb = new StringBuilder("version=1&");
        paramSb.append("type=2");
        paramSb.append("&app_id=").append("com.graph.weather.forecast.channel");
        paramSb.append("&request=https://api.forecast.io/forecast/TOH_KEY/");
        paramSb.append(lat).append(",").append(lon);
        if (L.debug) L.d("test", "" + paramSb.toString());
        return CodeX.encode(paramSb.toString());
    }

    private String getWeatherParams(double lat, double lon, long time) {
        StringBuilder paramSb = new StringBuilder("version=1&");
        paramSb.append("type=5");
        paramSb.append("&app_id=").append("com.graph.weather.forecast.channel");
        paramSb.append("&request=https://api.forecast.io/forecast/TOH_KEY/");
        paramSb.append(lat).append(",").append(lon);
        paramSb.append(",").append(time);
        if (L.debug) L.d("Test", "" + paramSb.toString());
        return CodeX.encode(paramSb.toString()).trim();
    }


    private String getSearchCityParams(String city){
        StringBuilder paramSb = new StringBuilder("version=1&");
        paramSb.append("type=4");
        paramSb.append("&app_id=").append("com.graph.weather.forecast.channel");
        paramSb.append("&request=https://maps.googleapis.com/maps/api/geocode/json?");
        paramSb.append("address=").append(CodeX.f(city));
        paramSb.append("&key=TOH_KEY");
        if (L.debug) L.d("test", "" + paramSb.toString());
        return CodeX.encode(paramSb.toString());
    }

    private String getAddressFromLatLngParams(double lat, double lon) {
        DecimalFormat format = new DecimalFormat("#.####");
        format.format(lat).replaceAll("\\,", ".");
        format.format(lon).replaceAll("\\,", ".");
        StringBuilder paramSb = new StringBuilder("version=1&");
        paramSb.append("type=4");
        paramSb.append("&app_id=").append("com.graph.weather.forecast.channel");
        paramSb.append("&request=https://maps.googleapis.com/maps/api/geocode/json?");
        paramSb.append("latlng=").append(lat).append(",").append(lon);
        paramSb.append("&key=TOH_KEY");
        return CodeX.encode(paramSb.toString());
    }
}
