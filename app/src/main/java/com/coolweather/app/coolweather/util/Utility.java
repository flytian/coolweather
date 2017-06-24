package com.coolweather.app.coolweather.util;

/**
 * Created by lenovo on 2017/6/24.
 */

import android.text.TextUtils;

import com.coolweather.app.coolweather.db.CoolWeatherDB;
import com.coolweather.app.coolweather.model.City;
import com.coolweather.app.coolweather.model.County;
import com.coolweather.app.coolweather.model.Province;

/**
 * 解析处理GET请求返回的数据的工具类
 */
public class Utility {
    /**
     * 解析和处理服务器返回的省级数据
     */
    public synchronized  static  boolean handleProvincesResponse(CoolWeatherDB coolWeatherDB,
                                                                 String response)
    {
        if(!TextUtils.isEmpty(response )){
            String[] allProvinces = response.split(",");
            if(allProvinces !=null && allProvinces.length>0){
                for(String p :allProvinces){
                    String[] array =  p.split("\\|");
                    Province province = new Province();
                    province.setProvinceCode(array[0]);
                    province.setProvinceName(array[1]);

                    //将 解析出来  的数据        存储到Province 表
                    coolWeatherDB.saveProvince(province);
                }
                return true;
            }
        }
        return  false;
    }

    public  synchronized  static  boolean handleCitiesResponse(CoolWeatherDB coolWeatherDB,
                                                               String response,int procinceId)
    {
        if(!TextUtils.isEmpty(response )){
            String[] allCitys = response.split(",");
            if(allCitys!=null&& allCitys.length>0){

                for(String  c:allCitys){
                    String[] array = c.split("\\|");
                    City city = new City();
                    city.setProvinceId(procinceId);
                    city.setCityCode(array[0]);
                    city.setCityName(array[1]);
                    coolWeatherDB.saveCity(city);
                }
                return  true;
            }
        }
        return  false;
    }


    /**
     * 解析和处理服务器返回的县级数据
     */
    public static boolean handleCountiesResponse(CoolWeatherDB coolWeatherDB,
                                                 String response, int cityId) {
        if (!TextUtils.isEmpty(response)) {
            String[] allCounties = response.split(",");
            if (allCounties != null && allCounties.length > 0) {
                for (String c : allCounties) {
                    String[] array = c.split("\\|");
                    County county = new County();
                    county.setCountyCode(array[0]);
                    county.setCountyName(array[1]);
                    county.setCityId(cityId);
                    // 将解析出来的数据存储到County表
                    coolWeatherDB.saveCounty(county);
                }
                return true;
            }
        }
        return false;
    }

}
