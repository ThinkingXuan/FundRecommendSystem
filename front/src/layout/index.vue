<template>
  <div :class="classObj" class="app-wrapper">
    <div :class="{ 'fixed-header': fixedHeader }">
      <navbar />
    </div>

    <div class="main-container" > 
      <app-main />
    </div>
    <!-- <div class="main-container">
      <y-footer />
    </div> -->
  </div>
</template>

<script>
import { Navbar, AppMain, YFooter } from "./components";
import ResizeMixin from "./mixin/ResizeHandler";

export default {
  name: "Layout",
  components: {
    Navbar,
    //Sidebar,
    AppMain,
    YFooter,
  },
  mixins: [ResizeMixin],
  computed: {
    device() {
      return this.$store.state.app.device;
    },
    fixedHeader() {
      return this.$store.state.settings.fixedHeader;
    },
    classObj() {
      return {
        mobile: this.device === "mobile",
      };
    },
  },
  methods: {
    handleClickOutside() {},
  },
};
</script>

<style lang="scss" scoped>
@import "~@/styles/mixin.scss";
@import "~@/styles/variables.scss";

.app-wrapper {
  @include clearfix;
  position: relative;
  height: 100%;
  width: 100%;
}
.drawer-bg {
  background: #000;
  opacity: 0.3;
  width: 100%;
  top: 0;
  height: 100%;
  position: absolute;
  z-index: 999;
}

.fixed-header {
  position: fixed;
  top: 0;
  right: 0;
  left: 0;
  z-index: 9;
  width: 100%;
  transition: width 0.28s;
}

.mobile .fixed-header {
  width: 100%;
}
</style>
