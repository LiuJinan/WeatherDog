package cn.liujinnan.weather.service.impl;

import cn.liujinnan.weather.conf.WeatherConfig;
import cn.liujinnan.weather.entity.request.WeatherRequest;
import cn.liujinnan.weather.entity.response.WeatherRespNow;
import cn.liujinnan.weather.entity.response.WeatherResponse;
import cn.liujinnan.weather.sender.EmailMesssage;
import cn.liujinnan.weather.sender.Sender;
import cn.liujinnan.weather.service.WeatherService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

@Service
public class WeatherServiceImpl implements WeatherService {

    private static final Logger logger = LoggerFactory.getLogger(WeatherServiceImpl.class);

    @Autowired
    @Qualifier("EmailSender")
    private Sender sender;

    @Autowired
    private WeatherConfig weatherConfig;

    private boolean isRainyDay() {

        WeatherRespNow weatherRespNow = getWeatherNow();
        if (Objects.nonNull(weatherRespNow)) {
            int code = Integer.parseInt(weatherRespNow.getCode());
            if (code >= 10 && code <= 19) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean notifyOfRain() {
        if (isRainyDay()) {
            logger.info("[WeatherServiceImpl]今天会下雨， 准备发送邮件通知");
            WeatherRespNow weatherRespNow = getWeatherNow();
            EmailMesssage emailMesssage = EmailMesssage.newBuilder()
                    .buildContext(weatherRespNow.getText() + " " + weatherRespNow.getTemperature())
                    .buildNickname("天气狗")
                    .buildReceiver("jinanvae@163.com")
                    .buildTitle("带伞")
                    .build();

            sender.send(emailMesssage);

            return true;
        }
        logger.info("[WeatherServiceImpl]今天不下雨，不作通知");

        return false;
    }

    /**
     * 请求第三方，获取天气信息
     * @return
     */
    private String requestWeatherInfo() {
        // 构建请求参数值
        WeatherRequest weatherRequest = new WeatherRequest();
        weatherRequest.setKey(weatherConfig.getSeniverseKey());
        weatherRequest.setLanguage(weatherConfig.getSeniverseLanguage());
        weatherRequest.setLocation(weatherConfig.getSeniverseLocation());
        weatherRequest.setUnit(weatherConfig.getSeniverseUnit());
        // 构建请求url
        StringBuffer url = new StringBuffer();
        url.append("https://api.seniverse.com/v3/weather/now.json");
        url.append("?key=").append(weatherRequest.getKey());
        url.append("&location=").append(weatherRequest.getLocation());
        url.append("&language=").append(weatherRequest.getLanguage());
        url.append("&unit=").append(weatherRequest.getUnit());
        // 请求第三方
        Request request = new Request.Builder().get()
                .url(url.toString())
                .build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);

        String result = "";
        try {
            Response response = call.execute();
            if (Objects.isNull(response) || Objects.isNull(response.body())) {
                return result;
            }
            result = response.body().string();
            logger.info("获取天气信息： {}", result);
            response.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 获取当前天气状况
     * @return
     */
    private WeatherRespNow getWeatherNow() {
        WeatherResponse weatherResponse = null;
        try {
            String responseStr = requestWeatherInfo();
            JSONObject jsonObject = JSON.parseObject(responseStr);
            String jsonStr = jsonObject.get("results").toString();
            jsonStr = jsonStr.substring(1, jsonStr.length() - 1);
            weatherResponse = JSON.parseObject(jsonStr, WeatherResponse.class);
        } catch (Exception e) {
            logger.error("转换json失败", e);
        }

        if (Objects.nonNull(weatherResponse) && Objects.nonNull(weatherResponse.getNow())) {
            logger.info("获取的当前天气信息不为空");

            return weatherResponse.getNow();
        }

        return null;
    }
}
