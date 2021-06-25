<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>预约单管理</title>
    <#include "/common/link.ftl">
</head>
<body class="hold-transition skin-black sidebar-mini">
<div class="wrapper">
    <#include "/common/navbar.ftl">
    <!--菜单回显-->
    <#assign currentMenu="appointment"/>
    <#include "/common/menu.ftl">
    <div class="content-wrapper">
        <section class="content-header">
            <h1>预约单管理</h1>
        </section>
        <section class="content">
            <div class="box">
                <!--高级查询--->
                <div style="margin: 20px 0px 0px 10px">
                    <form class="form-inline" id="searchForm" action="/appointment/list" method="post">
                        <input type="hidden" name="currentPage" id="currentPage" value="1">
                        <div class="form-group">
                            <label>预约单流水号</label>
                            <input type="text" class="form-control" placeholder="请输入预约单流水号" name="ano"
                                   value="${qo.ano}">
                        </div>
                        <div class="form-group">
                            <label>预约业务大类</label>
                            <select class="form-control" id="systemDictionaryId" name="systemDictionaryId">
                                <option value="">请选择业务大类</option>
                                <#list systemDictionaries as business>
                                    <option <#if qo.systemDictionaryId==business.id>selected</#if>
                                            value="${business.id}">
                                        ${business.name}
                                    </option>
                                </#list>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>预约单状态</label>
                            <select class="form-control" id="status" name="status">
                                <option value="">全部</option>
                                <#list  AppointmentStatusEnums as AppointmentStatusEnum>
                                    <option value="${AppointmentStatusEnum.getStatus()}"
                                            <#if qo.status==AppointmentStatusEnum.getStatus()>selected</#if>>
                                        ${AppointmentStatusEnum.getName()}
                                    </option>
                                </#list>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>门店查询</label>
                            <select class="form-control" id="businessId" name="businessId">
                                <option value="">请选择门店</option>
                                <#list businesses as business>
                                    <option value="${business.id}" <#if qo.businessId==business.id>selected</#if>>
                                        ${business.name}
                                    </option>
                                </#list>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>客户名称</label>
                            <input type="text" class="form-control" placeholder="请输入客户名称" name="contactName"
                                   value="${qo.contactName}">
                        </div>

                        <div class="form-group">
                            <label>客户手机号</label>
                            <input type="text" class="form-control" placeholder="请输入客户手机号" name="contactTel"
                                   value="${qo.contactTel}">
                        </div>

                        <br/>
                        <br/>

                        <div class="form-group">
                            <label>预约时间查询：</label>
                            <input placeholder="请输入开始时间" type="text" class="form-control input-date"
                                   name="beginAppointmentTime"
                                   value="${(qo.beginAppointmentTime?string('yyyy-MM-dd'))!}"/> -
                            <input placeholder="请输入结束时间" type="text" class="form-control input-date"
                                   name="endAppointmentTime"
                                   value="${(qo.endAppointmentTime?string('yyyy-MM-dd'))!}"/>
                        </div>

                        <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span>
                            查询
                        </button>

                        <a href="#" class="btn btn-success btn-input">
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
                            <th>业务大类</th>
                            <th>预约说明</th>
                            <th>预约时间</th>
                            <th>客户名称</th>
                            <th>联系方式</th>
                            <th>预约门店</th>
                            <th>状态</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list pageInfo.list as appointment>
                            <tr>
                                <td>${appointment.id}</td>
                                <td>${appointment.ano}</td>
                                <td>${appointment.systemDictionary.name}</td>
                                <td>${appointment.info}</td>
                                <td>${appointment.appointmentTime?datetime}</td>
                                <td>${appointment.contactName}</td>
                                <td>${appointment.contactTel}</td>
                                <td>${appointment.business.name}</td>
                                <td>${appointment.statusName}</td>
                                <td>
                                    <#if appointment.status == 0>
                                        <a href="#" data-json='${appointment.toJson()}'
                                           class="btn btn-info btn-xs btn-input">
                                            <span class="glyphicon glyphicon-pencil"></span> 编辑
                                        </a>
                                        <a class="btn btn-xs btn-primary btn-status" data-id="${appointment.id}"
                                           data-status="1">
                                            <span class="glyphicon glyphicon-phone-alt"></span> 确认预约</a>
                                        <a class="btn btn-xs btn-danger btn-status" data-id="${appointment.id}"
                                           data-status="4">
                                            <span class="glyphicon glyphicon-remove"></span> 取消预约</a>
                                    </#if>
                                    <#if appointment.status ==1>
                                        <a href="#" class="btn btn-success btn-xs btn-consume" data-id="${appointment.id}">
                                            <span class="glyphicon glyphicon-shopping-cart"></span> 确认到店
                                        </a>
                                    </#if>
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
<script>
    $(function () {
        $(".btn-input").click(function () {
            var data = $(this).data("json");
            $("input[name]").val("");
            $("select[name]").val("");
            $("textarea[name]").val("");
            if (data) {
                $("input[name=id]").val(data.id);
                $("select[name='business.id']").val(data.businessId);
                $("input[name=appointmentTime]").val(data.appointmentTime);
                $("select[name='systemDictionary.id']").val(data.systemDictionaryId);
                $("input[name=contactName]").val(data.contactName);
                $("input[name=contactTel]").val(data.contactTel);
                $("textarea[name=info]").val(data.info);
            }

            //若是修改才回显数据
            $("#editModal").modal("show");

        });

        //确认或者取消预约
        $('.btn-status').click(function () {
            //确认,提示客户真的预约
            //取消,提示客户真的想取消预约
            var status = $(this).data("status");
            var tip = status == 1 ? "用户预约?" : "用户取消预约?";
            var id = $(this).data("id");
            Swal.fire({
                title: "预约操作",
                text: tip,
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: '确定',
                cancelButtonText: '取消'
            }).then((result) => {
                //发送id 和 状态值
                location.href = "/appointment/updateStatus?id=" + id + "&status=" + status;
            });
        });

        //确认到店
        $(".btn-consume").click(function () {
            var id = $(this).data("id");
            Swal.fire({
                title: "预约操作",
                text: "时候要消费",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: '确定',
                cancelButtonText: '取消'
            }).then((result) => {
                //发送id 和 状态值
                if (result.value) {
                    //有消费,本质就是把对应预约改成消费中状态,还要往数据库结算单表中添加一条数据,结算单的状态是待结算状态
                    location.href = "/consumption/save?appointmentId=" + id;
                }else {
                    //无消费,本质就是把对应预约改成归档状态
                    location.href = "/appointment/updateStatus?id=" + id + "&status=3";
                }
            });
        });
    });
