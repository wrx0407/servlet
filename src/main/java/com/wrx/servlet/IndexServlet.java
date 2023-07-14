package com.wrx.servlet;

import com.wrx.core.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "index", value = "/index.check")
public class IndexServlet extends HttpServlet {
    /**
     * 跳转至首页
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        new ModelAndView(request, response)
                .JSP("index");
    }
}
