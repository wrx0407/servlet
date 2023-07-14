package com.wrx.servlet.user;

import com.wrx.AppFactory;
import com.wrx.core.ModelAndView;
import com.wrx.core.Page;
import com.wrx.entity.User;
import com.wrx.service.IUserService;
import com.wrx.vo.QueryParams;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "userList", value = "/user/list.check")
public class UserListServlet extends HttpServlet {
    /**
     * 分页条件查询用户
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ModelAndView mv = new ModelAndView(request, response);
        IUserService userService = AppFactory.getUserService();

        QueryParams queryParams = mv.getObject(QueryParams.class);
        Page<User> userPage = userService.getUserList(
                mv.getParam("pageNum", Integer.class),
                mv.getParam("pageSize", Integer.class),
                queryParams
        );

        mv.setModel("queryParams", queryParams)
                .setModel("userPage", userPage)
                .JSP("user/list");
    }

    /**
     * 页面条件查询表单
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
