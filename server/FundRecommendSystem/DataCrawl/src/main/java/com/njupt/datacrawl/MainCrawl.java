package com.njupt.datacrawl;

import com.njupt.datacrawl.crawl.CrawlControl;
import com.njupt.datacrawl.net.APIManage;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainCrawl {

    // 爬虫入口
    public static void main(String[] args) {
        CrawlControl crawlControl = new CrawlControl();
        crawlControl.startCrawl();

    }
}
