<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>员工管理</title>
    <#include "/common/link.ftl">

</head>
<body class="hold-transition skin-black sidebar-mini">
<div class="wrapper">
    <#include "/common/navbar.ftl">
    <!--菜单回显-->
    <#assign currentMenu="employee"/>
    <#include "/common/menu.ftl">
    <div class="content-wrapper">
        <section class="content-header">
            <h1>员工编辑</h1>
        </section>
        <section class="content">
            <div class="box">
                <form class="form-horizontal" action="/employee/saveOrUpdate" method="post" id="editForm" >
                    <input type="hidden" name="id" value="${employee.id}" >
                    <div class="form-group" style="margin-top: 10px;">
                        <label class="col-sm-2 control-label" >用户名：</label>
                        <div class="col-sm-6">
                            <input  <#if employee??>disabled</#if> value="${employee.username}" type="text" class="form-control" name="username"  placeholder="请输入用户名">
                        </div>
                    </div>
                    <div class="form-group" style="margin-top: 10px;">
                        <label class="col-sm-2 control-label">真实姓名：</label>
                        <div class="col-sm-6">
                            <input value="${employee.name}" type="text" class="form-control" name="name"  placeholder="请输入真实姓名">
                        </div>
                    </div>
                    <#if !employee??>
                        <div class="form-group">
                            <label for="password" class="col-sm-2 control-label">密码：</label>
                            <div class="col-sm-6">
                                <input value="${employee.password}" type="password" class="form-control" id="password" name="password" placeholder="请输入密码">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="repassword" class="col-sm-2 control-label">验证密码：</label>
                            <div class="col-sm-6">
                                <input value="${employee.password}" type="password" class="form-control" id="repassword" name="repassword" placeholder="再输入一遍密码">
                            </div>
                        </div>
                    </#if>
                    <div class="form-group">
                        <label for="email" class="col-sm-2 control-label">电子邮箱：</label>
                        <div class="col-sm-6">
                            <input type="text" value="${employee.email}" class="form-control"  name="email" placeholder="请输入邮箱">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="age" class="col-sm-2 control-label">年龄：</label>
                        <div class="col-sm-6">
                            <input type="text" value="${employee.age}" class="form-control" name="age" placeholder="请输入年龄">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="dept" class="col-sm-2 control-label">部门：</label>
                        <div class="col-sm-6">
                            <select class="form-control" id="dept" name="dept.id">
                                <option value="">全部</option>
                                <#list departments as department>
                                    <option ${(department.id == employee.dept.id)?string("selected","")} value="${department.id}">${department.name}</option>
                                </#list>
                            </select>
                        </div>
                    </div>
                    <div class="form-group" id="adminDiv">
                        <label for="admin" class="col-sm-2 control-label">超级管理员：</label>
                        <div class="col-sm-6"style="margin-left: 15px;">
                            <input ${(employee?? && employee.admin)?string("checked","")}  type="checkbox" id="admin" name="admin" class="checkbox">
                        </div>
                    </div>
                    <#--点了是超管,删除页面角色分配div,若点了不是超管,要还原-->
                    <script>
                        $(function () {

                            var $roleDiv;


                            $("#admin").click(function () {//获取选中状态
                                var checked = $(this).prop("checked");
                                // console.log(checked);
                                //根据选中状态来判断删除还是还原
                                if (checked) {
                                    //删除并保存
                                    $roleDiv = $("#roleDiv").remove();
                                }else {
                                    //还原
                                    $("#adminDiv").after($roleDiv);
                                }
                            });

                            //页面渲染完之后检查管理员复选框是否选中,若是选中,把下面角色分配div删除并保存
                            var checked = $("#admin").prop("checked");
                            if (checked){
                                $roleDiv = $("#roleDiv").remove();
                            }else {
                                $("#adminDiv").after($roleDiv);
                            }
                        });
                    </script>
                    <div class="form-group" id="roleDiv">
                        <label for="role" class="col-sm-2 control-label">分配角色：</label><br/>
                        <div class="row" style="margin-top: 10px">
                            <div class="col-sm-2 col-sm-offset-2">
                                <select multiple class="form-control allRoles" style="height: 350px;" size="15">
                                    <#list roles as role>
                                        <option value="${role.id}">${role.name}</option>
                                    </#list>
                                </select>
                            </div>
                            <script>
                                $(function () {
                                    //获取右边下拉框中的所有option vlaue 值 存到数组中
                                    var arr= [];
                                   $(".selfRoles > option").each(function (i,domEle) {
                                        arr.push($(domEle).val());
                                   });
                                   //遍历左边的option ,若左边的option中value属性值在数组中,移出
                                    $('.allRoles>option').each(function (i,domEle) {
                                        var $option = $(domEle);
                                        var val = $option.val();
                                        if ($.inArray(val, arr) >= 0) {
                                            $option.remove();
                                        }
                                    });
                                });
                            </script>
                            <div class="col-sm-1" style="margin-top: 60px;" align="center">
                                <div>
                                    <script>
                                            function moveAll(srcClass,targetClass) {
                                                $("."+targetClass).append($("."+srcClass+">option"));
                                            }
                                            function moveSelected(srcClass,targetClass) {
                                                $("."+targetClass).append($("."+srcClass+">option:selected"));
                                            }
                                    </script>
                                    <a type="button" class="btn btn-primary  " style="margin-top: 10px" title="右移动"
                                       onclick="moveSelected('allRoles', 'selfRoles')">
                                        <span class="glyphicon glyphicon-menu-right"></span>
                                    </a>
                                </div>
                                <div>
                                    <a type="button" class="btn btn-primary " style="margin-top: 10px" title="左移动"
                                       onclick="moveSelected('selfRoles', 'allRoles')">
                                        <span class="glyphicon glyphicon-menu-left"></span>
                                    </a>
                                </div>
                                <div>
                                    <a type="button" class="btn btn-primary " style="margin-top: 10px" title="全右移动"
                                       onclick="moveAll('allRoles', 'selfRoles')">
                                        <span class="glyphicon glyphicon-forward"></span>
                                    </a>
                                </div>
                                <div>
                                    <a type="button" class="btn btn-primary " style="margin-top: 10px" title="全左移动"
                                       onclick="moveAll('selfRoles', 'allRoles')">
                                        <span class="glyphicon glyphicon-backward"></span>
                                    </a>
                                </div>
                            </div>

                            <div class="col-sm-2">
                                <select multiple class="form-control selfRoles" style="height: 350px;" size="15" name="roleIds">
                                    <#list myRoles as role>
                                        <option value="${role.id}">${role.name}</option>
                                    </#list>
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-offset-1 col-sm-6">
                            <button type="submit" class="btn btn-primary btn-submit">保存</button>
                            <a href="javascript:window.history.back()" class="btn btn-danger">取消</a>
                        </div>
                    </div>
                    <script>
                        // $(function () {
                        //     $(".btn-submit").click(function () {
                        //         $(".selfRoles > option").prop("selected",true);
                        //         $("#editForm").submit();
                        //     })
                        // })
                    </script>
                </form>

            </div>
        </section>
    </div>
    <#include "/common/footer.ftl">
