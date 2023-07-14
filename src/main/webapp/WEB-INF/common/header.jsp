<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    .header {
        height: 50px;
        background-color: #324257;
        color: white;

        /*flex布局*/
        display: flex;
        /*主轴两端对齐*/
        justify-content: space-between;
        /*侧轴居中*/
        align-items: center;
        /*鼠标小手*/
        cursor: pointer;
    }

    .header-left {
        line-height: 50px;
        display: flex;
    }

    .header-right {
        padding-right: 20px;
    }

    .logo {
        width: 400px;
    }

    .logo img {
        /*基于文字中线对齐->图片垂直居中*/
        vertical-align: middle;
        margin: 0 10px;
    }

    .menu {
        width: 150px;
        height: 50px;
        text-align: center;
    }

    .menu:hover {
        background-color: #0e2944;
    }

    /*菜单激活*/
    .activate {
        height: 47px;
        color: #0085ff;
        border-bottom: 3px solid #0085ff;
    }
</style>
<div class="header">
    <div class="header-left">
        <div class="logo">
            <img src="${pageContext.request.contextPath}/img/logo.png" alt="logo"/>
            <span>Servlet + Jsp ( Demo )</span>
        </div>
        <div class="menu ${param.path=="/index.check"?"activate":""}"
             onclick="window.location.href= '${pageContext.request.contextPath}/index.check'">
            首页
        </div>
        <div class="menu ${param.path=="/user/list.check"?"activate":""}"
             onclick="window.location.href= '${pageContext.request.contextPath}/user/list.check'">
            用户管理
        </div>
        <div class="menu ${param.path=="/info.check"?"activate":""}"
             onclick="window.location.href= '${pageContext.request.contextPath}/info.check'">
            个人中心
        </div>
    </div>
    <div class="header-right">
        <button class="info" onclick="window.location.href= '${pageContext.request.contextPath}/logout.check'">
            退出登陆
        </button>
    </div>
</div>