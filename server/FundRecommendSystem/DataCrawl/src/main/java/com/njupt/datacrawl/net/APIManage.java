package com.njupt.datacrawl.net;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by youxuan
 */
public class APIManage {

    private Object monitor = new Object();
    private FundAPI mFundAPI;
    private static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response originalResponse = chain.proceed(chain.request());
            int maxAge = 60;  //在线缓存一分钟可读取
            return originalResponse.newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", "public, max-age=" + maxAge)
                    .build();
        }
    };

    //网络的log日志添加拦截器
    private static HttpLoggingInterceptor sLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
        @Override
        public void log(String message) {
            System.out.println("HttpLog " + " OkHttp====message " + message);
        }
    });
    public static APIManage apiManage;
    private static File httpCacheDiretory = new File("c:\\cache", "robotquery");
    private static int cacheSize = 10 * 1024 * 1024;
    private static Cache cache = new Cache(httpCacheDiretory, cacheSize);
    private static OkHttpClient client = new OkHttpClient.Builder()
            .addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
            .addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
//            .addInterceptor(sLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY))
            .readTimeout(60, TimeUnit.SECONDS)
            .cache(cache)
            .build();

    public static APIManage getInstence() {
//        if (apiManage == null) {
//            synchronized (APIManage.class) {
//                if (apiManage == null) {
//                    apiManage = new APIManage();
//                }
//            }
//        }
        return apiManage = new APIManage();
    }

    private FundAPI getBaseFundAPI(String bastUrl) {
        if (mFundAPI == null) {
            synchronized (monitor) {
                if (mFundAPI == null) {
                    mFundAPI = new Retrofit.Builder()
                            .baseUrl(bastUrl)
                            .client(client)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                            .create(FundAPI.class);
                }
            }
        }
        return mFundAPI;
    }

    public FundAPI getTianTianFundService() {
        String baseURL = "http://appactive.1234567.com.cn";
        return getBaseFundAPI(baseURL);
    }

    public FundAPI getTianTianFundService2() {
        String baseURL = "http://groupapi.1234567.com.cn/";
        return getBaseFundAPI(baseURL);
    }

    public FundAPI getTianTianFundDetailService() {
        String baseURL = "https://tradeapilvs8.1234567.com.cn/";
        return getBaseFundAPI(baseURL);
    }

    public FundAPI getTianTianFundGrowthService() {
        String baseURL = "https://tradeapilvs8.1234567.com.cn/";
        return getBaseFundAPI(baseURL);
    }

    public FundAPI getDuanjuanFundCombinationService(){
        String baseURL = "https://danjuanfunds.com/";
        return getBaseFundAPI(baseURL);
    }

    public FundAPI getMarkDown(){
        String baseURL = "https://api.github.com/";
        return getBaseFundAPI(baseURL);
    }

}
