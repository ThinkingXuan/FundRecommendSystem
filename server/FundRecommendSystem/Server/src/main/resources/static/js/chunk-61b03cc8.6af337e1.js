(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-61b03cc8"],{"1ef1":function(t,e,a){"use strict";var n=a("7731"),i=a.n(n);i.a},3044:function(t,e,a){},"3e65":function(t,e,a){"use strict";var n=a("3044"),i=a.n(n);i.a},"6e19":function(t,e,a){"use strict";a.r(e);var n=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"nolighttable",attrs:{id:"home"}},[a("main",[a("div",{staticClass:"topData"},[a("div",[t._v(" "+t._s(t.fund_data.plan_name)+" ")]),a("div",[t._v(t._s(t.fund_data.plan_code)+" "+t._s(t.fund_data.manager_name))])]),a("div",[a("el-table",{staticStyle:{width:"100%"},attrs:{data:t.list1,"show-header":!1,fit:""}},[a("el-table-column",{attrs:{align:"center"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v(" 成立以来收益 "),a("p",{staticClass:"p1",class:{redColor:Number(e.row.all_yield)>=0,greenColor:Number(e.row.all_yield)<0}},[t._v(" "+t._s(100*t.$jq(Number(e.row.all_yield)))+"% "),Number(e.row.all_yield)>=0?a("span",{staticClass:"redColor"},[t._v("↑")]):a("span",{staticClass:"greenColor"},[t._v("↓")])])]}}])}),a("el-table-column",{attrs:{align:"center"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v(" 成立天数 "),a("p",{staticClass:"p1"},[t._v(" "+t._s(e.row.found_days)+" ")])]}}])})],1),a("el-table",{staticStyle:{width:"100%"},attrs:{data:t.list2,"show-header":!1,fit:""}},[a("el-table-column",{attrs:{align:"center"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v(" 日涨跌 "),a("p",{staticClass:"p1",class:{redColor:Number(e.row.yield_t)>=0,greenColor:Number(e.row.yield_t)<0}},[t._v(" "+t._s(t.$jq(Number(e.row.yield_t)))+"% "),Number(e.row.yield_t)>=0?a("span",{staticClass:"redColor"},[t._v("↑")]):a("span",{staticClass:"greenColor"},[t._v("↓")])])]}}])}),a("el-table-column",{attrs:{align:"center"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v(" 成立以来年化 "),a("p",{staticClass:"p1",class:{redColor:Number(e.row.yield_middle)>=0,greenColor:Number(e.row.yield_middle)<0}},[t._v(" "+t._s(t.$jq(Number(e.row.yield_middle)))+"% "),Number(e.row.yield_middle)>=0?a("span",{staticClass:"redColor"},[t._v("↑")]):a("span",{staticClass:"greenColor"},[t._v("↓")])])]}}])}),a("el-table-column",{attrs:{align:"center"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v(" 跟投人数 "),a("p",{staticClass:"p1"},[t._v(" "+t._s(e.row.follow_num)+" ")])]}}])})],1)],1),a("div",{staticClass:"Echarts",staticStyle:{width:"100%",height:"400px"},attrs:{id:"line"}}),a("div",{staticClass:"Echarts",staticStyle:{width:"100%",height:"400px"},attrs:{id:"pie"}}),a("div",[a("el-table",{staticStyle:{width:"100%"},attrs:{data:t.list3,fit:""}},[a("el-table-column",{attrs:{align:"center",label:"持仓基金"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v(" "+t._s(e.row.title)+"|"+t._s(e.row.code)),0==Number(e.row.proportion)?a("span",{staticClass:"redColor"},[t._v("(已清仓)")]):t._e()]}}])}),a("el-table-column",{attrs:{align:"center",label:"比例"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v(" "+t._s(e.row.proportion))]}}])})],1)],1)])])},i=[],l=(a("d81d"),a("ac1f"),a("5319"),a("222b")),o=(a("313e"),{name:"home",data:function(){return{fund_data:{yield_middle:0,plan_code:this.$route.params.plan_code,follow_num:0,yield_t:0,found_days:null,all_yield:0,unit_nav:null,plan_name:null,manager_name:null},list1:[],list2:[],list3:[],position:[],position_name:[],line_date:[],line_value:[],line_thanvalue:[]}},components:{},mounted:function(){},methods:{pictureline:function(){var t=this.$echarts.init(document.getElementById("line")),e={title:{text:"净值"},color:["#ff6600"],xAxis:{type:"category",data:this.line_date,axisLine:{show:!1}},yAxis:{type:"value",axisLabel:{formatter:function(t){return t}},splitLine:{lineStyle:{type:"dashed"}}},legend:{data:["本产品"],left:"right"},tooltip:{trigger:"axis",formatter:'{b0}<br/><span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:#91C7AE"></span>{a0}:{c0}<br/>',padding:5,axisPointer:{type:"cross",lineStyle:{type:"dashed"}}},series:[{name:"本产品",data:this.line_value,type:"line",smooth:!0}]};t.setOption(e)},picturepie:function(){var t=this.$echarts.init(document.getElementById("pie")),e={title:{text:"持仓分布"},tooltip:{trigger:"item",formatter:"{d}%"},legend:{x:"79%",y:"28%",orient:"vertical",data:this.position_name},series:{center:["35%","47%"],type:"pie",selectedMode:"single",data:this.position}};t.setOption(e)},changeTime:function(t){Date.prototype.toLocaleString=function(){return this.getFullYear()+"年"+(this.getMonth()+1)+"月"+this.getDate()+"日 "};var e="";if(t){var a=new Date(1*t);e=a.toLocaleString()}return e},getNum:function(t){return parseInt(t.replace(/[^0-9]/gi,""))},fetchData:function(){var t=this;Object(l["g"])(this.$route.params.plan_code).then((function(e){t.fund_data.plan_name=e.data[0].plan_name,t.fund_data.manager_name=e.data[0].manager_name})),Object(l["h"])(this.$route.params.plan_code).then((function(e){t.list1=[{all_yield:"待加载",found_days:e.data.workDays}],t.list2=[{yield_middle:e.data.zhbintervalRate,follow_num:e.data.followFans,yield_t:e.data.dailyProfit}],t.list3=e.data.fundProportion,t.position=e.data.positionProportion.map((function(t){return{name:"".concat(t.title),value:"".concat(parseFloat(t.proportion))}})),t.position_name=e.data.positionProportion.map((function(t){return t.title})),t.picturepie()})),Object(l["i"])(this.$route.params.plan_code).then((function(e){t.list1[0].all_yield=e.data.AccountProfitRate,t.line_date=e.data.GraphSpotList.map((function(e){return t.changeTime(parseInt(e.AccountNav.NavDate.replace(/[^0-9]/gi,"")))})),t.line_value=e.data.GraphSpotList.map((function(t){return t.AccountNav.Nav})),t.pictureline()}))}},created:function(){this.fetchData()}}),r=o,s=(a("3e65"),a("1ef1"),a("2877")),d=Object(s["a"])(r,n,i,!1,null,"643a22fd",null);e["default"]=d.exports},7731:function(t,e,a){}}]);