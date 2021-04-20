package com.DuZongsheng.controller;

import com.DuZongsheng.dao.UserDao;
import com.DuZongsheng.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

@WebServlet(name = "UpdateUserServlet",value = "/updateUser")
public class UpdateUserServlet extends HttpServlet {
    Connection con =null;
    @Override
    public void init() throws ServletException {
        con = (Connection) getServletContext().getAttribute("con");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String email=request.getParameter("email");
        Date birthday= Date.valueOf(request.getParameter("birthday"));
        String gender=request.getParameter("gender");

        User user=new User(id,username,password,email,gender,birthday);
        UserDao userDao=new UserDao();

        try {
            userDao.updateUser(con,user);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        HttpSession session=request.getSession();
        session.setAttribute("user",user);
        session.setMaxInactiveInterval(30);
        request.getRequestDispatcher("WEB-INF/views/userInfo.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/views/updateUser.jsp").forward(request,response);
    }
}
