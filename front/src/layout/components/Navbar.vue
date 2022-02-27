<template>
  <div id="header" class="navbar" :class="{ fixed: st }">
    <div class="main-container">
      <el-menu
        :default-active="activeMenu"
        :text-color="variables.menuText"
        :unique-opened="false"
        :active-text-color="variables.menuActiveText"
        class="menu-bottom"
        mode="horizontal"
      >
        <el-menu-item>
          <router-link to="/">
            <img class="logo" src="../../assets/logo.png" />
          </router-link>
        </el-menu-item>

        <navbar-item
          v-for="route in routes"
          :key="route.path"
          :item="route"
          :base-path="route.path"
          class="navmenu-item"
        />
        <el-menu-item class="right-menu">
          <el-dropdown
            v-if="this.if_name"
            class="avatar-container"
            trigger="hover"
          >
            <div class="avatar-wrapper">
              <!-- <link rel="icon" href="./icons/svg/user.svg"> -->
              <!-- <img :src="avatar+'?imageView2/1/w/80/h/80'" class="user-avatar"> -->
              <span class="user-center">用户中心</span>
              <i class="el-icon-caret-bottom" />
            </div>
            <el-dropdown-menu slot="dropdown" class="user-dropdown">
              <el-dropdown-item>
                <span style="display: block" @click="test()"
                  >欢迎您，{{ this.if_name }}</span
                >
              </el-dropdown-item>
              <el-dropdown-item>
                <span style="display: block" @click="sets()">更改偏好设置</span>
              </el-dropdown-item>
              <el-dropdown-item @click.native="logout">
                <span style="display: block">安全退出</span>
              </el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
          <div v-else>
            <router-link to="/login">登录</router-link>|
            <router-link to="/register">注册</router-link>
          </div>
        </el-menu-item>
        <el-menu-item class="right">
          <el-select
            v-model="keyword"
            filterable
            remote
            reserve-keyword
            placeholder="请输入关键词"
            :remote-method="remoteMethod"
            :loading="loading"
          >
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
            </el-option>
          </el-select>
        </el-menu-item>
      </el-menu>
    </div>

    <el-dialog width="500px" :visible.sync="dialogVisible">
      <el-form label-width="200px">
        <el-form-item label="投资金额(人民币):">
          <el-input-number
            v-model="tendency_form.invest_amount"
            :min="0"
            :step="100"
            :max="100000000"
          ></el-input-number>
        </el-form-item>
        <el-form-item label="投资时长(天):">
          <el-input-number
            v-model="tendency_form.invest_duration"
            :min="0"
            :max="100000"
            :step="1"
          ></el-input-number>
        </el-form-item>
        <el-form-item label="投资类型:">
          <el-radio-group v-model="tendency_form.type">
            <el-radio-button label="0">激进型</el-radio-button>
            <el-radio-button label="1">灵活型</el-radio-button>
            <el-radio-button label="2">稳健型</el-radio-button>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="预期利润率(%):">
          <el-input-number
            v-model="tendency_form.profit"
            :min="0"
            :max="500"
            :step="1"
          ></el-input-number>
        </el-form-item>
        <el-form-item label="能接受的最大回撤(%):">
          <el-input-number
            v-model="tendency_form.dropdown"
            :max="500"
            :min="0"
            :step="1"
          ></el-input-number>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="usertendency()">确定</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>


