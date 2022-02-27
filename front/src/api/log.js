import request from '@/utils/request'
//api搜索
export function searchLog(currentPage,pageSize,params) {
  return request({
    url: `/sfm/auditor/sysloglist?index=${currentPage}&size=${pageSize}&admin_flag=1${params}`,
    method: 'get'    
  })
}
export function exportlog() {
    return request({
      url: `/sfm/auditor/exportlog`,
      responseType: 'blob',
      method: 'get',
    })
  }