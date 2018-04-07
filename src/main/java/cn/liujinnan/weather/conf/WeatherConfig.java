package cn.liujinnan.weather.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *  天气相关配置
 */
@Component
public class WeatherConfig {

    /**
     * 心知天气key配置
     * api 文档链接 https://www.seniverse.com/doc
     */
    @Value("${seniverse.key}")
    private String seniverseKey;

    /**
     * 获取某地区的天气， 当前默认配置为广州
     */
    @Value("${seniverse.location:WS0E9D8WN298}")
    private String seniverseLocation;

    /**
     * 语言配置， 默认简体中文
     */
    @Value("${seniverse.language:zh-Hans}")
    private String seniverseLanguage;

    /**
     *  温度配置
     */
    @Value("${seniverse.unit:c}")
    private String seniverseUnit;


    public String getSeniverseKey() {
        return seniverseKey;
    }

    public void setSeniverseKey(String seniverseKey) {
        this.seniverseKey = seniverseKey;
    }

    public String getSeniverseLocation() {
        return seniverseLocation;
    }

    public void setSeniverseLocation(String seniverseLocation) {
        this.seniverseLocation = seniverseLocation;
    }

    public String getSeniverseLanguage() {
        return seniverseLanguage;
    }

    public void setSeniverseLanguage(String seniverseLanguage) {
        this.seniverseLanguage = seniverseLanguage;
    }

    public String getSeniverseUnit() {
        return seniverseUnit;
    }

    public void setSeniverseUnit(String seniverseUnit) {
        this.seniverseUnit = seniverseUnit;
    }
}
