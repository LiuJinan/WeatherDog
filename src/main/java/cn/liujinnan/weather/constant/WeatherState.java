package cn.liujinnan.weather.constant;

/**
 * 详情状态请查看： https://www.seniverse.com/doc#code
 */
public enum WeatherState {

    /**
     *
     */
    Shower("10", "阵雨"),
    Thundershower("11", "雷阵雨"),
    ThundershowerWithHail("12", "雷阵雨伴有冰雹"),
    LightRain("13", "小雨"),
    ModerateRain("14", "中雨"),
    HeavyRain("15", "大雨"),
    Storm("16", "暴雨"),
    HeavyStorm("17", "大暴雨"),
    SevereStorm("18", "特大暴雨"),
    IceRain("19", "冻雨");


    private String code;
    private String desc;

    WeatherState(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
