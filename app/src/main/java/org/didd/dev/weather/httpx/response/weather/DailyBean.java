package org.didd.dev.weather.httpx.response.weather;

import java.util.List;

/**
 * Created by Jiangxuewu on 2017/12/5.
 */

public class DailyBean {

    /**
     * summary : No precipitation throughout the week, with temperatures rising to 74°F on Thursday.
     * icon : clear-day
     * data : [{"time":1512403200,"summary":"Mostly cloudy throughout the day.","icon":"partly-cloudy-day","sunriseTime":1512427848,"sunsetTime":1512466808,"moonPhase":0.56,"precipIntensity":3.0E-4,"precipIntensityMax":0.0018,"precipIntensityMaxTime":1512432000,"precipProbability":0.17,"precipType":"rain","temperatureHigh":68.33,"temperatureHighTime":1512460800,"temperatureLow":59.45,"temperatureLowTime":1512514800,"apparentTemperatureHigh":68.33,"apparentTemperatureHighTime":1512460800,"apparentTemperatureLow":59.45,"apparentTemperatureLowTime":1512514800,"dewPoint":49.41,"humidity":0.57,"pressure":1020.91,"windSpeed":7.13,"windGust":12.27,"windGustTime":1512421200,"windBearing":32,"cloudCover":0.63,"uvIndex":3,"uvIndexTime":1512442800,"visibility":6.22,"ozone":249.9,"temperatureMin":62.65,"temperatureMinTime":1512417600,"temperatureMax":68.33,"temperatureMaxTime":1512460800,"apparentTemperatureMin":62.65,"apparentTemperatureMinTime":1512417600,"apparentTemperatureMax":68.33,"apparentTemperatureMaxTime":1512460800},{"time":1512489600,"summary":"Mostly cloudy throughout the day.","icon":"partly-cloudy-day","sunriseTime":1512514287,"sunsetTime":1512553218,"moonPhase":0.59,"precipIntensity":3.0E-4,"precipIntensityMax":0.0025,"precipIntensityMaxTime":1512572400,"precipProbability":0.11,"precipType":"rain","temperatureHigh":71.21,"temperatureHighTime":1512540000,"temperatureLow":60.57,"temperatureLowTime":1512590400,"apparentTemperatureHigh":71.21,"apparentTemperatureHighTime":1512540000,"apparentTemperatureLow":60.57,"apparentTemperatureLowTime":1512590400,"dewPoint":49.79,"humidity":0.59,"pressure":1018.59,"windSpeed":7.58,"windGust":11.32,"windGustTime":1512507600,"windBearing":70,"cloudCover":0.65,"uvIndex":4,"uvIndexTime":1512529200,"ozone":246.73,"temperatureMin":59.45,"temperatureMinTime":1512514800,"temperatureMax":71.21,"temperatureMaxTime":1512540000,"apparentTemperatureMin":59.45,"apparentTemperatureMinTime":1512514800,"apparentTemperatureMax":71.21,"apparentTemperatureMaxTime":1512540000},{"time":1512576000,"summary":"Mostly cloudy throughout the day.","icon":"partly-cloudy-day","sunriseTime":1512600726,"sunsetTime":1512639630,"moonPhase":0.63,"precipIntensity":3.0E-4,"precipIntensityMax":0.0019,"precipIntensityMaxTime":1512576000,"precipProbability":0.07,"precipType":"rain","temperatureHigh":73.86,"temperatureHighTime":1512630000,"temperatureLow":58.88,"temperatureLowTime":1512691200,"apparentTemperatureHigh":73.86,"apparentTemperatureHighTime":1512630000,"apparentTemperatureLow":58.88,"apparentTemperatureLowTime":1512691200,"dewPoint":51.34,"humidity":0.6,"pressure":1018.72,"windSpeed":7.98,"windGust":15.88,"windGustTime":1512658800,"windBearing":36,"cloudCover":0.69,"uvIndex":4,"uvIndexTime":1512619200,"ozone":241.9,"temperatureMin":60.57,"temperatureMinTime":1512590400,"temperatureMax":73.86,"temperatureMaxTime":1512630000,"apparentTemperatureMin":60.57,"apparentTemperatureMinTime":1512590400,"apparentTemperatureMax":73.86,"apparentTemperatureMaxTime":1512630000},{"time":1512662400,"summary":"Mostly cloudy throughout the day.","icon":"partly-cloudy-day","sunriseTime":1512687164,"sunsetTime":1512726042,"moonPhase":0.67,"precipIntensity":1.0E-4,"precipIntensityMax":2.0E-4,"precipIntensityMaxTime":1512684000,"precipProbability":0.06,"precipType":"rain","temperatureHigh":69.89,"temperatureHighTime":1512716400,"temperatureLow":56.18,"temperatureLowTime":1512777600,"apparentTemperatureHigh":69.89,"apparentTemperatureHighTime":1512716400,"apparentTemperatureLow":56.18,"apparentTemperatureLowTime":1512777600,"dewPoint":42.09,"humidity":0.46,"pressure":1021.3,"windSpeed":12.06,"windGust":16.67,"windGustTime":1512687600,"windBearing":30,"cloudCover":0.73,"uvIndex":3,"uvIndexTime":1512702000,"ozone":236.6,"temperatureMin":58.88,"temperatureMinTime":1512691200,"temperatureMax":69.89,"temperatureMaxTime":1512716400,"apparentTemperatureMin":58.88,"apparentTemperatureMinTime":1512691200,"apparentTemperatureMax":69.89,"apparentTemperatureMaxTime":1512716400},{"time":1512748800,"summary":"Partly cloudy throughout the day.","icon":"partly-cloudy-day","sunriseTime":1512773602,"sunsetTime":1512812456,"moonPhase":0.7,"precipIntensity":0,"precipIntensityMax":2.0E-4,"precipIntensityMaxTime":1512824400,"precipProbability":0,"temperatureHigh":69.67,"temperatureHighTime":1512802800,"temperatureLow":56.58,"temperatureLowTime":1512864000,"apparentTemperatureHigh":69.67,"apparentTemperatureHighTime":1512802800,"apparentTemperatureLow":56.58,"apparentTemperatureLowTime":1512864000,"dewPoint":40.4,"humidity":0.46,"pressure":1020.84,"windSpeed":8.47,"windGust":11.53,"windGustTime":1512748800,"windBearing":43,"cloudCover":0.52,"uvIndex":5,"uvIndexTime":1512792000,"ozone":234.01,"temperatureMin":56.18,"temperatureMinTime":1512777600,"temperatureMax":69.67,"temperatureMaxTime":1512802800,"apparentTemperatureMin":56.18,"apparentTemperatureMinTime":1512777600,"apparentTemperatureMax":69.67,"apparentTemperatureMaxTime":1512802800},{"time":1512835200,"summary":"Clear throughout the day.","icon":"clear-day","sunriseTime":1512860039,"sunsetTime":1512898871,"moonPhase":0.74,"precipIntensity":0,"precipIntensityMax":2.0E-4,"precipIntensityMaxTime":1512885600,"precipProbability":0,"temperatureHigh":72.31,"temperatureHighTime":1512892800,"temperatureLow":55.46,"temperatureLowTime":1512946800,"apparentTemperatureHigh":72.31,"apparentTemperatureHighTime":1512892800,"apparentTemperatureLow":55.46,"apparentTemperatureLowTime":1512946800,"dewPoint":41.59,"humidity":0.46,"pressure":1019.91,"windSpeed":9.45,"windGust":11.59,"windGustTime":1512864000,"windBearing":42,"cloudCover":0.18,"uvIndex":6,"uvIndexTime":1512878400,"ozone":241.89,"temperatureMin":56.58,"temperatureMinTime":1512864000,"temperatureMax":72.31,"temperatureMaxTime":1512892800,"apparentTemperatureMin":56.58,"apparentTemperatureMinTime":1512864000,"apparentTemperatureMax":72.31,"apparentTemperatureMaxTime":1512892800},{"time":1512921600,"summary":"Clear throughout the day.","icon":"clear-day","sunriseTime":1512946476,"sunsetTime":1512985288,"moonPhase":0.78,"precipIntensity":0,"precipIntensityMax":3.0E-4,"precipIntensityMaxTime":1513004400,"precipProbability":0,"temperatureHigh":71.04,"temperatureHighTime":1512975600,"temperatureLow":58,"temperatureLowTime":1513036800,"apparentTemperatureHigh":71.04,"apparentTemperatureHighTime":1512975600,"apparentTemperatureLow":58,"apparentTemperatureLowTime":1513036800,"dewPoint":43.31,"humidity":0.5,"pressure":1020.41,"windSpeed":7.08,"windGust":16.61,"windGustTime":1513004400,"windBearing":53,"cloudCover":0.06,"uvIndex":6,"uvIndexTime":1512964800,"ozone":244.43,"temperatureMin":55.46,"temperatureMinTime":1512946800,"temperatureMax":71.04,"temperatureMaxTime":1512975600,"apparentTemperatureMin":55.46,"apparentTemperatureMinTime":1512946800,"apparentTemperatureMax":71.04,"apparentTemperatureMaxTime":1512975600},{"time":1513008000,"summary":"Clear throughout the day.","icon":"clear-day","sunriseTime":1513032913,"sunsetTime":1513071706,"moonPhase":0.81,"precipIntensity":1.0E-4,"precipIntensityMax":3.0E-4,"precipIntensityMaxTime":1513008000,"precipProbability":0.03,"precipType":"rain","temperatureHigh":68.85,"temperatureHighTime":1513062000,"temperatureLow":58.31,"temperatureLowTime":1513123200,"apparentTemperatureHigh":68.85,"apparentTemperatureHighTime":1513062000,"apparentTemperatureLow":58.31,"apparentTemperatureLowTime":1513123200,"dewPoint":47.35,"humidity":0.58,"pressure":1021.5,"windSpeed":10.16,"windGust":22.18,"windGustTime":1513090800,"windBearing":77,"cloudCover":0.07,"uvIndex":6,"uvIndexTime":1513051200,"ozone":240.91,"temperatureMin":58,"temperatureMinTime":1513036800,"temperatureMax":68.85,"temperatureMaxTime":1513062000,"apparentTemperatureMin":58,"apparentTemperatureMinTime":1513036800,"apparentTemperatureMax":68.85,"apparentTemperatureMaxTime":1513062000}]
     */

    private String summary;
    private String icon;
    private List<DataBeanX> data;

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<DataBeanX> getData() {
        return data;
    }

    public void setData(List<DataBeanX> data) {
        this.data = data;
    }

}
