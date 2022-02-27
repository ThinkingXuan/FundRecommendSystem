<template>
  <div class="app-container nolighttable">
    <div
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
</template>

<script>
import { getadviceList } from "@/api/fund";
import store from "@/store";
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
      count: 0,
      data: [],
      cheat: [{ value: null }],
      busy: false,
      list: [],
      listLoading: true,
      //重新确认api的对接
      total: 0,
      currentPage: 1,
      pageSize: 10,
      listQuery: {
        default_order: 1,
        invest_strategy: 0,
        manager_type: 0,
        from: 2,
      },
    };
  },
  created() {
    this.fetchData();
  },
  methods: {

    find_fund(plan_code) {},
    fetchData() {
      this.listLoading = true;
      /*getList().then(response => {
        this.list = response.data.items
        this.total=response.count
        this.listLoading = false
      })*/
      getadviceList(store.getters.name).then((response) => {
        this.list = response.data;
        this.total = 10;
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