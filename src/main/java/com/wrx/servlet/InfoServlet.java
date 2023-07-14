package com.wrx.servlet;

import com.wrx.AppFactory;
import com.wrx.core.ModelAndView;
import com.wrx.service.IUserService;
import com.wrx.vo.ResetPwdVO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "info", value = "/info.check")
public class InfoServlet extends HttpServlet {

    /**
     * 跳转至个人信息页
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        new ModelAndView(request, response)
                .JSP("info");
    }

    /**
     * 修改密码
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ModelAndView mv = new ModelAndView(request, response);
        IUserService userService = AppFactory.getUserService();

        ResetPwdVO resetPwdVO = mv.getObject(ResetPwdVO.class);

        int resetPwd = userService.resetPwd(resetPwdVO);
        if (resetPwd == -1) {
            mv.setModel("errMsg", "旧密码错误");
        } else if (resetPwd == 1) {
            mv.setModel("msg", "修改成功");
        }
        mv.JSP("info");

    }
}
