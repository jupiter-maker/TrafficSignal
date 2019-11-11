
function init_brigades_sts(){
    $.ajax({
        url: "/brigade/sts",
        type: "GET",
        success: function (result) {
            if (result.status == 200) {
                //alert(result.msg);
                //解析并显示大队数据可视化
                build_dd_sts(result);
            }

        }
    });
}
function init_roads_sts(){
    $.ajax({
        url: "/road/sts",
        type: "GET",
        success: function (result) {
            if (result.status == 200) {
                //alert(result.msg);
                //解析并显示道路数据可视化
                build_dl_sts(result);
            }

        }
    });
}
function init_is_sts(){
    $.ajax({
        url: "/brigade/list/sts",
        type: "GET",
        success: function (result) {
            if (result.status == 200) {
                //alert(result.msg);
                //解析并显示路口数据可视化
                build_is_sts(result);
            }

        }
    });
}
function build_is_sts(result){
    var roadSts = result.data;
    var ddNameArray = new Array();
    var isSdNumArray = new Array();
    var isSdNotNumArray = new Array();
    var exportJson = [];
    $.each(roadSts,function(index,item){
        //xAxis += item.ddName+",";
        ddNameArray[index] = item.ddName;
        isSdNumArray[index] = item.isSdNum;
        isSdNotNumArray[index] = item.isNum-item.isSdNum;
        // var param = {};
        // param.value = item.isNum;
        // param.name = item.ddName+"-"+item.dlName;
        // exportJson.push(param);
    });
    //构建道路-路口-统计图
    chart_is_sts("is_sts",ddNameArray,isSdNumArray,isSdNotNumArray);

}
function chart_is_sts(e,ddNameArray,isSdNumArray,isSdNotNumArray){
    var roadChart = echarts.init(document.getElementById(e));
    // 指定图表的配置项和数据
    var option = {
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        legend: {
            data: ['已配时段路口数', '未配时段路口数']
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis:  {
            type: 'value'
        },
        yAxis: {
            type: 'category',
            data: ddNameArray
        },
        series: [
            {
                name: '已配时段路口数',
                type: 'bar',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'insideRight'
                    }
                },
                data: isSdNumArray
            },
            {
                name: '未配时段路口数',
                type: 'bar',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'insideRight'
                    }
                },
                data: isSdNotNumArray
            }
        ]
    };
    roadChart.setOption(option);
}
function build_dl_sts(result){
    var roadSts = result.data;
    var dlNameArray = new Array();
    var exportJson = [];
    $.each(roadSts,function(index,item){
        //xAxis += item.ddName+",";
        dlNameArray[index] = item.ddName+"-"+item.dlName;
        var param = {};
        param.value = item.isNum;
        param.name = item.ddName+"-"+item.dlName;
        exportJson.push(param);
    });
    //构建道路-路口-统计图
    chart_dl_is_sts("dl_is",dlNameArray,exportJson);

}
function build_dd_sts(result){
    var brigadeSts = result.data;
    var ddNameArray = new Array();
    var dlNumArray = new Array();
    var isNumArray = new Array();
    $.each(brigadeSts,function(index,item){
        //xAxis += item.ddName+",";
        ddNameArray[index] = item.ddName;
        dlNumArray[index] = item.dlNum;
        isNumArray[index] = item.isNum;
    });
    //构建大队-道路-统计图
    chart_dd_dl_sts("dd_dl","大队-道路-统计",ddNameArray,dlNumArray);
    chart_dd_is_sts("dd_is","大队-路口-统计",ddNameArray,isNumArray);
}
function chart_dd_dl_sts(e,title,ddNameArray,numArray){
    var brigadeChart = echarts.init(document.getElementById(e));
    // 指定图表的配置项和数据
    var option = {
        title: {
            //text: 'ECharts 入门示例'
            text: title
        },
        tooltip: {},
        legend: {
            data:['道路数']
        },
        xAxis: {
            type: 'category',
            data: ddNameArray
        },
        yAxis: {
            type: 'value'
        },
        series: [{
            name: '道路数',
            type: 'bar',
            //data: [5, 20, 36, 10, 10, 20]
            data: numArray
        }]
    };
    brigadeChart.setOption(option);
}
function chart_dd_is_sts(e,title,ddNameArray,numArray){
    var brigadeChart = echarts.init(document.getElementById(e));
    // 指定图表的配置项和数据
    var option = {
        title: {
            //text: 'ECharts 入门示例'
            text: title
        },
        tooltip: {},
        legend: {
            data:['路口数']
        },
        xAxis: {
            //data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
            type: 'category',
            data: ddNameArray
        },
        yAxis: {
            type: 'value'
        },
        series: [{
            name: '路口数',
            type: 'bar',
            //data: [5, 20, 36, 10, 10, 20]
            data: numArray
        }]
    };
    brigadeChart.setOption(option);
}
function chart_dl_is_sts(e,dlNameArray,exportJson){
    var roadChart = echarts.init(document.getElementById(e));
    // 指定图表的配置项和数据
    var option = {
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            x: 'left',
            //data:['直接访问','邮件营销','联盟广告','视频广告','搜索引擎']
            data:dlNameArray
        },
        series: [
            {
                name:'路口数',
                type:'pie',
                radius: ['50%', '70%'],
                avoidLabelOverlap: false,
                label: {
                    normal: {
                        show: false,
                        position: 'center'
                    },
                    emphasis: {
                        show: true,
                        textStyle: {
                            fontSize: '15',
                            fontWeight: 'bold'
                        }
                    }
                },
                labelLine: {
                    normal: {
                        show: false
                    }
                },
                // data:[
                //     {value:335, name:'直接访问'},
                //     {value:310, name:'邮件营销'},
                //     {value:234, name:'联盟广告'},
                //     {value:135, name:'视频广告'},
                //     {value:1548, name:'搜索引擎'}
                // ]
                data:exportJson
            }
        ]
    };
    roadChart.setOption(option);
}
