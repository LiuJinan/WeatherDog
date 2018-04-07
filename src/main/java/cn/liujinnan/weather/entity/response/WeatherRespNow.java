package cn.liujinnan.weather.entity.response;

import cn.liujinnan.weather.constant.WeatherState;

public class WeatherRespNow {

    private String text;

    /**
     * @see WeatherState
     */
    private String code;

    private String temperature;


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
}
