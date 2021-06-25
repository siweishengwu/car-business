<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>结算单明细管理</title>
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
            <h1>结算单明细</h1>
        </section>
        <section class="content">
            <div class="box" style="padding: 10px;">
                <div class="box" style="border-top: none;">
                    <div class="box-header with-border">
                        <h3 class="box-title"><span class="glyphicon glyphicon-triangle-right"></span> 结算单明细</h3>
                    </div>
                    <div class="box-body no-padding">
                        <div class="mailbox-controls">
                            <div class="btn-group">
                                <#-- 新增 -->
                                <button type="button" id="input-data"
                                        class="btn btn-default btn-sm checkbox-toggle btn-input"><i
                                            class="fa fa-plus"></i></button>
                                <#-- 删除 -->
                                <button type="button" class="btn btn-default btn-sm btn-del"><i
                                            class="fa fa-trash-o"></i>
                                </button>
                            </div>
                        </div>
                        <div class="table-responsive mailbox-messages">
                            <table class="table table-hover table-striped">
                                <thead>
                                <tr>
                                    <th><input id="checkAll" onchange="checkChange(this)" type="checkbox"></th>
                                    <th>业务大类</th>
                                    <th>业务小类</th>
                                    <th>结算类型</th>
                                    <th>消费金额(元)</th>
                                    <th>优惠金额(元)</th>
                                    <th>实收金额(元)</th>
                                </tr>
                                </thead>
                                <tbody id="checkTbody">
                                <#list consumptionItems as consumptionItem>
                                    <tr>
                                        <td><input data-id="#{consumptionItem.id}" type="checkbox"></td>
                                        <td>${consumptionItem.category.name}</td>
                                        <td>${consumptionItem.categoryItem.name}</td>
                                        <td>${consumptionItem.payType.name}</td>
                                        <td>${consumptionItem.amount}</td>
                                        <td>${consumptionItem.discountAmount}</td>
                                        <td>${consumptionItem.payAmount}</td>
                                    </tr>
                                </#list>
                                </tbody>
                                <tfoot>
                                <tr>
                                    <th></th>
                                    <th></th>
                                    <th></th>
                                    <th></th>
                                    <th id="totalAmount" data-totalAmount="${(consumption.totalAmount)?c}">
                                        总消费金额: ${consumption.totalAmount} 元
                                    </th>
                                    <th id="discountAmount">总优惠金额: ${consumption.discountAmount} 元</th>
                                    <th id="payAmount">总实收金额: ${consumption.payAmount} 元</th>
                                </tr>
                                </tfoot>
                            </table>
                        </div>
                    </div>
                </div>
                <br/>
                <div class="box" style="border-top: none;">
                    <div class="box-header with-border">
                        <h3 class="box-title"><span class="glyphicon glyphicon-triangle-right"></span> 结算单信息</h3>
                    </div>
                    <form class="box-body" class="form-horizontal" id="editForm" action="/consumption/saveOrUpdate"
                          method="post">
                        <input type="hidden" id="cid" name="id" value="${consumption.id}">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>客户名称：</label>
                                    <input type="text" name="customerName" value="${consumption.customerName}"
                                           class="form-control" placeholder="请输入客户名称">
                                </div>
                                <div class="form-group">
                                    <label>客户电话：</label>
                                    <input type="text" class="form-control" name="customerTel"
                                           value="${consumption.customerTel}" placeholder="请输入客户电话">
                                </div>
                                <div class="form-group">
                                    <label>消费门店：</label>
                                    <select class="form-control" name="business.id">
                                        <option value="">请选择门店</option>
                                        <#list businesses as business>
                                            <option value="${business.id}"
                                                    <#if business.id==consumption.business.id>selected</#if>>${business.name}</option>
                                        </#list>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label>进店时间：</label>
                                    <input type="text" class="form-control input-date" name="checkinTime"
                                           value="${(consumption.checkinTime?string('yyyy-MM-dd HH:mm'))!}">
                                </div>
                                <div class="form-group">
                                    <label>离店时间：</label>
                                    <input type="text" class="form-control input-date" name="checkoutTime"
                                           value="${(consumption.checkoutTime?string('yyyy-MM-dd HH:mm'))!}">
                                </div>
                                <div class="form-group">
                                    <label>车牌记录：</label>
                                    <input type="text" class="form-control" placeholder="请输入车牌记录" name="carLicence"
                                           value="${consumption.carLicence}">
                                </div>
                                <div class="form-group">
                                    <label>车型记录：</label>
                                    <input type="text" class="form-control" placeholder="请输入车型记录" name="carType"
                                           value="${consumption.carType}">
                                </div>

                                <div class="form-group">
                                    <label>结算单备注：</label>
                                    <textarea class="form-control" rows="4" name="info"
                                              placeholder="请输入结算单备注">${consumption.info}</textarea>
                                </div>
                                <div class="form-group">
                                    <label>结算单状态：</label>
                                    <input type="text" class="form-control" readonly value="${consumption.statusName}"
                                           name="statusName">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>总消费金额(元)：</label>
                                    <input type="text" class="form-control totalAmount" placeholder="请输入总消费金额"
                                           name="totalAmount"
                                           value="${(consumption.totalAmount?c)!}">
                                </div>
                                <div class="form-group">
                                    <label>优惠金额(元)：</label>
                                    <input type="text" class="form-control" placeholder="请输入优惠金额" name="discountAmount"
                                           value="${(consumption.discountAmount?c)!}">
                                </div>
                                <div class="form-group">
                                    <label>实收金额(元)：</label>
                                    <input type="text" class="form-control" placeholder="请输入实收金额" name="payAmount"
                                           value="${((consumption.payAmount)?c)!}">
                                </div>
                                <div class="form-group">
                                    <label>结算单流水号：</label>
                                    <input type="text" class="form-control" readonly value="${consumption.cno}"
                                           name="cno">
                                </div>
                                <div class="form-group">
                                    <label>关联预约单流水号：</label>
                                    <input type="text" class="form-control" readonly
                                           value="${consumption.appointmentAno}" name="appointmentAno">
                                </div>
                                <div class="form-group">
                                    <label>创建时间：</label>
                                    <input type="text" class="form-control" readonly
                                           value="${(consumption.createTime?string('yyyy-MM-dd HH:mm:ss'))!}">
                                </div>
                                <div class="form-group">
                                    <label>结算时间：</label>
                                    <input type="text" readonly class="form-control" readonly
                                           value="${(consumption.payTime?string('yyyy-MM-dd HH:mm:ss'))!}"/>
                                </div>
                                <div class="form-group">
                                    <label>结算人：</label>
                                    <input type="text" class="form-control" readonly value="${consumption.payee.name}">
                                </div>
                                <div class="form-group">
                                    <label>审核时间：</label>
                                    <input type="text" class="form-control" readonly
                                           value="${(consumption.auditTime?string('yyyy-MM-dd HH:mm:ss'))!}">
                                </div>
                                <div class="form-group">
                                    <label>审核人：</label>
                                    <input type="text" class="form-control" readonly
                                           value="${consumption.auditor.name}">
                                </div>
                            </div>
                        </div>

                        <div class="pull-right">
                            <button type="submit" class="btn btn-primary btn-submit"><span
                                        class="glyphicon glyphicon-book"></span> 保存
                            </button>
                            <button type="button" class="btn btn-warning btn-consume"><span
                                        class="glyphicon glyphicon-yen"></span> 结算
                            </button>
                            <button type="button" class="btn btn-success btn-audit"><span
                                        class="glyphicon glyphicon-flag"></span> 审核
                            </button>
                            <button type="button" class="btn btn-danger btn-delete"><span
                                        class="glyphicon glyphicon-trash"></span> 删除
                            </button>
                            <a href="/consumption/list" type="button" class="btn btn-default"
                               data-dismiss="modal">后退</a>
                        </div>

                    </form>
                </div>
            </div>
        </section>
    </div>
    <#include "/common/footer.ftl">
