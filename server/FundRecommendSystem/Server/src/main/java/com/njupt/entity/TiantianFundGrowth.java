package com.njupt.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class TiantianFundGrowth {

    @JsonProperty("AccountProfitRate")
    private Double AccountProfitRate;
    @JsonProperty("IndexProfitCode")
    private String IndexProfitCode;
    @JsonProperty("IndexProfitRate")
    private Double IndexProfitRate;
    @JsonProperty("LatestIntervalInfoList")
    private List<?> LatestIntervalInfoList;
    @JsonProperty("GraphSpotList")
    private List<GraphSpotListDTO> GraphSpotList;

    @NoArgsConstructor
    @Data
    public static class GraphSpotListDTO {
        /**
         * NavDate : /Date(1583337600000)/
         * IndexNav : {"NavDate":"/Date(1583337600000)/","Nav":1166.2448,"Rate":null,"TotalRate":0}
         * AccountNav : {"NavDate":"/Date(1583337600000)/","Nav":1.6507,"Rate":0.0089,"TotalRate":0}
         * IsTrade : 0
         * HoldTotalChange : 0
         * Profit : 114.8914
         * TotalProfit : 114.8914
         */

        @JsonProperty("NavDate")
        private String NavDate;
        @JsonProperty("IndexNav")
        private IndexNavDTO IndexNav;
        @JsonProperty("AccountNav")
        private AccountNavDTO AccountNav;
        @JsonProperty("IsTrade")
        private Integer IsTrade;
        @JsonProperty("HoldTotalChange")
        private Integer HoldTotalChange;
        @JsonProperty("Profit")
        private Double Profit;
        @JsonProperty("TotalProfit")
        private Double TotalProfit;

        @NoArgsConstructor
        @Data
        public static class IndexNavDTO {
            /**
             * NavDate : /Date(1583337600000)/
             * Nav : 1166.2448
             * Rate : null
             * TotalRate : 0
             */

            @JsonProperty("NavDate")
            private String NavDate;
            @JsonProperty("Nav")
            private Double Nav;
            @JsonProperty("Rate")
            private Object Rate;
            @JsonProperty("TotalRate")
            private Double TotalRate;
        }

        @NoArgsConstructor
        @Data
        public static class AccountNavDTO {
            /**
             * NavDate : /Date(1583337600000)/
             * Nav : 1.6507
             * Rate : 0.0089
             * TotalRate : 0
             */

            @JsonProperty("NavDate")
            private String NavDate;
            @JsonProperty("Nav")
            private Double Nav;
            @JsonProperty("Rate")
            private Double Rate;
            @JsonProperty("TotalRate")
            private Double TotalRate;
        }
    }

//    private double AccountProfitRate;
//    private String IndexProfitCode;
//    private double IndexProfitRate;
//    private List<LatestIntervalInfoList> LatestIntervalInfoList;
//    private List<GraphSpotList> GraphSpotList;



}

//@Data
//class LatestIntervalInfoList {
//
//    private int IntervalType;
//    private String IntervalName;
//    private double ProfitRate;
//    private String IntervalRate;
//    private double IndexProfitRate;
//    private String IndexIntervalRate;
//    private int ProfitGrade;
//    private String IntervalProfitRankDesc;
//
//}
//
//@Data
//class IndexNav {
//
//    private String NavDate;
//    private double Nav;
//    private String Rate;
//    private int TotalRate;
//
//
//}
//
//@Data
//class AccountNav {
//
//    private String NavDate;
//    private double Nav;
//    private double Rate;
//    private int TotalRate;
//
//
//}
//
//@Data
//class GraphSpotList {
//    private String NavDate;
//    private IndexNav IndexNav;
//    private AccountNav AccountNav;
//    private int IsTrade;
//    private int HoldTotalChange;
//    private double Profit;
//    private double TotalProfit;
//
//}