<script>
import { mapGetters } from "vuex";
import Breadcrumb from "@/components/Breadcrumb";
import Hamburger from "@/components/Hamburger";
import NavbarItem from "./NavbarItem";
import variables from "@/styles/variables.scss";
import { getName, removeName } from "@/utils/auth";
import { searchFund } from "@/api/fund.js";
import { get_usertendency, set_usertendency } from "@/api/user.js";
import store from "@/store";
export default {
  data() {
    return {
      keyword: null,
      options: [],
      loading: false,
      st: false,
      offsetTop: 0,
      offsetHeight: 0,
      dialogVisible: false,
      if_name: store.getters.name,

      tendency_form: {
        username: store.getters.name,
        invest_amount: 0,
        invest_duration: 0,
        type: 0,
        profit: 0,
        dropdown: 0,
      },
    };
  },
  components: {
    Breadcrumb,
    Hamburger,
    NavbarItem,
  },
  computed: {
    ...mapGetters(["avatar"]),
    routes() {
      return this.$router.options.routes;
    },
    activeMenu() {
      const route = this.$route;
      const { meta, path } = route;
      // if set path, the sidebar will highlight the path you set
      //有点问题
      return meta.activeMenu;
    },
    variables() {
      return variables;
    },
  },

  methods: {
    test() {
    },
    remoteMethod(query) {
      if (query !== "") {
        this.loading = true;
        searchFund(query).then((response) => {
          this.options = response.data.map((item) => {
            return {
              value: `${item.plan_code}`,
              label: `${item.plan_name}[${item.plan_code}](${item.manager_name})`,
            };
          });
          this.loading = false;
        });
      } else {
        this.options = [];
      }
    },
    get_form() {
      get_usertendency(store.getters.name).then((response) => {
        // this.$message({
        //   message: response,
        //   type: "success",
        // });
        this.tendency_form.type = response.data.type;
        this.tendency_form.dropdown = response.data.dropdown;
        this.tendency_form.invest_amount = response.data.invest_amount;
        this.tendency_form.invest_duration = response.data.invest_duration;
        this.tendency_form.profit = response.data.profit;
      });
    },
    usertendency() {
      set_usertendency(this.tendency_form).then((response) => {
        this.$message({
          message: response.message,
          type: "success",
        });
        this.dialogVisible = false;
      });
    },
    sets() {
      this.get_form();
      this.$nextTick(function () {
        this.dialogVisible = true;
      });
    },
    //控制顶部
    navFixed() {
      // 得到页面滚动的距离
      let scrollTop =
        window.pageYOffset ||
        document.documentElement.scrollTop ||
        document.body.scrollTop;
      // 判断页面滚动的距离是否大于吸顶元素的位置
      this.st = scrollTop > this.offsetTop + this.offsetHeight;
    },

    // async logout() {
    //   this.$confirm("是否确定安全退出系统？", "安全退出", {
    //     confirmButtonText: "确定",
    //     cancelButtonText: "取消",
    //     type: "warning",
    //   }).then(async () => {
    //     await this.$store.dispatch("user/logout");
    //     this.$router.push(`/login?redirect=${this.$route.fullPath}`);
    //     this.$message({
    //       type: "success",
    //       message: "安全退出成功!",
    //     });
    //   });
    //   // await this.$store.dispatch("user/logout");
    //   // this.$router.push(`/login?redirect=${this.$route.fullPath}`);
    // },
    async logout() {
      this.$confirm("是否确定退出？", "退出", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(async () => {
        removeName();
        this.if_name = false;
        this.$forceUpdate();
      });
    },
  },
  watch: {
    keyword: function (newValue, oldValue) {
      if (newValue.indexOf("CSI") != -1) {
        this.$router.push("/home/danjuan_fund/" + newValue);
      }
      if (newValue.indexOf("CSI") == -1) {
        this.$router.push("/home/tiantian_fund/" + newValue);
      }
    },
  },
  created() {
    if (this.$route.params.is_register) {
      this.dialogVisible = true;
    }
    if (store.getters.name) {
      this.get_form();
    }
  },
  mounted() {
    this.navFixed();
    window.addEventListener("scroll", this.navFixed);
    window.addEventListener("resize", this.navFixed);
    this.$nextTick(function () {
      let header = document.getElementById("header");
      // 这里要得到top的距离和元素自身的高度
      this.offsetTop = header.offsetTop;
      this.offsetHeight = header.offsetHeight;
    });
  },
  destroyed() {
    window.removeEventListener("scroll", this.navFixed);
    window.removeEventListener("resize", this.navFixed);
  },
};
</script>

<style lang="scss" scoped>
.navmenu-item {
  display: inline-block;
}
.menu-bottom {
  border-bottom: #ffffff !important;
}
.navbar {
  height: 65px;
  overflow: hidden;
  position: relative;
  background: #fff;
  box-shadow: 0 1px 4px rgba(0, 21, 41, 0.08);
  &.fixed {
    position: fixed;
    z-index: 21;
    height: 60px;
    top: 0;
    left: 0;
    right: 0;
    background-image: -webkit-linear-gradient(#fff, #f1f1f1);
    background-image: linear-gradient(#fff, #f1f1f1);
  }
  .nav-list {
    display: flex;
    justify-content: center;
    align-items: center;
    float: right;
    height: 100%;
    line-height: 50px;
    margin-right: 2%;

    &:focus {
      outline: none;
    }
    .el-autocomplete {
      width: 305px;
    }
    a {
      width: 110px;
      color: #c8c8c8;
      display: block;
      font-size: 14px;
      padding: 0 25px;
      &:hover {
        color: #fff;
      }
    }
    a:nth-child(2) {
      // width: 5vw;
      margin-left: -10px;
    }
    // a:nth-child(3){
    //   width: 5vw;
    // }
  }
  .logo {
    height: 55px;
    margin: 0;
  }
  .right {
    float: right;
    height: 100%;
    line-height: 50px;
    margin-right: 2%;
    margin-top: 5px;
  }
  .right-menu {
    float: right;
    height: 100%;
    line-height: 50px;
    margin-right: 2%;

    &:focus {
      outline: none;
    }

    .right-menu-item {
      display: inline-block;
      padding: 0 8px;
      height: 100%;
      font-size: 18px;
      color: #5a5e66;
      vertical-align: text-bottom;

      &.hover-effect {
        cursor: pointer;
        transition: background 0.3s;

        &:hover {
          background: rgba(0, 0, 0, 0.025);
        }
      }
    }

    .avatar-container {
      margin-right: 30px;

      .avatar-wrapper {
        margin-top: 5px;
        position: relative;

        .user-center {
          cursor: pointer;
          font-size: 24px;
          width: 50px;
          height: 50px;
          border-radius: 10px;
        }

        .el-icon-caret-bottom {
          cursor: pointer;
          position: absolute;
          right: -20px;
          top: 25px;
          font-size: 12px;
        }
      }
    }
  }
}
</style>
