<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>门店管理</title>
    <#include "/common/link.ftl">
</head>
<body class="hold-transition skin-black sidebar-mini">
<div class="wrapper">
    <#include "/common/navbar.ftl">
    <!--菜单回显-->
    <#assign currentMenu="business"/>
    <#include "/common/menu.ftl">
    <div class="content-wrapper">
            <section class="content-header">
            <h1>登录IP查询</h1>
        </section>
        <section class="content">
            <div class="box">
                <!--高级查询--->
                <div style="margin: 20px 0px 0px 10px">
                    <form class="form-inline" id="searchForm" action="/ipLogin/list" method="post">
                        <input type="hidden" name="currentPage" id="currentPage" value="1">
                                <div class="form-group">
                                    <label for="name">用户名：</label>
                                    <input type="text" class="form-control" name="username" value="${qo.username}"
                                           placeholder="请输入用户名">
                                </div>
                                <div class="form-group">
                                    <label for="scope">ip地址：</label>
                                    <input type="text" class="form-control" name="ip" value="${qo.ip}"
                                           placeholder="请输入ip地址">
                                </div>
                        <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span> 查询</button>
                    </form>
                </div>
                <div class="box-body table-responsive">
                <table class="table table-hover table-bordered table-striped">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>用户名</th>
                        <th>ip地址</th>
                        <th>登录时间</th>
                    </tr>
                    </thead>
                    <tbody>
                        <#list pageInfo.list as business>
                            <tr>
                            <td>${business.id}</td>
                            <td>${business.username}</td>
                            <td>${business.ip}</td>
                            <td>${(business.time?string("yyyy-MM-dd HH:mm:ss"))!}</td>
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
