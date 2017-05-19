<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="fa fa-home"></i> 首页</el-breadcrumb-item>
                <el-breadcrumb-item><i class="fa fa-camera"></i> 监控</el-breadcrumb-item>
                <el-breadcrumb-item><i class="fa fa-server"></i> 运行环境</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <el-tabs v-model="activeName2" type="card" @tab-click="handleClick">
            <el-tab-pane label="服务器状态" name="dynamic">
                <div class="echarts">
                    <IEcharts :option="line"></IEcharts>
                </div>
                <div class="echarts">
                    <IEcharts :option="bar"></IEcharts>
                </div>
            </el-tab-pane>
            <el-tab-pane label="告警配置" name="config">告警配置</el-tab-pane>
            <el-tab-pane label="服务器信息" name="info">服务器信息</el-tab-pane>
        </el-tabs>
    </div>
    <!--sql详情-->
</template>

<script>
import IEcharts from 'vue-echarts-v3';
export default {
    components: {
        IEcharts
    },
    data() {
        return {
            activeName2: 'dynamic',
            line: {
                color: ["#20a0ff", "#13CE66", "#F7BA2A", "#FF4949"],
                title: {
                    text: '主机实时性能曲线'
                },
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    data: ['CPU', 'JVM', 'RAM', 'SWAP', "DISK", 'NETWORK IN', 'NETWORK OUT']
                },
                xAxis: [{
                    type: 'category',
                    boundaryGap: true,
                    data: []
                }],
                yAxis: [{
                    type: 'value',
                    scale: true,
                    name: '百分比',
                    max: 100,
                    min: 0,
                    boundaryGap: [0.2, 0.2]
                }, {
                    type: 'value',
                    scale: true,
                    max: 5000,
                    min: 0,
                    name: ' k/s',
                    boundaryGap: [0.2, 0.2]
                }],
                series: [{
                    name: 'CPU',
                    type: 'line',
                    smooth: true,
                    data: []
                }, {
                    name: 'JVM',
                    type: 'line',
                    smooth: true,
                    data: []
                }, {
                    name: 'RAM',
                    type: 'line',
                    smooth: true,
                    data: []
                },
                {
                    name: 'SWAP',
                    type: 'line',
                    smooth: true,
                    yAxisIndex: 0,
                    data: []
                },
                {
                    name: 'DISK',
                    type: 'line',
                    smooth: true,
                    yAxisIndex: 0,
                    data: []
                },

                {
                    name: 'NETWORK IN',
                    type: 'line',
                    smooth: true,
                    yAxisIndex: 1,
                    data: []
                }, {
                    name: 'NETWORK OUT',
                    type: 'line',
                    smooth: true,
                    yAxisIndex: 1,
                    data: []
                }]
            },
            bar: {
                color:["#20a0ff","#13CE66","#F7BA2A","#FF4949"],
                title: {
                    text: '柱状图'
                },
                xAxis: {
                    data: ["衬衫", "羊毛衫", "雪纺衫", "裤子", "高跟鞋", "袜子"]
                },
                yAxis:{},
                series: [
                    {
                        name: "销量",
                        type: "bar",
                        data: [5, 20, 36, 10, 10, 20]
                    }
                ]
            },
        };
    },
    methods: {
        handleClick(tab, event) {
            console.log(tab, event);
        }
    },
    mounted() {
        const self = this;
        setInterval(function () {
            self.get('/sigar/api?type=ALL', res => {
                self.line.xAxis[0].data.push(new Date().toLocaleTimeString());//时间戳
                self.line.series[0].data.push(res.data.cpuUsage);
                self.line.series[1].data.push(res.data.jvmUasge);
                self.line.series[2].data.push(res.data.ramUasge);
                self.line.series[3].data.push(res.data.swapUasge);
                self.line.series[4].data.push(res.data.diskUsage);
                self.line.series[5].data.push(res.data.niUsage);
                self.line.series[6].data.push(res.data.noUsage);
            })
        }, 2000)

    }
};
</script>
<style scoped>
.el-row {
    margin-bottom: 20px;
    &:last-child {
        margin-bottom: 0;
    }
}

.el-col {
    border-radius: 4px;
}

.bg-purple-dark {
    background: #99a9bf;
}

.bg-purple {
    background: #d3dce6;
}

.bg-purple-light {
    background: #e5e9f2;
}

.grid-content {
    border-radius: 4px;
    min-height: 36px;
}

.row-bg {
    padding: 10px 0;
    background-color: #f9fafc;
}

.echarts {
    float: left;
    width: 100%;
    height: 400px;
}
</style>
