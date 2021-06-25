<!DOCTYPE html>
<html>
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
            <h1>角色编辑</h1>
        </section>
        <section class="content">
            <div class="box">
                <form class="form-horizontal" action="/role/saveOrUpdate" method="post" id="editForm">

                    <input type="hidden" name="id" value="${role.id}">
                    <div class="form-group" style="margin-top: 10px;">
                        <label class="col-sm-2 control-label">角色名称：</label>
                        <div class="col-sm-6">
                            <input value="${role.name}" type="text" class="form-control" name="name" placeholder="请输入角色名称">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">角色编号：</label>
                        <div class="col-sm-6">
                            <input value="${role.sn}" type="text" class="form-control" name="sn" placeholder="请输入角色编号">
                        </div>
                    </div>
                    <div class="form-group " id="role">
                        <label for="role" class="col-sm-2 control-label">分配权限：</label><br/>
                        <div class="row" style="margin-top: 10px">
                            <div class="col-sm-2 col-sm-offset-2">
                                <select multiple class="form-control allPermissions" style="height: 350px;" size="15">
                                    <#list permissions as permission>
                                        <option value="${permission.id}">${permission.name}</option>
                                    </#list>
                                </select>
                            </div>

                            <div class="col-sm-1" style="margin-top: 60px;" align="center">
                                <div>
                                    <script>
                                        function moveAll(srcClass, targetClass) {
                                            $('.'+targetClass).append($("." + srcClass + ">option"));
                                        }

                                        function moveSelected(srcClass, targetClass) {
                                            $('.'+targetClass).append($("." + srcClass + ">option:selected"));
                                        }

                                    </script>
                                    <a type="button" class="btn btn-primary" style="margin-top: 10px" title="右移动"
                                       onclick="moveSelected('allPermissions', 'selfPermissions')">
                                        <span class="glyphicon glyphicon-menu-right"></span>
                                    </a>
                                </div>
                                <div>
                                    <a type="button" class="btn btn-primary " style="margin-top: 10px" title="左移动"
                                       onclick="moveSelected('selfPermissions', 'allPermissions')">
                                        <span class="glyphicon glyphicon-menu-left"></span>
                                    </a>
                                </div>
                                <div>
                                    <a type="button" class="btn btn-primary " style="margin-top: 10px" title="全右移动"
                                       onclick="moveAll('allPermissions', 'selfPermissions')">
                                        <span class="glyphicon glyphicon-forward"></span>
                                    </a>
                                </div>
                                <div>
                                    <a type="button" class="btn btn-primary " style="margin-top: 10px" title="全左移动"
                                       onclick="moveAll('selfPermissions', 'allPermissions')">
                                        <span class="glyphicon glyphicon-backward"></span>
                                    </a>
                                </div>
                            </div>
                            <div class="col-sm-2">
                                <select multiple class="form-control selfPermissions" style="height: 350px;" size="15"
                                        name="permissionIds">
                                        <#list myPermissions as permission>
                                            <option value="${permission.id}">${permission.name}</option>
                                        </#list>
                                </select>
                            </div>


                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-1 col-sm-6">
                            <button type="button" class="btn btn-primary btn-submit">保存</button>
                            <a href="javascript:window.history.back()" class="btn btn-danger">取消</a>
                        </div>
                    </div>
<#--                    页面渲染完成之后,做角色去重-->
                    <script>
                        $(function () {
                            //做角色去重
                            //先获取右边下拉框中的option的value值,即权限id存到一个数组中
                            var arr = [];
                            $(".selfPermissions > option").each(function (i,domEle) {
                                arr.push($(domEle).val());
                            });
                            //遍历左边的option,获取其value属性值跟上面数组中的值进行比对,则删除这个option
                            $(".allPermissions > option").each(function (i,domEle) {
                                var $option = $(domEle);
                                var val = $option.val();
                                if ($.inArray(val, arr) >= 0) {
                                    $option.remove();
                                }
                            });
                        });
                    </script>
                    <script>
                        $(".btn-submit").click(function () {
                            //在提交表单之前选中右边下拉框中所有的option
                            $(".selfPermissions > option").prop("selected",true);
                            $("#editForm").submit();
                        })
                    </script>
                </form>
            </div>
        </section>
    </div>
    <#include "/common/footer.ftl">
</div>
</body>
</html>
