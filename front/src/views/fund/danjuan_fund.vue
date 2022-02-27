<template>
  <div id="home" class="nolighttable">
    <main>
      <div class="topData">
        <div>
          {{ fund_data.plan_name }}
        </div>

        <div>{{ fund_data.plan_code }} {{ fund_data.manager_name }}</div>
      </div>
      <div>
        <el-table :data="list1" :show-header="false" fit style="width: 100%">
          <el-table-column align="center">
            <template slot-scope="scope">
              成立以来收益
              <p
                class="p1"
                :class="{
                  redColor: Number(scope.row.all_yield) >= 0,
                  greenColor: Number(scope.row.all_yield) < 0,
                }"
              >
                {{ $jq(Number(scope.row.all_yield)) }}%
                <span class="redColor" v-if="Number(scope.row.all_yield) >= 0"
                  >↑</span
                >
                <span class="greenColor" v-else>↓</span>
              </p>
            </template>
          </el-table-column>
          <el-table-column align="center">
            <template slot-scope="scope">
              成立天数
              <p class="p1">
                {{ scope.row.found_days }}
              </p>
            </template>
          </el-table-column>
        </el-table>
        <el-table :data="list2" :show-header="false" fit style="width: 100%">
          <el-table-column align="center">
            <template slot-scope="scope">
              日涨跌
              <p
                class="p1"
                :class="{
                  redColor: Number(scope.row.yield_t) >= 0,
                  greenColor: Number(scope.row.yield_t) < 0,
                }"
              >
                {{ $jq(Number(scope.row.yield_t)) }}%
                <span class="redColor" v-if="Number(scope.row.yield_t) >= 0"
                  >↑</span
                >
                <span class="greenColor" v-else>↓</span>
              </p></template
            >
          </el-table-column>
          <el-table-column align="center">
            <template slot-scope="scope">
              成立以来年化
              <p
                class="p1"
                :class="{
                  redColor: Number(scope.row.yield_middle) >= 0,
                  greenColor: Number(scope.row.yield_middle) < 0,
                }"
              >
                {{ $jq(Number(scope.row.yield_middle)) }}%
                <span
                  class="redColor"
                  v-if="Number(scope.row.yield_middle) >= 0"
                  >↑</span
                >
                <span class="greenColor" v-else>↓</span>
              </p></template
            >
          </el-table-column>
          <el-table-column align="center">
            <template slot-scope="scope">
              最新净值
              <p class="p1">
                {{ scope.row.unit_nav }}
              </p>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <div class="Echarts" id="line" style="width: 100%; height: 400px"></div>
      <div class="Echarts" id="pie" style="width: 100%; height: 400px"></div>
      <div>
        <el-table :data="list3" fit style="width: 100%">
          <el-table-column align="center" label="持仓基金">
            <template slot-scope="scope">
              {{ scope.row.fd_name }}|{{ scope.row.fd_code
              }}<span class="redColor" v-if="Number(scope.row.percent) == 0"
                >(已清仓)</span
              ></template
            >
          </el-table-column>
          <el-table-column align="center" label="比例">
            <template slot-scope="scope"> {{ scope.row.percent }}</template>
          </el-table-column>
          <el-table-column align="center" label="收益">
            <template slot-scope="scope">
              <span
                class="redColor"
                v-if="Number(scope.row.total_gain_rate) >= 0"
                >{{ scope.row.total_gain_rate }}↑</span
              >
              <span class="greenColor" v-else
                >{{ scope.row.total_gain_rate }}↓</span
              >
            </template>
          </el-table-column>
        </el-table>
      </div>
    </main>
  </div>
</template>

<script>
import {
  funddetail_plan,
  searchFund,
  funddetail_detail,
  funddetail_summary,
  funddetail_growth,
} from "@/api/fund";
import echarts from "echarts";

