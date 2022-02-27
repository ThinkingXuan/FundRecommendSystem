package com.njupt.service.impl;

import com.njupt.common.*;
import com.njupt.dao.*;
import com.njupt.entity.*;
import com.njupt.entity.request.RequestUserTendency;
import com.njupt.service.IFundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Pattern;
import java.util.zip.DeflaterOutputStream;

@Service
public class FundServiceImpl implements IFundService {

    @Autowired
    IFundTendencyDao fundTendencyDao;
    @Autowired
    IUserDao userDao;
    @Autowired
    IBaseFundInfoDao baseFundInfoDao;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private IFundRecommenderDao fundRecommenderDao;

    @Override
    public ReturnMessage saveUserTendency(RequestUserTendency requestUserTendency) {

        // 查询此用户是否存在
        User nowUser = userDao.findByUsernameEquals(requestUserTendency.getUsername());
        if (null == nowUser) {
            return ReturnMessage.OfReturn(ReturnType.ERROR.getType(), "不存在此用户");
        }

        // 类型转换
        UserTendency nowUserTendency = new UserTendency();
        nowUserTendency.setUsername(requestUserTendency.getUsername());
        nowUserTendency.setInvestAmount(requestUserTendency.getInvestAmount());
        nowUserTendency.setInvestDuration(requestUserTendency.getInvestDuration());
        nowUserTendency.setProfit(requestUserTendency.getProfit());
        nowUserTendency.setType(requestUserTendency.getType());
        nowUserTendency.setDropdown(requestUserTendency.getDropdown());

        // 查询用户的基金投资倾向是否存在
        UserTendency oldUserTendency = fundTendencyDao.findByUsernameEquals(requestUserTendency.getUsername());

        // Save方法，没有_id的数据不存在创建一个，如果存在就直接修改数据
        String tendencyID;
        if (oldUserTendency != null) {
            nowUserTendency.set_id(oldUserTendency.get_id());
        }
        tendencyID = fundTendencyDao.save(nowUserTendency).get_id();

        if ("".equals(tendencyID)) {
            return ReturnMessage.OfReturn(ReturnType.ERROR.getType(), "保存错误");
        }
        return ReturnMessage.OfReturn(ReturnType.SUCCESS.getType(), "保存成功");
    }

    @Override
    public ReturnMessage getUserTendency(String username) {
        // 查询此用户是否存在
        User nowUser = userDao.findByUsernameEquals(username);
        if (null == nowUser) {
            return ReturnMessage.OfReturn(ReturnType.ERROR.getType(), "不存在此用户");
        }
        UserTendency userTendency = fundTendencyDao.findByUsernameEquals(username);
        return ReturnMessage.OfReturn(ReturnType.SUCCESS.getType(), "获取成功", userTendency);
    }

    @Override
    public ReturnMessage getFundsPage(String defaultOrder, int currentPage, int pageSize, String investStrategy, String managerType, String from) {

        // 获取Mongo的查询条件Query
        Query query = new Query();
        Criteria criteria = new Criteria();
        List<Criteria> criteriaList = new ArrayList<>();

        if (!"".equals(investStrategy)) {
            criteriaList.add(Criteria.where("fund_type").is(Integer.valueOf(investStrategy)));
        }
        if (!"".equals(managerType)) {
            criteriaList.add(Criteria.where("manager_type").is(Integer.valueOf(managerType)));
        }
        if (!"".equals(from)) {
            criteriaList.add(Criteria.where("from").is(Integer.valueOf(from)));
        }

        if (criteriaList.size() > 0) {
            criteria.andOperator(criteriaList.toArray(new Criteria[0]));
            query.addCriteria(criteria);
        }

        // 排序方式
        Map<String, String> mapValue = new HashMap<>();
        Map<String, Sort.Direction> mapDirection = new HashMap<>();

        mapValue.put("0", "final_prior");
        mapDirection.put("0", Sort.Direction.DESC);

        mapValue.put("1", "yield");
        mapDirection.put("1", Sort.Direction.DESC);

        mapValue.put("2", "all_yield");
        mapDirection.put("2", Sort.Direction.DESC);

        mapValue.put("3", "max_drawdown");
        mapDirection.put("3", Sort.Direction.ASC);

        mapValue.put("4", "sharpe");
        mapDirection.put("4", Sort.Direction.DESC);

        mapValue.put("5", "follow_num");
        mapDirection.put("5", Sort.Direction.DESC);

        mapValue.put("6", "sales");
        mapDirection.put("6", Sort.Direction.DESC);


        if (!"".equals(defaultOrder)) {
            query.with(Sort.by(mapDirection.get(defaultOrder), mapValue.get(defaultOrder)));
        }

        // 计算分页
        MongoUtil<BaseFund> mongoUtil = new MongoUtil<>();
        mongoUtil.start(currentPage, pageSize, query);
        // 开始查询
        List<BaseFund> baseFunds = mongoTemplate.find(query, BaseFund.class);
        // 计算实际的数据的数量
        long total = mongoTemplate.count(query, BaseFund.class);

        // 封装PageHelper
        PageHelper<BaseFund> pageHelper = mongoUtil.pageHelper(total, baseFunds);

        return ReturnMessage.OfReturn(ReturnType.SUCCESS.getType(), "获取成功", pageHelper);
    }

