package com.njupt.net;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface FundAPI {

    // 天天基金组合详情页
    @POST("User/SubA/SubAProfit")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    Call<ResponseBody> getFundDetailReward(@Body String content);
}
