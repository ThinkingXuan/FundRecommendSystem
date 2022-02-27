package com.njupt.datacrawl.crawl;

import com.njupt.datacrawl.net.APIManage;
import com.njupt.datacrawl.util.JsonPathUtil;
import okhttp3.ResponseBody;
import retrofit2.Call;

import java.io.IOException;
import java.util.HashMap;

public class TiantianCrawl {
    /**
     * 天天基金 组合宝数据 PageType为不同的页卡数据。
     * SteadyManage  稳健理财
     * MoneyManage  活钱管理
     * EarningManage  追求回报
     * GeniusComb  牛人组合
     * CombinationBag 精选
     */

    //
    public static String GetFundCombination(String pageType) {
        String message = GetBaseFundCombination(pageType);
        return JsonPathUtil.getTiantianCustomArray(message);
    }


    public static String GetFundAllCombination(String condition, String pageNum, String pageCount) {
        String message = GetTianTianFundAllData(condition, pageNum, pageCount);
        return JsonPathUtil.getTiantianAllCustomArray(message);
    }


    public static String GetDuanjuanFund(String tab, String pageNum, String pageCount) {
        Call<ResponseBody> call = APIManage
                .getInstence()
                .getDuanjuanFundCombinationService()
                .getDanjuanFund(tab, pageNum, pageCount);
        return JsonPathUtil.getDanjuanFund(getHttpResponseString(call));
    }


    public static String GetAllFundDetail(String SubAccountNo) {
        HashMap<String, String> map = new HashMap<>();
        map.put("product", "EFund");
        map.put("appVersion", "6.3.7");
        map.put("ServerVersion", "6.3.7");
        map.put("Passportid", "1234396095681234");
        map.put("SubAccountNo", SubAccountNo);
        map.put("deviceid", "12fgd7420esfgs758b35bde0f6601da6");
        map.put("version", "6.3.7");
        map.put("CustomerNo", "12340b59a3831234a81df51161eaff04");
        map.put("PhoneType", "android");
        map.put("Version", "6.3.7");
        map.put("MobileKey", "ab78da420e2fe4758b35bde0f6601da6||344781032144578");
        map.put("UserId", "12340b59a3831234a81df51161eaff04");
        map.put("UToken", "q--8-a16d8--qfnr8nraqn--nuf8jrac0161-hb4.9");
        map.put("CToken", "-rj6k-1nek1dqkesqceq11unrqdnrhuh.8");

        Call<ResponseBody> response = APIManage
                .getInstence()
                .getTianTianFundDetailService()
                .getFundDetail(map);
        String content = JsonPathUtil.getTiantianFundDetailData(getHttpResponseString(response));
        response.cancel();
        return content;
    }

    public static String GetAllFundDetailReward(String SubAccountNo) {

        String content = "DataType=1&IntervalType=1&SubCustomerNo=" + SubAccountNo + "&UserId=b6fb0b59a3834343a81df51161eaff04&SubAccountNo=10017235&deviceid=0b78d7420e1fe4758b35bde0f6601da6%7C%7C344783030144568&version=6.3.7&product=EFund&plat=Iphone&appVersion=6.3.7&ServerVersion=6.3.7&Version=6.3.7&CToken=-rj6k-1nek1dqkekeceq11unrqdnrhuh.8&UToken=q--8-a16d8--qfnr8nraqn--nuf8jrac0161-hb4.8&MobileKey=0b78d7420e1fe4758b35bde0f6601da6%7C%7C344783030144568&PhoneType=android&Passportid=1234396095681234&CustomerNo=b6fb0b59a3834343a81df51161eaff04";
        Call<ResponseBody> response = APIManage
                .getInstence()
                .getTianTianFundDetailService()
                .getFundDetailReward(content);
        return JsonPathUtil.getTiantianFundDetailData(getHttpResponseString(response));
    }


    // 获取组合宝首页推荐数据
    private static String GetBaseFundCombination(String pageType) {
        Call<ResponseBody> response = APIManage
                .getInstence()
                .getTianTianFundService()
                .getMessage2(pageType);

        return getHttpResponseString(response);
    }

    //  获取组合宝个人组合排行所有数据
    private static String GetTianTianFundAllData(String condition, String pageNum, String pageCount) {
        Call<ResponseBody> response = APIManage
                .getInstence()
                .getTianTianFundService2()
                .getTiantianAll(condition, pageNum, pageCount);

        return getHttpResponseString(response);
    }

    private static String getHttpResponseString(Call<ResponseBody> message2) {
        String mess = "";
        try {
            ResponseBody body = message2.execute().body();
            if (null != body) {
                mess = body.string();
            } else {
                return "{}";
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return mess;
    }
}
