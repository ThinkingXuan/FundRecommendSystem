package com.njupt.datacrawl.crawl;

import com.njupt.datacrawl.util.FileUtil;
import com.njupt.datacrawl.util.JsonPathUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;


public class CrawlControl {
    /**
     * 天天基金 组合宝数据 PageType为不同的页卡数据。
     * SteadyManage  稳健理财  0
     * MoneyManage  活钱管理   1
     * EarningManage  追求回报 2
     * GeniusComb  牛人组合    3
     * CombinationBag 精选     4
     */

    private List<String> subAllAccountNos = new ArrayList<>();
    private List<String> planCodeNos = new ArrayList<>();

    public void startCrawl() {
        Work1();  // 天天基金组合宝推荐主页爬取
        Work2();  // 天天基金组合宝实盘秀主页推荐爬取
        Work3();  // 天天基金组合宝组合持仓等信息爬取
        Work4();  // 蛋卷基金组合主要爬取
        Work5(); // 蛋卷基金组合细节爬取
    }

    // 爬取天天基金组合宝首页推荐数据
    private void Work1() {

        String SteadyManage = "SteadyManage";
        String MoneyManage = "MoneyManage";
        String EarningManage = "EarningManage";
        String GeniusComb = "GeniusComb";
        String CombinationBag = "CombinationBag";
        String[] pageType = {SteadyManage, MoneyManage, EarningManage, GeniusComb, CombinationBag};

        List<String> subAccountNos = new ArrayList<>();
        // 爬取不同PageType数据提取SubAccountNo,处理后存入文件
        for (String s : pageType) {
            String result = TiantianCrawl.GetFundCombination(s);

            //
            try {
                // JSON的集合提取出来
                JSONArray jsonArray = new JSONArray(result);
                for (int i = 0; i < jsonArray.length(); i++) {
                    // 利用JSONObject添加fund_type类型
                    JSONObject jsonObject = new JSONObject(jsonArray.get(i).toString());
                    jsonObject.put("fund_type", s);
                    appendFile(jsonObject + "\n", "DataCrawl/data/tiantian/TianTianCombinationFund.json");
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            // 获取结果中的SubAccountNo字段，用户查询基金的详细信息
            List<String> subAccountNo = JsonPathUtil.getTiantianFundSubAccountNo(result);
            // 添加进入集合
            subAccountNos.addAll(subAccountNo);
            // 文件存储
            saveFile(result, "DataCrawl/data/" + s + ".json");
        }
//        saveFile(Arrays.toString(subAccountNos.toArray()), "DataCrawl/data/tiantian/SubAccountNos.json");
    }

    // 爬取天天基金组合宝实盘秀所有组合数据
    private void Work2() {
        /*
         * 天天基金组合实盘秀的的查询条件
         * ACCSTY%3AS1 激进型 0
         * ACCSTY%3AS2 灵活型 1
         * ACCSTY%3AS3 稳健型 2
         */
        String[] conditions = {"ACCSTY%3AS1", "ACCSTY%3AS2", "ACCSTY%3AS3"};
        Map<String, String> mapCondition = new HashMap<>();
        mapCondition.put("ACCSTY%3AS1", "0");
        mapCondition.put("ACCSTY%3AS2", "1");
        mapCondition.put("ACCSTY%3AS3", "2");

        for (String s : conditions) {
            String result = TiantianCrawl.GetFundAllCombination(s, "1", "20000");

            try {
                // JSON的集合提取出来
                JSONArray jsonArray = new JSONArray(result);
                for (int i = 0; i < jsonArray.length(); i++) {
                    // 利用JSONObject添加fund_type类型
                    JSONObject jsonObject = new JSONObject(jsonArray.get(i).toString());
                    jsonObject.put("fund_type", Integer.valueOf(mapCondition.get(s)));
                    jsonObject.put("from", 1);             // 1 天天，2 蛋卷

                    // 0：基金达人，1：普通基名
                    if (jsonObject.get("VipType").equals("1")) {
                        jsonObject.put("manager_type", 0);
                    } else {
                        jsonObject.put("manager_type", 1);
                    }
                    appendFile(jsonObject + "\n", "DataCrawl/data/tiantian/CombinationAllData.json");
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            // 获取结果中的SubAccountNo字段，用户查询基金的详细信息
            List<String> subAccountNo = JsonPathUtil.getTiantianFundActualShowSubAccountNo(result);
            // 添加进入集合
            subAllAccountNos.addAll(subAccountNo);
        }

        // 文件SubAllAccountNos存储
        saveFile(Arrays.toString(subAllAccountNos.toArray()), "DataCrawl/data/tiantian/SubAccountNos.json");

    }

    private void Work3() {

        for (int i = 0; i < subAllAccountNos.size(); i++) {
            String result = TiantianCrawl.GetAllFundDetail(subAllAccountNos.get(i));
            if (!result.equals("")) {
                String content = result.substring(1, result.length() - 1);

                try {
                    JSONObject jsonObject = new JSONObject(content);
                    jsonObject.put("subAccountNo", subAllAccountNos.get(i));
                    appendFile(jsonObject.toString() + "\n", "DataCrawl/data/tiantian/CombinationFundDetail.json");
                    System.out.println("总共" + subAllAccountNos.size() + "条   " + "已完成" + (i + 1) + "条    " + "完成度:" + (i + 1.0) / subAllAccountNos.size() + "%    " + "已完成的代码：" + subAllAccountNos.get(i));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            // 获取每日的Growth  文件太大了，不要爬取了。直接请求
//            String result2 = TiantianCrawl.GetAllFundDetailReward(subAllAccountNos.get(i));
//            if (!result2.equals("")) {
//                String content = result2.substring(1, result2.length() - 1);
//
//                try {
//                    JSONObject jsonObject = new JSONObject(content);
//                    jsonObject.put("subAccountNo", subAllAccountNos.get(i));
//                    appendFile(jsonObject.toString() + "\n", "DataCrawl/data/tiantian/CombinationFundGrowth.json");
//                    System.out.println("总共" + subAllAccountNos.size() + "条   " + "已完成" + (i + 1) + "条    " + "完成度:" + (i + 1.0) / subAllAccountNos.size() + "%    " + "已完成的代码：" + subAllAccountNos.get(i));
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
        }
    }


    private void Work4() {
        /*
         *  蛋卷的标签
         * tab = 4   积极增值
         * tab = 2   稳健理财
         * tab = 0   零钱管理
         *
         * * 天天基金组合实盘秀的的查询条件
         * ACCSTY%3AS1 激进型 0
         * ACCSTY%3AS2 灵活型 1
         * ACCSTY%3AS3 稳健型 2
         *
         *
         *共同产生fund_type，以天天基金的为基准
         * tab = 4   积极增值 对应  0
         * tab = 0   零钱管理 对应  1
         * tab = 2   稳健理财 对应  2
         *
         *
         */
        String[] tabs = {"0", "2", "4"};

        for (String s : tabs) {
            // 抓取蛋卷基金组合所有数据
            String result = DanjuanCrawl.GetDuanjuanFund(s, "1", "10000");

            // 获取蛋卷基金的planCode
            List<String> plancodes = JsonPathUtil.getDanjuanPlanCode(result);
            planCodeNos.addAll(plancodes);
            try {
                // JSON的集合提取出来
                JSONArray jsonArray = new JSONArray(result);
                for (int i = 0; i < jsonArray.length(); i++) {
                    // 利用JSONObject添加fund_type类型
                    JSONObject jsonObject = new JSONObject(jsonArray.get(i).toString());
                    if (s.equals("4")) {
                        jsonObject.put("fund_type", 0);
                    } else if (s.equals("0")) {
                        jsonObject.put("fund_type", 1);
                    } else if (s.equals("2")) {
                        jsonObject.put("fund_type", 2);
                    }
                    jsonObject.put("from", 2);  // 1 天天，2 蛋卷
                    jsonObject.put("manager_type", 0);  // 0：基金达人，1：普通基名
                    appendFile(jsonObject.toString() + "\n", "DataCrawl/data/danjuan/DanjuanCombinationFund.json");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    void Work5() {
        for (String planCode : planCodeNos) {
            // Homepage
            String result1 = DanjuanCrawl.GetDanjuanFundHomepage(planCode);

            try {
                JSONObject jsonObject = new JSONObject(result1.substring(1, result1.length() - 1));
                jsonObject.put("plan_code", planCode);
                appendFile(jsonObject.toString() + "\n", "DataCrawl/data/danjuan/combinationdetail/hompage.json");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            String result2 = DanjuanCrawl.GetDuanjuanFundPlan(planCode);
            try {
                JSONObject jsonObject = new JSONObject(result2.substring(1, result2.length() - 1));
                jsonObject.put("plan_code", planCode);
                appendFile(jsonObject.toString() + "\n", "DataCrawl/data/danjuan/combinationdetail/plan.json");
            } catch (JSONException e) {
                e.printStackTrace();
            }


            String result3 = DanjuanCrawl.GetDuanjuanFundIndicator(planCode);
            appendFile(result3.substring(1, result3.length() - 1) + "\n", "DataCrawl/data/danjuan/combinationdetail/indcator.json");


            String result4 = DanjuanCrawl.GetDuanjuanFundSummary(planCode);
            try {
                JSONObject jsonObject = new JSONObject(result4.substring(1, result4.length() - 1));
                jsonObject.put("plan_code", planCode);
                appendFile(jsonObject.toString() + "\n", "DataCrawl/data/danjuan/combinationdetail/summary.json");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            String result5 = DanjuanCrawl.GetDuanjuanFundDetail(planCode);

            try {
                JSONObject jsonObject = new JSONObject(result5.substring(1, result5.length() - 1));
                jsonObject.put("plan_code", planCode);
                appendFile(jsonObject.toString() + "\n", "DataCrawl/data/danjuan/combinationdetail/detail.json");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String result6 = DanjuanCrawl.GetDuanjuanFundGrowth(planCode);

            try {
                JSONObject jsonObject = new JSONObject(result6.substring(1, result6.length() - 1));
                jsonObject.put("plan_code", planCode);
                appendFile(jsonObject.toString() + "\n", "DataCrawl/data/danjuan/combinationdetail/growth.json");
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }


    private static void saveFile(String content, String path) {
        boolean isSuccess = FileUtil.saveAs(content, path);
        if (isSuccess) {
            System.out.println("\n" + path + "写入文件成功");
        } else {
            System.out.println("\n" + path + "写入文件失败");
        }
    }

    private static void appendFile(String content, String path) {
        boolean isSuccess = FileUtil.appendFile(content, path);
        if (isSuccess) {
            System.out.println("\n" + path + "内容追加成功");
        } else {
            System.out.println("\n" + path + "内容追加失败");
        }
    }

}
