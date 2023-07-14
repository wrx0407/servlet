package com.wrx.servlet.user;

import com.wrx.AppFactory;
import com.wrx.core.ModelAndView;
import com.wrx.service.IUserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "userDelete", value = "/user/delete.check")
public class UserDeleteServlet extends HttpServlet {
    /**
     * 删除用户
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ModelAndView mv = new ModelAndView(request, response);
        Integer id = mv.getParam("id", Integer.class);

        IUserService userService = AppFactory.getUserService();
        userService.deleteById(id);

        mv.redirect("/user/list.check");
    }
}