    @Override
    public ReturnMessage searchFunds(String keyword) {

        // 预编译正则表达式
        // 左匹配
        Pattern leftPattern = Pattern.compile("^" + ExprSpecial.SpecialWord(keyword) + ".*$", Pattern.CASE_INSENSITIVE);
        // 模糊匹配
        Pattern pattern = Pattern.compile("^.*" + ExprSpecial.SpecialWord(keyword) + ".*$", Pattern.CASE_INSENSITIVE);

        // 获取Mongo的查询条件Query并查询
        List<BaseFund> baseFunds = new ArrayList<>();
        if (!"".equals(keyword)) {
            Query planQuery = new Query().addCriteria(Criteria.where("plan_name").regex(pattern));
            long count1 = mongoTemplate.count(planQuery, BaseFund.class);
            if (count1 > 0) {
                baseFunds.addAll(mongoTemplate.find(planQuery, BaseFund.class));
            }

            Query managerQuery = new Query().addCriteria(Criteria.where("manager_name").regex(pattern));
            long count2 = mongoTemplate.count(managerQuery, BaseFund.class);
            if (count2 > 0) {
                baseFunds.addAll(mongoTemplate.find(managerQuery, BaseFund.class));
            }

            Query planCodeQuery = new Query().addCriteria(Criteria.where("plan_code").regex(leftPattern));
            long count3 = mongoTemplate.count(planCodeQuery, BaseFund.class);
            if (count3 > 0) {
                baseFunds.addAll(mongoTemplate.find(planCodeQuery, BaseFund.class));
            }
        }

        return ReturnMessage.OfReturn(ReturnType.SUCCESS.getType(), "查询成功", baseFunds);
    }

    @Override
    public ReturnMessage getRecommendFund(String username) {

        UserTendency userTendency = fundTendencyDao.findByUsernameEquals(username);

        if (userTendency == null) {
            return ReturnMessage.OfReturn(ReturnType.ERROR.getType(), "请先填写投资意向");
        }

        int fund_type = userTendency.getType();
        // 计算此用户的final_prior
        double final_prior = (userTendency.getProfit() - userTendency.getDropdown() + 50 + 2 * 100);

        List<BaseFund> fundList = baseFundInfoDao.findByFundTypeEqualsAndFinalPriorIsGreaterThanEqual(fund_type, 0);

        fundList.sort(new Comparator<BaseFund>() {
            @Override
            public int compare(BaseFund o1, BaseFund o2) {

                double final_prior1 = Math.abs(o1.getFinalPrior() - final_prior);
                double final_prior2 = Math.abs(o2.getFinalPrior() - final_prior);
                if (final_prior2 == final_prior1) {
                    return 0;
                }
                // 从小到大
                return final_prior1 > final_prior2 ? 1 : -1;
            }
        });

        List<BaseFund> finalList;
        if (fundList.size() >= 10) {
            finalList = fundList.subList(0, 10);
        } else {
            finalList = fundList;
        }

        if (finalList.size() <= 0) {
            ReturnMessage.OfReturn(ReturnType.SUCCESS.getType(), "没有查到", finalList);
        }

        return ReturnMessage.OfReturn(ReturnType.SUCCESS.getType(), "获取成功", finalList);

    }

}
