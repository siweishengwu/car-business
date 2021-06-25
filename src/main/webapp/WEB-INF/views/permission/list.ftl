<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>权限管理</title>
    <#include "/common/link.ftl">
</head>
<body class="hold-transition skin-black sidebar-mini">
<div class="wrapper">
    <#include "/common/navbar.ftl">
    <!--菜单回显-->
    <#assign currentMenu="permission"/>
    <#include "/common/menu.ftl">
    <div class="content-wrapper">
        <section class="content-header">
            <h1>权限管理</h1>
        </section>
        <section class="content">
            <div class="box" >
                <!--高级查询--->
                <form class="form-inline" id="searchForm" action="/permission/list" method="post">
                    <input type="hidden" name="currentPage" id="currentPage" value="1">
                    <a href="javascript:;" class="btn btn-success btn-reload" style="margin: 10px;">
                        <span class="glyphicon glyphicon-repeat"></span>  重新加载
                    </a>

                </form>
                <script>
                    $(function () {
                        $(".btn-reload").click(function () {
                            $.post("/permission/reload",function (data) {
                                console.log(data);
                                if (data.success){
                                    alert("权限加载成功");
                                    location.href="/permission/list";
                                }else {
                                    alert(data.msg);
                                }
                            });
                        });
                    });
                </script>
                <div class="box-body table-responsive ">
                <table class="table table-hover table-bordered table-striped" >
                    <thead>
                    <tr>
                        <th>编号</th>
                        <th>权限名称</th>
                        <th>权限表达式</th>
                   
                    </tr>
                    </thead>
                    <tbody>
                    <#list pageInfo.list as permission>
                    <tr>
                        <td>${permission.id}</td>
                        <td>${permission.name}</td>
                        <td>${permission.expression}</td>
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
</body>
</html>
