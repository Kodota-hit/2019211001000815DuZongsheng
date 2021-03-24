package com.DuZongsheng.week3;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "RegisterServlet",urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    Connection con =null;
    @Override
    public void init() throws ServletException {
        ServletContext context = getServletConfig().getServletContext();
        String driver = context.getInitParameter("driver");
        String url = context.getInitParameter("url");
        String username = context.getInitParameter("username");
        String password = context.getInitParameter("password");

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url,username,password);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");
        String gender=request.getParameter("gender");
        String password=request.getParameter("password");
        String email=request.getParameter("email");
        String birthday=request.getParameter("birthday");

        PrintWriter writer = response.getWriter();
/*        writer.println("<br> username : "+username);
        writer.println("<br> password : "+password);
        writer.println("<br> email : "+email);
        writer.println("<br> gender : "+gender);
        writer.println("<br> birthday : "+birthday);
        writer.close();*/


        try {
            String sql="INSERT INTO usertable VALUES (?,?,?,?,?,?);";

            PreparedStatement pstmt =con.prepareStatement(sql);
            pstmt.setInt(1,20190818);  //id should be generated automaticly, but I don't know how
            pstmt.setString(2,username);
            pstmt.setString(3,password);
            pstmt.setString(4,email);
            pstmt.setString(5,gender);
            pstmt.setDate(6, Date.valueOf(birthday));

            int rs = pstmt.executeUpdate();
            if(rs==1) System.out.println("OK");
            else System.out.println("ERROR");

            sql="SELECT * FROM userdb.usertable";
            pstmt=con.prepareStatement(sql);
            ResultSet results=pstmt.executeQuery();
            int col=results.getMetaData().getColumnCount();
            while (results.next()) {

                String ID=results.getString(1);
                username=results.getString(2);
                password=results.getString(3);
                email=results.getString(4);
                gender=results.getString(5);
                birthday=results.getString(6);

                writer.print("<table border=\"1\">\n" +
                        "  <tr>\n" +
                        "    <th>ID</th>\n" +
                        "    <th>UserName</th>\n" +
                        "    <th>Password</th>\n" +
                        "    <th>Email</th>\n" +
                        "    <th>Gender</th>\n" +
                        "    <th>Birthday</th>\n" +
                        "    \n" +
                        "  </tr>\n" +
                        "  <tr>\n" +
                        "    <td>"+ID+"</td>\n" +
                        "    <td>"+username+"</td>\n" +
                        "    <td>"+password+"</td>\n" +
                        "    <td>"+email+"</td>\n" +
                        "    <td>"+gender+"</td>\n" +
                        "    <td>"+birthday+"</td>\n" +
                        "  </tr>\n" +
                        "</table>");
            }

            writer.close();
            results.close();
            pstmt.close();
            if(con!=null) con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