</script>

<#-- 文件上传模态框 -->
<!--模态框-->
<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel">新增/编辑</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
            </div>

            <form id="editForm" action="/appointment/saveOrUpdate" method="post">
                <div class="modal-body">
                    <input type="hidden" name="id">
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">预约门店：</label>
                        <div class="col-sm-7">
                            <select class="form-control" name="business.id">
                                <option value="">请选择预约门店</option>
                                <#list businesses as business>
                                    <option value="${business.id}">${business.name}</option>
                                </#list>
                            </select>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">预约时间：</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control input-date " placeholder="请输入预约时间"
                                   name="appointmentTime"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">业务大类：</label>
                        <div class="col-sm-7">
                            <select class="form-control" name="systemDictionary.id">
                                <option value="">请选择业务大类</option>
                                <#list systemDictionaries as systemDictionary>
                                    <option value="${systemDictionary.id}">${systemDictionary.name}</option>
                                </#list>
                            </select>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">联系人：</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" name="contactName"
                                   placeholder="请输入联系人">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">联系电话：</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" name="contactTel"
                                   placeholder="请输入联系电话">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">预约说明：</label>
                        <div class="col-sm-7">
                            <textarea type="text" class="form-control" name="info"
                                      placeholder="请输入预约说明"></textarea>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="submit" class="btn btn-primary btn-submit">保存</button>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>
