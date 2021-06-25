<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>结算单管理</title>
    <#include "/common/link.ftl">
</head>
<body class="hold-transition skin-black sidebar-mini">
<div class="wrapper">
    <#include "/common/navbar.ftl">
    <!--菜单回显-->
    <#assign currentMenu="consumption"/>
    <#include "/common/menu.ftl">
    <div class="content-wrapper">
        <section class="content-header">
            <h1>结算单管理</h1>
        </section>
        <section class="content">
            <div class="box">
                <!--高级查询--->
                <div style="margin: 20px 0px 0px 10px">
                    <form class="form-inline" id="searchForm" action="/consumption/list" method="post">
                        <input type="hidden" name="currentPage" id="currentPage" value="1">
                                <div class="form-group">
                                    <label>结算单状态</label>
                                    <select class="form-control" name="status" >
                                        <option value="">全部</option>
                                        <#list consumptionStatusEnums as consumptionStatus>
                                            <option value="${consumptionStatus.getStatus()}" <#if qo.status==consumptionStatus.getStatus() >selected</#if>>${consumptionStatus.getName()}</option>
                                        </#list>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label>门店查询</label>
                                    <select class="form-control" name="businessId">
                                        <option value="">请选择门店</option>
                                        <#list businesses as business>
                                            <option value="${business.id}" <#if qo.businessId==business.id >selected</#if> >${business.name}</option>
                                        </#list>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label>预约类型</label>
                                    <select class="form-control" name="appointmentType">
                                        <option value="">请选择预约类型</option>
                                        <option value="1" >预约</option>
                                        <option value="0">非预约</option>
                                    </select>
                                </div>
                                <script>
                                    $(function () {
                                        $("select[name=appointmentType]").val(${(qo.appointmentType?string("1","0"))!});
                                    })
                                </script>
                                <div class="form-group">
                                    <label>客户名称</label>
                                    <input type="text" name="customerName" value="${qo.customerName}" class="form-control"  placeholder="请输入客户名称">
                                </div>

                                <div class="form-group">
                                    <label>客户手机号</label>
                                    <input type="text" name="customerTel" value="${qo.customerTel}" class="form-control" placeholder="请输入客户手机号">
                                </div>
                                <div class="form-group">
                                    <label>预约单流水号</label>
                                    <input type="text" name="AppointmentAno" value="${qo.appointmentAno}" class="form-control" placeholder="预约单流水号">
                                </div>


                                <br/>
                                <br/>

                        <div class="form-group">
                            <label>结算单流水号</label>
                            <input type="text" name="cno" value="${qo.cno}" class="form-control" placeholder="结算单流水号">
                        </div>

                        <div class="form-group">
                            <label>结算时间查询：</label>
                            <input placeholder="请输入开始时间" value="${(qo.beginPayTime?string("yyyy-MM-dd HH:mm:ss"))!}" name="beginPayTime" type="text"  class="form-control input-date"   /> -
                            <input placeholder="请输入结束时间" value="${(qo.endPayTime?string("yyyy-MM-dd HH:mm:ss"))!}" name="endPayTime" type="text"  class="form-control input-date" />
                        </div>

                        <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span> 查询</button>
                            <a href="/consumption/input" class="btn btn-success btn-input">
                                <span class="glyphicon glyphicon-plus"></span> 添加
                            </a>
                    </form>

                </div>
                <div class="box-body table-responsive ">
                <table class="table table-hover table-bordered table-striped">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>流水号</th>
                        <th>预约单号</th>
                        <th>客户名称</th>
                        <th>联系方式</th>
                        <th>总消费金额</th>
                        <th>实收金额</th>
                        <th>结算时间</th>
                        <th>结算人</th>
                        <th>消费门店</th>
                        <th>状态</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list pageInfo.list as consumption>
                        <tr>
                            <td>${consumption.id}</td>
                            <td>${consumption.cno}</td>
                            <td>${consumption.appointmentAno}</td>
                            <td>${consumption.customerName}</td>
                            <td>${consumption.customerTel}</td>
                            <td>${consumption.totalAmount}</td>
                            <td>${consumption.payAmount}</td>
                            <td>${(consumption.payTime?string("yyyy-MM-dd HH:mm:ss"))!}</td>
                            <td>${consumption.payeeId}</td>
                            <td>${consumption.business.name}</td>
                            <td>${consumption.statusName}</td>
                            <td>
                                <a href="/consumption/input?id=${consumption.id}" class="btn btn-info btn-xs btn-input" >
                                    <span class="glyphicon glyphicon-pencil"></span> 详情
                                </a>
                            </td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
                    <!--分页-->
                    <#include "/common/page.ftl">
                </div>

            </div>
        </section>
    </div>
    <#include "/common/footer.ftl">
</div>

</body>
</html>