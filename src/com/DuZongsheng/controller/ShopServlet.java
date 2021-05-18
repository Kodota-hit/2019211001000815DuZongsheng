package com.DuZongsheng.controller;

import com.DuZongsheng.dao.ProductDao;
import com.DuZongsheng.model.Category;
import com.DuZongsheng.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ShopServlet",value = "/shop")
public class ShopServlet extends HttpServlet {
    Connection con=null;
    @Override
    public void init() throws ServletException {
        con = (Connection) getServletContext().getAttribute("con");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Category category=new Category();
        List<Category> categoryList=category.findAllCategory(con);
        request.setAttribute("categoryList",categoryList);

        ProductDao productDao=new ProductDao();
        List<Product> productList=null;
        try {
            if(request.getParameter("categoryId")==null) {
                productList=productDao.findAll(con);
            }else {
                int categoryId=Integer.parseInt(request.getParameter("categoryId"));
                productList=productDao.findByCategoryId(categoryId,con);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        request.setAttribute("productList",productList);
        request.getRequestDispatcher("/WEB-INF/views/shop.jsp").forward(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
