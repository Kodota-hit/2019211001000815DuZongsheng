package com.DuZongsheng.week5;

import com.DuZongsheng.dao.UserDao;
import com.DuZongsheng.model.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "LoginServlet",value = "/login")
public class LoginServlet extends HttpServlet {
    Connection con =null;
    @Override
    public void init() throws ServletException {
/*        ServletContext context = getServletConfig().getServletContext();
        String driver = context.getInitParameter("driver");
        String url = context.getInitParameter("url");
        String username = context.getInitParameter("username");
        String password = context.getInitParameter("password");

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url,username,password);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }*/

        con = (Connection) getServletContext().getAttribute("con");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        PrintWriter out=response.getWriter();


        UserDao userDao=new UserDao();
        try {
            User user= userDao.findByUsernamePassword(con,username,password);
            if(user!=null) {
                String rememberMe=request.getParameter("rememberMe");
                if(rememberMe!=null && rememberMe.equals("1")){
                    Cookie usernameCookies=new Cookie("cUsername",user.getUsername());
                    Cookie passwordCookies=new Cookie("cPassword",user.getPassword());
                    Cookie rememberMeCookies=new Cookie("cRememberMe",rememberMe);

                    usernameCookies.setMaxAge(5);
                    passwordCookies.setMaxAge(5);
                    rememberMeCookies.setMaxAge(5);

                    response.addCookie(usernameCookies);
                    response.addCookie(passwordCookies);
                    response.addCookie(rememberMeCookies);
                }

                HttpSession session=request.getSession();
                System.out.println("session id-->"+session.getId());
                session.setMaxInactiveInterval(30);
                session.setAttribute("user",user);

                //request.setAttribute("user",user);
                request.getRequestDispatcher("WEB-INF/views/userInfo.jsp").forward(request,response);
            }
            else {
                request.setAttribute("message","Username or Password Error!!!");
                request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request,response);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        /*try {
            String sql="select * from usertable where username=? and password=?";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,password);
            ResultSet rs=ps.executeQuery();
            if(rs.next()) {
                //out.println("Login Success!!!");
                //out.println("Welcome, "+username);
                request.setAttribute("id",rs.getInt("id"));
                request.setAttribute("username",rs.getString("username"));
                request.setAttribute("password",rs.getString("password"));
                request.setAttribute("email",rs.getString("email"));
                request.setAttribute("gender",rs.getString("gender"));
                request.setAttribute("birthday",rs.getString("birthday"));
                request.getRequestDispatcher("userInfo.jsp").forward(request,response);
            }
            else {
                //out.println("Username or Password Error!!!");
                request.setAttribute("message","Username or Password Error!!!");
                request.getRequestDispatcher("login.jsp").forward(request,response);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }*/

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request,response);
    }
}