</div>
<script>
    $(function () {
        $(".btn-input").click(function () {
            $("#editModal").modal("show");
        });
    });
</script>

<!--模态框-->
<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel">新增结算明细</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form id="consumptionItemForm" action="/consumptionItem/saveOrUpdate" method="post">
                <#-- 结算单流水号 -->
                <input type="hidden" name="cno" value="${consumption.cno}">
                <div class="modal-body">
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">业务大类：</label>
                        <div class="col-sm-7">
                            <select class="form-control" id="category" name="category.id">
                                <option value="">请选择业务大类</option>
                                <#list categories as category>
                                    <option value="${category.id}">${category.name}</option>
                                </#list>
                            </select>
                        </div>
                    </div>
                    <script>
                        $(function () {
                            $("#category").change(function () {
                                var id = $(this).val();
                                var $select = $("#categoryItem");

                                //清楚旧有的数据
                                $select.empty();
                                $select.append('<option value="">请选择业务小类</option>');

                                $.get("/systemDictionary/queryItemById", "id=" + id, function (data) {
                                    //对数据字典进行遍历
                                    data.forEach(function (ele) {
                                        $select.append('<option value="' + ele.id + '">' + ele.name + '</option>');
                                    });
                                });
                            });
                        });
                    </script>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">业务小类：</label>
                        <div class="col-sm-7">
                            <select class="form-control" id="categoryItem" name="categoryItem.id">
                                <option value="">请选择业务小类</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">结算类型：</label>
                        <div class="col-sm-7">
                            <select class="form-control" id="payType" name="payType.id">
                                <option value="">请选择结算类型</option>
                                <#list payTypes as payType>
                                    <option value="${payType.id}">${payType.name}</option>
                                </#list>
                            </select>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">消费金额(元)：</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="amount" name="amount"
                                   placeholder="请输入应收金额">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">优惠金额(元)：</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="discountAmount" name="discountAmount"
                                   placeholder="请输入优惠金额">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">实收金额(元)：</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="payAmount" name="payAmount"
                                   placeholder="请输入实收金额">
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary btn-submit">保存</button>
                </div>
            </form>
        </div>
    </div>
