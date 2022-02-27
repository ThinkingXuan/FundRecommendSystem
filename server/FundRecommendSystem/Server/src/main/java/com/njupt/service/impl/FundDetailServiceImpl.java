package com.njupt.service.impl;

import com.njupt.common.ReturnMessage;
import com.njupt.common.ReturnType;
import com.njupt.dao.*;
import com.njupt.entity.*;
import com.njupt.service.IFundDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FundDetailServiceImpl implements IFundDetailService {

    @Autowired
    IFundDetailDao fundDetailDao;
    @Autowired
    IFundGrowthDao fundGrowthDao;

    @Autowired
    IFundPlanDao fundPlanDao;

    @Autowired
    IFundIndactorDao fundIndactorDao;

    @Autowired
    IFundSummaryDao fundSummaryDao;

    @Autowired
    ITianTianFundDetailDao tianTianFundDetailDao;


    @Override
    public ReturnMessage getFundDetail(String planCode) {
        FundDetail fundDetail = fundDetailDao.findByPlanCodeEquals(planCode);
        if (null != fundDetail) {
            return ReturnMessage.OfReturn(ReturnType.SUCCESS.getType(), "获取成功", fundDetail);
        }
        return ReturnMessage.OfReturn(ReturnType.ERROR.getType(), "没有此数据");
    }

    @Override
    public ReturnMessage getFundGrowth(String planCode) {
        FundGrowth fundGrowth = fundGrowthDao.findByPlanCodeEquals(planCode);
        if (null != fundGrowth) {
            return ReturnMessage.OfReturn(ReturnType.SUCCESS.getType(), "获取成功", fundGrowth);
        }
        return ReturnMessage.OfReturn(ReturnType.ERROR.getType(), "没有此数据");
    }

    @Override
    public ReturnMessage getFundPlan(String planCode) {
        FundPlan fundPlan = fundPlanDao.findByPlanCodeEquals(planCode);
        if (null != fundPlan) {
            return ReturnMessage.OfReturn(ReturnType.SUCCESS.getType(), "获取成功", fundPlan);
        }
        return ReturnMessage.OfReturn(ReturnType.ERROR.getType(), "没有此数据");
    }

    @Override
    public ReturnMessage getFundIndicator(String planCode) {
        FundIndicator fundIndicator = fundIndactorDao.findByPlanCodeEquals(planCode);
        if (null != fundIndicator) {
            return ReturnMessage.OfReturn(ReturnType.SUCCESS.getType(), "获取成功", fundIndicator);
        }
        return ReturnMessage.OfReturn(ReturnType.ERROR.getType(), "没有此数据");
    }

    @Override
    public ReturnMessage getFundSummary(String planCode) {
        FundSummary fundSummary = fundSummaryDao.findByPlanCodeEquals(planCode);
        if (null != fundSummary) {
            return ReturnMessage.OfReturn(ReturnType.SUCCESS.getType(), "获取成功", fundSummary);
        }
        return ReturnMessage.OfReturn(ReturnType.ERROR.getType(), "没有此数据");
    }

    @Override
    public ReturnMessage getTiantainFundDetail(String subAccount) {
        TianTianFundDetail tianTianFundDetail = tianTianFundDetailDao.findBySubAccountNoEquals(subAccount);
        if (null != tianTianFundDetail) {
            return ReturnMessage.OfReturn(ReturnType.SUCCESS.getType(), "获取成功", tianTianFundDetail);
        }
        return ReturnMessage.OfReturn(ReturnType.ERROR.getType(), "没有此数据");
    }
}
