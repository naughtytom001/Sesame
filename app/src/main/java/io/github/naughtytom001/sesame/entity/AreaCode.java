package io.github.naughtytom001.sesame.entity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.github.naughtytom001.sesame.util.FileUtil;
import io.github.naughtytom001.sesame.util.Log;

public class AreaCode extends IdAndName {
    private static final String TAG = AreaCode.class.getSimpleName();
    private static List<AreaCode> list;

    public AreaCode(String i, String n) {
        id = i;
        name = n;
    }

    public static List<AreaCode> getList() {
        if (list == null) {
            String cityCode = FileUtil.readFromFile(FileUtil.getCityCodeFile());
            JSONArray ja;
            try {
                ja = new JSONArray(cityCode);
            } catch (Throwable e) {
                cityCode = "[" +
                        "{\"cityCode\":\"320100\",\"cityName\":\"南京市\"}," +
                        "{\"cityCode\":\"330100\",\"cityName\":\"杭州市\"}," +
                        "{\"cityCode\":\"350100\",\"cityName\":\"福州市\"}," +
                        "{\"cityCode\":\"370100\",\"cityName\":\"济南市\"}," +
                        "{\"cityCode\":\"430100\",\"cityName\":\"长沙市\"}," +
                        "{\"cityCode\":\"440100\",\"cityName\":\"广州市\"}" +
                        "]";
                try {
                    ja = new JSONArray(cityCode);
                } catch (JSONException ex) {
                    ja = new JSONArray();
                }
            }

            list = new ArrayList<>();
            try {
                for (int i = 0; i < ja.length(); i++) {
                    JSONObject jo = ja.getJSONObject(i);
                    list.add(new AreaCode(jo.getString("cityCode"), jo.getString("cityName")));
                }
            } catch (Throwable th) {
                Log.printStackTrace(TAG, th);
            }
        }
        return list;
    }

}
