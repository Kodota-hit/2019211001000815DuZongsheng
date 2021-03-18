package com.DuZongsheng.week3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet(name = "RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");
        String gender=request.getParameter("gender");
        String password=request.getParameter("password");
        String email=request.getParameter("email");
        String birthday=request.getParameter("birthday");

        PrintWriter writer = response.getWriter();
        writer.println("<br> username : "+username);
        writer.println("<br> password : "+password);
        writer.println("<br> email : "+email);
        writer.println("<br> gender : "+gender);
        writer.println("<br> birthday : "+birthday);
        writer.close();


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
