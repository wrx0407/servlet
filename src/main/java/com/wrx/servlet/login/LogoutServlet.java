package com.wrx.servlet.login;

import com.wrx.core.ModelAndView;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "logout", value = "/logout.check")
public class LogoutServlet extends HttpServlet {
    /**
     * 退出登陆
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("loginUser");
        new ModelAndView(request, response)
                .redirect("/login.check");
    }
}
