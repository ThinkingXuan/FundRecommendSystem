import request from '@/utils/request'

export function getList(params) {
  return request({
    url: '/auditor/table/list',
    method: 'get',
    params
  })
}
