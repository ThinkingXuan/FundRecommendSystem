package com.njupt.datacrawl.net;


import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.HashMap;

public interface FundAPI {

    // 天天基金 组合宝数据 PageType为不同的页卡数据。
    @GET("/AppoperationApi/Modules/GetModuleConfig?appversion=6.2.0&UserId=b6fb0b59a3834343a81df51161eaff04")
    Call<ResponseBody> getMessage2(@Query("PageType") String PageType);

    // 天天基金个人组合排行
    @GET("FundMCApi/FundMSubAccount/MSA20GetCommonTypeSubAccountList?product=EFund&appVersion=6.3.7&ServerVersion=6.3.7&deviceid=0b78d742024758b35bde0f6601da6%7C%7C344783030144568&version=6.3.7&plat=Iphone&orderField=MPINGFEN%3Adesc")
    Call<ResponseBody> getTiantianAll(@Query("condition") String condition, @Query("pageNum") String pageNum, @Query("pageCount") String pageCount);

    // 天天基金组合详情页
    @POST("User/SubA/SubAGradingIndexDetail")
    @FormUrlEncoded
    @Headers("Content-Type: application/x-www-form-urlencoded")
    Call<ResponseBody> getFundDetail(@FieldMap HashMap<String, String> data);

    // 天天基金组合详情页
    @POST("User/SubA/SubAProfit")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    Call<ResponseBody> getFundDetailReward(@Body String content);


    // 蛋卷基金组合首页
    @GET("djapi/fundx/portfolio/v3/plan/united/page?default_order=0&invest_strategy=&type=&manager_type=&yield_between=&mz_between=")
    Call<ResponseBody> getDanjuanFund(@Query("tab") String tab, @Query("page") String pageNum, @Query("size") String pageCount);

    // 组合解读
    @GET("djapi/v2/plan/detail/homepage")
    Call<ResponseBody> getDanjuanHomePage(@Query("plan_code") String plan_code);

    @GET("djapi/plan/{plan_code}")
    Call<ResponseBody> getDanjuanPlan(@Path("plan_code") String plan_code);

    // 组合指示
    @GET("djapi/plan/nav/indicator")
    Call<ResponseBody> getDanjuanIndicator(@Query("plan_code") String plan_code);

    // 组合概览
    @GET("djapi/plan/position/summary")
    Call<ResponseBody> getDanjuanSummary(@Query("plan_code") String plan_code);

    // 组合详细
    @GET("djapi/plan/position/detail")
    Call<ResponseBody> getDanjuanDetail(@Query("plan_code") String plan_code);

    // 组合每天首页
    @GET("djapi/plan/v2/growth/{plan_code}?type=2&day=all")
    Call<ResponseBody> getDanjuanGrowth(@Path("plan_code") String plan_code);

    @Headers({"Accept: application/vnd.github.v3+json"})
    @POST("markdown")
    Call<ResponseBody> getMarkDown(@Body RequestBody body);

    // 天天基金组合详情页
    @POST("User/SubA/SubAProfit")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    Call<ResponseBody> getTiantianGrowth(@Body String content);
}
