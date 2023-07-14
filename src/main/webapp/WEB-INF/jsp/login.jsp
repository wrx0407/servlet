<%--  2023/6/20 - 18:04  --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--导入头部--%>
<%@include file="../common/top.jsp" %>
<style>
    html {
        background-image: url("${pageContext.request.contextPath}/img/loginbg.png");
    }

    /*表单*/
    .login {
        height: 300px;
        width: 400px;
        background-color: #ffffff;
        /*绝对定位-相对于浏览器*/
        position: absolute;
        /*偏移*/
        left: 50%;
        top: 50%;
        /*外边距*/
        margin-top: -150px;
        margin-left: -200px;
        /*圆角*/
        border-radius: 5px;
        /*文本居中*/
        text-align: center;
    }

    /*表单标题*/
    .login .title {
        color: #717171;
        margin-top: 15px;
    }

    /*表单输入框*/
    .login input {
        /*转为块元素*/
        display: block;
        width: 350px;
        height: 40px;
        margin-top: 30px;
        margin-left: 25px;
    }

    /*用户名输入框*/
    .login .username {
        width: 315px;
        /*背景图片、背景图片位置*/
        background: url("${pageContext.request.contextPath}/svg/username.svg") no-repeat 5px;
        /*内边距*/
        padding-left: 35px;
    }

    /*密码输入框*/
    .login .password {
        width: 315px;

        background: url("${pageContext.request.contextPath}/svg/password.svg") no-repeat 5px;
        padding-left: 35px;
    }

    /*登陆按钮*/
    .login button {
        width: 350px;
        height: 40px;
    }

    .msg {
        height: 30px;
        text-align: center;
        color: red;
    }

</style>
<body>
<div>
    <form class="login" action="${pageContext.request.contextPath}/login.check" onsubmit="return  checkForm()" method="post">
        <h2 class="title">Servlet + Jsp</h2>
        <input type="text" id="username" class="username" name="username" placeholder="用户名" value="admin">
        <input type="password" id="password" class="password" name="password" placeholder="密码" value="1234">
        <p class="msg">${errMsg}</p>
        <button>登 陆</button>
    </form>
</div>
</body>

<script>
    window.history.replaceState(null, null, window.location.href);

    function checkForm() {
        var username = document.getElementById('username').value;
        var password = document.getElementById('password').value;
        if (username.length < 1) {
            alert('用户名不能为空')
            return false
        } else if (password.length < 1) {
            alert('密码不能为空')
            return false
        } else {
            return true
        }
    }
</script>

