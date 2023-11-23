<%--
 Created by IntelliJ IDEA.
 User: zhangyunpeng
 Date: 2021/8/6
 Time: 8:51 上午
 To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"
%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <%@include file="../../basepath.jsp"%>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="layui/css/layui.css"/>
    <link rel="stylesheet" type="text/css" href="css/common.css?v=
<%=Math.random()%>"/>
</head>
<body>
<span class="layui-breadcrumb">
 <a href="dept/list?pid=${pid}">部⻔维护</a>
 <a >部⻔列表</a>
 </span>
<table id="t" lay-filter="table"></table>
<script type="text/javascript" src="layui/layui.js"></script>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/qs.min.js"></script>
<script id="query-form" type="text/html">
    <form class="layui-form" id="form">
        <!-- 这⾥需要将部⻔的pid参数保存到表单中保证每次提交时查询的是当前层级的部⻔信息-->
        <input type="hidden" name="pid" value="${pid}"/>
        <div class="layui-inline">
            <label class="layui-form-label" style="width: auto">名称</label>
            <div class="layui-input-inline">
                <input class="layui-input"
                       name="name" type="text" placeholder="请输⼊部⻔名称"
                       value="{{d.where.name}}"
                />
            </div>
        </div>
        <div class="layui-inline">
            <shiro:hasPermission name="permission:query">
                <button type="button"
                        lay-event="query"
                        class="layui-btn ">查询</button>
            </shiro:hasPermission>
            <shiro:hasPermission name="permission:insert">
                <a href="dept/add/page?pid=${pid}" class="layui-btn ">新增</a>
            </shiro:hasPermission>
        </div>
    </form>
</script>
<!-- 定义图标模版将数据部分变成layui的图标组件 -->
<script type="text/html" id="icon">
    <i class="layui-icon {{d.icon}}"></i>
</script>

<!--
在模版中追加如下按钮，除了嵌套权限标签外，这⾥需要使⽤layui的模版语法
他与jsp的模版语法类似 {{#相当于创建⼀个js代码空间最后会将结果⽣成在html模版中}}
这⾥依然需要使⽤d对象来将我们访问的列表pid设置成我们想要查询的部⻔id这样再次访问的⻚⾯就是⼦部⻔⻚
⾯
当⼦部⻔的isLeaf属性为1的时候就不会显示该按钮了。
-->
<script type="text/html" id="tool">
    {{# if(d.isLeaf == 0){ }}
    <shiro:hasPermission name="permission:query">
        <a href="dept/list?pid={{d.id}}"
           class="layui-btn layui-btn-primary layui-btn-xs">
            ⼦部⻔列表
        </a>
    </shiro:hasPermission>
    {{# } }}
    <shiro:hasPermission name="permission:update">
        <a href="dept/edit/page?pid=${pid}&id={{d.id}}" class="layui-btn layui-btn-warm
layui-btn-xs" >修改</a>
    </shiro:hasPermission>
    <shiro:hasPermission name="permission:delete">
        <button type="button" lay-event="delete"
                class="layui-btn layui-btn-danger layui-btn-xs">删除</button>
    </shiro:hasPermission>
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
                {field:'name',title:'部⻔名称'},
                {field:'icon',title:'部⻔图标',templet:'#icon'},
                {title:'操作',templet: '#tool'}
            ]],
            // data:[
            // {id:'1',sexName:'男'},
            // {id:'2',sexName:'⼥'}
            // ],
            page:true,
            url:'dept/list/page',
            response: {
                statusCode: 200
            },
            request: {
                pageName: 'pno', //⻚码的参数名称，默认：page
                limitName: 'psize' //每⻚数据量的参数名，默认：limit
            },
            where:{
                name:'',
                //初始化数据需要传⼊pid
                pid:${pid}
            }
        })
        table.on('toolbar(table)',function(obj){
            if(obj.event == 'query'){
                var queryForm = $('#form').serialize()
                var queryFormObj = Qs.parse(queryForm)
                console.log(queryFormObj)
                table.reload('t',{
                    where:queryFormObj
                })
            }
        })
        var layer = layui.layer
        table.on('tool(table)',function(obj){
            //当点击了删除按钮时触发的事件
            //当点击了删除按钮时触发的事件
            if(obj.event == 'delete'){
                //获取本⾏数据的id
                var id = obj.data.id
                console.log(id)
                layer.confirm('正在删除当前数据是否继续?',{
                    icon:3,
                    title:'提示'
                },function(index){
                    //修改删除地址部分，传⼊id和pid两个参数
                    location.href = 'dept/delete?id='+id+'&pid=${pid}'
                    layer.close(index)
                })
            }
        })
        table.on('sort(table)',function(obj){
            console.log(obj)
            var queryForm = $('#form').serialize()
            var queryFormObj = Qs.parse(queryForm)
            table.reload('t',{
            initSort: obj,
                where:{
                    ...queryFormObj,
                    sortField:obj.field,
                    sortType:obj.type
            }
        })
        })
    })
</script>

</body>
</html>
