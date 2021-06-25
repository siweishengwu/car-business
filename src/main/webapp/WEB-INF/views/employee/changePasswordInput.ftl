<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>角色管理</title>
    <#include "/common/link.ftl">
</head>
<body class="hold-transition skin-black sidebar-mini">
<div class="wrapper">
    <#include "/common/navbar.ftl">
    <!--菜单回显-->
    <#assign currentMenu="role"/>
    <#include "/common/menu.ftl">
    <div class="content-wrapper">
        <section class="content-header">
            <h1>修改密码</h1>
        </section>
        <section class="content">
            <div class="box">
                <div style="margin: 10px;">
                    <form class="form-inline" id="searchForm" action="/employee/changePassword" method="post">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>旧密码：</label>
                                    <input type="text" name="oldPassword" class="form-control" placeholder="请输入旧密码">
                                </div>
                                </br></br>
                                <div class="form-group">
                                    <label>新密码：</label>
                                    <input type="text" name="newPassword" class="form-control" placeholder="请输入新密码">
                                </div>
                                </br></br>
                                <button type="button" class="btn btn-primary">提交</button>
                            </div>
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
        $(".btn-primary").click(function () {
            var params = $("#searchForm").serialize();
            console.log(params)
            $.post("/employee/changePassword",params, function (data) {
                if (data.success) {
                    alert(data.msg);
                    location.href = "/employee/list";
                } else {
                    alert(data.msg);
                }
            });
        });
    })
</script>
</body>
</html>
