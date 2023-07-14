<%--  2023/6/23 - 19:28  --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../common/top.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
    .update {
        display: flex;
        justify-content: center;
        align-items: center;
    }

    input {
        padding-left: 5px;
    }
</style>
<body>
<%--导入菜单--%>
<jsp:include page="../../common/header.jsp">
    <jsp:param name="path" value="/user/list.check"/>
</jsp:include>
<div class="update">
    <div>
        <form id="update" action="${pageContext.request.contextPath}/user/update.check" method="post">
            <table>
                <tr>
                    <th style="width: 300px;" colspan="2">${user==null?"添加用户":"修改用户"}</th>
                    <input type="hidden" name="id" value="${user.id}"/>
                </tr>
                <tr>
                    <td>用户名</td>
                    <td><input type="text" name="username" placeholder="请输入用户名" value="${user.username}"></td>
                </tr>
                <c:if test="${empty user}">
                    <tr>
                        <td>密码</td>
                        <td><input type="password" name="password" placeholder="请输入密码"></td>
                    </tr>
                </c:if>
                <tr>
                    <td>手机号</td>
                    <td><input type="text" name="phone" placeholder="请输入手机号" value="${user.phone}"></td>
                </tr>
                <tr>
                    <td>年龄</td>
                    <td><input type="text" name="age" placeholder="请输入年龄" value="${user.age}"></td>
                </tr>
                <tr>
                    <td>性别</td>
                    <td><select name="sex" style="width: 165px;">
                        <option value=""></option>
                        <option value="0" ${user.sex=='0'?'selected':''}>未知</option>
                        <option value="1" ${user.sex=='1'?'selected':''}>男</option>
                        <option value="2" ${user.sex=='2'?'selected':''}>女</option>
                    </select></td>
                </tr>
            </table>
        </form>
        <button class="info" style="margin: 10px 110px 0;" onclick="document.getElementById('update').submit()">
            ${user==null?"添加":"修改"}
        </button>
    </div>
</div>
</body>

