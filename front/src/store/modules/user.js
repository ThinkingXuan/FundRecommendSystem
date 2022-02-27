import { login, logout} from '@/api/user'
import { getName, setName, removeName } from '@/utils/auth'
import { resetRouter } from '@/router'

const getDefaultState = () => {
  return {
    name: getName(),
  }
}

const state = getDefaultState()

const mutations = {
  RESET_STATE: (state) => {
    Object.assign(state, getDefaultState())
  },
  SET_NAME: (state, name) => {
    state.name = name
  }
}

const actions = {
  // user login
  login({ commit }, userInfo) {
    return new Promise((resolve, reject) => {
      login({ username: userInfo.username, password: userInfo.password }).then(response => {
        commit('SET_NAME', userInfo.username)
        setName(userInfo.username)
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // get user info
  //在permission中有调用
  /*
  getInfo({ commit, state }) {
    return new Promise((resolve, reject) => {
      getInfo(state.token).then(response => {
        const { data } = response

        if (!data) {
          return reject('Verification failed, please Login again.')
        }

        const { name, avatar } = data

        commit('SET_NAME', name)
        commit('SET_AVATAR', avatar)
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },*/

  // user logout
  logout({ commit, state }) {
    return new Promise((resolve, reject) => {
      logout(state.user_account).then(() => {
        removeName() // must remove  token  first
        resetRouter()
        commit('RESET_STATE')
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // remove token
  resetName({ commit }) {
    return new Promise(resolve => {
      removeName() // must remove  token  first
      commit('RESET_STATE')
      resolve()
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}

