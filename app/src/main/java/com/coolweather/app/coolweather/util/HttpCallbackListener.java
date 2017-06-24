package com.coolweather.app.coolweather.util;

/**
 * Created by lenovo on 2017/6/24.
 */

public interface HttpCallbackListener {
    void onFinish(String response);

    void onError(Exception e);
}
