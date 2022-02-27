import request from '@/utils/request'
export function changepwd(params) {
  //通过后端接口获得数据
  return request({
    url: '/sfm/secadmin/updatepassword',
    method: 'put',
    params:params,
    transformRequest:[function(){
      return JSON.stringify(params)
    }],
    headers: {
      'Content-Type':'application/json;'}
  })
}