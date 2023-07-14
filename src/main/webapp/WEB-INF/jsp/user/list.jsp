<jsp:useBean id="queryParams" scope="request" type="com.wrx.vo.QueryParams"/>
<jsp:useBean id="userPage" scope="request" type="com.wrx.core.Page<com.wrx.entity.User>"/>
<%--  2023/6/21 - 15:14  --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--导入头部--%>
<%@include file="../../common/top.jsp" %>
<style>
    input, select {
        text-align: center;
    }

    .users {
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
    }
</style>
<body>
<%--导入菜单--%>
<jsp:include page="../../common/header.jsp">
    <jsp:param name="path" value="/user/list.check"/>
</jsp:include>
<div class="users">
    <!--  多条件搜索  -->
    <div style="margin-top: 20px;">
        <form id="getUsers" action="${pageContext.request.contextPath}/user/list.check" method="post"
              style="display:inline;">
            <input type="hidden" name="pageNum" value="${userPage.pageNum}"/>
            <input type="hidden" name="pageSize" value="${userPage.pageSize}"/>
            <label>
                用户名 <input type="text" name="username" placeholder="请输入用户名" value="${queryParams.username}">
            </label>
            <label>
                性别
                <select name="sex" style="width: 70px;">
                    <option value=""></option>
                    <option value="0" ${queryParams.sex=='0'?'selected':''}>未知</option>
                    <option value="1" ${queryParams.sex=='1'?'selected':''}>男</option>
                    <option value="2" ${queryParams.sex=='2'?'selected':''}>女</option>
                </select>
            </label>
            <label>
                年龄 <input type="text" name="age" placeholder="请输入年龄" value="${queryParams.age}">
            </label>
            <label>
                手机号 <input type="text" name="phone" placeholder="请输入手机号" value="${queryParams.phone}">
            </label>
            <label>
                创建日期
                <input type="date" name="beginTime"
                       value="<fmt:formatDate value="${queryParams.beginTime}" pattern="yyyy-MM-dd"/>"/>
            </label>
            <label>
                至
                <input type="date" name="endTime"
                       value="<fmt:formatDate value="${queryParams.endTime}" pattern="yyyy-MM-dd"/>"/>
            </label>
        </form>
        <button onclick="getUsers()">搜索</button>
        <button class="success"
                onclick="window.location.href='${pageContext.request.contextPath}/user/update.check?id=-1'">
            添加用户
        </button>
    </div>
    <!--  表格  -->
    <c:if test="${empty userPage.result}">
        <table>
            <tr>
                <th style="width: 80px;">用户编号</th>
                <th style="width: 200px;">用户名</th>
                <th style="width: 80px;">性别</th>
                <th style="width: 80px;">年龄</th>
                <th style="width: 150px;">手机号</th>
                <th style="width: 300px;">创建日期</th>
                <th style="width: 150px;">操作</th>
            </tr>
            <tr>
                <td colspan="7">暂无数据</td>
            </tr>
        </table>
    </c:if>
    <c:if test="${!empty userPage.result}">
        <table>
            <tr>
                <th style="width: 80px;">用户编号</th>
                <th style="width: 200px;">用户名</th>
                <th style="width: 80px;">性别</th>
                <th style="width: 80px;">年龄</th>
                <th style="width: 150px;">手机号</th>
                <th style="width: 300px;">创建日期</th>
                <th style="width: 150px;">操作</th>
            </tr>
            <c:forEach items="${userPage.result}" var="user">
                <tr>
                    <!--  td：数据单元格  -->
                    <td>${user.id}</td>
                    <td>${user.username}</td>
                    <td>${user.sex=="0"?"未知":(user.sex=="1"?"男":"女")}</td>
                    <td>${user.age}</td>
                    <td>${user.phone}</td>
                    <td><fmt:formatDate value="${user.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td>
                        <button onclick="window.location.href='${pageContext.request.contextPath}/user/update.check?id=${user.id}'">
                            修改
                        </button>
                        <button class="danger" onclick="deleteUser(${user.id})">删除</button>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <!-- 分页 -->
        <div class="page">
            <span>共${userPage.total}条</span>
            <select id="newPageSize" onchange="newPageSize()" style="width: 70px;">
                <option value="5" ${userPage.pageSize==5?'selected':''}>5条/页</option>
                <option value="10" ${userPage.pageSize==10?'selected':''}>10条/页</option>
                <option value="20" ${userPage.pageSize==20?'selected':''}>20条/页</option>
                <option value="40" ${userPage.pageSize==40?'selected':''}>40条/页</option>
            </select>
            <button onclick="setPageNum(1)">首页</button>
            <button onclick="setPageNum(${userPage.pageNum}-1)">上一页</button>
            <button onclick="setPageNum(${userPage.pageNum}+1)">下一页</button>
            <button onclick="setPageNum(${userPage.pages})">尾页</button>
            <span>前往<input id="newPageNum" onchange="newPageNum()" type="text"
                             value="${userPage.pageNum}">/ ${userPage.pages}页</span>
        </div>
    </c:if>
</div>
</body>
<script>
    //禁用“确认重新提交表单”
    window.history.replaceState(null, null, window.location.href);

    // 回车事件
    document.getElementById('newPageNum').onkeyup = function (e) {
        if (e.keyCode === 13) {
            newPageNum()
        }
    }

    // 每页显示条数
    function newPageSize() {
        var newPageSize = document.getElementById('newPageSize');
        var pageSize = document.getElementsByName("pageSize")[0];
        pageSize.value = newPageSize.value
        getUsers()
    }

    // 分页输入框
    function newPageNum() {
        var pageNum = document.getElementsByName("pageNum")[0];
        var newPageNum = document.getElementById('newPageNum');
        if (!/^[0-9]*$/.test(newPageNum.value)) {
            newPageNum.value = pageNum.value
            alert('请输入数字')
            return
        }
        pageNum.value = newPageNum.value
        getUsers()
    }

    // 条件查询
    function getUsers() {
        var phone = document.getElementsByName("phone")[0];
        var age = document.getElementsByName("age")[0];
        if (!/^[0-9]{0,11}$/.test(phone.value)) {
            phone.value = ''
            alert('手机号请输入不超过11位的数字')
            return
        }
        if (!/^[0-9]*$/.test(age.value)) {
            age.value = ''
            alert('年龄必须为数字')
            return
        } else if (/^[0-9]+$/.test(age.value) && (age.value < 18 || age.value > 65)) {
            alert('年龄需在 18 - 65 之间')
            return
        }
        document.getElementById('getUsers').submit()
    }

    // 页码更改
    function setPageNum(num) {
        document.getElementsByName("pageNum")[0].value = num
        getUsers()
    }

    function deleteUser(id) {
        var isDel = confirm("确定要删除编号为 " + id + " 的用户吗？");
        if (isDel) {
            window.location.href = '${pageContext.request.contextPath}/user/delete.check?id=' + id
        }
    }
</script>
