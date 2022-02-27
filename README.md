# FundRecommendSystem

📖 发现杯-基于大数据的基金组合智能推荐系统-赛区二等奖📃

### 简介：

本系统的数据来源主要是通过爬取天天基金组合宝和蛋卷基金平台等组合信息。通过深度的处理后，形成用户可见的数据。

通过该系统，用户可以查询天天组合宝和蛋卷基金平台所有的组合情况，包括组合中相关的数据和图表，并通过人性化的操作界面，按照相关顺序查询所有的基金组合情况，如果用户登录注册后，填写意向投资表，系统会实时分析平台中所有的数据，并通过一定的友好算法来推荐最适合此用户的基金。

未来还会此系统还用通过用户点击、查询和打分等功能，实时的分析用户个各种爱好需求，并分析此用户的投资倾向以做到更优的推荐。

### 技术点：

前端：Vue3.0，JavaScript，Echart等等

大数据分析：Flume，HDFS，Spark，Kafka等等

后端：SpringBoot, MongoDB，Redis等等

### 系统设计图：

- 系统功能图

  ![image-20220227162431846](https://cdn.jsdelivr.net/gh/ThinkingXuan/HexoStaticImage/img/image-20220227162431846.png)

- 数据流图

  ![image-20220227162444584](https://cdn.jsdelivr.net/gh/ThinkingXuan/HexoStaticImage/img/image-20220227162444584.png)

- 数据仓库架构图

  ![image-20220227162454416](https://cdn.jsdelivr.net/gh/ThinkingXuan/HexoStaticImage/img/image-20220227162454416.png)

- 架构设计

  ![image-20220227162511809](https://cdn.jsdelivr.net/gh/ThinkingXuan/HexoStaticImage/img/image-20220227162511809.png)

### 前端演示：

- 登录注册界面

  ![](https://cdn.jsdelivr.net/gh/ThinkingXuan/HexoStaticImage/img/image-20220227162601705.png)![image-20220227162601705](https://cdn.jsdelivr.net/gh/ThinkingXuan/HexoStaticImage/img/image-20220227162601705.png)

- 投资倾向填写界面

  ![image-20220227162720676](https://cdn.jsdelivr.net/gh/ThinkingXuan/HexoStaticImage/img/image-20220227162720676.png)

- 基金组合主页

  ![image-20220227162726304](https://cdn.jsdelivr.net/gh/ThinkingXuan/HexoStaticImage/img/image-20220227162726304.png)

- 基金组合详细页

  ![image-20220227162804047](https://cdn.jsdelivr.net/gh/ThinkingXuan/HexoStaticImage/img/image-20220227162804047.png)

  ![image-20220227162809950](https://cdn.jsdelivr.net/gh/ThinkingXuan/HexoStaticImage/img/image-20220227162809950.png)

  ![image-20220227162816663](https://cdn.jsdelivr.net/gh/ThinkingXuan/HexoStaticImage/img/image-20220227162816663.png)

- 基金推荐界面

  ![image-20220227162826540](https://cdn.jsdelivr.net/gh/ThinkingXuan/HexoStaticImage/img/image-20220227162826540.png)