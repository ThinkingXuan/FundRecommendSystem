package test;


import com.github.houbb.markdown.toc.core.impl.AtxMarkdownToc;
import com.njupt.datacrawl.net.APIManage;
import com.njupt.datacrawl.util.FileUtil;
import com.njupt.datacrawl.util.JsonPathUtil;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.Test;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * test Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>2月 19, 2021</pre>
 */
public class testTest {

    @Test
    public void testTest2() {
        String content = "123456789";
        System.out.println(content.substring(1, content.length() - 1));


    }

    @Test
    public void testTest1() throws Exception {
//    String content = "";
//    JSONArray jsonArray = new JSONArray(content);
//    System.out.println(jsonArray.get(0));
//    System.out.println(jsonArray.get(1));

        String content2 = "{\"FollowFlag\":true,\"IsRedem\":false,\"IsTransfer\":false,\"IsContinueBuy\":true,\"ZHBOnway\":false,\"FollowCount\":0,\"HasBonus\":false,\"Type\":\"3\",\"IntervalRate\":0.2853,\"ZHBIntervalDesc\":\"成立以来年化\",\"ZHBType\":\"S1\",\"ZHBLabel\":null,\"ZHBIntervalType\":9,\"ZHBIntervalRate\":16.54,\"IsPrivacy\":0,\"AuditingFields\":[],\"MCompositeScore\":95.0,\"QCompositeScore\":88.0,\"RiskLabels\":null,\"Audit\":null,\"GradingIndexDesc\":\"近3月评分指标\",\"IsAllowOpen\":true,\"Self\":false,\"ManualReviewState\":2,\"PassportID\":\"6873135317429102\",\"FollowFans\":\"11\",\"FollowBuyCount\":1,\"NeverHoldShare\":false,\"AssetVol\":\"132846.3776\",\"Alias\":null,\"State\":1,\"Style\":\"激进型\",\"Property\":\"闲钱打理\",\"FollowedSubAccount\":null,\"FollowSubAName\":null,\"FollowSubAOpenFlag\":0,\"FollowSubAState\":0,\"FollowedCustomerNo\":null,\"CustomizeProperty\":\"\",\"SubAccountNoIdea\":\"敢于承担风险，从风险中捕捉投资机会，力求获取高额收益。\",\"LastCloseTime\":\"0001-01-01\",\"IsCanOpen\":true,\"ReasonOfCanNotOpen\":null,\"Name\":\"Wangxy\",\"OpenState\":2,\"VipType\":null,\"SubType\":\"0\",\"Tips\":null,\"DailyProfit\":\"0.0900\",\"NavDate\":\"02-26\",\"Nav\":\"1.3199\",\"ProfitdrawDownRate\":null,\"Sharp\":null,\"Profit\":null,\"MaxRetrace\":null,\"Tradecount\":null,\"CompositeScore\":null,\"ProfitGrade\":null,\"StdGrade\":null,\"StableGrade\":null,\"OperationGrade\":null,\"HoldGrade\":null,\"Evaluation\":\"该组合在运行期间的收益大幅跑赢基准，并超过了我们设置的理论值，表现出了较强的投资能力。控制最大回撤的能力较强，择时能力较弱，削弱了组合的累计收益，风险收益比要高于基准，在一半的交易日里能跑赢基准。\",\"GroupCreatTime\":\"2019-05-06\",\"WorkDays\":\"运作662天\",\"ProfitIntervalType\":\"1\",\"PositionProportion\":[{\"Title\":\"QDII型\",\"Code\":\"4\",\"Proportion\":\"97.03%\",\"TargetProportion\":\"0.00%\"},{\"Title\":\"指数型\",\"Code\":\"7\",\"Proportion\":\"2.97%\",\"TargetProportion\":\"0.00%\"},{\"Title\":\"货币理财型\",\"Code\":\"5\",\"Proportion\":\"0.00%\",\"TargetProportion\":\"0.00%\"}],\"FundProportion\":[{\"Title\":\"易方达原油C类人民币\",\"Code\":\"003321\",\"Proportion\":\"92.05%\",\"RealProportion\":null,\"RealProportionVal\":0,\"FdProfit\":\"--\",\"NaturalDayProfit\":null,\"TargetProportion\":\"0.00%\",\"Amount\":\"122290.9158\",\"RealAmount\":null,\"RealAmountVal\":0,\"Type\":\"4\",\"TypeName\":\"QDII型\",\"HoldAmountChange\":\"18925.9750\",\"SubAEnableSh\":true,\"ProportionChange\":\"0.0115\",\"FundHoldChange\":0,\"FundHasBonus\":false},{\"Title\":\"易方达亚洲精选股票\",\"Code\":\"118001\",\"Proportion\":\"3.40%\",\"RealProportion\":null,\"RealProportionVal\":0,\"FdProfit\":\"--\",\"NaturalDayProfit\":null,\"TargetProportion\":\"0.00%\",\"Amount\":\"4520.8416\",\"RealAmount\":null,\"RealAmountVal\":0,\"Type\":\"4\",\"TypeName\":\"QDII型\",\"HoldAmountChange\":\"190.9094\",\"SubAEnableSh\":true,\"ProportionChange\":\"-0.0041\",\"FundHoldChange\":0,\"FundHasBonus\":false},{\"Title\":\"工银中证传媒指数(LOF)A\",\"Code\":\"164818\",\"Proportion\":\"2.97%\",\"RealProportion\":null,\"RealProportionVal\":0,\"FdProfit\":\"--\",\"NaturalDayProfit\":null,\"TargetProportion\":\"0.00%\",\"Amount\":\"3938.9114\",\"RealAmount\":null,\"RealAmountVal\":0,\"Type\":\"7\",\"TypeName\":\"指数型\",\"HoldAmountChange\":\"-186.0377\",\"SubAEnableSh\":true,\"ProportionChange\":\"-0.0066\",\"FundHoldChange\":0,\"FundHasBonus\":false},{\"Title\":\"华安标普全球石油指数\",\"Code\":\"160416\",\"Proportion\":\"1.16%\",\"RealProportion\":null,\"RealProportionVal\":0,\"FdProfit\":\"--\",\"NaturalDayProfit\":null,\"TargetProportion\":\"0.00%\",\"Amount\":\"1537.5245\",\"RealAmount\":null,\"RealAmountVal\":0,\"Type\":\"4\",\"TypeName\":\"QDII型\",\"HoldAmountChange\":\"175.7171\",\"SubAEnableSh\":true,\"ProportionChange\":\"-0.0004\",\"FundHoldChange\":0,\"FundHasBonus\":false},{\"Title\":\"交银中证海外中国互联网指数\",\"Code\":\"164906\",\"Proportion\":\"0.42%\",\"RealProportion\":null,\"RealProportionVal\":0,\"FdProfit\":\"--\",\"NaturalDayProfit\":null,\"TargetProportion\":\"0.00%\",\"Amount\":\"558.1744\",\"RealAmount\":null,\"RealAmountVal\":0,\"Type\":\"4\",\"TypeName\":\"QDII型\",\"HoldAmountChange\":\"25.2840\",\"SubAEnableSh\":true,\"ProportionChange\":\"-0.0005\",\"FundHoldChange\":0,\"FundHasBonus\":false},{\"Title\":\"天弘弘运宝货币A\",\"Code\":\"001386\",\"Proportion\":\"0.00%\",\"RealProportion\":null,\"RealProportionVal\":0,\"FdProfit\":\"--\",\"NaturalDayProfit\":null,\"TargetProportion\":\"0.00%\",\"Amount\":\"0.0100\",\"RealAmount\":null,\"RealAmountVal\":0,\"Type\":\"5\",\"TypeName\":\"货币理财型\",\"HoldAmountChange\":\"0.0000\",\"SubAEnableSh\":true,\"ProportionChange\":\"0.0000\",\"FundHoldChange\":0,\"FundHasBonus\":false}],\"RationPlans\":[],\"SubIntervalIndexList\":[{\"IntervalType\":4,\"SubIndexList\":[{\"IndexID\":2,\"IndexName\":\"最大回撤\",\"IndexValue\":\"1.28%\",\"ReferOptimalValue\":\"0.84%\",\"ReferWorstValue\":\"16.30%\",\"Percent\":95,\"PercentEvaluate\":\"回撤很低\"},{\"IndexID\":3,\"IndexName\":\"夏普比率\",\"IndexValue\":\"0.77\",\"ReferOptimalValue\":\"0.15\",\"ReferWorstValue\":\"-0.39\",\"Percent\":100,\"PercentEvaluate\":\"性价比很高\"},{\"IndexID\":5,\"IndexName\":\"区间波动率\",\"IndexValue\":\"1.09%\",\"ReferOptimalValue\":\"2.62%\",\"ReferWorstValue\":\"0.18%\",\"Percent\":5,\"PercentEvaluate\":\"波动很小\"},{\"IndexID\":8,\"IndexName\":\"操作有效性\",\"IndexValue\":\"-1.26\",\"ReferOptimalValue\":\"--\",\"ReferWorstValue\":\"--\",\"Percent\":0,\"PercentEvaluate\":\"--\"},{\"IndexID\":9,\"IndexName\":\"胜率\",\"IndexValue\":\"73.68%\",\"ReferOptimalValue\":\"--\",\"ReferWorstValue\":\"--\",\"Percent\":0,\"PercentEvaluate\":\"--\"},{\"IndexID\":11,\"IndexName\":\"收益回撤比\",\"IndexValue\":\"12.63\",\"ReferOptimalValue\":\"0.87\",\"ReferWorstValue\":\"-0.95\",\"Percent\":100,\"PercentEvaluate\":\"盈利质量很高\"}]},{\"IntervalType\":3,\"SubIndexList\":[{\"IndexID\":2,\"IndexName\":\"最大回撤\",\"IndexValue\":\"3.24%\",\"ReferOptimalValue\":\"2.29%\",\"ReferWorstValue\":\"17.11%\",\"Percent\":95,\"PercentEvaluate\":\"回撤很低\"},{\"IndexID\":3,\"IndexName\":\"夏普比率\",\"IndexValue\":\"0.41\",\"ReferOptimalValue\":\"0.19\",\"ReferWorstValue\":\"-0.15\",\"Percent\":100,\"PercentEvaluate\":\"性价比很高\"},{\"IndexID\":5,\"IndexName\":\"区间波动率\",\"IndexValue\":\"1.07%\",\"ReferOptimalValue\":\"2.17%\",\"ReferWorstValue\":\"0.31%\",\"Percent\":10,\"PercentEvaluate\":\"波动很小\"},{\"IndexID\":8,\"IndexName\":\"操作有效性\",\"IndexValue\":\"-5.87\",\"ReferOptimalValue\":\"--\",\"ReferWorstValue\":\"--\",\"Percent\":0,\"PercentEvaluate\":\"--\"},{\"IndexID\":9,\"IndexName\":\"胜率\",\"IndexValue\":\"50.82%\",\"ReferOptimalValue\":\"--\",\"ReferWorstValue\":\"--\",\"Percent\":0,\"PercentEvaluate\":\"--\"},{\"IndexID\":11,\"IndexName\":\"收益回撤比\",\"IndexValue\":\"8.81\",\"ReferOptimalValue\":\"2.01\",\"ReferWorstValue\":\"-0.34\",\"Percent\":100,\"PercentEvaluate\":\"盈利质量很高\"}]},{\"IntervalType\":2,\"SubIndexList\":[{\"IndexID\":2,\"IndexName\":\"最大回撤\",\"IndexValue\":\"18.62%\",\"ReferOptimalValue\":\"4.51%\",\"ReferWorstValue\":\"18.33%\",\"Percent\":0,\"PercentEvaluate\":\"回撤较大\"},{\"IndexID\":3,\"IndexName\":\"夏普比率\",\"IndexValue\":\"0.13\",\"ReferOptimalValue\":\"0.14\",\"ReferWorstValue\":\"-0.09\",\"Percent\":95,\"PercentEvaluate\":\"性价比很高\"},{\"IndexID\":5,\"IndexName\":\"区间波动率\",\"IndexValue\":\"1.46%\",\"ReferOptimalValue\":\"2.00%\",\"ReferWorstValue\":\"0.46%\",\"Percent\":60,\"PercentEvaluate\":\"波动尚可\"},{\"IndexID\":8,\"IndexName\":\"操作有效性\",\"IndexValue\":\"-8.64\",\"ReferOptimalValue\":\"--\",\"ReferWorstValue\":\"--\",\"Percent\":0,\"PercentEvaluate\":\"--\"},{\"IndexID\":9,\"IndexName\":\"胜率\",\"IndexValue\":\"49.58%\",\"ReferOptimalValue\":\"--\",\"ReferWorstValue\":\"--\",\"Percent\":0,\"PercentEvaluate\":\"--\"},{\"IndexID\":11,\"IndexName\":\"收益回撤比\",\"IndexValue\":\"1.30\",\"ReferOptimalValue\":\"3.12\",\"ReferWorstValue\":\"-0.51\",\"Percent\":55,\"PercentEvaluate\":\"盈利质量较高\"}]},{\"IntervalType\":1,\"SubIndexList\":[{\"IndexID\":2,\"IndexName\":\"最大回撤\",\"IndexValue\":\"29.07%\",\"ReferOptimalValue\":\"6.51%\",\"ReferWorstValue\":\"24.01%\",\"Percent\":0,\"PercentEvaluate\":\"回撤较大\"},{\"IndexID\":3,\"IndexName\":\"夏普比率\",\"IndexValue\":\"0.04\",\"ReferOptimalValue\":\"0.15\",\"ReferWorstValue\":\"-0.03\",\"Percent\":15,\"PercentEvaluate\":\"性价比较低\"},{\"IndexID\":5,\"IndexName\":\"区间波动率\",\"IndexValue\":\"1.57%\",\"ReferOptimalValue\":\"2.09%\",\"ReferWorstValue\":\"0.46%\",\"Percent\":55,\"PercentEvaluate\":\"波动尚可\"},{\"IndexID\":8,\"IndexName\":\"操作有效性\",\"IndexValue\":\"-15.03\",\"ReferOptimalValue\":\"--\",\"ReferWorstValue\":\"--\",\"Percent\":0,\"PercentEvaluate\":\"--\"},{\"IndexID\":9,\"IndexName\":\"胜率\",\"IndexValue\":\"48.97%\",\"ReferOptimalValue\":\"--\",\"ReferWorstValue\":\"--\",\"Percent\":0,\"PercentEvaluate\":\"--\"},{\"IndexID\":11,\"IndexName\":\"收益回撤比\",\"IndexValue\":\"0.60\",\"ReferOptimalValue\":\"5.15\",\"ReferWorstValue\":\"0.16\",\"Percent\":5,\"PercentEvaluate\":\"盈利质量较低\"}]}],\"ScoreDetail\":{\"CompositeScore\":\"88\",\"IntervalType\":\"3\",\"IsPunish\":0,\"ScoreList\":[{\"IndexType\":1,\"IndexName\":\"收益率\",\"IndexComment\":\"根据阶段内收益率及排名综合计算得出，收益率越高且排名越靠前得分越高。\",\"ScoreRatio\":\"(权重50%)\",\"Score\":99.0,\"IsPunish\":1,\"PunishRatio\":\"修正系数100%\",\"ScoreWithPunish\":99.0,\"ScoreWithPunishShow\":\"99\"},{\"IndexType\":2,\"IndexName\":\"最大回撤\",\"IndexComment\":\"根据阶段内最大回撤计算得出，最大回撤越低得分越高。\",\"ScoreRatio\":\"(权重15%)\",\"Score\":100.0,\"IsPunish\":1,\"PunishRatio\":\"修正系数100%\",\"ScoreWithPunish\":100.0,\"ScoreWithPunishShow\":\"100\"},{\"IndexType\":3,\"IndexName\":\"平衡能力\",\"IndexComment\":\"根据阶段内夏普比率计算得出，夏普比率越大得分越高。\",\"ScoreRatio\":\"(权重10%)\",\"Score\":100.0,\"IsPunish\":1,\"PunishRatio\":\"修正系数100%\",\"ScoreWithPunish\":100.0,\"ScoreWithPunishShow\":\"100\"},{\"IndexType\":8,\"IndexName\":\"择时能力\",\"IndexComment\":\"根据阶段内操作有效性在同类型中排名计算得出，排名越靠前得分越高。\",\"ScoreRatio\":\"(权重15%)\",\"Score\":43.0,\"IsPunish\":0,\"PunishRatio\":\"\",\"ScoreWithPunish\":43.0,\"ScoreWithPunishShow\":\"43\"},{\"IndexType\":9,\"IndexName\":\"胜率\",\"IndexComment\":\"根据阶段内日超额收益胜率计算得出，胜率越高得分越高。\",\"ScoreRatio\":\"(权重10%)\",\"Score\":72.0,\"IsPunish\":0,\"PunishRatio\":\"\",\"ScoreWithPunish\":72.0,\"ScoreWithPunishShow\":\"72\"}],\"Evaluation\":null,\"PunishWarning\":null},\"TagList\":[],\"WealthNo\":\"\",\"MySubAccountNo\":null,\"SubATitle\":\"赚取第一桶金\",\"FollowSuggestTip\":null,\"DeviatioTip\":null,\"NeedFollow\":false,\"IsOnRestore\":false,\"TjCode\":\"\"}";
        JSONObject jsonObject = new JSONObject(content2);
        jsonObject.put("subAccountNo", "123213213");
        System.out.println(jsonObject.toString());

    }

    @Test
    public void testTest3() throws Exception {
        String content = FileUtil.readByLines("C:\\Users\\Administrator\\Desktop\\基金推荐系统\\fund.md");
        String replace = content.replace("\"", "\\\"").replace("\n","\\n");
//        System.out.println("content = " + content);
//        String content = "```json\\n" +
//                "{ \\\"username\\\": 100\\n" +
//                "}\\n" +
//                "```";
        String str = "{\"text\":\"" + replace + "\"}";

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), str);
        Call<ResponseBody> markDown = APIManage.getInstence().getMarkDown().getMarkDown(requestBody);
        String result = markDown.execute().body().string();
        System.out.println("result = " + result);

    }



    @Test
    public void testTest4() throws Exception {
//        String content = FileUtil.readByLines("C:\\Users\\Administrator\\Desktop\\基金推荐系统\\前后台API对接.md");
        AtxMarkdownToc.newInstance().genTocFile("C:\\Users\\Administrator\\Desktop\\基金推荐系统\\fund.md");

//        System.out.println("result = " + result);

    }

}
