<template>
  <div class="app-container nolighttable" id="body">
    <div class="selectors" style="height: 20px;margin-left:10%">
      排序:<el-select
        v-model="listQuery.default_order"
        placeholder="请选择"
        style="width: 22%"
        @change="fetchData()"
      >
        <el-option
          v-for="item in default_order"
          :key="item.value"
          :label="item.label"
          :value="item.value"
        >
        </el-option>
      </el-select>
      投资策略:<el-select
        v-model="listQuery.invest_strategy"
        placeholder="请选择"
        style="width: 13%"
        @change="fetchData()"
      >
        <el-option
          v-for="item in invest_strategy"
          :key="item.value"
          :label="item.label"
          :value="item.value"
        >
        </el-option>
      </el-select>
      管理者类型:<el-select
        v-model="listQuery.manager_type"
        placeholder="请选择"
        style="width: 15%"
        @change="fetchData()"
      >
        <el-option
          v-for="item in manager_type"
          :key="item.value"
          :label="item.label"
          :value="item.value"
        >
        </el-option>
      </el-select>
      平台:<el-select
        v-model="listQuery.from"
        placeholder="请选择"
        style="width: 15%"
        @change="fetchData()"
      >
        <el-option
          v-for="item in from"
          :key="item.value"
          :label="item.label"
          :value="item.value"
        >
        </el-option>
      </el-select>
    </div>
    <div>
      <div
        class="infinite-list-wrapper"
        v-infinite-scroll="loadMore"
        infinite-scroll-disabled="busy"
        infinitelist-scroll-distance="10"
        style="overflow: auto; margin-top: 20px; overflow-y: hidden"
      >
        <div
          v-for="item in list"
          :key="item.plan_code"
          style="width: 85%; height: 190px"
          class="list"
        >
          <router-link
            v-if="item.from == 1"
            :to="'/home/tiantian_fund/' + item.plan_code"
          >
            <span class="title">{{ item.plan_name }}</span>
            <br />
            <span class="left"> {{ item.manager_name }}</span>
            <span class="left">{{ item.follow_num }}人跟买</span>
            <br />
            <el-table
              :data="cheat"
              :show-header="false"
              fit
              style="
                width: 100%;
                height: 100px;
                font-family: 'Times New Roman', Times, serif;
                font-size: 20px;
                color: #000000;
              "
            >
              <el-table-column >
                
                <span class="redColor" v-if="Number(item.all_yield) >= 0"
                  >{{ item.all_yield }}%↑</span
                >
                <span class="greenColor" v-else>{{ item.all_yield }}%↓</span>
                <p>成立以来年化</p>
              </el-table-column>
              <el-table-column >
                天天基金
                <p>来源</p>
              </el-table-column>
              <el-table-column>
                {{ item.plan_code }}
                <p>组合编号</p>
              </el-table-column>
            </el-table>
          </router-link>
          <router-link
            v-else
            :to="'/home/danjuan_fund/' + item.plan_code"
            style="width: 100%"
            ><span class="title">{{ item.plan_name }}</span>
            <br />
            <span class="left">{{ item.manager_name }}</span>
            <span class="left">{{ item.follow_num }}人跟买</span>
            <br />
            <el-table
              :data="cheat"
              :show-header="false"
              fit
              style="
                width: 100%;
                height: 100px;
                font-family: 'Times New Roman', Times, serif;
                font-size: 20px;
                color: #000000;
              "
            >
              <el-table-column >
                
                <span class="redColor" v-if="Number(item.all_yield) >= 0"
                  >{{ item.all_yield }}%↑</span
                >
                <span class="greenColor" v-else>{{ item.all_yield }}%↓</span>
                <p>成立以来年化</p>
              </el-table-column>
              <el-table-column >
                蛋卷基金
                <p>来源</p>
              </el-table-column>
              <el-table-column>
                {{ item.plan_code }}
                <p>组合编号</p>
              </el-table-column>
            </el-table>
          </router-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { getList } from "@/api/fund";

