package com.wrx.filter;

import com.wrx.core.ModelAndView;
import com.wrx.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "LoginFilter", urlPatterns = "*.check")
public class LoginFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        ModelAndView mv = new ModelAndView(httpRequest, httpResponse);

        final String uri = httpRequest.getRequestURI();
        if (uri.endsWith("login.check")) {
            User loginUser = (User) httpRequest.getSession().getAttribute("loginUser");
            if (loginUser != null) {
                mv.redirect("/index.check");
            } else {
                chain.doFilter(request, response);
            }
            return;
        }
        User loginUser = (User) httpRequest.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            mv.redirect("/login.check");
            return;
        }
        chain.doFilter(request, response);
    }
}
