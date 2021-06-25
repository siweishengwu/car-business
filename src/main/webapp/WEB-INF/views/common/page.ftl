<div style="text-align: center;">
    <ul id="pagination" class="pagination"></ul>
</div>
<script>
    //分页
    $(function(){
        <#--$("#pagination").twbsPagination({-->
        <#--    totalPages: ${result.pages} || 1,-->
        <#--        startPage: ${result.pageNum} || 1,-->
        <#--        visiblePages:5,-->
        <#--        first:"首页",-->
        <#--        prev:"上页",-->
        <#--        next:"下页",-->
        <#--        last:"尾页",-->
        <#--        initiateStartPageClick:false,-->
        <#--        onPageClick:function(event,page){-->
		<#--			$("#currentPage").val(page);-->
		<#--			$("#searchForm").submit();-->
		<#--		}-->
		<#--});-->
        $('#pagination').twbsPagination({
            totalPages: ${pageInfo.pages} || 1,
            startPage:${pageInfo.pageNum} || 1,
            visiblePages: 5,
            first: "首页",
            prev: "上页",
            next: "下页",
            last: "尾页",
            onPageClick: function (event, page) {
                $('#currentPage').val(page);//当前页的数据传值到value
                $("#searchForm").submit();//发送form表单
            }
        });
    });
</script>