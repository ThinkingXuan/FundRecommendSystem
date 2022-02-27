package com.njupt.controller;

import com.njupt.common.ReturnMessage;
import com.njupt.common.ReturnType;
import com.njupt.common.ServerResponse;
import com.njupt.dao.IBaseFundInfoDao;
import com.njupt.entity.request.RequestUserTendency;
import com.njupt.service.IFundDetailService;
import com.njupt.service.IFundService;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;


@RequestMapping("frs/fund")
@RestController
@Validated
public class FundController {

    @Autowired
    IFundService fundService;
    @Autowired
    IFundDetailService fundDetailService;


    // 添加或更改用户的投资倾向
    @PostMapping("/usertendency")
    public ServerResponse saveUserTendency(@Valid @RequestBody RequestUserTendency userTendency) {

        ReturnMessage returnMessage = fundService.saveUserTendency(userTendency);

        if (returnMessage.getType() != ReturnType.SUCCESS.getType()) {
            return ServerResponse.ofError(returnMessage.getMessage());
        }
        return ServerResponse.ofSuccess(returnMessage.getMessage());

    }

    // 获取用户的投资倾向
    @GetMapping("/getusertendency")
    public ServerResponse getUserTendency(@Valid @NotBlank(message = "用户名不能为空") String username) {

        ReturnMessage returnMessage = fundService.getUserTendency(username);

        if (returnMessage.getType() != ReturnType.SUCCESS.getType()) {
            return ServerResponse.ofError(returnMessage.getMessage());
        }
        return ServerResponse.ofSuccess(returnMessage.getMessage(), returnMessage.getObj());
    }


    /**
     * 分页多条件获取所有基金组合信息
     *
     * @param currentPage    当前页
     * @param pageSize       每一页数据量
     * @param defaultOrder   默认排序
     * @param investStrategy 投资策略
     * @param managerType    管理类型
     * @param from           来自于那个平台
     * @return ServerResponse
     */

    @GetMapping("/getfundlist")
    public ServerResponse getFundsList(
            @RequestParam(name = "current_page") int currentPage,
            @RequestParam(name = "page_size") int pageSize,
            @RequestParam(name = "default_order", required = false) String defaultOrder,
            @RequestParam(name = "invest_strategy", required = false) String investStrategy,
            @RequestParam(name = "manager_type", required = false) String managerType,
            @RequestParam(name = "from", required = false) String from) {

        ReturnMessage fundsPage = fundService.getFundsPage(defaultOrder, currentPage, pageSize, investStrategy, managerType, from);
        return ServerResponse.ofSuccess(fundsPage.getMessage(), fundsPage.getObj());
    }

    /**
     * 全局搜索
     *
     * @param keyword 搜索关键字
     * @return
     */
    @GetMapping("/searchfund")
    public ServerResponse searchFund(@Valid @NotBlank(message = "关键词不能为空") String keyword) {

        ReturnMessage funds = fundService.searchFunds(keyword);
        if (funds.getType() != ReturnType.SUCCESS.getType()) {
            return ServerResponse.ofError(funds.getMessage());
        }
        return ServerResponse.ofSuccess(funds.getMessage(), funds.getObj());
    }

    @GetMapping("/funddetail")
    public ServerResponse getFundDetail(@Valid @NotBlank(message = "字段不能为空")
                                        @RequestParam(name = "plan_code") String planCode) {
        ReturnMessage fundDetail = fundDetailService.getFundDetail(planCode);
        return ServerResponse.ofSuccess(fundDetail.getMessage(), fundDetail.getObj());
    }

    @GetMapping("/fundgrowth")
    public ServerResponse getFundGrowth(@Valid @NotBlank(message = "字段不能为空")
                                        @RequestParam(name = "plan_code") String planCode) {
        ReturnMessage fundDetail = fundDetailService.getFundGrowth(planCode);
        return ServerResponse.ofSuccess(fundDetail.getMessage(), fundDetail.getObj());
    }

    @GetMapping("/recommender")
    public ServerResponse getRecommenderFund(@Valid @NotBlank(message = "用户名不能为空") String username) {

        ReturnMessage funds = fundService.getRecommendFund(username);
        if (funds.getType() != ReturnType.SUCCESS.getType()) {
            return ServerResponse.ofError(funds.getMessage());
        }
        return ServerResponse.ofSuccess(funds.getMessage(), funds.getObj());
    }
}
