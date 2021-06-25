<script src="/static/js/plugins/echarts/echarts.common.min.js"></script>
<div id="main" style="width: 800px;height:500px;"></div>

<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据
    var option = {
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross',
                crossStyle: {
                    color: '#999'
                }
            }
        },
        toolbox: {
            feature: {
                dataView: {show: true, readOnly: false},
                magicType: {show: true, type: ['line', 'bar']},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        legend: {
            data: ['总消费金额', '总优惠金额', '总实收金额', '订单数']
        },
        xAxis: [
            {
                type: 'category',
                data: ${groupTypes},
                axisPointer: {
                    type: 'shadow'
                }
            }
        ],
        yAxis: [
            {
                type: 'value',
                name: '金额',
                min: 0,
                axisLabel: {
                    formatter: '{value} $'
                }
            },
            {
                type: 'value',
                name: '数量',
                min: 0,
                axisLabel: {
                    formatter: '{value} 个'
                }
            }
        ],
        series: [
            {
                name: '总消费金额',
                type: 'bar',
                data: ${totalAmounts},
                markPoint: {
                    data: [
                        {type: 'max', name: '最大值'},
                        {type: 'min', name: '最小值'}
                    ]
                },
                markLine: {
                    data: [
                        {type: 'average', name: '平均值'}
                    ]
                }
            },
            {
                name: '总优惠金额',
                type: 'bar',
                data: ${totalDiscountAmounts},
            }, {
                name: '总实收金额',
                type: 'bar',
                data: ${totalPayAmounts},
            },
            {
                name: '订单数',
                type: 'line',
                yAxisIndex: 1,
                data: ${counts},
            }
        ]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
</script>
