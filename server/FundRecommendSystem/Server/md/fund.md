## 基金组合推荐系统API

[toc]

### 1、用户注册

请求地址：`http://1.15.136.176:8080/frs/user/register`

请求方法：`POST`

请求头：`Content-Type: application/json`

请求参数：

```json
{
  "username": "youxuan",
  "password": "123456",
  "again_password": "123456"
}
```

响应：

> 注意：统一标准，JSON中所有的字段是小写，单词之间使用下划线连接，和之前不同。

```json

{
  "status_code": 200,
  "message": "注册成功",
  "data": null
}

{
  "status_code": 400,
  "message": "用户已存在",
  "data": null
}

{
  "status_code": 400,
  "message": "注册失败,两次输入的密码不一致",
  "data": null
}
```

### 2、用户登陆

请求地址：`http://1.15.136.176:8080/frs/user/login`

请求方法：`POST`

请求头：`Content-Type: application/json`

请求参数：

```json
{
  "username": "youxuan",
  "password": "123456"
}
```

响应：

```json
{
  "status_code": 200,
  "message": "登陆成功",
  "data": null
}


{
  "status_code": 400,
  "message": "密码或账号错误 ",
  "data": null
}
```

### 3、获取用户的投资倾向

请求地址：`http://1.15.136.176:8080/frs/fund/getusertendency?username=youxuan`

请求方法：`GET`

请求参数：`username=youxuan`

响应：

```json
{
  "status_code": 200,
  "message": "获取成功",
  "data": {
    "_id": "603e2a2104a592470ec2214c",
    "username": "youxuan",
    "type": 2,                  
    "profit": 101,
    "dropdown": 22,
    "invest_amount": "123",
    "invest_duration": 100
  }
}
```



### 4、添加或更改用户的投资倾向

请求地址：`http://1.15.136.176:8080/frs/fund/usertendency`

请求方法：`POST`

请求参数：
```json
{
  "username": "youxuan",
  "invest_amount": "123",
  "invest_duration": 100,
  "type": 2,
  "profit" : 101,
  "dropdown": 22
}
```

注：

| 字段            | 字段说明               | 注                              |
| --------------- | ---------------------- | ------------------------------- |
| username        | 用户名                 | 不能为空                        |
| invest_amount   | 投资金额（人民币）     | 大于等于0                       |
| invest_duration | 投资时长（天）         | 大于等于0                       |
| type            | 投资类型               | 0：激进型  1：灵活型  2：稳健型 |
| profit          | 预期利润率（%）        | 大于等于0                       |
| dropdown        | 能接受的最大回撤 （%） | 大于等于0                       |

响应：

```json
{
  "status_code": 200,
  "message": "保存成功",
  "data": null
}
```



### 5、多条件分页获取基金组合数据

请求地址：`http://1.15.136.176:8080/frs/fund/getfundlist?current_page=1&page_size=10&default_order=&invest_strategy=&manager_type=&from=`

请求方法：`GET`

请求参数：

|    字段参数     |  值  |                             说明                             |
| :-------------: | :--: | :----------------------------------------------------------: |
|  current_page   |  1   |                            不可空                            |
|    page_size    |  10  |                            不可空                            |
|  default_order  |  0   | 不可空，默认顺序，0：综合排序 1：年化 2：总收益率 3：回撤 （从小到大） 4：夏普比率 5：跟随人数  6：持仓金额 |
| invest_strategy |  0   |          投资策略， 0：激进型，1：灵活型 2：稳健型           |
|  manager_type   |  0   |             管理者类型，0：基金达人，1：普通基民             |
|      from       |  1   |                平台：1：天天基金，2：蛋卷基金                |



响应：

```json
{
  "message": "获取成功",
  "data": {
    "total": 10,
    "list": [
      {
        "_id": "603a56a61f1b0b6c6617c5ed",
        "yield": 2.84,
        "sharpe": 2.58,
        "sales": "1139756730",
        "from": 2,
        "plan_code": "CSI1021",
        "follow_num": 60633,
        "plan_name": "钉钉宝90天组合",
        "manager_name": "银行螺丝钉",
        "max_drawdown": 0.33,
        "all_yield": "3.97",
        "found_days": 516,
        "fund_type": 1
      },
      {
        "_id": "603a56a61f1b0b6c6617c5ee",
        "yield": 0.9,
        "sharpe": -0.36,
        "sales": "25515765",
        "from": 2,
        "plan_code": "CSI1054",
        "follow_num": 3435,
        "plan_name": "股市药丸-固若金汤",
        "manager_name": "股市药丸",
        "max_drawdown": 2.11,
        "all_yield": "0.82",
        "found_days": 334,
        "fund_type": 1
      },
      {
        "_id": "603a56a61f1b0b6c6617c5ef",
        "yield": 7.54,
        "sharpe": 1.98,
        "sales": "1813625944",
        "from": 2,
        "plan_code": "CSI1014",
        "follow_num": 73698,
        "plan_name": "我要稳稳的幸福",
        "manager_name": "交银施罗德基金",
        "max_drawdown": 2.79,
        "all_yield": "34.68",
        "found_days": 1499,
        "fund_type": 2
      },
      {
        "_id": "603a56a61f1b0b6c6617c5f0",
        "yield": 7.72,
        "sharpe": 2.36,
        "sales": "9735216628",
        "from": 2,
        "plan_code": "CSI1019",
        "follow_num": 122576,
        "plan_name": "钉钉宝365天组合",
        "manager_name": "银行螺丝钉",
        "max_drawdown": 1.35,
        "all_yield": "10.90",
        "found_days": 516,
        "fund_type": 2
      },
      {
        "_id": "603a56a61f1b0b6c6617c5f1",
        "yield": 8.69,
        "sharpe": 2.09,
        "sales": "115707963",
        "from": 2,
        "plan_code": "CSI1056",
        "follow_num": 8998,
        "plan_name": "绝对收益目标组合",
        "manager_name": "二鸟说",
        "max_drawdown": 1.96,
        "all_yield": "21.36",
        "found_days": 850,
        "fund_type": 2
      }
    ],
    "current_page": 1,
    "page_size": 10
  },
  "status_code": 200
}
```



