import request from '@/utils/request'

export function login(params) {
  return request({
    url: '/frs/user/login',
    method: 'post',
    params:params,
    transformRequest:[function(){
      return JSON.stringify(params)
    }],
    headers: {
      'Content-Type':'application/json;'
    }
  })
}
export function register(params) {
  return request({
    url: '/frs/user/register',
    method: 'post',
    params:params,
    transformRequest:[function(){
      return JSON.stringify(params)
    }],
    headers: {
      'Content-Type':'application/json;'
    }
  })
}
export function logout(user_account) {
  return request({
    url: `/sfm/user/logout/${user_account}`,
    method: 'post'
  })
}
export function set_usertendency(params) {
  return request({
    url: '/frs/fund/usertendency',
    method: 'post',
    params:params,
    transformRequest:[function(){
      return JSON.stringify(params)
    }],
    headers: {
      'Content-Type':'application/json;'
    }
  })
}

export function get_usertendency(username) {
  return request({
  url: `/frs/fund/getusertendency?username=${username}`,  
  method: 'get',
    headers: {
      'Content-Type':'application/json;'
    }
  })
}