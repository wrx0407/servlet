package com.wrx.servlet.user;

import com.wrx.AppFactory;
import com.wrx.core.ModelAndView;
import com.wrx.entity.User;
import com.wrx.service.IUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "userUpdate", value = "/user/update.check")
public class UserUpdateServlet extends HttpServlet {
    /**
     * 跳转至新增或修改页面，如果是修改需要数据回显
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ModelAndView mv = new ModelAndView(request, response);
        final Integer id = mv.getParam("id", Integer.class);
        if (id == -1) {
            /*
             * 新增用户
             */
            mv.JSP("user/update");
        } else {
            /*
             * 修改用户
             */
            IUserService userService = AppFactory.getUserService();
            User userById = userService.getUserById(id);
            mv.setModel("user", userById)
                    .JSP("user/update");
        }
    }

    /**
     * 处理新增或修改请求
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ModelAndView mv = new ModelAndView(request, response);
        IUserService userService = AppFactory.getUserService();

        final User user = mv.getObject(User.class);
        if (user.getId() == null) {
            /*
             * 新增用户
             */
            final User loginUser = (User) request.getSession().getAttribute("loginUser");
            user.setCreateBy(loginUser.getId());
            userService.save(user);
        } else {
            /*
             * 修改用户
             */
            userService.updateById(user);
        }
        mv.redirect("/user/list.check");
    }
}
