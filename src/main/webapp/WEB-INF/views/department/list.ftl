<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>部门管理</title>
    <#-- 使用相对当前模板文件的路径 再去找另一个模板文件 -->
    <#include "/common/link.ftl">

</head>
<body class="hold-transition skin-black sidebar-mini">
<div class="wrapper">
    <#include "/common/navbar.ftl">
    <!--定义一个变量  用于菜单回显-->
    <#assign currentMenu="department"/>

    <#include "/common/menu.ftl">
    <div class="content-wrapper">
        <section class="content-header">
            <h1>部门管理</h1>
        </section>
        <section class="content">
            <div class="box">
                <!--高级查询--->
                <form class="form-inline" id="searchForm" action="/department/list" method="post">
                    <input type="hidden" name="currentPage" id="currentPage" value="1">
                    <a href="#" class="btn btn-success btn-input" style="margin: 10px">
                        <span class="glyphicon glyphicon-plus"></span> 添加
                    </a>
                </form>
                <!--编写内容-->
                <div class="box-body table-responsive ">
                    <table class="table table-hover table-bordered table-striped">
                        <thead>
                        <tr>
                            <th>编号</th>
                            <th>部门名称</th>
                            <th>部门编号</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>

                        <#list pageInfo.list as department>
                            <tr>
                                <td>${department.id}</td>
                                <td>${department.name}</td>
                                <td>${department.sn}</td>
                                <td>
                                    <a href="#" class="btn btn-info btn-xs btn-input" data-option='${department.toJson()}'>
                                        <span class="glyphicon glyphicon-pencil"></span> 编辑
                                    </a>
                                    <a class="btn btn-danger btn-xs btn-delete" data-url="/department/delete?id=${department.id}">
                                        <span class="glyphicon glyphicon-trash" ></span> 删除
                                    </a>
                                </td>
                            </tr>
                        </#list>


                        </tbody>
                    </table>
                    <#--                    <!--分页&ndash;&gt;-->
                    <#include "/common/page.ftl" >
                </div>
            </div>
        </section>
    </div>
    <#include "/common/footer.ftl" >
</div>


<div class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">新增/编辑</h4>
            </div>
            <form method="post" action="/department/saveOrUpdate">
                <div class="modal-body">
                    <input type="hidden" name="id">
                    <div class="form-group">
                        <label for="exampleInputEmail1">名称</label>
                        <input type="text" class="form-control" id="name" name="name" placeholder="名称">
                    </div>
                    <div class="form-group">
                        <label for="exampleInputPassword1">简称</label>
                        <input type="text" class="form-control" id="sn" name="sn" placeholder="简称">
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="submit" class="btn btn-primary">保存</button>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<script>
    $(function () {
        $(".btn-input").click(function () {
            var data = $(this).data("option");
            console.log(data);
            $("input[name]").val("");
            if (data){
                $("input[name=id]").val(data.id);
                $("input[name=name]").val(data.name);
                $("input[name=sn]").val(data.sn);
            }
            $(".modal").modal("show");
        })
    });
</script>
</body>
</html>
