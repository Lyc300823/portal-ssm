<%--
 Created by IntelliJ IDEA.
 User: zhangyunpeng
 Date: 2021/8/6
 Time: 8:51 上午
 To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <%@include file="../../basepath.jsp"%>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="layui/css/layui.css"/>
    <link rel="stylesheet" type="text/css" href="css/common.css?v=<%=Math.random()%>"/>
</head>
<body>
<span class="layui-breadcrumb">
 <a href="sex/list?sexName=${sexName}">性别维护</a>
 <a href="">性别列表</a>
 </span>
<table id="t" lay-filter="table"></table>
<script type="text/javascript" src="layui/layui.js"></script>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/qs.min.js"></script>
<shiro:hasPermission name="permission:delete">
    <button type="button" lay-event="delete"
            class="layui-btn layui-btn-danger layui-btn-xs">删除</button>
</shiro:hasPermission>


<!--
该模块代表头部模版的⾃定义模块，需要使⽤script标签type=“text/html”来实现
该段代码⽆需⼿写，粘贴到list.jsp中直接使⽤即可，但是需要通过标签结构来学习如何进⾏构建
-->
<script id="query-form" type="text/html" >
    <form class="layui-form" id="form">
        <div class="layui-inline">
            <label class="layui-form-label" style="width: auto">名称</label>
            <div class="layui-input-inline">
                <input class="layui-input" type="text" name = "sexName" value="{{d.where.sexName}}" placeholder="请输⼊性别名称"/>
            </div>
        </div>
        <div class="layui-inline">
            <!-- 查询按钮需要出发事件所以使⽤button lay-event是出发事件的⼀个key后续使⽤-->

            <button type="button"
                    lay-event="query"
                    class="layui-btn ">查询</button>
            <!-- 新增按钮会跳转⻚⾯所以这⾥使⽤a标签超链接按钮来做展示-->
            <shiro:hasPermission name="permission:insert">
                <a href="sex/add/page" class="layui-btn ">新增</a>
            </shiro:hasPermission>
        </div>
    </form>

</script>

<!-- 每⾏⼯具栏的模版 -->
<script type="text/html" id="tool">
    <a href="sex/edit?id={{d.id}}" class="layui-btn layui-btn-warm layui-btn-xs" >修改</a>
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
                {field:'sexName',title:'性别名称'},
                {title:'操作',templet: '#tool'}
            ]],
            //需要将data假数据注释掉
            // data:[
            // {id:'1',sexName:'男'},
            // {id:'2',sexName:'⼥'}
            // ],
            page:true,
            //然后在这⾥添加url属性，地址就填写controller中的完整路径，http部分已经在basePath中设置好了
            url:'sex/list/page',
            response: {
                //将返回的默认成功状态码定义为200
                statusCode: 200
            },
            request: {
                pageName: 'pno', //⻚码的参数名称，默认：page
                limitName: 'psize' //每⻚数据量的参数名，默认：limit
            },
            where:{
                sexName:''
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
        //获取弹出层对象
        var layer = layui.layer
        table.on('tool(table)',function(obj){
            //当点击了删除按钮时触发的事件
            if(obj.event == 'delete'){
                //获取本⾏数据的id
                var id = obj.data.id
                console.log(id)
                //弹出询问框，第⼀个参数为弹出内容
                //第⼆个参数为询问框配置
                //第三个参数代表点击确认时的函数
                layer.confirm('正在删除当前数据是否继续?',{
                    icon:3,
                    title:'提示'
                },function(index){
                    //提交到删除接⼝的路径并且传⼊id
                    location.href = 'sex/delete?id='+id
                    layer.close(index)
                })
            }
        })

    })



</script>

</body>
</html>
