package com.DuZongsheng.filter;

import com.sun.xml.internal.ws.resources.HttpserverMessages;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/admin/*")
public class AdminAuthenticationFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest= (HttpServletRequest) req;
        HttpServletResponse httpResponse= (HttpServletResponse) resp;
        HttpSession session=httpRequest.getSession(false);

        boolean isLoggedIn=(session!=null && session.getAttribute("user")!=null);
        String loginURI=httpRequest.getContextPath()+"/login";//need fix!!!
        boolean isLoginRequest=httpRequest.getRequestURI().equals(loginURI);
        boolean isLoginPage=httpRequest.getRequestURI().endsWith("login");

        if(isLoggedIn && (isLoginRequest || isLoginPage)) {
            //System.out.println("111111111111");
            req.getRequestDispatcher("/admin/home").forward(req,resp);
        }
        else if(isLoggedIn || isLoginRequest){
            //System.out.println("222222222222");
            chain.doFilter(req, resp);
        }
        else {//need fix !!!!
            //System.out.println("33333333!@");
            httpResponse.sendRedirect(httpRequest.getContextPath()+"/login");
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }
    public void destroy() {
    }

}
