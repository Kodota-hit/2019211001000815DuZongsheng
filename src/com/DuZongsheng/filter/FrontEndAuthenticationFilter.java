package com.DuZongsheng.filter;

import com.sun.deploy.net.HttpRequest;

import javax.print.attribute.standard.RequestingUserName;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.PrivateKey;

@WebFilter("/*")
public class FrontEndAuthenticationFilter implements Filter {
    private HttpServletRequest httpRequest;
    private static final String[] loginRequiredURLs={"/updateUser","/logout","/myCart"};

    private boolean isLoginRequired(){
        String requestURL=httpRequest.getRequestURL().toString();

        for(String loginRequiredURL:loginRequiredURLs){
            if(requestURL.contains(loginRequiredURL)) return true;
        }
        return false;
    }


    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        httpRequest= (HttpServletRequest) req;
        String path=httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
        if(path.startsWith("/admin/")) {
            chain.doFilter(req, resp);
            return;
        }
        HttpSession session=httpRequest.getSession(false);
        boolean isLoggedIn=(session!=null && session.getAttribute("user")!=null);
        String loginURI=httpRequest.getContextPath()+"/login";
        boolean isLoginRequest=httpRequest.getRequestURI().equals(loginURI);
        boolean isLoginPage=httpRequest.getRequestURI().endsWith("login");

        if(isLoggedIn && (isLoginRequest || isLoginPage)) {
            httpRequest.getRequestDispatcher("/").forward(req,resp);
        }
        else if(!isLoggedIn && isLoginRequired()){
            httpRequest.getRequestDispatcher("/login").forward(req,resp);
        }
        else {
            chain.doFilter(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }
    public void destroy() {
    }
}
