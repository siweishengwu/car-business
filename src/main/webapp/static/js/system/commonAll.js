$(function () {
    $(".btn-delete").click(function () {
        var url = $(this).data("url");
        Swal.fire({
            title: '删除?',
            text: "操作不可逆!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: '确定',
            cancelButtonText: '取消'
        }).then((result) => {
            if(result.value) {
                location.href=url;
            }
        });
    });

        // $(".input-date").datetimepicker({
        //     language: 'zh-CN',
        //     autoclose: true,
        //     todayHighlight: true, // 今天时间高亮
        //     format: 'yyyy-mm-dd',
        //     endDate: new Date(),   // 今天之后的日期都不可以选择
        //     todayBtn:true
        // });
    $('.input-date').datetimepicker({
        language: 'zh-CN',
        autoclose: true,
        minView : 0,
        todayHighlight: true, // 今天时间高亮
        format: 'yyyy-mm-dd hh:ii:ss',
        todayBtn: true
    });

});