</div>
<script>
    $(function () {
        $(".btn-submit").click(function () {
            var id = $("#cid").val();
            $.post("/consumptionItem/ajaxSave", $("#consumptionItemForm").serialize(), function (data) {

                    // 局部刷新
                    // $(".no-padding").load(location.href+" .no-padding");


                    //刷新下面三个input框
                    // var totalAmount  = $("#totalAmount").data("totalamount");
                    // console.log(totalAmount);
                    // $("input[name=totalAmount]").val(totalAmount);
                console.log(data);
                $("#checkTbody").append('<tr>\n' +
                        '<td><input data-id="" type="checkbox"></td>\n' +
                        '<td>'+data.consumptionItem.category+'</td>\n' +
                        '<td>'+data.consumptionItem.categoryItem+'</td>\n' +
                        '<td>'+data.consumptionItem.payType+'</td>\n' +
                        '<td>'+data.consumptionItem.amount+'</td>\n' +
                        '<td>'+data.consumptionItem.discountAmount+'</td>\n' +
                        '<td>'+data.consumptionItem.payAmount+'</td>\n' +
                        '</tr>');

                    //关闭模态框
                    $("#editModal").modal("hide");
            });
        });

        $(document).on('click', '.btn-del', function () {
            // $(".btn-del").click(function () {
            //获取一个已选框的id
            var trList = $("#checkTbody").children("tr");
            //创建一个数组
            var arr = [];
            //将选择的checkbox封装进数组
            for (var i = 0; i <= trList.length; i++) {
                var tdArr = trList.eq(i).find("td");
                $checkArr = (tdArr.eq(0).find("input:checkbox:checked"));
                var id = $checkArr.data("id");
                if (id >= 0) {
                    arr.push(id);
                }
            }
            // $.ajaxSettings.traditional=true;//携带数组
            //发送ajax请求 将数组转成Json数组 传递到后台
            $.post("/consumptionItem/deleteIds", {'ids': JSON.stringify(arr)}, function (data) {
                if (data.success) {
                    //局部刷新页面
                    $(".no-padding").load(location.href + " .no-padding");


                    //刷新下面三个input框
                    var totalAmount = $("#totalAmount").data("totalamount");

                    console.log(totalAmount);
                    $("input[name=totalAmount]").val(totalAmount);

                } else {
                    alert(data.msg);
                }

            });
        });


    });

    //全选 全不选 (复选框)
    function checkChange(src) {
        // console.log(src);
        var flag = $(src).prop("checked");
        // console.log(flag);
        $("input[type=checkbox]").prop("checked", flag);
    }
</script>
</body>
</html>
