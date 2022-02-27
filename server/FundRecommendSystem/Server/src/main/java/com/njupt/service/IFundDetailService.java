package com.njupt.service;

import com.njupt.common.ReturnMessage;
import org.springframework.stereotype.Service;


public interface IFundDetailService {

    ReturnMessage getFundDetail(String planCode);

    ReturnMessage getFundGrowth(String planCode);

    ReturnMessage getFundPlan(String planCode);

    ReturnMessage getFundIndicator(String planCode);

    ReturnMessage getFundSummary(String planCode);

    ReturnMessage getTiantainFundDetail(String subAccount);


}
