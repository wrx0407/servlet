<%--  2023/6/22 - 20:29  --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--导入头部--%>
<%@include file="../common/top.jsp" %>
<style>
    .home {
        text-align: center;
    }
    .home h1{
        margin: 20px 0;
    }
    .home p{
        margin: 5px 0;
    }
</style>
<body>
<%--导入菜单--%>
<jsp:include page="../common/header.jsp">
    <jsp:param name="path" value="/index.check"/>
</jsp:include>
<div class="home">
    <h1>C3P0 + JDBC + Servlet + JSP + Logback</h1>
    <p>对 JDBC 和 Servlet 进行了简单的封装，简化了的持久层和控制器的开发</p>
    <p>提取了一些公共样式；利用 JSP 的动态导入实现菜单切换</p>
    <p>100 万条测试数据，条件查询大概响应时间为 0.5 秒</p>
    <br>
    <p>缺点：数据校验没怎么做</p>
</div>
</body>