export default {
  filters: {
    statusFilter(status) {
      const statusMap = {
        published: "success",
        draft: "gray",
        deleted: "danger",
      };
      return statusMap[status];
    },
  },
  data() {
    return {
      offsetHeight: 0,
      count: 0,
      data: [],
      cheat: [{ value: null }],
      busy: false,
      list: [],
      default_order: [
        {
          value: 0,
          label: "综合排序",
        },
        {
          value: 1,
          label: "年化",
        },
        {
          value: 2,
          label: "总收益率",
        },
        {
          value: 3,
          label: "回撤 （从小到大）",
        },
        {
          value: 4,
          label: "夏普比率",
        },

        {
          value: 5,
          label: "跟随人数",
        },
        {
          value: 6,
          label: "持仓金额",
        },
      ],
      invest_strategy: [
        {
          value: 0,
          label: "激进型",
        },
        {
          value: 1,
          label: "灵活型",
        },
        {
          value: 2,
          label: "稳健型",
        },
        {
          value: "",
          label: "全部",
        },
      ],
      manager_type: [
        {
          value: 0,
          label: "基金达人",
        },
        {
          value: 1,
          label: "普通基民",
        },
        {
          value: "",
          label: "全部",
        },
      ],
      from: [
        {
          value: 1,
          label: "天天基金",
        },
        {
          value: 2,
          label: "蛋卷基金",
        },
        {
          value: "",
          label: "全部",
        },
      ],
      //重新确认api的对接
      total: 0,
      currentPage: 1,
      pageSize: 10,
      listQuery: {
        default_order: 0,
        invest_strategy: "",
        manager_type: "",
        from: "",
      },
    };
  },
  mounted() {
    this.navFixed();
    window.addEventListener("scroll", this.navFixed);
    window.addEventListener("resize", this.navFixed);
    this.$nextTick(function () {
      let body = document.getElementById("body");
      // 这里要得到top的距离和元素自身的高度
      this.offsetHeight = body.offsetHeight;
    });
  },
  destroyed() {
    window.removeEventListener("scroll", this.navFixed);
    window.removeEventListener("resize", this.navFixed);
  },
  created() {
    this.fetchData();
  },
  methods: {
    loadMore() {
      this.busy = true;
      setTimeout(() => {
        getList(
          this.currentPage,
          this.pageSize,
          this.listQuery.default_order,
          this.listQuery.invest_strategy,
          this.listQuery.manager_type,
          this.listQuery.from
        ).then((response) => {
          for (var i in response.data.list) {
            this.list.push(response.data.list[i]);
          }
        });
        this.currentPage++;
        this.busy = false;
      }, 1000);
    },
    fetchData() {
      this.busy = true;
      this.list = [];
      this.currentPage = 1;
      setTimeout(() => {
        getList(
          this.currentPage,
          this.pageSize,
          this.listQuery.default_order,
          this.listQuery.invest_strategy,
          this.listQuery.manager_type,
          this.listQuery.from
        ).then((response) => {
          for (var i in response.data.list) {
            this.list.push(response.data.list[i]);
          }
        });
        this.currentPage++;
        this.busy = false;
      }, 1000);
    },
    navFixed() {
      let body = document.getElementById("body");
      // 这里要得到top的距离和元素自身的高度

      this.offsetHeight = body.offsetHeight;

      // 得到页面滚动的距离
      let scrollTop =
        window.pageYOffset ||
        document.documentElement.scrollTop ||
        document.body.scrollTop;

      var windowHeight = 0;
      if (document.compatMode == "CSS1Compat") {
        windowHeight = document.documentElement.clientHeight;
      } else {
        windowHeight = document.body.clientHeight;
      }

      var scrollbottom = windowHeight + window.pageYOffset - this.offsetHeight;
      //判断页面滚动的距离是否大于吸顶元素的位置
      if (scrollbottom >= -2 && scrollbottom < 100) {
        this.loadMore();
      }
    },
  },
};
</script>
<style scoped>
.nolighttable >>> .el-table tbody tr:hover > td {
  background-color: #ffffff !important;
}
</style> 
<style lang="scss" scoped>
.list {
  font-family: "Times New Roman", Times, serif;
  line-height: 1.5em;
  font-size: 20px;
  color: #000000;
  margin-left: 15%;
  .title {
    margin-left: 1.5%;
    font-weight: bold;
    font-size: 30px;
  }
  .left {
    margin-left: 1.5%;
    font-size: 15px;
  }
}
.selectors {
  display: inline-block;
}
.el-table__row > td {
  border: none;
}
.el-table::before {
  //去掉最下面的那一条线
  height: 0px;
}
.redColor {
  color: #ff4d4d !important;
}

.greenColor {
  color: #009900 !important;
}
</style>