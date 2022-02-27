package com.njupt.controller;

import com.google.gson.Gson;
import com.njupt.common.JsonPathUtil;
import com.njupt.common.ReturnMessage;
import com.njupt.common.ReturnType;
import com.njupt.common.ServerResponse;
import com.njupt.entity.TiantianFundGrowth;
import com.njupt.entity.request.RequestUserTendency;
import com.njupt.net.APIManage;
import com.njupt.service.IFundDetailService;
import com.njupt.service.IFundService;
import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import retrofit2.Call;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.IOException;


@RequestMapping("frs/funddetail")
@RestController
@Validated
public class FundDetailController {

    @Autowired
    IFundDetailService fundDetailService;

    @GetMapping("/detail")
    public ServerResponse getFundDetail(@Valid @NotBlank(message = "字段不能为空")
                                        @RequestParam(name = "plan_code") String planCode) {
        ReturnMessage fundDetail = fundDetailService.getFundDetail(planCode);
        return ServerResponse.ofSuccess(fundDetail.getMessage(), fundDetail.getObj());
    }

    @GetMapping("/growth")
    public ServerResponse getFundGrowth(@Valid @NotBlank(message = "字段不能为空")
                                        @RequestParam(name = "plan_code") String planCode) {
        ReturnMessage fundDetail = fundDetailService.getFundGrowth(planCode);
        return ServerResponse.ofSuccess(fundDetail.getMessage(), fundDetail.getObj());
    }

    @GetMapping("/plan")
    public ServerResponse getFundPlan(@Valid @NotBlank(message = "字段不能为空")
                                      @RequestParam(name = "plan_code") String planCode) {
        ReturnMessage fundDetail = fundDetailService.getFundPlan(planCode);
        return ServerResponse.ofSuccess(fundDetail.getMessage(), fundDetail.getObj());
    }

    @GetMapping("/indicator")
    public ServerResponse getFundIndicator(@Valid @NotBlank(message = "字段不能为空")
                                           @RequestParam(name = "plan_code") String planCode) {
        ReturnMessage fundDetail = fundDetailService.getFundIndicator(planCode);
        return ServerResponse.ofSuccess(fundDetail.getMessage(), fundDetail.getObj());
    }

    @GetMapping("/summary")
    public ServerResponse getFundSummary(@Valid @NotBlank(message = "字段不能为空")
                                         @RequestParam(name = "plan_code") String planCode) {
        ReturnMessage fundDetail = fundDetailService.getFundSummary(planCode);
        return ServerResponse.ofSuccess(fundDetail.getMessage(), fundDetail.getObj());
    }

    @GetMapping("/tiantian_detail")
    public ServerResponse getTiantianFundDetail(@Valid @NotBlank(message = "字段不能为空")
                                                @RequestParam(name = "plan_code") String subAccountNo) {
        ReturnMessage fundDetail = fundDetailService.getTiantainFundDetail(subAccountNo);
        return ServerResponse.ofSuccess(fundDetail.getMessage(), fundDetail.getObj());
    }

    @GetMapping("/tiantian_fund_growth")
    public ServerResponse getTiantianFundGrowth(@Valid @NotBlank(message = "字段不能为空")
                                                @RequestParam(name = "plan_code") String subAccountNo) {

        // JSON -> entity
        String content = getAllFundDetailReward(subAccountNo);
        Gson gson = new Gson();
        TiantianFundGrowth tiantianFundGrowth = gson.fromJson(content.substring(1,content.length()-1), TiantianFundGrowth.class);

        return ServerResponse.ofSuccess("获取成功", tiantianFundGrowth);
    }

    public String getAllFundDetailReward(String SubAccountNo) {

        // IntervalType = 1 近1年
        // IntervalType = 2 近6个月
        // IntervalType = 3 近3个月
        // IntervalType = 4 近1个月
        // IntervalType = 9 成立来

        String content = "DataType=1&IntervalType=9&SubCustomerNo=" + SubAccountNo + "&UserId=b6fb0b59a3834343a81df51161eaff04&SubAccountNo=10017235&deviceid=0b78d7420e1fe4758b35bde0f6601da6%7C%7C344783030144568&version=6.3.7&product=EFund&plat=Iphone&appVersion=6.3.7&ServerVersion=6.3.7&Version=6.3.7&CToken=-rj6k-1nek1dqkekeceq11unrqdnrhuh.8&UToken=q--8-a16d8--qfnr8nraqn--nuf8jrac0161-hb4.8&MobileKey=0b78d7420e1fe4758b35bde0f6601da6%7C%7C344783030144568&PhoneType=android&Passportid=1234396095681234&CustomerNo=b6fb0b59a3834343a81df51161eaff04";
        Call<ResponseBody> response = APIManage
                .getInstence()
                .getTianTianFundGrowthService()
                .getFundDetailReward(content);


        return JsonPathUtil.getTiantianFundDetailData(getHttpResponseString(response));
    }

    private String getHttpResponseString(Call<ResponseBody> message2) {
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
