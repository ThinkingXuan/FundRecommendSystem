<template>
  <div class="app-container">
    <div class="selectors">
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
    <el-table
      v-loading="listLoading"
      :data="list"
      :show-header="false"
      element-loading-text="Loading"
      fit
      highlight-current-row
    >
      <el-table-column align="center" style="width: 100%">
        <template slot-scope="scope">
          <router-link
            v-if="scope.row.from == 1"
            :to="'/home/tiantian_fund/' + scope.row.plan_code"
            >{{ scope.row.plan_name }}
            <br />
            {{ scope.row.manager_name }}
            {{ scope.row.follow_num }}人跟买
            <br />
            {{ scope.row.all_yield }}
            成立以来年化
            <span>天天基金</span>

            来源
            {{ scope.row.plan_code }}
            组合编号
          </router-link>
          <router-link v-else :to="'/home/danjuan_fund/' + scope.row.plan_code"
            >{{ scope.row.plan_name }}
            <br />
            {{ scope.row.manager_name }}
            {{ scope.row.follow_num }}人跟买
            <br />
            {{ scope.row.all_yield }}
            成立以来年化
            <span>蛋卷基金</span>
            来源
            {{ scope.row.plan_code }}
            组合编号
          </router-link>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="handelSizeChange"
      @current-change="handelCurrentChange"
      :current-page="currentPage"
      :page-sizes="[10, 20, 30, 40]"
      :page-size="pageSize"
      layout="total,sizes,prev,pager,next,jumper"
      :total="total"
      align="center"
    >
    </el-pagination>
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
      list: null,
      listLoading: true,
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
  created() {
    this.fetchData();
  },
  methods: {
    handelSizeChange(val) {
      this.pageSize = val;
      this.fetchData();
    },
    handelCurrentChange(val) {
      this.currentPage = val;
      this.fetchData();
    },
    find_fund(plan_code) {},
    fetchData() {
      this.listLoading = true;
      /*getList().then(response => {
        this.list = response.data.items
        this.total=response.count
        this.listLoading = false
      })*/
      getList(
        this.currentPage,
        this.pageSize,
        this.listQuery.default_order,
        this.listQuery.invest_strategy,
        this.listQuery.manager_type,
        this.listQuery.from
      ).then((response) => {
        this.list = response.data.list;
        this.total = response.data.total;
        this.listLoading = false;
        // this.$message({
        //   type: "success",
        //   message: response.data,
        // });
      });
    },
  },
};
</script>
<style lang="scss" scoped>
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
</style>