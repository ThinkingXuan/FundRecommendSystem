import Vue from 'vue'
import Vuex from 'vuex'
import getters from './getters'
import app from './modules/app'
import settings from './modules/settings'
import user from './modules/user'

Vue.use(Vuex)

const store = new Vuex.Store({
  modules: {
    app,
    settings,
    user
  },
  getters,
  //一下为kline
  state: {
		yPrice: '', 
	},
	actions: {
		priceAction({
			commit
		}, date) {
			commit('upPrice', date)
		}
	},
	mutations: {
		upPrice(state, date) {
			state.yPrice = date;
		},
	},
})

export default store
