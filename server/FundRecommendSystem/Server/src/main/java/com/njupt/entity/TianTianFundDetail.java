package com.njupt.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "tiantian_fund_detail")
public class TianTianFundDetail {
    private String _id;
    private String AssetVol;
    private List<String> AuditingFields;
    private String CustomizeProperty;
    private String DailyProfit;
    private String Evaluation;
    private int FollowBuyCount;
    private int FollowCount;
    private String FollowFans;
    private boolean FollowFlag;
    private int FollowSubAOpenFlag;
    private int FollowSubAState;
    private List<FundProportion> FundProportion;
    private String GradingIndexDesc;
    private String GroupCreatTime;
    private boolean HasBonus;
    private double IntervalRate;
    private boolean IsAllowOpen;
    private boolean IsCanOpen;
    private boolean IsContinueBuy;
    private boolean IsOnRestore;
    private int IsPrivacy;
    private boolean IsRedem;
    private boolean IsTransfer;
    private String LastCloseTime;
    private int MCompositeScore;
    private int ManualReviewState;
    private String Name;
    private String Nav;
    private String NavDate;
    private boolean NeedFollow;
    private boolean NeverHoldShare;
    private int OpenState;
    private String PassportID;
    private List<PositionProportion> PositionProportion;
    private String ProfitIntervalType;
    private String Property;
    private int QCompositeScore;
    private List<RationPlans> RationPlans;
    private ScoreDetail ScoreDetail;
    private boolean Self;
    private int State;
    private String Style;
    private String SubATitle;
    private String SubAccountNoIdea;
    private List<SubIntervalIndexList> SubIntervalIndexList;
    private String SubType;
    private List<String> TagList;
    private String TjCode;
    private String Type;
    private String WealthNo;
    private String WorkDays;
    private String ZHBIntervalDesc;
    private double ZHBIntervalRate;
    private int ZHBIntervalType;
    private boolean ZHBOnway;
    private String ZHBType;
    private String subAccountNo;

}

@Data
class FundProportion {

    private String Amount;
    private String Code;
    private String FdProfit;
    private boolean FundHasBonus;
    private int FundHoldChange;
    private String HoldAmountChange;
    private String Proportion;
    private String ProportionChange;
    private int RealAmountVal;
    private int RealProportionVal;
    private boolean SubAEnableSh;
    private String TargetProportion;
    private String Title;
    private String Type;
    private String TypeName;

}

@Data
class PositionProportion {

    private String Code;
    private String Proportion;
    private String TargetProportion;
    private String Title;

}

@Data
class RationPlans {

    private String InvestAmount;
    private String InvestFundCode;
    private String InvestFundName;
    private String InvestTime;

}

@Data
class ScoreList {

    private String IndexComment;
    private String IndexName;
    private int IndexType;
    private int IsPunish;
    private String PunishRatio;
    private int Score;
    private String ScoreRatio;
    private int ScoreWithPunish;
    private String ScoreWithPunishShow;

}

@Data
class ScoreDetail {

    private String CompositeScore;
    private String IntervalType;
    private int IsPunish;
    private List<ScoreList> ScoreList;


}

@Data
class SubIndexList {

    private int IndexID;
    private String IndexName;
    private String IndexValue;
    private int Percent;
    private String PercentEvaluate;
    private String ReferOptimalValue;
    private String ReferWorstValue;

}

@Data
class SubIntervalIndexList {

    private int IntervalType;
    private List<SubIndexList> SubIndexList;

    public void setIntervalType(int IntervalType) {
        this.IntervalType = IntervalType;
    }

    public int getIntervalType() {
        return IntervalType;
    }

    public void setSubIndexList(List<SubIndexList> SubIndexList) {
        this.SubIndexList = SubIndexList;
    }

    public List<SubIndexList> getSubIndexList() {
        return SubIndexList;
    }

}