export default {
  name: "home",
  data() {
    return {
      fund_data: {
        yield_middle: 0, //成立以来年化
        plan_code: this.$route.params.plan_code,
        follow_num: 0, //跟买人数
        yield_t: 0, //日涨跌
        found_days: null, //成立天数
        all_yield: 0, //成立以来收益
        unit_nav: null, //净值

        plan_name: null,
        manager_name: null,
      },
      list1: [],
      list2: [],
      list3: [],
      position: [],
      position_name: [],
      line_date: [],
      line_value: [],
      line_thanvalue: [],
    };
  },
  components: {},
  mounted() {},
  methods: {
    pictureline() {
      var lineChart = this.$echarts.init(document.getElementById("line"));
      var lineoption = {
        title: {
          text: "收益曲线",
        },
        color: ["#ff6600", "#00c0ff"],
        xAxis: {
          type: "category",
          data: this.line_date,
          axisLine: { show: false },
        },
        yAxis: {
          type: "value",
          axisLabel: {
            //科学计数法
            formatter: function (value) {
              return value.toString() + "%";
            },
          },
          splitLine: {
            lineStyle: {
              type: "dashed", // y轴分割线类型
            },
          },
        },
        legend: {
          data: ["本产品", "沪深300指数"],
          left: "right",
        },
        tooltip: {
          trigger: "axis",
          formatter:
            "{b0}<br/>" +
            '<span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:#c23531"></span>{a0}:{c0}%<br/>' +
            '<span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:#91C7AE"></span>{a1}:{c1}%<br/>', // 显示百分比
          padding: 5, // 内边距
          axisPointer: {
            type: "cross",
            lineStyle: {
              type: "dashed",
            },
          },
        },

        series: [
          {
            name: "本产品",
            data: this.line_value,
            type: "line",
            smooth: true,
            valueType: "percent",
          },
          {
            name: "沪深300指数",
            data: this.line_thanvalue,
            type: "line",
            smooth: true,
            valueType: "percent",
          },
        ],
      };
      // 使用刚指定的配置项和数据显示图表。
      lineChart.setOption(lineoption);
    },
    picturepie() {
      var pieChart = this.$echarts.init(document.getElementById("pie"));
      var pieoption = {
        title: {
          text: "持仓分布",
        },
        tooltip: {
          trigger: "item",
          formatter: "{d}%",
        },
        legend: {
          x: "79%",
          y: "28%",
          orient: "vertical",
          data: this.position_name,
        },
        series: {
          center: ["35%", "47%"], //图的位置
          type: "pie",
          selectedMode: "single",
          data: this.position,
        },
      };
      // 使用刚指定的配置项和数据显示图表。
      pieChart.setOption(pieoption);
    },
    fetchData() {
      funddetail_plan(this.$route.params.plan_code).then((response) => {
        this.fund_data.yield_middle = response.data.yield_middle;
        this.fund_data.yield_t = response.data.yield_t;
        this.fund_data.follower_count = response.data.follower_count;
        this.fund_data.found_days = response.data.found_days;
        this.fund_data.all_yield = response.data.all_yield;
        this.fund_data.unit_nav = response.data.plan_derived.unit_nav;

        this.list1 = [
          {
            all_yield: this.fund_data.all_yield,
            found_days: this.fund_data.found_days,
          },
        ];
        this.list2 = [
          {
            yield_middle: this.fund_data.yield_middle,
            unit_nav: this.fund_data.unit_nav,
            yield_t: this.fund_data.yield_t,
          },
        ];
      });

      searchFund(this.$route.params.plan_code).then((response) => {
        this.fund_data.plan_name = response.data[0].plan_name;
        this.fund_data.manager_name = response.data[0].manager_name;
      });
      funddetail_detail(this.$route.params.plan_code).then((response) => {
        this.list3 = response.data.items;
      });
      funddetail_summary(this.$route.params.plan_code).then((response) => {
        this.position = response.data.position.map((item) => {
          return {
            name: `${item.type_desc}`,
            value: `${item.percent}`,
          };
        });
        // this.$message({
        //   message: this.position,
        //   type: "success",
        // });
        this.position_name = response.data.position.map((item) => {
          return item.type_desc;
        });
        this.picturepie();
      });
      funddetail_growth(this.$route.params.plan_code).then((response) => {
        this.line_date = response.data.fund_nav_growth.map((item) => {
          return item.date;
        });
        this.line_value = response.data.fund_nav_growth.map((item) => {
          return (item.value * 100).toFixed(2);
        });
        this.line_thanvalue = response.data.fund_nav_growth.map((item) => {
          return (item.than_value * 100).toFixed(2);
        });
        this.pictureline();
      });
    },
  },
  created() {
    this.fetchData();
  },
};
</script>
<style scoped>
.nolighttable >>> .el-table tbody tr:hover > td {
  background-color: #ffffff !important;
}
</style> 
<style  scoped lang="scss">
#home {
  overflow: hidden;
  position: relative;
  background: #fff;
  margin-top: 5px;
  .top_title {
    width: 100%;
    height: 1.2rem;
    text-align: center;
    line-height: 1.2rem;
    color: #fff;
    font-size: 0.4rem;
    background-color: #ff3048;
  }
}
.redColor {
  color: #ff4d4d !important;
}

.greenColor {
  color: #009900 !important;
}

.blackColor {
  color: #333333;
}

.grayColor {
  color: #999999;
}

.flex {
  display: flex;
  align-items: center;
}

main {
  padding: 0.133333rem 0 0.4rem;
  box-sizing: border-box;
}

.topData {
  background: #fff;
  padding: 0.2rem 0.4rem 0.2rem;
  font-size: 0.346666rem;
  justify-content: space-between;
  border-bottom: 0.026666rem solid #f2f2f2;

  > div {
    flex-grow: 1;

    p {
      line-height: 0.6rem;
    }
  }

  .p1 {
    font-size: 0.333333rem;

    span {
      font-size: 0.4rem;
      margin-left: 0.2rem;
    }
  }

  span {
    margin-left: 0.2rem;
  }

  .span1 {
    margin-right: 0.266666rem;
    margin-left: 0;
  }
}
</style>