</div>
<script>
    $("#editForm").bootstrapValidator({
        feedbackIcons: { // 图标
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields:{ // 配置要验证的字段
            username:{
                validators:{ // 验证的规则
                    notEmpty:{ // 不能为空
                        message:"用户名必填" // 错误时的提示信息
                    },
                    stringLength: { // 字符串的长度范围
                        min: 1,
                        max: 5
                    },
                    remote: { // 远程验证
                        type: 'get', // 请求方式
                        url: '/employee/checkUserName', // 请求地址
                        message: '用户名已经存在', // 验证不通过时的提示信息
                        delay: 1000, // 输入内容 1 秒后发请求
                    }
                }
            },
            name:{
                validators:{ // 验证的规则
                    notEmpty:{ // 不能为空
                        message:"姓名必填" // 错误时的提示信息
                    },
                    stringLength: { // 字符串的长度范围
                        min: 1,
                        max: 5
                    }
                }
            },
            password:{
                validators:{
                    notEmpty:{ // 不能为空
                        message:"密码必填" // 错误时的提示信息
                    },
                }
            },
            repassword:{
                validators:{
                    notEmpty:{ // 不能为空
                        message:"密码必填" // 错误时的提示信息
                    },
                    identical: {// 两个字段的值必须相同
                        field: 'password',
                        message: '两次输入的密码必须相同'
                    },
                }
            },
            email: {
                validators: {
                    emailAddress: {} // 邮箱格式
                }
            },
            age:{
                validators: {
                    between: { // 数字的范围
                        min: 18,
                        max: 60
                    }
                }
            }
        }
    }).on('success.form.bv', function(e) {
        //组织表单提交
        e.preventDefault();
        console.log(1)
        //右边全选
        $('.selfRoles > option').prop('selected', 'true');
        // TODO 这里可以改成用异步的方式提交表单

        //获取表单
        var $form = $(e.target);

        //使用ajax
        $.post($form.attr("action"),$form.serialize(),function (data) {
            if (data.success){
                location.href="/employee/list";
            }else {}
                alert(data.msg);
        })
    });
</script>
</body>
</html>
