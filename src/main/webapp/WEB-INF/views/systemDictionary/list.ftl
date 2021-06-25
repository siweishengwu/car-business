<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>字典目录管理</title>
    <#include "/common/link.ftl">

</head>
<body class="hold-transition skin-black sidebar-mini">
<div class="wrapper">
    <#include "/common/navbar.ftl">

    <#assign currentMenu="systemDictionary"/>

    <#include "/common/menu.ftl">
    <div class="content-wrapper">
        <section class="content-header">
            <h1>数据字典管理</h1>
        </section>
        <section class="content">
            <div class="box">
                <button type="button" class="btn btn-primary btn-save"><span class="glyphicon glyphicon-plus"
                                                                             aria-hidden="true"></span> 添加根级数据字典
                </button>
                <#macro tree systemDictionaries>
                    <#list systemDictionaries as systemDictory>
                        <li class="treeview">
                            <a href="#">
                                <span>${systemDictory.name}</span>
                                <span style="float: right;margin-right: 30px;">
                                    <button data-json='${systemDictory.toJson()}' class="btn btn-primary btn-save btn-xs">添加</button>

                                    <button data-json='${systemDictory.toJson()}' class="btn btn-primary btn-update btn-xs">修改</button>

                                    <button data-url="/systemDictionary/delete?id=${systemDictory.id}" class="btn btn-danger btn-delete btn-xs">删除</button>
                                </span>
                                <#if systemDictory.items?size gt 0>
                                    <span   class="pull-right-container">
                                            <i class="fa fa-angle-left pull-right"></i>
                                        </span>
                                </#if>
<#--                                <button type="button" style="float: right;position: relative; margin-right: 20px;"-->
<#--                                        class="btn btn-danger btn-delete btn-xs  text-right"><span-->
<#--                                            class="glyphicon glyphicon-plus"-->
<#--                                            aria-hidden="true"></span> 删除-->
<#--                                </button>-->

                            </a>
                            <#if systemDictory.items?size gt 0>
                                <ul class="treeview-menu">
                                    <@tree systemDictory.items></@tree>
                                </ul>
                            </#if>

                        </li>
                    </#list>
                </#macro>


                <ul class="sidebar-menu" data-widget="tree">
                    <@tree systemDictionaries></@tree>
                </ul>
                <script>
                    $(function () {
                        $(".btn-save").click(function () {
                            //清楚模态框你的数据
                            $("#saveModal input").val("");
                            var data = $(this).data("json");
                            if (data){
                                //若是添加根数据字典
                                $("select[name=parentId] > option").val(data.id).html(data.name);
                            }else {
                                //若是添加非根数据字典
                                $("select[name=parentId] > option").val("").html("");
                            }
                            //把对应的
                            $("#saveModal").modal("show");

                            //阻止事件传递
                            return false;
                        });

                        $(".btn-update").click(function () {
                            var data = $(this).data("json");
                            if (data){
                                $("#updateModal input[name=id]").val(data.id);
                                $("#updateModal input[name=name]").val(data.name);
                                $("#updateModal input[name=sn]").val(data.sn);
                                $("#updateModal input[name=intro]").val(data.intro);
                            }
                            $("#updateModal").modal("show");
                            //阻止事件传递
                            return false;
                        });
                    });
                </script>
            </div>
        </section>
    </div>
    <#include "/common/footer.ftl" >
</div>


<!-- Modal -->
<div class="modal fade" id="saveModal" tabindex="-1" role="dialog" aria-labelledby="mySaveModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="mySaveModalLabel">新增</h4>
            </div>
            <form class="form-horizontal" action="/systemDictionary/saveOrUpdate" method="post" id="editForm">
                <div class="modal-body">
                    <div class="form-group" style="margin-top: 10px;">
                        <label for="name" class="col-sm-3 control-label">名称：</label>
                        <div class="col-sm-6">
                            <input type="text" name="name" class="form-control" placeholder="请输入名称">
                        </div>
                    </div>
                    <div class="form-group" style="margin-top: 10px;">
                        <label for="name" class="col-sm-3 control-label">编码：</label>
                        <div class="col-sm-6">
                            <input type="text" name="sn" class="form-control" placeholder="请输入编码">
                        </div>
                    </div>
                    <div class="form-group" style="margin-top: 10px;">
                        <label for="intro" class="col-sm-3 control-label">简介：</label>
                        <div class="col-sm-6">
                            <input type="text" name="intro" class="form-control" placeholder="请输入介绍">
                        </div>
                    </div>
                    <div id="parentDiv" class="form-group" style="margin-top: 10px;">
                        <label for="parentId" class="col-sm-3 control-label">父级：</label>
                        <div class="col-sm-6">
                            <select name="parentId" readonly="" class="form-control">
                                <option value=""></option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary btn-submit">保存</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                </div>
            </form>
        </div>
    </div>
</div>

<div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="myUpdateModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myUpdateModalLabel">编辑</h4>
            </div>
            <form class="form-horizontal" action="/systemDictionary/saveOrUpdate" method="post" id="editForm">
                <div class="modal-body">
                    <input type="hidden" name="id">
                    <div class="form-group" style="margin-top: 10px;">
                        <label for="name" class="col-sm-3 control-label">名称：</label>
                        <div class="col-sm-6">
                            <input type="text" name="name" class="form-control" placeholder="请输入名称">
                        </div>
                    </div>
                    <div class="form-group" style="margin-top: 10px;">
                        <label for="name" class="col-sm-3 control-label">编码：</label>
                        <div class="col-sm-6">
                            <input type="text" name="sn" class="form-control" placeholder="请输入编码">
                        </div>
                    </div>
                    <div class="form-group" style="margin-top: 10px;">
                        <label for="intro" class="col-sm-3 control-label">简介：</label>
                        <div class="col-sm-6">
                            <input type="text" name="intro" class="form-control" placeholder="请输入介绍">
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary btn-submit">保存</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
