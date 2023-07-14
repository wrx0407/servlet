<jsp:useBean id="loginUser" scope="session" type="com.wrx.entity.User"/>
<%--  2023/6/22 - 20:29  --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--导入头部--%>
<%@include file="../common/top.jsp" %>
<style>

    .user-info {
        display: flex;
        justify-content: center;
        align-items: center;
    }

    input {
        padding-left: 5px;
    }

    .msg {
        height: 30px;
        text-align: center;
    }
</style>
<body>
<%--导入菜单--%>
<jsp:include page="../common/header.jsp">
    <jsp:param name="path" value="/info.check"/>
</jsp:include>
<div class="user-info">
    <div style="height: 300px;margin-right: 50px;">
        <table>
            <tr>
                <th style="width: 300px;" colspan="2">基本信息</th>
            </tr>
            <tr>
                <td>用户名</td>
                <td>${loginUser.username}</td>
            </tr>
            <tr>
                <td>性别</td>
                <td>${loginUser.sex=="0"?"未知":(loginUser.sex=="1"?"男":"女")}</td>
            </tr>
            <tr>
                <td>年龄</td>
                <td>${loginUser.age}</td>
            </tr>
            <tr>
                <td>手机号</td>
                <td>${loginUser.phone}</td>
            </tr>
            <tr>
                <td>创建时间</td>
                <td><fmt:formatDate value="${loginUser.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            </tr>
        </table>
    </div>
    <div style="height: 300px;">
        <form id="resetPwd" action="${pageContext.request.contextPath}/info.check" method="post">
            <table>
                <tr>
                    <th style="width: 300px;" colspan="2">修改密码</th>
                    <input type="hidden" name="id" value="${loginUser.id}"/>
                </tr>
                <tr>
                    <td>旧密码</td>
                    <td><input name="password" placeholder="请输入旧密码"></td>
                </tr>
                <tr>
                    <td>新密码</td>
                    <td><input type="password" name="newPwd" placeholder="请输入新密码"></td>
                </tr>
                <tr>
                    <td>确认密码</td>
                    <td><input type="password" id="newPwd" placeholder="请再次输入密码"></td>
                </tr>
            </table>
        </form>
        <c:if test="${!empty msg}">
            <p class="msg" style="color: #0085ff">${msg}</p>
        </c:if>
        <c:if test="${!empty errMsg}">
            <p class="msg" style="color: red">${errMsg}</p>
        </c:if>
        <c:if test="${empty errMsg && empty msg}">
            <p class="msg"></p>
        </c:if>
        <button class="info" style="margin: 0 110px;" onclick="newPwd()">
            修改密码
        </button>
    </div>
</div>
<script>
    window.history.replaceState(null, null, window.location.href);

    function newPwd() {
        var password = document.getElementsByName("password")[0];
        var newPwd = document.getElementsByName("newPwd")[0];
        var newPwd_2 = document.getElementById("newPwd");

        if (password.value.length < 1) {
            alert("旧密码不为空")
            return
        }
        if (newPwd.value.length < 1) {
            alert("新密码不为空")
            return;
        }
        if (newPwd_2.value.length < 1) {
            alert("确认密码不为空")
            return;
        }
        if (newPwd.value != newPwd_2.value) {
            alert("确认密码与新密码不相等")
            return;
        }
        document.getElementById('resetPwd').submit()
    }
</script>
</body>

