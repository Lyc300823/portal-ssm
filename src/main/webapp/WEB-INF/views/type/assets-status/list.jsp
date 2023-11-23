<%--
 Created by IntelliJ IDEA.
 User: zhangyunpeng
 Date: 2021/8/6
 Time: 8:51 上午
 To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <%@include file="../../basepath.jsp"%>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="layui/css/layui.css"/>
    <link rel="stylesheet" type="text/css" href="css/common.css?v=<%=Math.random()%>"/>
</head>
<body>
<span class="layui-breadcrumb">
 <a href="sex/list?sexName=${sexName}">资产类型维护</a>
 <a href="">资产列表</a>
 </span>
<table id="t" lay-filter="table"></table>
<script type="text/javascript" src="layui/layui.js"></script>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/qs.min.js"></script>



<!--
该模块代表头部模版的⾃定义模块，需要使⽤script标签type=“text/html”来实现
该段代码⽆需⼿写，粘贴到list.jsp中直接使⽤即可，但是需要通过标签结构来学习如何进⾏构建
-->
<script id="query-form" type="text/html" >
    <form class="layui-form" id="form">
        <div class="layui-inline">
            <label class="layui-form-label" style="width: auto">名称</label>
            <div class="layui-input-inline">
                <input class="layui-input" type="text" name="assetStatusNa" value="{{d.where.assetStatusNa}}"placeholder="请输⼊资产名称"/>
            </div>
        </div>
        <div class="layui-inline">
            <!-- 查询按钮需要出发事件所以使⽤button lay-event是出发事件的⼀个key后续使⽤-->
            <button type="button"
                    lay-event="query"
                    class="layui-btn ">查询</button>
            <!-- 新增按钮会跳转⻚⾯所以这⾥使⽤a标签超链接按钮来做展示-->
            <a href="#" class="layui-btn ">新增</a>
        </div>
    </form>

</script>

<!-- 每⾏⼯具栏的模版 -->
<script type="text/html" id="tool">
    <a href="#" class="layui-btn layui-btn-warm layui-btn-xs" >修改</a>
    <button type="button" lay-event="delete"
            class="layui-btn layui-btn-danger layui-btn-xs">删除</button>
</script>
<script type="text/javascript">
    layui.use('table',function(){
        var table = layui.table
        table.init('table',{
            elem: '#t',
            height: 'full-80',
            autoSort: false ,
            toolbar: '#query-form',
            cols:[[
                {field:'id',title:'主键',sort: true},
                {field:'assetStatusNa',title:'资产状态'},
                {title:'操作',templet: '#tool'}
            ]],

            page:true,
            //然后在这⾥添加url属性，地址就填写controller中的完整路径，http部分已经在basePath中设置好了
            url:'assets-status/list/page',
            response: {
                //将返回的默认成功状态码定义为200
                statusCode: 200
            },
            request: {
                pageName: 'pno', //⻚码的参数名称，默认：page
                limitName: 'psize' //每⻚数据量的参数名，默认：limit
            },
            where: {
                assetStatusNa:''
            }
        })
        table.on('toolbar(table)',function(obj){
            if(obj.event == 'query'){
                var queryForm = $('#form').serialize()
                var queryFormObj = Qs.parse(queryForm)
                console.log(queryFormObj)
                //调⽤表格重载函数会出发表格重新查询，这⾥需要
                //在where中传⼊我们的条件查询对象，这个属性就会传到后台
                table.reload('t',{
                    where:queryFormObj
                })
            }
        })
    })


</script>

</body>
</html>
