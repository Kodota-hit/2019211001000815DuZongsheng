package com.DuZongsheng.week4;

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet(
        urlPatterns = {"/config"},
        initParams = {
                @WebInitParam(name = "driver",value = "com.mysql.cj.jdbc.Driver"),
                @WebInitParam(name = "url",value = "jdbc:mysql://localhost:3306/userdb"),
                @WebInitParam(name = "username",value = "root"),
                @WebInitParam(name = "password",value = "acid0000"),
                @WebInitParam(name="name",value = "DuZongsheng"),
                @WebInitParam(name="studentid",value = "2019211001000815")
        },loadOnStartup = 1
)

public class ConfigDemoServlet extends HttpServlet {
    Connection con = null;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String driver = getServletConfig().getInitParameter("driver");
        String url = getServletConfig().getInitParameter("url");
        String username = getServletConfig().getInitParameter("username");
        String password = getServletConfig().getInitParameter("password");
        String name = getServletConfig().getInitParameter("name");
        String studentid = getServletConfig().getInitParameter("studentid");



        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url,username,password);

            System.out.println("name: "+name);
            System.out.println("studentid: "+studentid);

            PrintWriter writer = response.getWriter();
            writer.println("<br> name : "+name);
            writer.println("<br> studentid : "+studentid);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
