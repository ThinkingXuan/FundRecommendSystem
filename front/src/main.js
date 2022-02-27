import Vue from 'vue'
import 'normalize.css/normalize.css' // A modern alternative to CSS resets
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import locale from 'element-ui/lib/locale/lang/en' // lang i18n
import '@/styles/index.scss' // global css
import App from './App'
import store from './store'
import router from './router'
import echarts from 'echarts' //k线图
import '@/icons' // icon
import '@/permission' // permission control
import 'lib-flexible' //rem转换
import Vant from "vant"
import "../node_modules/vant/lib/index.css"
import infiniteScroll from 'vue-infinite-scroll'
/**
 * If you don't want to use mock-server
 * you want to use MockJs for mock api
 * you can execute: mockXHR()
 *
 * Currently MockJs will be used in the production environment,
 * please remove it before going online ! ! !
 */
if (process.env.NODE_ENV === 'production') {
  const { mockXHR } = require('../mock')
  mockXHR()
}

Vue.use(infiniteScroll)
// set ElementUI lang to EN
Vue.use(ElementUI, { locale })
// 如果想要中文版 element-ui，按如下方式声明
// Vue.use(ElementUI)


//kline加载的东西
import {
  Lazyload
} from 'vant';
import {
  Toast
} from 'vant';
Toast.setDefaultOptions({
  duration: 1000
}); //设置提示时间
Vue.use(Vant);
import {
  Dialog
} from 'vant';
Vue.use(Dialog);
Vue.use(Lazyload, '');
Vue.use(Toast);

function jiequ(str) { //截取小数点后两位
	if (String(str).indexOf(".") > -1) {
		var temp = Number(str);
		temp = Math.floor(temp * 1000) / 1000;
		temp = temp.toFixed(3);
		return temp
	} else {
		return str
	}
}
Vue.config.productionTip = false
Vue.prototype.$echarts = echarts
Vue.prototype.$jq = jiequ;
//分割


new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
})
