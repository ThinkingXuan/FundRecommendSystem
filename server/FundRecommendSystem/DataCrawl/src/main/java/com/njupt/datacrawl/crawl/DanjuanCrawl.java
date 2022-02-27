package com.njupt.datacrawl.crawl;

import com.njupt.datacrawl.net.APIManage;
import com.njupt.datacrawl.util.JsonPathUtil;
import okhttp3.ResponseBody;
import retrofit2.Call;

import java.io.IOException;
import java.util.List;

public class DanjuanCrawl {

    public static String GetDuanjuanFund(String tab, String pageNum, String pageCount) {
        Call<ResponseBody> call = APIManage
                .getInstence()
                .getDuanjuanFundCombinationService()
                .getDanjuanFund(tab, pageNum, pageCount);
        return JsonPathUtil.getDanjuanFund(getHttpResponseString(call));
    }

    public static List<String> GetDuanjuanFundList(String tab, String pageNum, String pageCount) {
        Call<ResponseBody> call = APIManage
                .getInstence()
                .getDuanjuanFundCombinationService()
                .getDanjuanFund(tab, pageNum, pageCount);
        return JsonPathUtil.getDanjuanFundList(getHttpResponseString(call));
    }

    public static String GetDanjuanFundHomepage(String plan_code) {
        Call<ResponseBody> call = APIManage
                .getInstence()
                .getDuanjuanFundCombinationService()
                .getDanjuanHomePage(plan_code);
        return JsonPathUtil.getDanjuanFundHomepage(getHttpResponseString(call));
    }

    public static String GetDuanjuanFundPlan(String plan_code) {
        Call<ResponseBody> call = APIManage
                .getInstence()
                .getDuanjuanFundCombinationService()
                .getDanjuanPlan(plan_code);
        return JsonPathUtil.getDanjuanFundPlan(getHttpResponseString(call));
    }

    public static String GetDuanjuanFundIndicator(String plan_code) {
        Call<ResponseBody> call = APIManage
                .getInstence()
                .getDuanjuanFundCombinationService()
                .getDanjuanIndicator(plan_code);
        return JsonPathUtil.getDanjuanFundIndicator(getHttpResponseString(call));
    }

    public static String GetDuanjuanFundSummary(String plan_code) {
        Call<ResponseBody> call = APIManage
                .getInstence()
                .getDuanjuanFundCombinationService()
                .getDanjuanSummary(plan_code);
        return JsonPathUtil.getDanjuanFundSummary(getHttpResponseString(call));
    }

    public static String GetDuanjuanFundDetail(String plan_code) {
        Call<ResponseBody> call = APIManage
                .getInstence()
                .getDuanjuanFundCombinationService()
                .getDanjuanDetail(plan_code);
        return JsonPathUtil.getDanjuanFundDetail(getHttpResponseString(call));
    }

    public static String GetDuanjuanFundGrowth(String plan_code) {
        Call<ResponseBody> call = APIManage
                .getInstence()
                .getDuanjuanFundCombinationService()
                .getDanjuanGrowth(plan_code);
        return JsonPathUtil.getDanjuanFundGrowth(getHttpResponseString(call));
    }

    private static String getHttpResponseString(Call<ResponseBody> message2) {
        String mess = "";
        try {
            mess = message2.execute().body().string();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return mess;
    }
}
