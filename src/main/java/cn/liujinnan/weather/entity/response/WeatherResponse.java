package cn.liujinnan.weather.entity.response;

import java.util.Date;

public class WeatherResponse {

    private WeatherRespNow now;

    private Date last_update;

    public WeatherRespNow getNow() {
        return now;
    }

    public void setNow(WeatherRespNow now) {
        this.now = now;
    }

    public Date getLast_update() {
        return last_update;
    }

    public void setLast_update(Date last_update) {
        this.last_update = last_update;
    }
}
