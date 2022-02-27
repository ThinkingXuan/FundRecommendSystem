import request from '@/utils/request'


//请求mock的数据
export function getMinute_k(params) {
    return request({
        url: "/index/minute_k",
        method: "post",
        params
    })
}

//默认数据
export function getMarket(params) {
    return request({
        url: "/index/market",
        method: "post",
        params
    })
}


//日k
export function getDay_k(params) {
    return request({
        url: "/index/day_k",
        method: "get",
        params
    })
}


//周k
export function getWeek_k(params) {
    return request({
        url: "/index/week_k",
        method: "get",
        params
    })
}


//月k
export function getMonth_k(params) {
    return request({
        url: "/index/month_k",
        method: "get",
        params
    })
}

export function getList(current_page, page_size, default_order, invest_strategy, manager_type, from) {
    return request({
        url: `/frs/fund/getfundlist?current_page=${current_page}&page_size=${page_size}&default_order=${default_order}&invest_strategy=${invest_strategy}&manager_type=${manager_type}&from=${from}`,

        //url: `/frs/fund/getfundlist?current_page=${current_page}&page_size=${page_size}&default_order=${default_order}&invest_strategy=${invest_strategy}&manager_type=&from=${from}`,
        method: 'get',
        headers: {
            'Content-Type': 'application/json;'
        }
    })
}

export function getadviceList(name) {
    return request({
        url: `/frs/fund/recommender?username=${name}`,
        method: 'get',
        headers: {
            'Content-Type': 'application/json;'
        }
    })
}

export function searchFund(keyword) {
    return request({
        url: `/frs/fund/searchfund?keyword=${keyword}`,
        method: 'get',
        headers: {
            'Content-Type': 'application/json;'
        }
    })
}


export function funddetail_plan(plan_code) {
    return request({
        url: `/frs/funddetail/plan?plan_code=${plan_code}`,
        method: 'get',
        headers: {
            'Content-Type': 'application/json;'
        }
    })
}

export function funddetail_detail(plan_code) {
    return request({
        url: `/frs/funddetail/detail?plan_code=${plan_code}`,
        method: 'get',
        headers: {
            'Content-Type': 'application/json;'
        }
    })
}

export function funddetail_summary(plan_code) {
    return request({
        url: `/frs/funddetail/summary?plan_code=${plan_code}`,
        method: 'get',
        headers: {
            'Content-Type': 'application/json;'
        }
    })
}

export function funddetail_growth(plan_code) {
    return request({
        url: `/frs/funddetail/growth?plan_code=${plan_code}`,
        method: 'get',
        headers: {
            'Content-Type': 'application/json;'
        }
    })
}


export function tiantian_detail(plan_code) {
    return request({
        url: `/frs/funddetail/tiantian_detail?plan_code=${plan_code}`,
        method: 'get',
        headers: {
            'Content-Type': 'application/json;'
        }
    })
}



export function tiantian_fund_growth(plan_code) {
    return request({
        url: `/frs/funddetail/tiantian_fund_growth?plan_code=${plan_code}`,
        method: 'get',
        headers: {
            'Content-Type': 'application/json;'
        }
    })
}