### 6、全局多条件模糊搜索

请求地址：`http://1.15.136.176:8080/frs/fund/searchfund?keyword=`

请求方法：`GET`

请求参数：`keyword`

keyword参数可填入plan_code、manager_name和plan_name字段的值

例如：

```json
GET http://1.15.136.176:8080/frs/fund/searchfund?keyword=10374451         // plan_code
GET http://1.15.136.176:8080/frs/fund/searchfund?keyword=CIS              // plan_code
GET http://1.15.136.176:8080/frs/fund/searchfund?keyword=Alex价值发现者    // manager_name
GET http://1.15.136.176:8080/frs/fund/searchfund?keyword=价值五剑          // plan_name
```

响应：

```json
{
  "message": "查询成功",
  "data": [
    {
      "_id": "603a56a61f1b0b6c6617c621",
      "yield": 38.55,
      "sharpe": 1.68,
      "sales": "292570723",
      "from": 2,
      "plan_code": "CSI1006",
      "follow_num": 33911,
      "plan_name": "全球精选",
      "manager_name": "Alex价值发现者",
      "max_drawdown": 15.92,
      "all_yield": "122.66",
      "found_days": 884,
      "fund_type": 0,
      "manager_type": 0
    },
    {
      "_id": "603a6a4047b8f657e7d0ee9d",
      "yield": 66.9,
      "sharpe": 0.2674,
      "sales": "2589.1674",
      "from": 1,
      "plan_code": "10014979",
      "follow_num": 0,
      "plan_name": "封闭基金组合",
      "manager_name": "价值投资Alex",
      "max_drawdown": 5.64,
      "all_yield": "129.76",
      "found_days": 787,
      "fund_type": 0,
      "manager_type": 0
    }
  ],
  "status_code": 200
}
```

### 7、蛋卷基金组合详情信息

> 包括5个请求。

请求地址：

```http
// 细节 （基金持仓信息）
http://1.15.136.176:8080/frs/funddetail/detail?plan_code=CSI2001
// 每日增长
http://1.15.136.176:8080/frs/funddetail/growth?plan_code=CSI2001
// 计划 (年化，成立日期，跟投人数等待)
http://1.15.136.176:8080/frs/funddetail/plan?plan_code=CSI2001
// 指标 （回撤，夏普比率等待）
http://1.15.136.176:8080/frs/funddetail/indicator?plan_code=CSI2001
// 总结 持仓分类
http://1.15.136.176:8080/frs/funddetail/summary?plan_code=CSI2001
```

请求方法：`GET`

请求参数：`plan_code`

响应：参数太多，把链接直接复制到浏览器查看。

### 8、天天基金组合详情信息

请求地址：

```
http://1.15.136.176:8080/frs/funddetail/tiantian_detail?plan_code=10106134

// 每日增长
http://1.15.136.176:8080/frs/funddetail/tiantian_fund_growth?plan_code=10106134
```

请求方法：`GET`

请求参数：`plan_code`

响应：参数太多，把链接直接复制到浏览器查看。

### 9、获取推荐列表

请求地址：`http://1.15.136.176:8080/frs/fund/recommender?username=youxuan`

请求方法：`GET`

请求参数：`username`

响应：

```json
// 总共10条与用户相似度最高的数据，这里文章有限，只展示两条。
{
  "message": "获取成功",
  "data": [
    {
      "_id": "60448bc863b0da59a290a8bf",
      "yield": 2.87,
      "sharpe": 2.64,
      "sales": "1144905464",
      "from": 2,
      "plan_code": "CSI1021",
      "follow_num": 61355,
      "plan_name": "钉钉宝90天组合",
      "manager_name": "银行螺丝钉",
      "max_drawdown": 0.33,
      "all_yield": "4.06",
      "found_days": 521,
      "fund_type": 1,
      "manager_type": 0,
      "final_prior": 270.6
    },
    {
      "_id": "60448bc863b0da59a290a8c0",
      "yield": 1.0,
      "sharpe": -0.3,
      "sales": "25518015",
      "from": 2,
      "plan_code": "CSI1054",
      "follow_num": 3454,
      "plan_name": "股市药丸-固若金汤",
      "manager_name": "股市药丸",
      "max_drawdown": 2.11,
      "all_yield": "0.92",
      "found_days": 339,
      "fund_type": 1,
      "manager_type": 0,
      "final_prior": -30.19
    }
  "status_code": 200
}
```

