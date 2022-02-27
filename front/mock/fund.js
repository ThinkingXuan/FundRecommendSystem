//添加一个mock规则
const Mock = require('mockjs')


//获取mock.random对象
const Random = Mock.Random

// kline模拟数据
const getMinute_k = function () {
  let data = [];
  for (let i = 0; i < 100; i++) {
    let newArticleObject = {
      id: i,
      close: Random.natural(2800, 2888),
      high: Random.natural(2800, 2888),
      low: Random.natural(2800, 2888),
      ma5: Random.natural(2800, 2888),
      ma10: Random.natural(2800, 2888),
      ma20: Random.natural(2800, 2888),
      ma30: Random.natural(2800, 2888),
      open: Random.natural(2800, 2888),
      price: Random.natural(2800, 2888),
      price_equal: Random.natural(1000, 2000),
      time: Random.date(),
      volume: Random.natural(10000, 20000),
      yesterday_close: Random.natural(2800, 2888),
    }
    data.push(newArticleObject)
  }
  return {
    data
  }
}

//home默认数据
const getMarket = function () {
  let data = {
    buy_chang_price: Random.natural(0, 1),
    buy_five_amount: false,
    buy_five_price: "0",
    buy_four_amount: false,
    buy_four_price: "0",
    buy_one_amount: false,
    buy_one_price: "0",
    buy_three_amount: false,
    buy_three_price: "0",
    buy_two_amount: false,
    buy_two_price: "0",
    code: "000001",
    currency: 0,
    current_price: Random.natural(2000, 3000),
    debt_sign: 255,
    exchange_code: "1",
    highest: Random.natural(2000, 3000),
    info: "",
    lowest: Random.natural(2000, 3000),
    mini_trans: 100,
    name: "上证指数",
    national_debt: "0.00",
    open_price: Random.natural(2000, 3000),
    price_range: Random.natural(2000, 3000),
    price_rate: Random.natural(2000, 3000),
    sell_chang_price: "0.01",
    sell_five_amount: false,
    sell_five_price: "0",
    sell_four_amount: false,
    sell_four_price: "0",
    sell_one_amount: false,
    sell_one_price: "0",
    sell_three_amount: false,
    sell_three_price: "0",
    sell_two_amount: false,
    sell_two_price: "0",
    sub: 0,
    time: Random.date(),
    turnover: Random.natural(20000000, 30000000),
    type: 1,
    volume: Random.natural(2000000, 3000000),
    yesterday_price: Random.natural(2000, 3000),
  }
  return {
    data
  }
}


//home 日k
const getDay_k = function () {
  let data = [];
  for (let i = 0; i < 100; i++) {
    let newArticleObject = {
      id: i,
      close: Random.natural(2647, 3100),
      high: Random.natural(2647, 3100),
      low: Random.natural(2647, 3100),
      ma5: Random.natural(2647, 3100),
      ma10: Random.natural(2647, 3100),
      ma20: Random.natural(2647, 3100),
      ma30: Random.natural(3000, 4000),
      open: Random.natural(2647, 3000),
      price_equal: Random.natural(1000, 2000),
      time: Random.date(),
      volume: Random.natural(2000000, 3000000),
      yesterday_close: Random.natural(2647, 3100),
    }
    data.push(newArticleObject)
  }
  return {
    data
  }
}

//home 周k
const getWeek_k = function () {
  let data = [];
  for (let i = 0; i < 100; i++) {
    let newArticleObject = {
      id: i,
      close: Random.natural(2647, 3100),
      high: Random.natural(2647, 3100),
      low: Random.natural(2647, 3100),
      ma5: Random.natural(2647, 3100),
      ma10: Random.natural(2647, 3100),
      ma20: Random.natural(2647, 3100),
      ma30: Random.natural(3000, 4000),
      open: Random.natural(2647, 3000),
      price_equal: Random.natural(1000, 2000),
      time: Random.date(),
      volume: Random.natural(2000000, 3000000),
      yesterday_close: Random.natural(2647, 3100),
    }
    data.push(newArticleObject)
  }
  return {
    data
  }
}

//home 月k
const getMonth_k = function () {
  let data = [];
  for (let i = 0; i < 100; i++) {
    let newArticleObject = {
      id: i,
      close: Random.natural(2647, 3100),
      high: Random.natural(2647, 3100),
      low: Random.natural(2647, 3100),
      ma5: Random.natural(2647, 3100),
      ma10: Random.natural(2647, 3100),
      ma20: Random.natural(2647, 3100),
      ma30: Random.natural(3000, 4000),
      open: Random.natural(2647, 3000),
      price_equal: Random.natural(1000, 2000),
      time: Random.date(),
      volume: Random.natural(2000000, 3000000),
      yesterday_close: Random.natural(2647, 3100),
    }
    data.push(newArticleObject)
  }
  return {
    data
  }
}


module.exports = [
  {
    url: '/index/minute_k',
    type: 'post',
    response: config => {
      return {
        code: 20000,
        data: getMinute_k()
      }
    }
  },
  {
    url: '/index/market',
    type: 'post',
    response: config => {
      return {
        code: 20000,
        data: getMarket()
      }
    }
  },
  {
    url: '/index/day_k',
    type: 'get',
    response: config => {
      return {
        code: 20000,
        data: getDay_k()
      }
    }
  },
  {
    url: '/index/week_k',
    type: 'get',
    response: config => {
      return {
        code: 20000,
        data: getWeek_k()
      }
    }
  },
  {
    url: '/index/month_k',
    type: 'get',
    response: config => {
      return {
        code: 20000,
        data: getMonth_k()
      }
    }
  },
]