package com.njupt.common;


import java.util.List;

public class JsonPathUtil {


    // 组合宝个人基金详情页的JSON提取
    public static String getTiantianFundDetailData(String jsonString) {
        String JsonPath = "$..Data";
        return getString(jsonString, JsonPath);
    }

    private static String getString(String jsonString, String JsonPath) {
        if (null != jsonString && !jsonString.equals("") && !jsonString.equals("{}")) {
            return com.jayway.jsonpath.JsonPath.read(jsonString, JsonPath).toString();
        }
        return "";
    }


}
