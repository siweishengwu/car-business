<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>卢本伟nb互联网商户平台</title>
    <#include "/common/link.ftl"/>
    <link rel="stylesheet" href="/static/css/index_css.css">
</head>
<body>
<div class="nav">
    <div class="nav-div">
        <div class="row justify-content-between">
            <div class="col-md-4 border-right" style="padding: 0px;">
                <h4>卢本伟</h4>
                <h5 style="color:#434343;font-size: 18px">连锁运营顾问 / 高级服务专家</h5>
            </div>
            <div class="col-md-3 category" style="padding-left: 30px">
                <#list systemDictionaries as systemDictionary>
                    <button class="btn btn-primary-cus ">${systemDictionary.name}</button>
                </#list>
            </div>
            <div class="col-md-1" style="text-align: center;padding: 20px;">
                <button class="btn btn-primary-full" type="button" id="btn-appointment">
                    马上预约
                </button>

            </div>
            <div class="col-md-4 tel" style="padding: 15px;">
                <h2 style="vertical-align:middle;"><i class="fa fa-phone"></i></h2>
                <h5 style="color:#434343; ">全国免费热线:</h5>
                <h2>020-lubenwei</h2>
            </div>
        </div>
    </div>
    <script>
        $(function () {
            $("#btn-appointment").click(function () {
                $("#appointmentModal").modal("show");
            })
        })
    </script>
</div>
<div class="body">
    <div class="banner">
        <img src="/static/img/system/banner.png"/>
    </div>
</div>
<div class="footer">
    <div class="row">
        <div class="col-sm-4">
            <div class="position-relative p-3 bg-white " style="height: 200px">
                <blockquote><strong>店铺信息</strong></blockquote>
                <small style="line-height:30px">BUSINESS INFORMATION</small>
                <p class="text-muted">
                    ${business.intro}
                </p>
            </div>
        </div>
        <div class="col-sm-4 ">
            <div class="position-relative p-3 bg-white " style="height: 200px">
                <blockquote><strong>服务与支持</strong></blockquote>
                <small style="line-height:30px">SERVICE AND SUPPORT</small>
                <p class="text-muted">${business.scope}</div>
        </div>
        <div class="col-sm-4 ">
            <div class="position-relative p-3 bg-white" style="height: 200px">
                <blockquote><strong>联系我们</strong></blockquote>
                <small style="line-height:30px">CONCAT US</small>
                <h5 class="text-muted"><strong style="font-size: 17px">联系电话：</strong>${business.tel}</h5>
                <h5 class="text-muted"><strong style="font-size: 17px">联系地址：</strong>${business.address}</h5>
            </div>
        </div>
    </div>

</div>

<!-- Modal -->


<div class="modal fade" id="appointmentModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog " style="max-width: 380px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">请输入预约信息</h4>
            </div>
            <form id="appointmentForm" method="post" action="/index/appointment">
                <div class="modal-body">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-user"></i> </span>
                        <input class="form-control" placeholder="请输入您的姓名" name="contactName">
                    </div>
                    <br/>
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-phone"></i></span>
                        <input class="form-control" placeholder="请输入您的电话" name="contactTel">
                    </div>
                    <br/>
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-home"></i></span>
                        <select class="form-control" placeholder="请选择预约门店" name="business.id">
                            <option value="">请选择预约门店</option>
                            <#list businesses as business>
                                <option value="${business.id}">${business.name}</option>
                            </#list>
                        </select>
                    </div>
                    <br/>
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-tag"></i></span>
                        <select class="form-control" placeholder="请选择预约业务" name="systemDictionary.id">
                            <option value="">请选择预约业务</option>
                            <#list systemDictionaries as systemDictionary>
                                <option value="${systemDictionary.id}">${systemDictionary.name}</option>
                            </#list>
                        </select>
                    </div>
                    <br/>
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                        <input class="form-control input-date" placeholder="请选择预约时间" name="appointmentTime">
                    </div>
                    <br/>
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-bookmark"></i></span>
                        <textarea class="form-control" placeholder="备注说明" name="info"></textarea>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" id="btn-save" class="btn btn-primary-full">确定预约</button>
                </div>
            </form>
        </div>
    </div>
</div>
<script>
    $(function () {
        $("#btn-save").click(function () {
            $.post("/index/appointment",$("#appointmentForm").serialize(),function (data) {
                Swal.fire({
                    title: '预约结果',
                    text: data.msg
                });
                if (data.success){
                    $("#appointmentModal input").val("");
                    $("#appointmentModal select").val("");
                    $("#appointmentModal textarea").val("");
                    $("#appointmentModal").modal('hide');
                }
                });
            });
        });
</script>

</body>
</html>
