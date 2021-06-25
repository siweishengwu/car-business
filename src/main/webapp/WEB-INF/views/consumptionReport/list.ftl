<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>门店收入报表</title>
    <#include "/common/link.ftl" >

</head>
<body class="hold-transition skin-black sidebar-mini">
<div class="wrapper">
    <#include "/common/navbar.ftl" >

    <#assign currentMenu="businessReport"/>

    <#include "/common/menu.ftl" >
    <div class="content-wrapper">
        <section class="content-header">
            <h1>门店收入报表</h1>
        </section>
        <section class="content">
            <div class="box">
                <div style="margin: 10px;">
                    <!--高级查询--->
                    <form class="form-inline" id="searchForm" action="/consumptionReport/list" method="post">
                        <input type="hidden" name="currentPage" id="currentPage" value="1">
                        <div class="form-group">
                            <label>门店查询</label>
                            <select class="form-control" name="businessId">
                                <option value="">请选择门店</option>
                                <#list businesses as business>
                                    <option value="${business.id}" <#if business.id == qo.businessId>selected</#if> >${business.name}</option>
                                </#list>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>时间段查询:</label>
                            <div class="input-daterange input-group">
                                <input type="text" name="beginCreateTime" value="${(qo.beginCreateTime?string('yyyy-MM-dd'))!}" class="input-sm form-control input-date2"/>
                                <span class="input-group-addon">到</span>
                                <input type="text" name="endCreateTime" value="${(qo.endCreateTime?string('yyyy-MM-dd'))!}" class="input-sm form-control input-date2"/>
                            </div>
                        </div>
                        <script>
                            $(function () {
                                $('.input-date2').datetimepicker({
                                    language: 'zh-CN',
                                    autoclose: true,
                                    minView : 2,
                                    todayHighlight: true, // 今天时间高亮
                                    format: 'yyyy-mm-dd',
                                    todayBtn: true
                                });
                            });
                        </script>
                        <div class="form-group">
                            <label>预约类型</label>
                            <select class="form-control" name="appointmentType">
                                <option value="">请选择预约类型</option>
                                <option value="1">预约</option>
                                <option value="0">非预约</option>
                            </select>
                        </div>
                        <script>
                            $(function () {
                                $("select[name=appointmentType]").val(${(qo.appointmentType?string("1","0"))!});
                            })
                        </script>
                        <div class="form-group">
                            <label for="status">分组类型:</label>
                            <select class="form-control" name="groupType">
                                <option value="shop">门店</option>
                                <option value="year">年</option>
                                <option value="month">月</option>
                                <option value="day">日</option>
                            </select>
                        </div>
                        <script>
                            $(function () {
                                $("select[name=groupType]").val("${(qo.groupType)!}");
                            })
                        </script>
                        <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span>
                            查询
                        </button>
                        <button  type="button" class="btn btn-info btn-chart">
                            <span class="glyphicon glyphicon-stats"></span> 柱状图
                        </button>
                    </form>
                    <script>
                        $(function () {
                            $(".btn-chart").click(function () {
                                $('#chartModal').modal({
                                    remote: "/consumptionReport/listBar?" + $("#searchForm").serialize()  // 加上高级查询的条件
                                });
                            });
                        });
                    </script>
                </div>
                <div class="box-body table-responsive no-padding ">
                    <table class="table table-hover table-bordered">
                        <thead>
                        <tr>
                            <th>分组类型</th>
                            <th>总订单数</th>
                            <th>总消费金额</th>
                            <th>总实收金额</th>
                            <th>总优惠金额</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list pageInfo.list as vo>
                            <tr>
                                <td>${vo.groupType}</td>
                                <td>${vo.totalCount}</td>
                                <td>${vo.totalAmount}</td>
                                <td>${vo.totalPayAmount}</td>
                                <td>${vo.totalDiscountAmount}</td>
                            </tr>
                        </#list>

                        </tbody>

                    </table>
                    <#include "/common/page.ftl">
                </div>
            </div>
        </section>
    </div>
    <#include "/common/footer.ftl">
</div>

<!-- Modal模态框 -->
<div class="modal fade" id="chartModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content" style="padding: 20px">

        </div>
    </div>
</div>

</body>
</html>