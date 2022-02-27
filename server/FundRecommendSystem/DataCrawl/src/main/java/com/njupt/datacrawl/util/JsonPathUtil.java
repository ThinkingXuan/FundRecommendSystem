package com.njupt.datacrawl.util;


import java.util.List;

public class JsonPathUtil {

    // 组合宝首页的JSON提取
    public static String getTiantianCustomArray(String jsonString) {
        String JsonPath = "$.datas.Modules[1].CustomArray";
        return getString(jsonString, JsonPath);
    }

    // 组合宝的JSON提取 SubAccountNo字段
    public static List<String> getTiantianFundSubAccountNo(String jsonString) {
        String JsonPath = "$..SubAccountNo";
        List<String> result = com.jayway.jsonpath.JsonPath.read(jsonString, JsonPath);
        return result;
    }

    // 组合宝的实盘秀JSON提取 SubAccountNo字段
    public static List<String> getTiantianFundActualShowSubAccountNo(String jsonString) {
        String JsonPath = "$..subAccountNo";
        List<String> result = com.jayway.jsonpath.JsonPath.read(jsonString, JsonPath);
        return result;
    }


    // 组合宝所有个人基金的JSON提取
    public static String getTiantianAllCustomArray(String jsonString) {
        String JsonPath = "$.Data.result[*]";
        return getString(jsonString, JsonPath);
    }

    // 组合宝个人基金详情页的JSON提取
    public static String getTiantianFundDetailData(String jsonString) {
        String JsonPath = "$..Data";
        return getString(jsonString, JsonPath);
    }

    // 蛋卷基金组合提取
    public static String getDanjuanFund(String jsonString) {
        String JsonPath = "$.data.items";
        return getString(jsonString, JsonPath);
    }

    // 蛋卷基金组合plan
    public static String getDanjuanFundPlan(String jsonString) {
        String JsonPath = "$..data";
        return getString(jsonString, JsonPath);
    }

    public static String getDanjuanFundHomepage(String jsonString) {
        String JsonPath = "$..data";
        return getString(jsonString, JsonPath);
    }

    public static String getDanjuanFundIndicator(String jsonString) {
        String JsonPath = "$..data";
        return getString(jsonString, JsonPath);
    }

    public static String getDanjuanFundSummary(String jsonString) {
        String JsonPath = "$..data";
        return getString(jsonString, JsonPath);
    }

    public static String getDanjuanFundDetail(String jsonString) {
        String JsonPath = "$..data";
        return getString(jsonString, JsonPath);
    }

    public static String getDanjuanFundGrowth(String jsonString) {
        String JsonPath = "$..data";
        return getString(jsonString, JsonPath);
    }

    public static List<String> getDanjuanPlanCode(String jsonString) {
        String JsonPath = "$..plan_code";
        return com.jayway.jsonpath.JsonPath.read(jsonString, JsonPath);
    }

    // 蛋卷基金组合提取
    public static List<String> getDanjuanFundList(String jsonString) {
        String JsonPath = "$.data.items";
        return com.jayway.jsonpath.JsonPath.read(jsonString, JsonPath);
    }


    private static String getString(String jsonString, String JsonPath) {
        if (null != jsonString && !jsonString.equals("") && !jsonString.equals("{}")) {
            return com.jayway.jsonpath.JsonPath.read(jsonString, JsonPath).toString();
        }
        return "";
    }


}
