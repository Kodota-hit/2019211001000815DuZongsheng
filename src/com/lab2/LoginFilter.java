package com.lab2;

import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
/* todo 1: map this filter for  - /lab2/welcome.jsp */
@WebFilter(filterName = "LoginFilter",urlPatterns = "/lab2/welcome.jsp")
public class LoginFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //todo 4: print i am in LoginFilter--doFilter()-- request before chain
        System.out.println("I am in LoginFilter--doFilter--request before chain");

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session=request.getSession(false);
        //todo 6: if session not new
        if (session!=null && session.getAttribute("user")!=null){
            //todo 7: forward to lab2/welcome.jsp
            request.getRequestDispatcher("welcome.jsp").forward(request,response);
        }else {
            //todo 8: else redirect to login.jsp
            response.sendRedirect("login.jsp");
        }
        chain.doFilter(request, response);
        System.out.println("I am in LoginFilter--doFilter--request after chain");
    }

    public void init(FilterConfig config) throws ServletException {
        /* todo 2: print i am in LoginFilter--init() */
        System.out.println(" I am in LoginFilter--init()");
    }
    public void destroy() {
        //todo 3: print i am in LoginFilter--destroy()
        System.out.println(" I am in LoginFilter--destroy()");
    }
}
