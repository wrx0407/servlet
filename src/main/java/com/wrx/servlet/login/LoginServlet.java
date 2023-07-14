package com.wrx.servlet.login;

import com.wrx.AppFactory;
import com.wrx.core.ModelAndView;
import com.wrx.entity.User;
import com.wrx.service.IUserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "login", value = "/login.check")
public class LoginServlet extends HttpServlet {
    /**
     * 跳转至登陆页面
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        new ModelAndView(request, response)
                .JSP("login");
    }

    /**
     * 登陆请求
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ModelAndView mv = new ModelAndView(request, response);
        User user = mv.getObject(User.class);

        IUserService userService = AppFactory.getUserService();
        User login = userService.login(user);

        if (login != null) {
            request.getSession().setAttribute("loginUser", login);
            mv.redirect("/index.check");
        } else {
            mv.setModel("errMsg", "用户名或密码错误")
                    .JSP("login.check");
        }
    }
}
