package cn.liujinnan.weather.entity.request;

public class WeatherRequest {

    private String key;

    private String location;

    private String language;

    private String unit;

    public static WeatherRequest defaultRequest() {
        WeatherRequest request = new WeatherRequest();
        request.setKey("ysdw8fxaeuyabw3n");
        request.setLocation("WS0E9D8WN298");        // 广州： WS0E9D8WN298
        request.setLanguage("zh-Hans");             //简体
        request.setUnit("c");           //温度
        return  request;
    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
