package com.njupt.service;

import com.njupt.common.ReturnMessage;
import com.njupt.entity.request.RequestUserTendency;

public interface IFundService {

    /**
     * 保存用户投资倾向
     *
     * @param requestUserTendency
     * @return
     */
    ReturnMessage saveUserTendency(RequestUserTendency requestUserTendency);

    /**
     * 获取用户投资倾向
     *
     * @param username
     * @return
     */
    ReturnMessage getUserTendency(String username);

    ReturnMessage getFundsPage(String defaultOrder,int currentPage, int pageSiz, String investStrategy, String managerType, String from);

    ReturnMessage searchFunds(String keyword);

    ReturnMessage getRecommendFund(String username);


}
